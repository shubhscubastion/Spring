package com.shubh.graphqlnew.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubh.graphqlnew.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    
}
