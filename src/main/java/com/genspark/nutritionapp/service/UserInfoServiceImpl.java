package com.genspark.nutritionapp.service;

import com.genspark.nutritionapp.DAO.UserRepository;
import com.genspark.nutritionapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String theUsername) {
        Optional<User> result = userRepository.findById(theUsername);

        User user = null;

        if(result.isPresent()){
            user = result.get();
        } else {
            throw new RuntimeException("Did not find by username: " + theUsername);
        }

        return user;
    }

    @Override
    public User save(User theUser) {
        theUser.setActive(1);
        theUser.setRole("ROLE_USER");
        theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        return userRepository.save(theUser);
    }

    @Override
    public void deleteByUsername(String theUsername) {
        userRepository.deleteById(theUsername);
    }
}
