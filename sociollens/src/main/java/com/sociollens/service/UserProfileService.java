package com.sociollens.service;

import com.sociollens.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Service to manage User Profiles in-memory.
 */
@Service
public class UserProfileService {
    // In-memory HashMap storage as requested
    private final Map<String, UserProfile> userProfileStore = new HashMap<>();

    public UserProfile saveProfile(UserProfile profile) {
        if (profile.getId() == null || profile.getId().isEmpty()) {
            profile.setId(UUID.randomUUID().toString());
        }
        userProfileStore.put(profile.getId(), profile);
        return profile;
    }

    public UserProfile getProfile(String id) {
        return userProfileStore.get(id);
    }
}
