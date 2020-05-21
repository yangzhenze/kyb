package com.system.dao.impl;

import com.system.bean.RolePer;
import com.system.dao.IRolePerDao;
import com.zzy.db.helper.BaseDao;
import com.zzy.db.helper.JdbcTemplateHelper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzy
 * @Date 2018/7/17 下午3:31
 */
@Repository
public class RolePerDaoImpl implements IRolePerDao {
    @Override
    public List<RolePer> findByRoleId(Integer roleId) {
        return JdbcTemplateHelper.findEntity (RolePer.class,"select * from sys_role_per where role_id = ?",roleId);
    }

    @Override
    public boolean save(RolePer rolePer) {
        return JdbcTemplateHelper.insertEntity(rolePer);
    }

    @Override
    public boolean deleteByRoleId(Integer roleId) {
        return JdbcTemplateHelper.update("delete from sys_role_per where role_id = ?",roleId) >0?true:false;
    }
}
