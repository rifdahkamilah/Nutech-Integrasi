package com.boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.demo.model.UserProfile;
import com.boot.demo.repository.UserProfileRepository;



@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	public UserProfile getUserProfileByEmail(String email) {
		return userProfileRepository.getUserProfileByEmail(email);
	}

	@Override
	public UserProfile updateUserProfile(UserProfile existingProfile) {
		return userProfileRepository.save(existingProfile);
	}
}