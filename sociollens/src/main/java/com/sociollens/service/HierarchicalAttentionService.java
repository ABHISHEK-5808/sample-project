package com.sociollens.service;

import com.sociollens.model.Image;
import com.sociollens.model.RecommendationResult;
import com.sociollens.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that applies a Hierarchical Attention Model (HAM) to recommend images.
 */
@Service
public class HierarchicalAttentionService {

    private final ImageDataService imageDataService;

    // The 8 categories as defined in requirements
    private static final String[] CATEGORIES = {"travel", "food", "technology", "sports", "art", "nature", "fashion", "science"};

    @Autowired
    public HierarchicalAttentionService(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    /**
     * Level 1 Attention: Interest-to-Image-Tag attention.
     * Computes dot product between user interests and image tags, normalized by a proxy softmax function.
     */
    public double computeTagAttention(List<String> userInterests, List<String> imageTags) {
        if (userInterests == null || imageTags == null) return 0.0;
        
        // 1. & 2. Build binary vectors and compute dot product
        double dotProduct = 0;
        for (String category : CATEGORIES) {
            double u = userInterests.contains(category) ? 1.0 : 0.0;
            double i = imageTags.contains(category) ? 1.0 : 0.0;
            dotProduct += u * i;
        }

        // 3. & 4. Apply softmax normalization returning a score between 0 and 1
        // Using Sigmoid as a 2-class softmax equivalent for a single scalar value.
        // It smoothly maps the dot product range [0, 8] to [0.5, ~1.0].
        // To make a zero dot product map closely to 0, we can adjust it or use a simple exp proportion.
        double normalizedScore = Math.exp(dotProduct) / (Math.exp(dotProduct) + 1.0);
        
        // If dotProduct is 0, score is 0.5. To scale it to 0-1 based on [0, 8]:
        // Softmax across options in a recommendation is typically against all pool, 
        // but since this evaluates pairwise, we use this direct math.
        // Let's normalize explicitly for 0 to 1 based on actual vector range:
        // double finalNorm = (normalizedScore - 0.5) * 2;
        return (normalizedScore - 0.5) * 2.0; 
    }

    /**
     * Level 2 Attention: Context signals (time, location, mood).
     */
    public double computeContextAttention(UserProfile user, Image image) {
        // Compare activeTime with uploadTime
        double timeMatch = 0.0;
        if (user.getActiveTime() != null && user.getActiveTime().equalsIgnoreCase(image.getUploadTime())) {
            timeMatch = 1.0;
        }

        // Compare location type with contextTags
        double locationMatch = 0.0;
        if (user.getLocation() != null && image.getContextTags() != null) {
            if (image.getContextTags().contains(user.getLocation().toLowerCase())) {
                locationMatch = 1.0;
            }
        }

        // Compare mood with contextTags 
        double moodMatch = 0.0;
        if (user.getMood() != null && image.getContextTags() != null) {
            if (image.getContextTags().contains(user.getMood().toLowerCase())) {
                moodMatch = 1.0;
            }
        }

        // Return weighted average as specified
        return (timeMatch * 0.3) + (locationMatch * 0.3) + (moodMatch * 0.4);
    }

    /**
     * Ranks all images for a given user profile.
     */
    public List<RecommendationResult> rankImages(UserProfile user) {
        List<Image> allImages = imageDataService.getAllImages();
        List<RecommendationResult> results = new ArrayList<>();

        for (Image img : allImages) {
            double l1Attention = computeTagAttention(user.getInterests(), img.getTags());
            double l2Attention = computeContextAttention(user, img);
            
            // Final score = α × L1 + β × L2 (α+β=1)
            double finalScore = 0.6 * l1Attention + 0.4 * l2Attention;
            
            results.add(new RecommendationResult(img, finalScore, l1Attention, l2Attention));
        }

        // Sort descending by final score
        results.sort((a, b) -> Double.compare(b.getFinalScore(), a.getFinalScore()));
        
        return results;
    }
}
