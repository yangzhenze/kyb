package com.system.controller;

import com.system.common.util.DateUtil;
import com.system.common.util.Ret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

@RestController
@Api(description = "公开默认操作接口")
public class DefaultController {

    @Value("${web.upload-path}")
    private String filePath;
    private String staticPath = "static/";
    @Value("${web.apiPath}")
    private String apiPath;


    @RequestMapping(value="/admin/upload",method = RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    @ApiOperation(value = "管理员头像上传",notes = "管理员头像上传接口")
    public String uploadAdminImg(@RequestParam("file") MultipartFile file) {
        return this.upload(file,"admin",false);

    }

    @RequestMapping(value="/edit/upload",method = RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    @ApiOperation(value = "文本编辑器图片上传",notes = "文本编辑器图片上传接口")
    public String uploadEdit(@RequestParam("file") MultipartFile file) {
        return this.upload(file,"edit",true);
    }

    private String upload(MultipartFile file, String dicName, boolean completePath){
        // 获取静态上传路径
        String realFilePath = filePath+dicName+"/";
        String fileName = this.reNameFile(file.getOriginalFilename());
        try {
            uploadFile(file.getBytes(), realFilePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.msgSetVal("文件上传失败");
        }

        String filePath = staticPath+dicName+"/"+fileName;

        if(completePath){
            filePath = apiPath+filePath;
        }

        return Ret.msgSuccess(filePath);
    }

    private void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(false == targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    private String reNameFile(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        return DateUtil.formatDate(new Date(),"yyyyMMddhhssmmSSS")+ UUID.randomUUID()+suffix;
    }
}
