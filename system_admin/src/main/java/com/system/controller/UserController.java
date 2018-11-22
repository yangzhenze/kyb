package com.system.controller;

import com.system.bean.User;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取用户列表",notes = "根据页数获得用户列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getArticlePage(@PathVariable Integer page, Integer pageSize, String userType,String userStatus){

        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }

        return Ret.msgSuccess(userService.getPage(page, pageSize, userType, userStatus));
    }

    @RequestMapping(value = "/{id}/{status}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新冻结状态", notes = "更新冻结状态")
    public String update(@PathVariable String id,@PathVariable String status){
        User user = new User();
        user.setId(id);
        user.setUserStatus(status);
        if(userService.update(user)){
            return Ret.msgUpdate();
        }
        return Ret.msgUpdateFail();

    }
}
