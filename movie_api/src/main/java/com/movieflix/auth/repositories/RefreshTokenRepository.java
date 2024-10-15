package com.movieflix.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.auth.entities.RefreshToken;

/**
 * RefreshTokenRepository
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    
    
}