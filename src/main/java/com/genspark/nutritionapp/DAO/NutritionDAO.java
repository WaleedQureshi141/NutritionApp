package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.Nutrition;

import java.util.List;

public interface NutritionDAO
{
    List<Nutrition> findByUsername(String userName);

    Nutrition findByFoodname(String foodName);

    Nutrition findByid(int id);

    Nutrition save(Nutrition nutrition);

    List<Nutrition> getAllNutrition();

    void deleteById(int id);
}
