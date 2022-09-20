package com.pnt.mobileshop.serviceImpl;

import com.pnt.mobileshop.enity.User;
import com.pnt.mobileshop.repository.UserRepository;
import com.pnt.mobileshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


//    @Override
//    public List<Object> getAllUsersDetail() {
//        return userRepository.getAllUsersDetail();
//    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
