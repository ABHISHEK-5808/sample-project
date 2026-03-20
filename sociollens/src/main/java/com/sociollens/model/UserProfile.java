package com.sociollens.model;

import java.util.List;

/**
 * Represents a user profile in SocioLens.
 */
public class UserProfile {
    private String id;
    private String name;
    private int age;
    private List<String> interests;
    private String location; // e.g., urban, rural
    private String activeTime; // e.g., morning, afternoon, evening, night
    private String mood; // e.g., calm, energetic

    // Constructors
    public UserProfile() {}

    public UserProfile(String id, String name, int age, List<String> interests, String location, String activeTime, String mood) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.interests = interests;
        this.location = location;
        this.activeTime = activeTime;
        this.mood = mood;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getActiveTime() { return activeTime; }
    public void setActiveTime(String activeTime) { this.activeTime = activeTime; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }
}
