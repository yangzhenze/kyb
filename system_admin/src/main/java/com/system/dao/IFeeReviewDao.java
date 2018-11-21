package com.system.dao;

import com.system.bean.FeeReview;
import com.system.common.util.Page;

public interface IFeeReviewDao extends IBaseDao<FeeReview> {

    Page<FeeReview> getPage(int page,int pageSize,String reviewStatus);
}
