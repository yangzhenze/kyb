package com.system.dao.impl;

import com.system.bean.Article;
import com.system.common.util.Page;
import com.system.dao.BaseDao;
import com.system.dao.IArticleDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2018/11/18 1:39 PM
 */
@Repository
public class ArticleDaoImpl  extends BaseDao<Article> implements IArticleDao {
    @Override
    public boolean save(Article article) {
        return this.save(article);
    }

    @Override
    public boolean delete(Article article) {
        return false;
    }

    @Override
    public boolean delById(Serializable... ids) {
        return this.deleteById(ids);
    }

    @Override
    public boolean update(Article article) {
        return this.update(article);
    }

    @Override
    public Article findById(Serializable id) {
        return this.findById(id);
    }

    @Override
    public Page<Article> findPage(int page, int pageSize, Object... args) {
        String sql = "order by create_date desc";
        return this.paginate(page,pageSize,sql,args);
    }
}
