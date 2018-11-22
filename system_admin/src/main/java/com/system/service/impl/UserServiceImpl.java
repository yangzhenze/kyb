package com.system.service.impl;

import com.system.bean.User;
import com.system.common.util.Page;
import com.system.dao.IUserDao;
import com.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public Page<User> getPage(int page, int pageSize, String userType, String userStatus) {
        return userDao.getPage(page,pageSize,userType,userStatus);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public User getUser(Integer id) {
        return null;
    }
}
