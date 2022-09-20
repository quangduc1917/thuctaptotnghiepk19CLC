package com.pnt.mobileshop.service;

import com.pnt.mobileshop.enity.User;

import java.util.List;

public interface UserService {
//    public List<Object> getAllUsersDetail();

    List<User> findAll();

    User findUserById(Long id);

    void saveUser(User user);

    void deleteUserById(Long id);

    User findUserByUsername(String username);
}
