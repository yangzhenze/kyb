package com.system.service.ipml;

import com.system.bean.Product;
import com.system.common.util.Page;
import com.system.dao.IProductDao;
import com.system.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductDao productDao;

    @Override
    public boolean add(Product product) {
        product.setCreateDate(new Date());
        return productDao.save(product);
    }

    @Override
    public boolean delById(int... ids) {
        return productDao.delById(ids);
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(product);
    }

    @Override
    public Product getById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Page<Product> getPage(int page, int pageSize) {
        return productDao.findPage(page,pageSize);
    }
}
