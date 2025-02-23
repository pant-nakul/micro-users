package com.techcret.service.user.services;

import com.techcret.service.user.entities.User;

import java.util.List;

public interface UserService {

    //User operations
    //create
    User saveUSer(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    //TODO:Delete

    //TODO:Update

}
