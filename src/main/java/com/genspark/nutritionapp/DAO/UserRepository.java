package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, String> {

}
