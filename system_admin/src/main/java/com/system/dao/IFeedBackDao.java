package com.system.dao;

import com.system.bean.Feedback;
import com.system.common.util.Page;

/**
 * @author zzy
 * @Date 2018/11/19 8:44 PM
 */
public interface IFeedBackDao extends IBaseDao<Feedback> {
    Page<Feedback> getPage(int page, int pageSize, String status);
}
