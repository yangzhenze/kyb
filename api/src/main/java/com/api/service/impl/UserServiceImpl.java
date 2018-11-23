package com.api.service.impl;

import com.api.bean.User;
import com.api.dao.UserRepository;
import com.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }
}
