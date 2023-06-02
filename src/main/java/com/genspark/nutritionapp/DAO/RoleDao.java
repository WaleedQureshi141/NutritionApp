package com.genspark.nutritionapp.DAO;

import com.genspark.nutritionapp.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String roleName);
}
