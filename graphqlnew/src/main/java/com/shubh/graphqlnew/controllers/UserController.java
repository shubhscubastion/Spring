package com.shubh.graphqlnew.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.shubh.graphqlnew.entities.User;
import com.shubh.graphqlnew.services.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User API

    @MutationMapping()
    public User createUser(@Argument String name, @Argument String phone, @Argument String email,
            @Argument String password) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        return userService.createUser(user);
    }

    // get Users

    @QueryMapping(name = "getUsers")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @QueryMapping
    public User getUser(@Argument int userId) {
        return userService.getUser(userId);
    }

    @MutationMapping
    // @SchemaMapping(typeName="Mutation", field = "deleteUser")
    public boolean deleteUser(@Argument int userId) {
        return userService.deleteUser(userId);
    }

}
