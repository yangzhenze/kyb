package com.system.dao.impl;

import com.system.bean.FeeReview;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IFeeReviewDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class FeeReviewDaoImpl extends BaseDao<FeeReview> implements IFeeReviewDao {
    @Override
    public Page<FeeReview> getPage(int page, int pageSize, String reviewStatus) {
        String sql = "where review_status = ? order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,reviewStatus);
    }

    @Override
    public boolean save(FeeReview feeReview) {
        return super.insertEntity(feeReview);
    }

    @Override
    public boolean delete(FeeReview feeReview) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return false;
    }

    @Override
    public boolean update(FeeReview feeReview) {
        return super.updateEntity(feeReview);
    }

    @Override
    public FeeReview findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<FeeReview> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,args);
    }
}
