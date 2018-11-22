package com.system.dao;

import com.system.bean.User;
import com.system.common.util.Page;

/**
 * @author zzy
 * @Date 2018/11/19 8:42 PM
 */
public interface IUserDao extends IBaseDao<User>  {
    Page<User> getPage(int page, int pageSize, String userType, String userStatus);
}
