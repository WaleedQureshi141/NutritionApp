package com.genspark.nutritionapp.service;

import com.genspark.nutritionapp.entity.Nutrition;
import com.genspark.nutritionapp.entity.User;

import java.util.List;

public interface NutritionInfoService
{
    List<Nutrition> findAllNutrition();

    List<Nutrition> findByNutritionUserName(String userName);

    Nutrition findByFoodName(String foodName);

    Nutrition findById(int id);

    Nutrition saveNutrition(Nutrition nutrition);

    List<Nutrition> deleteById(int id);
}
