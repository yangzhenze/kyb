package com.system.dao.impl;

import com.system.bean.Commission;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.ICommissionDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:02 PM
 */
@Repository
public class CommissionDaoImpl extends BaseDao<Commission> implements ICommissionDao {
    @Override
    public boolean save(Commission commission) {
        return super.insertEntity(commission);
    }

    @Override
    public boolean delete(Commission commission) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Commission commission) {
        StringBuffer sb = new StringBuffer("update commission set one_level_agent=?,two_level_agent=?,initail_free=?");
        return super.jdbcTemplate.update(sb.toString(),commission.getOneLevelAgent(),commission.getTwoLevelAgent(),commission.getInitailFree())>0?true:false;
    }

    @Override
    public Commission findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Commission> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,args);
    }

    @Override
    public Commission getCommission() {
        String sql = "select * from commission";
        return super.findFirstEntity(sql);
    }
}
