package com.genspark.nutritionapp.entity;

import jakarta.persistence.*;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

import java.sql.Date;

@Entity
@Table(name = "nutrition")
public class Nutrition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "calories")
    private int calories;

    @Column(name = "protein_g")
    private int protein;
    @Column(name = "fat_g")
    private int fat;
    @Column(name = "carbs_g")
    private int carbs;

    @Column(name = "add_date")
    private Date addDate;

    public Nutrition() {
    }

    public Nutrition(int id, String userName, String foodName, int calories, int protein, int carbs, int fat, Date addDate) {
        this.id = id;
        this.userName = userName;
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.addDate = addDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fat=" + fat +
                ", addDate='" + addDate + '\'' +
                '}';
    }
}
