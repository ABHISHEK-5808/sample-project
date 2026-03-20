package com.sociollens.service;

import com.sociollens.model.Image;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service to manage Image data in-memory. Preloads 20+ images.
 */
@Service
public class ImageDataService {
    // In-memory HashMap storage as requested
    private final Map<String, Image> imageStore = new HashMap<>();

    @PostConstruct
    public void initData() {
        // Preload at least 20 sample images
        addImage("1", "Majestic Mountains", "nature", 0.9, "morning", 
            List.of("nature", "travel", "art"), List.of("rural", "calm", "outdoor"));
        addImage("2", "City Skyline", "travel", 0.85, "night", 
            List.of("travel", "art", "technology"), List.of("urban", "energetic", "outdoor"));
        addImage("3", "Classic Burger", "food", 0.95, "afternoon", 
            List.of("food", "science"), List.of("urban", "calm", "indoor"));
        addImage("4", "Cyberpunk Future", "technology", 0.75, "night", 
            List.of("technology", "art", "science"), List.of("urban", "energetic", "indoor"));
        addImage("5", "Morning Coffee", "food", 0.88, "morning", 
            List.of("food", "art"), List.of("urban", "calm", "indoor"));
        addImage("6", "Deep Forest", "nature", 0.92, "afternoon", 
            List.of("nature", "travel"), List.of("rural", "calm", "outdoor"));
        addImage("7", "Fashion Runway", "fashion", 0.70, "evening", 
            List.of("fashion", "art"), List.of("urban", "energetic", "indoor"));
        addImage("8", "Quantum Code", "science", 0.81, "night", 
            List.of("science", "technology"), List.of("urban", "calm", "indoor"));
        addImage("9", "Beach Volleyball", "sports", 0.89, "afternoon", 
            List.of("sports", "travel", "nature"), List.of("urban", "energetic", "outdoor"));
        addImage("10", "Abstract Painting", "art", 0.65, "evening", 
            List.of("art", "fashion"), List.of("urban", "calm", "indoor"));
        addImage("11", "Vintage Car", "travel", 0.82, "afternoon", 
            List.of("travel", "technology", "art"), List.of("urban", "energetic", "outdoor"));
        addImage("12", "Sushi Platter", "food", 0.91, "evening", 
            List.of("food", "art", "travel"), List.of("urban", "calm", "indoor"));
        addImage("13", "Tech Gadgets", "technology", 0.88, "morning", 
            List.of("technology", "fashion"), List.of("urban", "calm", "indoor"));
        addImage("14", "Marathon Runners", "sports", 0.94, "morning", 
            List.of("sports", "health"), List.of("urban", "energetic", "outdoor"));
        addImage("15", "Wildlife Safari", "nature", 0.97, "morning", 
            List.of("nature", "travel", "science"), List.of("rural", "energetic", "outdoor"));
        addImage("16", "Microscope View", "science", 0.78, "afternoon", 
            List.of("science", "technology"), List.of("urban", "calm", "indoor"));
        addImage("17", "Street Fashion", "fashion", 0.83, "afternoon", 
            List.of("fashion", "art", "travel"), List.of("urban", "energetic", "outdoor"));
        addImage("18", "Desert Dunes", "nature", 0.86, "evening", 
            List.of("nature", "travel"), List.of("rural", "calm", "outdoor"));
        addImage("19", "E-Sports Tournament", "technology", 0.93, "night", 
            List.of("technology", "sports"), List.of("urban", "energetic", "indoor"));
        addImage("20", "Farmer's Market", "food", 0.79, "morning", 
            List.of("food", "travel", "nature"), List.of("rural", "energetic", "outdoor"));
    }

    private void addImage(String id, String title, String category, double popularity, 
                          String uploadTime, List<String> tags, List<String> contextTags) {
        String url = "https://picsum.photos/seed/" + id + "/400/300";
        Image img = new Image(id, url, title, tags, category, popularity, uploadTime, contextTags);
        imageStore.put(id, img);
    }

    public List<Image> getAllImages() {
        return new ArrayList<>(imageStore.values());
    }

    public Image getImage(String id) {
        return imageStore.get(id);
    }
}
