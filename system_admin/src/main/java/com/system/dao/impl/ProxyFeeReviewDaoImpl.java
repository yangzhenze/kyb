package com.system.dao.impl;

import com.system.bean.FeeReview;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IProxyFeeReviewDao;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:04 PM
 */
public class ProxyFeeReviewDaoImpl extends BaseDao<FeeReview> implements IProxyFeeReviewDao {
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
        return super.deleteById(ids);
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
        return super.paginate(page,pageSize,sql,args);
    }
}
