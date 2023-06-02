package com.genspark.nutritionapp.service;

import java.util.List;
import com.genspark.nutritionapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends UserDetailsService {

    List<User> findAll();

    User findByUserName(String userName);

    User save(User user);


}
