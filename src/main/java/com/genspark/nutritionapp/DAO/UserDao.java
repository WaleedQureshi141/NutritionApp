package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.User;

import java.util.List;


public interface UserDao {

    User findByUsername(String username);

    User save(User user);

    List<User> getAllUsers();

    void deleteByUserName(String userName);
}
