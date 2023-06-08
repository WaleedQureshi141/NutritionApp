package com.genspark.nutritionapp.service;

import com.genspark.nutritionapp.DAO.NutritionDAO;
import com.genspark.nutritionapp.entity.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionInfoServiceImpl implements NutritionInfoService
{
    private NutritionDAO nutritionDAO;

    @Autowired
    public NutritionInfoServiceImpl(NutritionDAO nutritionDAO) {
        this.nutritionDAO = nutritionDAO;
    }

    @Override
    public List<Nutrition> findAllNutrition() {
        return nutritionDAO.getAllNutrition();
    }

    @Override
    public List<Nutrition> findByNutritionUserName(String userName) {
        return nutritionDAO.findByUsername(userName);
    }

    @Override
    public Nutrition findByFoodName(String foodName) {
        return nutritionDAO.findByFoodname(foodName);
    }

    @Override
    public Nutrition findById(int id) {
        return nutritionDAO.findByid(id);
    }

    @Override
    public Nutrition saveNutrition(Nutrition nutrition) {
        return nutritionDAO.save(nutrition);
    }

    @Override
    public List<Nutrition> deleteById(int id)
    {
        this.nutritionDAO.deleteById(id);
        return this.nutritionDAO.getAllNutrition();
    }

}
