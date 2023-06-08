package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.Nutrition;
import com.genspark.nutritionapp.entity.Role;
import com.genspark.nutritionapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NutritionDaoImpl implements NutritionDAO
{
    private EntityManager entityManager;
    public NutritionDaoImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Nutrition> findByUsername(String userName) {
        TypedQuery<Nutrition> query = entityManager.createQuery("from Nutrition where userName=:user", Nutrition.class);
        query.setParameter("user",userName);

        List<Nutrition> nutrition = null;
        try {
            nutrition = query.getResultList();
        } catch (Exception e) {
            nutrition = new ArrayList<>();
        }

        return nutrition;
    }

    @Override
    public Nutrition findByFoodname(String foodName) {
        TypedQuery<Nutrition> query = entityManager.createQuery("from Nutrition where foodName=:food", Nutrition.class);
        query.setParameter("food",foodName);

        Nutrition nutrition = null;
        try {
            nutrition = query.getSingleResult();
        } catch (Exception e) {
            nutrition = new Nutrition();
        }

        return nutrition;
    }

    @Override
    public Nutrition findByid(int id) {
        TypedQuery<Nutrition> query = entityManager.createQuery("from Nutrition where id=:id", Nutrition.class);
        query.setParameter("id",id);

        Nutrition nutrition = null;
        try {
            nutrition = query.getSingleResult();
        } catch (Exception e) {
            nutrition = new Nutrition();
        }

        return nutrition;
    }

    @Override
    @Transactional
    public Nutrition save(Nutrition nutrition) {
        entityManager.createNativeQuery("INSERT INTO nutrition " +
                        "(username, food_name, calories, protein_g, fat_g, carbs_g, add_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, nutrition.getUserName())
                .setParameter(2, nutrition.getFoodName())
                .setParameter(3,nutrition.getCalories())
                .setParameter(4,nutrition.getProtein())
                .setParameter(5, nutrition.getFat())
                .setParameter(6, nutrition.getCarbs())
                .setParameter(7,nutrition.getAddDate())
                .executeUpdate();
        return nutrition;
    }

    @Override
    @Transactional
    public List<Nutrition> getAllNutrition() {
        TypedQuery<Nutrition> query = entityManager.createQuery("from Nutrition", Nutrition.class);
        List<Nutrition> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void deleteById(int id)
    {
        entityManager.createNativeQuery("DELETE FROM nutrition WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }
}
