package com.system.service;

import com.system.bean.Banner;
import com.system.common.util.Page;

public interface IBannerService {

    boolean add(Banner banner);

    boolean update(Banner banner);

    Banner getById(int id);

    boolean delete(Integer... ids);

    Page<Banner> getPage(int page, int pageSize);
}
