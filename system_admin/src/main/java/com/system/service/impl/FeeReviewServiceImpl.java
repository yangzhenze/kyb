package com.system.service.impl;

import com.system.bean.FeeReview;
import com.system.common.util.Page;
import com.system.dao.IFeeReviewDao;
import com.system.service.IFeeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeReviewServiceImpl implements IFeeReviewService {
    @Autowired
    IFeeReviewDao feeReviewDao;

    @Override
    public boolean add(FeeReview feeReview) {
        return feeReviewDao.save(feeReview);
    }

    @Override
    public boolean update(FeeReview feeReview) {
        return feeReviewDao.update(feeReview);
    }

    @Override
    public FeeReview getById(int id) {
        return feeReviewDao.findById(id);
    }

    @Override
    public Page<FeeReview> getPage(int page, int pageSize, String reviewStatus) {
       if(null != reviewStatus){
           return feeReviewDao.findPage(page,pageSize,reviewStatus);
       }
        return feeReviewDao.findPage(page,pageSize);
    }
}
