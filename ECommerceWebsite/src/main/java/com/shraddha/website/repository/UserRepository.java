package com.shraddha.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shraddha.website.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByEmail(String email);
}
