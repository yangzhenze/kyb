package com.system.service;

import com.system.bean.Product;
import com.system.common.util.Page;

import java.io.Serializable;

public interface IProductService {

    boolean add(Product product);

    boolean delById(int ... ids);

    boolean update(Product product);

    Product getById(int id);

    Page<Product> getPage(int page,int pageSize);
}
