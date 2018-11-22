package com.system.dao.impl;

import com.system.bean.Banner;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IBannerDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 9:02 PM
 */
@Repository
public class BannerDaoImpl extends BaseDao<Banner> implements IBannerDao {
    @Override
    public boolean save(Banner banner) {
        return super.insertEntity(banner);
    }

    @Override
    public boolean delete(Banner banner) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Banner banner) {
        return super.updateEntity(banner);
    }

    @Override
    public Banner findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Banner> findPage(int page, int pageSize, Object... args) {
        String sql = "order by sort ";
        return super.paginateEntity(page,pageSize,sql,args);
    }
}
