package com.system.service.impl;

import com.system.bean.Banner;
import com.system.common.util.Page;
import com.system.dao.IBannerDao;
import com.system.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    IBannerDao bannerDao;
    @Override
    public boolean add(Banner banner) {
        return bannerDao.save(banner);
    }

    @Override
    public boolean update(Banner banner) {
        return bannerDao.update(banner);
    }

    @Override
    public Banner getById(int id) {
        return bannerDao.findById(id);
    }

    @Override
    public boolean delete(Integer... ids) {
        return bannerDao.delById(ids);
    }

    @Override
    public Page<Banner> getPage(int page, int pageSize) {
        return bannerDao.findPage(page,pageSize);
    }
}
