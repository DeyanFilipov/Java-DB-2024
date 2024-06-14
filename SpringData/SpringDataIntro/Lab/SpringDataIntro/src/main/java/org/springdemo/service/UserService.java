package org.springdemo.service;

import org.springdemo.data.entities.User;

public interface UserService {
    void register(User user);

    User findUserById(int id);
}
