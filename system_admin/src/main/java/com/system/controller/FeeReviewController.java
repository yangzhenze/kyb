package com.system.controller;

import com.system.bean.FeeReview;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.IFeeReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/fee")
public class FeeReviewController {
    @Autowired
    IFeeReviewService feeReviewService;

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取佣金审核列表",notes = "根据页数获佣金审核列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getArticlePage(@PathVariable Integer page, Integer pageSize,String reviewStatus){

        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }

        return Ret.msgSuccess(feeReviewService.getPage(page, pageSize, reviewStatus));
    }

    @RequestMapping(value = "/{id}/{status}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新审核状态", notes = "更新审核状态")
    public String update(@PathVariable int id,@PathVariable String status,String remark){
        FeeReview feeReview = new FeeReview();
        feeReview.setId(id);
        feeReview.setReviewStatus(status);
        feeReview.setReviewDate(new Date());
        if(null != remark){
            feeReview.setRemark(remark);
        }
        if(feeReviewService.update(feeReview)){
            return Ret.msgUpdate();
        }
        return Ret.msgUpdateFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取佣金审核详情",notes = "根据id获取佣金审核详细内容")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,paramType = "path",dataType = "Integer")
    public String getArticleById(@PathVariable Integer id){

        return Ret.msgSuccess(feeReviewService.getById(id));
    }

}
