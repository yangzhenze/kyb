package com.system.dao.impl;

import com.system.bean.Admin;
import com.system.dao.IAdminDao;
import com.zzy.db.helper.BaseDao;
import com.zzy.db.helper.Page;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class AdminDaoImpl extends BaseDao<Admin,Integer> implements IAdminDao {
    @Override
    public boolean save(Admin entity) {
        return super.insertEntity(entity);
    }

    @Override
    public Admin saveAndGet(Admin admin) {
        return null;
    }

    @Override
    public boolean delete(Admin entity) {
        return super.deleteEntity(entity);
    }

    @Override
    public boolean delById(Integer [] ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Admin entity) {
        return super.updateEntity(entity);
    }

    @Override
    public Admin updateAndGet(Admin admin) {
        return null;
    }

    @Override
    public Admin findById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Admin> findPage(int page, int pageSize) {
        String sql = "order by create_date desc";
        return super.paginate(page,pageSize,sql);
    }

    @Override
    public Admin getByAccAndPwd(String account, String password) {
        String sql = "select * from sys_admin where account = ? and password = ?";
        return super.findFirstEntity(sql,account,password);
    }

    @Override
    public Admin getByAccAndId(String account, Integer id) {
        String sql = "select * from sys_admin where account = ?";
        Object [] args = null;

        if(null != id){
            sql += " and id != ?";
            args = new Object[]{account,id};

        }else{
            args = new Object[]{account};
        }

        return super.findFirstEntity(sql,args);
    }
}
