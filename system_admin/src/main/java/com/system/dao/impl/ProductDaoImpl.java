package com.system.dao.impl;

import com.system.bean.Product;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IProductDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class ProductDaoImpl extends BaseDao<Product> implements IProductDao{
    @Override
    public boolean save(Product product) {
        return super.insertEntity(product);
    }

    @Override
    public boolean delete(Product product) {
        return super.deleteEntity(product);
    }

    @Override
    public boolean delById(Serializable... ids) {
        return super.deleteById(ids);
    }

    @Override
    public boolean update(Product product) {
        return super.updateEntity(product);
    }

    @Override
    public Product findById(Serializable id) {
        return super.findEntityById(id);
    }

    @Override
    public Page<Product> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return super.paginate(page,pageSize,sql,args);
    }
}
