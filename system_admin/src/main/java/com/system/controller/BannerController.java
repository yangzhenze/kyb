package com.system.controller;

import com.system.bean.Banner;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.IBannerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    IBannerService bannerService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "创建横幅",notes = "创建横幅")
    public String addBanner(Banner Banner){;
        if(bannerService.add(Banner)){
            return Ret.msgAdd();
        }

        return Ret.msgAddFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取文章详情",notes = "根据id获取文章详细内容")
    @ApiImplicitParam(name = "id",value = "横幅id",required = true,paramType = "path",dataType = "Integer")
    public String getBannerById(@PathVariable Integer id){

        return Ret.msgSuccess(bannerService.getById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新横幅", notes = "更新横幅")
    public String update(Banner Banner){

        if(bannerService.update(Banner)){
            return Ret.msgUpdate();
        }
        return Ret.msgUpdateFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除横幅", notes = "根据id删除横幅")
    @ApiImplicitParam(name = "id",value = "管理员id",required = true,paramType = "path",dataType = "Integer")
    public String delBanner(@PathVariable Integer [] id){
        if(bannerService.delete(id)){
            return Ret.setSuccessMsg("删除成功");
        }
        return Ret.msgSetVal("删除失败");
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取横幅列表",notes = "根据页数获取横幅列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getBannerPage(@PathVariable Integer page,Integer pageSize){

        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }

        return Ret.msgSuccess(bannerService.getPage(page, pageSize));
    }
}
