package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.Role;
import com.genspark.nutritionapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where userName=:user",User.class);
        query.setParameter("user",username);

        User theUser = null;
        try {
            theUser = query.getSingleResult();
        } catch (Exception e) {
            theUser = new User();
        }

        return theUser;
    }

    @Override
    @Transactional
    public User save(User user){
        entityManager.createNativeQuery("INSERT INTO user (username, password, enabled) VALUES (?, ?, ?)")
                .setParameter(1, user.getUserName())
                .setParameter(2, user.getPassword())
                .setParameter(3,user.isEnabled())
                .executeUpdate();
        for(Role role: user.getRoles()){
            entityManager.createNativeQuery("INSERT INTO users_roles (username, role_id) VALUES (?, ?)")
                    .setParameter(1, user.getUserName())
                    .setParameter(2,role.getId())
                    .executeUpdate();
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> getAllUsers(){
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void deleteByUserName(String userName){
        entityManager.createNativeQuery("DELETE FROM users_roles WHERE userName = ?")
                .setParameter(1, userName)
                .executeUpdate();
        entityManager.createNativeQuery("DELETE FROM user WHERE userName = ?")
                .setParameter(1, userName)
                .executeUpdate();
    }


}
