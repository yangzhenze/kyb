package com.system.controller;

import com.system.bean.Commission;
import com.system.bean.Review;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.ICashReviewService;
import com.system.service.ICommissionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/comm")
public class CommissionController {
    @Autowired
    ICommissionService commissionService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取提现审核列表",notes = "根据页数获提现审核列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getCommPage(){
        return Ret.msgSuccess(commissionService.get());
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新提现状态", notes = "更新提现状态")
    public String update(String oneLevelAgent, String twoLevelAgent, String initailFree){
        Commission commission = new Commission();
        commission.setOneLevelAgent(oneLevelAgent);
        commission.setTwoLevelAgent(twoLevelAgent);
        commission.setInitailFree(Double.parseDouble(initailFree));
        if(commissionService.update(commission)){
            return Ret.msgUpdate();
        }
        return Ret.msgUpdateFail();

    }

}
