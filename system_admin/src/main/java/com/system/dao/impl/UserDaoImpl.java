package com.system.dao.impl;

import com.system.bean.User;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:04 PM
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao{
    @Override
    public boolean save(User user) {
        return super.insertEntity(user);
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(User user) {
        return super.updateEntity(user);
    }

    @Override
    public User findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<User> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,args);
    }
}
