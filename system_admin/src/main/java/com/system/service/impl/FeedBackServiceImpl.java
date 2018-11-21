package com.system.service.impl;

import com.system.bean.Feedback;
import com.system.common.util.Page;
import com.system.dao.IFeedBackDao;
import com.system.service.IFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl implements IFeedBackService {
    @Autowired
    IFeedBackDao feedBackDao;
    @Override
    public boolean add(Feedback feedback) {
        return feedBackDao.save(feedback);
    }

    @Override
    public boolean update(Feedback feedback) {
        return feedBackDao.update(feedback);
    }

    @Override
    public Feedback getById(int id) {
        return feedBackDao.findById(id);
    }

    @Override
    public Page<Feedback> getPage(int page, int pageSize, String status) {
        Page<Feedback> getPage;

        if(null != status){
            getPage = feedBackDao.getPage(page,pageSize,status);
        }else{
            getPage = feedBackDao.findPage(page,pageSize);
        }
        return getPage;
    }
}
