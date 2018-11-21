package com.system.service.impl;

import com.system.bean.Review;
import com.system.common.util.Page;
import com.system.dao.ICashReviewDao;
import com.system.service.ICashReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashReviewServiceImpl implements ICashReviewService {
    @Autowired
    ICashReviewDao cashReviewDao;

    @Override
    public boolean add(Review review) {
        return cashReviewDao.save(review);
    }

    @Override
    public boolean update(Review review) {
        return cashReviewDao.update(review);
    }

    @Override
    public Review getById(int id) {
        return cashReviewDao.findById(id);
    }

    @Override
    public Page<Review> getPage(int page, int pageSize, String reviewStatus) {
        Page<Review> getPage;

        if(null != reviewStatus){
            getPage = cashReviewDao.findPage(page,pageSize,reviewStatus);
        }else{
            getPage = cashReviewDao.findPage(page,pageSize);
        }
        return getPage;
    }
}
