package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public Role findRoleByName(String roleName) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where name=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        Role theRole = null;

        try {
            theRole = query.getSingleResult();
        } catch (Exception e) {
            theRole = null;
        }

        return theRole;
    }
}
