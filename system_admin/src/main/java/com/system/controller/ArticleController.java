package com.system.controller;

import com.system.bean.Article;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.IArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzy
 * @Date 2018/11/18 2:16 PM
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "创建文章",notes = "创建文章")
    public String addAdmin(Article article){

        if(articleService.add(article)){
            return Ret.msgAdd();
        }

        return Ret.msgAddFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取文章详情",notes = "根据id获取文章详细内容")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,paramType = "path",dataType = "Integer")
    public String getArticleById(@PathVariable Integer id){

        return Ret.msgSuccess(articleService.getById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新管理员", notes = "更新管理员信息")
    public String update(Article article){

        if(articleService.update(article)){
            return Ret.msgUpdate();
        }
        return Ret.msgUpdateFail();

    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取管理员列表",notes = "根据页数获取管理员列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getArticlePage(@PathVariable Integer page,Integer pageSize){

        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }

        return Ret.msgSuccess(articleService.getPage(page, pageSize));
    }
}
