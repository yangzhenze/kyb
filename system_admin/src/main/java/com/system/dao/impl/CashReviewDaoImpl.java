package com.system.dao.impl;

import com.system.bean.Review;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.ICashReviewDao;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:02 PM
 */
public class CashReviewDaoImpl extends BaseDao<Review> implements ICashReviewDao {
    @Override
    public boolean save(Review review) {
        return super.insertEntity(review);
    }

    @Override
    public boolean delete(Review review) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Review review) {
        return super.updateEntity(review);
    }

    @Override
    public Review findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Review> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginate(page,pageSize,sql,args);
    }
}
