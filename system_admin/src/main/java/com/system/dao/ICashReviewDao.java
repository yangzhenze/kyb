package com.system.dao;

import com.system.bean.Review;
import com.system.common.util.Page;

/**
 * @author zzy
 * @Date 2018/11/19 8:51 PM
 */
public interface ICashReviewDao extends IBaseDao<Review> {

    Page<Review> findPage(int page, int pageSize, String reviewStatus);
}
