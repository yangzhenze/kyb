package com.system.service;

import com.system.bean.User;
import com.system.common.util.Page;

public interface IUserService {

    Page<User> getPage(int page, int pageSize, String userType,String userStatus);

    boolean update(User user);

    User getUser(Integer id);
}
