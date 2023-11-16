package com.boot.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.demo.model.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
	
	public Optional<UserProfile> findByEmail(String email);

	public UserProfile getUserProfileByEmail(String email);

}
