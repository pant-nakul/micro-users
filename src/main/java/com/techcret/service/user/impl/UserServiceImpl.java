package com.techcret.service.user.impl;

import com.techcret.service.user.entities.User;
import com.techcret.service.user.exceptions.ResourceNotFoundException;
import com.techcret.service.user.repositories.UserRepository;
import com.techcret.service.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    /**
     * @param user
     * @return
     */
    @Override
    public User saveUSer(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found on server with id :- " + userId));
    }
}
