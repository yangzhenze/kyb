package com.system.service.ipml;

import com.system.bean.Article;
import com.system.common.util.Page;
import com.system.dao.IArticleDao;
import com.system.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/18 1:45 PM
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    IArticleDao articleDao;

    @Override
    public boolean add(Article article) {
        return articleDao.save(article);
    }

    @Override
    public boolean delById(Serializable... ids) {
        return articleDao.delById(ids);
    }

    @Override
    public boolean update(Article article) {
        return articleDao.update(article);
    }

    @Override
    public Article getById(Serializable id) {
        return articleDao.findById(id);
    }

    @Override
    public Page<Article> getPage(int page, int pageSize) {
        return articleDao.findPage(page,pageSize);
    }
}
