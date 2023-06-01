package com.genspark.nutritionapp.controller;

import com.genspark.nutritionapp.entity.User;
import com.genspark.nutritionapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {


    private UserInfoService userInfoService;

    @Autowired
    public UserRestController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    // GET /users endpoint for getting all users
    @GetMapping("/users")
    public List<User> findAll(){
        return userInfoService.findAll();
    }

    //  GET users/{username} endpoint for getting single user info
    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username){
        User user = userInfoService.findByUsername(username);

        if(user == null){
            throw new RuntimeException("Username not found: " + username);
        }

        return user;
    }

    // POST /users for adding a new user
    @PostMapping("/users")
    public User addUser(@RequestBody User user){

        User dbUser = userInfoService.save(user);

        return dbUser;
    }

    // DELETE /users/{username} for adding delete
    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username){
        User temp = userInfoService.findByUsername(username);

        if(temp == null){
            throw new RuntimeException("Username not found: " + username);
        }

        userInfoService.deleteByUsername(username);
        return "Deleted username: " + username;
    }
}
