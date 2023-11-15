package com.boot.demo.service;

import com.boot.demo.model.UserProfile;

public interface UserProfileService {
	
	public UserProfile getUserProfileByEmail(String email);

	public UserProfile updateUserProfile(UserProfile existingProfile);

}
