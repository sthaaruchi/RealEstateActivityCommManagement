package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.ReUser;

public interface UserRepository extends JpaRepository<ReUser, Integer>{
	ReUser findByUsername(String username);
}
