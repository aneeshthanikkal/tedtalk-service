package com.io.tedtalk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.tedtalk.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
	Optional<UserProfile> findByUsername(String username);

	Boolean existsByUsername(String username);
}