package com.system.controller;

import com.system.bean.Message;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.IMessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MessageController {
    @Autowired
    IMessageService messageService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "创建消息",notes = "创建消息")
    public String addMsg(Message article){
        if(messageService.add(article)){
            return Ret.msgAdd();
        }

        return Ret.msgAddFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取消息详情",notes = "根据id获取消息详细内容")
    @ApiImplicitParam(name = "id",value = "消息id",required = true,paramType = "path",dataType = "Integer")
    public String getMsgById(@PathVariable Integer id){

        return Ret.msgSuccess(messageService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除消息", notes = "根据id删除消息")
    @ApiImplicitParam(name = "id",value = "消息id",required = true,paramType = "path",dataType = "Integer")
    public String deMsg(@PathVariable Integer [] id){
        if(messageService.delByIds(id)){
            return Ret.setSuccessMsg("删除成功");
        }
        return Ret.msgSetVal("删除失败");
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取消息列表",notes = "根据页数获取消息列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getMsgPage(@PathVariable Integer page,Integer pageSize){

        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }

        return Ret.msgSuccess(messageService.getPage(page, pageSize));
    }
}
