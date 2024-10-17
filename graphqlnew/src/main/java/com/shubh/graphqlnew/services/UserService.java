package com.shubh.graphqlnew.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shubh.graphqlnew.entities.User;
import com.shubh.graphqlnew.helper.ExceptionHelper;
import com.shubh.graphqlnew.repositories.UserRepo;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    // create User

    public User createUser(User user){
        return userRepo.save(user);
    }

    // get all users

    public List <User> getAllUser(){
        return userRepo.findAll();
    }


    // get single user

    public User getUser(int userId){
        User user = userRepo.findById(userId).orElseThrow(ExceptionHelper ::throwResourceNotFoundException);
        return user;
    }

    // delete user

    public boolean deleteUser(int userId){
        User user = userRepo.findById(userId).orElseThrow(ExceptionHelper :: throwResourceNotFoundException);
        userRepo.delete(user);
        return true;
    }
}
