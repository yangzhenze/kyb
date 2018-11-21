package com.system.service;

import com.system.bean.Review;
import com.system.common.util.Page;

public interface ICashReviewService {

    boolean add(Review review);

    boolean update(Review review);

    Review getById(int id);

    Page<Review> getPage(int page,int pageSize,String reviewStatus);
}
