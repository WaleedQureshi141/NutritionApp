package com.genspark.nutritionapp.service;

import com.genspark.nutritionapp.DAO.RoleDao;
import com.genspark.nutritionapp.DAO.RoleDaoImpl;
import com.genspark.nutritionapp.DAO.UserDao;
import com.genspark.nutritionapp.entity.Role;
import com.genspark.nutritionapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    private UserDao userDao;

    private RoleDao roleDao;

    @Autowired
    public UserInfoServiceImpl(UserDao userDao, RoleDao roleRepository){
        this.userDao = userDao;
        this.roleDao = roleRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        return userDao.getAllUsers();
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setId(2L);
        role.setName("ROLE_USER");
        ArrayList<Role> roleArrayList = new ArrayList<>();
        roleArrayList.add(role);
        user.setRoles(roleArrayList);
        user.setEnabled(true);
        //user.setDay(1)
        return userDao.save(user);
    }

    @Override
    public List<User> deleteByUserName(String username) {
        this.userDao.deleteByUserName(username);
        return this.userDao.getAllUsers();
    }

    @Override
    public User saveDeveloper(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_DEVELOPER");
        ArrayList<Role> roleArrayList = new ArrayList<>();
        roleArrayList.add(role);
        user.setRoles(roleArrayList);
        user.setEnabled(true);
        //user.setDay(1)
        return userDao.save(user);
    }

    //code was gotten from tutorials in https://www.udemy.com/course/spring-hibernate-tutorial
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    //code was gotten from tutorials in https://www.udemy.com/course/spring-hibernate-tutorial
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
