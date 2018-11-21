package com.system.dao.impl;

import com.system.bean.Poster;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IPosterDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/19 10:41 PM
 */
@Repository
public class PosterDaoImpl extends BaseDao<Poster> implements IPosterDao {
    @Override
    public boolean save(Poster poster) {
        return super.insertEntity(poster);
    }

    @Override
    public boolean delete(Poster poster) {
        return super.deleteById(poster);
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Poster poster) {
        return super.updateEntity(poster);
    }

    @Override
    public Poster findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Poster> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,args);
    }

    @Override
    public Page<Poster> findPage(int page, int pageSize, String posterStatus) {
        String sql = "where poster_status = ? order by create_date desc";
        return super.paginateEntity(page,pageSize,sql,posterStatus);
    }
}
