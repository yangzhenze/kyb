package com.system.dao.impl;

import com.system.bean.Feedback;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IFeedBackDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:03 PM
 */
@Repository
public class FeedBackDaoImpl extends BaseDao<Feedback> implements IFeedBackDao {
    @Override
    public boolean save(Feedback feedback) {
        return super.insertEntity(feedback);
    }

    @Override
    public boolean delete(Feedback feedback) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Feedback feedback) {
        return super.updateEntity(feedback);
    }

    @Override
    public Feedback findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Feedback> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,args);
    }

    @Override
    public Page<Feedback> getPage(int page, int pageSize, String status) {
        String sql = "where feedback_status = ? order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,status);
    }
}
