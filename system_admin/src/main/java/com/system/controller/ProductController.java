package com.system.controller;

import com.system.bean.Product;
import com.system.common.Const;
import com.system.common.util.Ret;
import com.system.service.IProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "创建产品",notes = "创建产品")
    public String addProduct(Product product){;
        if(productService.add(product)){
            return Ret.msgAdd();
        }

        return Ret.msgAddFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取文章详情",notes = "根据id获取文章详细内容")
    @ApiImplicitParam(name = "id",value = "产品id",required = true,paramType = "path",dataType = "Integer")
    public String getProductById(@PathVariable Integer id){

        return Ret.msgSuccess(productService.getById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新产品", notes = "更新产品")
    public String update(Product product){

        if(productService.update(product)){
            return Ret.msgUpdate();
        }
        return Ret.msgUpdateFail();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除产品", notes = "根据id删除产品")
    @ApiImplicitParam(name = "id",value = "管理员id",required = true,paramType = "path",dataType = "Integer")
    public String delProduct(@PathVariable int [] id){
        if(productService.delById(id)){
            return Ret.setSuccessMsg("删除成功");
        }
        return Ret.msgSetVal("删除失败");
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取产品列表",notes = "根据页数获取产品列表")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页行数", required = false)
    public String getProductPage(@PathVariable Integer page,Integer pageSize){

        if(null == pageSize){
            pageSize = Const.PAGE_SIZE;
        }

        return Ret.msgSuccess(productService.getPage(page, pageSize));
    }
}
