package com.system.dao.impl;

import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IBannerDao;
import org.springframework.boot.Banner;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:02 PM
 */
public class BannerDaoImpl extends BaseDao<Banner> implements IBannerDao {
    @Override
    public boolean save(Banner banner) {
        return false;
    }

    @Override
    public boolean delete(Banner banner) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return false;
    }

    @Override
    public boolean update(Banner banner) {
        return false;
    }

    @Override
    public Banner findById(Serializable id) {
        return null;
    }

    @Override
    public Page<Banner> findPage(int page, int pageSize, Object... args) {
        return null;
    }
}
