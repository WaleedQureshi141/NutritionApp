package com.genspark.nutritionapp.controller;

import com.genspark.nutritionapp.entity.User;
import com.genspark.nutritionapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRestController {


    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = userInfoService.findByUserName(username);

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

    // POST /users/dev for adding a new developer
    @PostMapping("/dev")
    public User addAdmin(@RequestBody User user){

        User dbUser = userInfoService.saveDeveloper(user);

        return dbUser;
    }
    // POST /users/{username} for checking if the user exists (used for login)
    @PostMapping("/users/login")
    public User checkUser(@RequestBody User user){
        User temp = userInfoService.findByUserName(user.getUserName());
        if(temp.getUserName() == null){
            return new User();
        }
        if(temp.getUserName().equals(user.getUserName()) && passwordEncoder.matches(user.getPassword(), temp.getPassword())){
            return temp;
        } else {
            return new User(); //returns null user
        }
    }
    // DELETE /users/{username} for adding delete
    @DeleteMapping("/users/{username}")
    public List<User> deleteUser(@PathVariable String username){
        User temp = userInfoService.findByUserName(username);
        if(temp == null){
            throw new RuntimeException("Username not found: " + username);
        }

        List<User> rv = userInfoService.deleteByUserName(username);
        return rv;
    }
}
