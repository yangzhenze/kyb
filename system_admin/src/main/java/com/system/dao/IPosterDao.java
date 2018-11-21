package com.system.dao;

import com.system.bean.Poster;
import com.system.common.util.Page;

/**
 * @author zzy
 * @Date 2018/11/19 10:40 PM
 */
public interface IPosterDao extends IBaseDao<Poster> {
    Page<Poster> findPage(int page, int pageSize, String posterStatus);
}
