package com.sociollens.model;

import java.util.List;

/**
 * Represents an image metadata object.
 */
public class Image {
    private String id;
    private String url;
    private String title;
    private List<String> tags; // Selected from the 8 categories
    private String category; // Primary category
    private double popularity;
    private String uploadTime; // morning, afternoon, evening, night
    private List<String> contextTags; // e.g. ["urban", "calm", "indoor"]

    public Image() {}

    public Image(String id, String url, String title, List<String> tags, String category, double popularity, String uploadTime, List<String> contextTags) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.tags = tags;
        this.category = category;
        this.popularity = popularity;
        this.uploadTime = uploadTime;
        this.contextTags = contextTags;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPopularity() { return popularity; }
    public void setPopularity(double popularity) { this.popularity = popularity; }

    public String getUploadTime() { return uploadTime; }
    public void setUploadTime(String uploadTime) { this.uploadTime = uploadTime; }

    public List<String> getContextTags() { return contextTags; }
    public void setContextTags(List<String> contextTags) { this.contextTags = contextTags; }
}
