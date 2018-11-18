package com.system.service;

import com.system.bean.Article;
import com.system.common.util.Page;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/18 1:45 PM
 */
public interface IArticleService {

    boolean add(Article article);

    boolean delById(Serializable... ids);

    boolean update(Article article);

    Article getById(Serializable id);

    Page<Article> getPage(int page, int pageSize);
}
