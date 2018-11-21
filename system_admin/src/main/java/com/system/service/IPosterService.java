package com.system.service;

import com.system.bean.Poster;
import com.system.common.util.Page;

public interface IPosterService {

    boolean add(Poster poster);

    boolean update(Poster poster);

    Page<Poster> getPage(int page, int pageSize,String posterStatus);
}
