package com.system.service;

import com.system.bean.Feedback;
import com.system.common.util.Page;

public interface IFeedBackService {

    boolean add(Feedback feedback);

    boolean update(Feedback feedback);

    Feedback getById(int id);

    Page<Feedback> getPage(int page, int pageSize, String status);
}
