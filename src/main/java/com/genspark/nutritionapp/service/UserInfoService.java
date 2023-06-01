package com.genspark.nutritionapp.service;

import java.util.List;
import com.genspark.nutritionapp.entity.User;

public interface UserInfoService {

    List<User> findAll();

    User findByUsername(String theUsername);

    User save(User theUser);

    void deleteByUsername(String theUsername);

}
