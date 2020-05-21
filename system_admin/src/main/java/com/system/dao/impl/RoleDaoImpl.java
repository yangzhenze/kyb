package com.system.dao.impl;

import com.system.bean.Role;
import com.system.dao.IRoleDao;
import com.zzy.db.helper.BaseDao;
import com.zzy.db.helper.Page;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author zzy
 * @Date 2018/7/16 上午10:54
 */
@Repository
public class RoleDaoImpl extends BaseDao<Role,Integer> implements IRoleDao {

    @Override
    public boolean save(Role role) {
        return super.insertEntity(role);
    }

    @Override
    public Role saveAndGet(Role role) {
        return null;
    }

    @Override
    public boolean delete(Role role) {
        return super.deleteEntity(role);
    }

    @Override
    public boolean delById(Integer... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Role role) {
        return super.updateEntity(role);
    }

    @Override
    public Role updateAndGet(Role role) {
        return null;
    }

    @Override
    public Role findById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Role> findPage(int page, int pageSize) {
        String sql = "order by create_date desc";
        return super.paginate(page,pageSize,sql);
    }

    @Override
    public List<Role> findList() {
        return super.findEntity("select * from sys_role order by create_date desc");
    }
}
