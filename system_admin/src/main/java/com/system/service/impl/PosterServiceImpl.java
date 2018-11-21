package com.system.service.impl;

import com.system.bean.Poster;
import com.system.common.util.Page;
import com.system.dao.IPosterDao;
import com.system.service.IPosterService;
import org.springframework.beans.factory.annotation.Autowired;

public class PosterServiceImpl implements IPosterService {
    @Autowired
    IPosterDao posterDao;

    @Override
    public boolean add(Poster poster) {
        return posterDao.save(poster);
    }

    @Override
    public boolean update(Poster poster) {
        return posterDao.update(poster);
    }

    @Override
    public Page<Poster> getPage(int page, int pageSize, String posterStatus) {
        if(null != posterStatus){
            return posterDao.findPage(page,pageSize,posterStatus);
        }

        return posterDao.findPage(page,pageSize);
    }
}
