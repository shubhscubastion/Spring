package com.movieflix.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.auth.entities.User;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Integer>{

    
}