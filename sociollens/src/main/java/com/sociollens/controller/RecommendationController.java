package com.sociollens.controller;

import com.sociollens.model.Image;
import com.sociollens.model.RecommendationResult;
import com.sociollens.model.UserProfile;
import com.sociollens.service.HierarchicalAttentionService;
import com.sociollens.service.ImageDataService;
import com.sociollens.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for serving images and recommendations.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class RecommendationController {

    private final ImageDataService imageDataService;
    private final UserProfileService userProfileService;
    private final HierarchicalAttentionService attentionService;

    @Autowired
    public RecommendationController(ImageDataService imageDataService, 
                                    UserProfileService userProfileService, 
                                    HierarchicalAttentionService attentionService) {
        this.imageDataService = imageDataService;
        this.userProfileService = userProfileService;
        this.attentionService = attentionService;
    }

    @GetMapping("/images")
    public List<Image> getAllImages() {
        return imageDataService.getAllImages();
    }

    @PostMapping("/profile")
    public UserProfile saveProfile(@RequestBody UserProfile profile) {
        return userProfileService.saveProfile(profile);
    }

    @PostMapping("/recommend")
    public List<RecommendationResult> getRecommendations(@RequestBody UserProfile profile) {
        // If the profile does not have an ID, we save it (or update it)
        UserProfile savedProfile = userProfileService.saveProfile(profile);
        return attentionService.rankImages(savedProfile);
    }
    
    @GetMapping("/attention/{imageId}")
    public Map<String, Double> getAttentionForImage(@PathVariable String imageId, 
                                                    @RequestParam String userId) {
        UserProfile user = userProfileService.getProfile(userId);
        Image image = imageDataService.getImage(imageId);
        
        Map<String, Double> response = new HashMap<>();
        if (user != null && image != null) {
            response.put("level1", attentionService.computeTagAttention(user.getInterests(), image.getTags()));
            response.put("level2", attentionService.computeContextAttention(user, image));
        }
        return response;
    }
}
