package com.system.service;

import com.system.bean.FeeReview;
import com.system.common.util.Page;

public interface IFeeReviewService {

    boolean add(FeeReview feeReview);

    boolean update(FeeReview feeReview);

    FeeReview getById(int id);

    Page<FeeReview> getPage(int page,int pageSize,String reviewStatus);
}
