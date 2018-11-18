package com.system.controller;

import com.system.bean.Admin;
import com.system.feign.IAdminService;
import com.system.jwt.JwtInfo;
import com.system.jwt.JwtUtil;
import com.system.util.DESEncryptUtil;
import com.system.util.StrUtil;
import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAdminService adminService;

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(String username,String password){
        JwtInfo jwtInfo = new JwtInfo();
        Map<String,Object> ret = new HashMap<>();
        ret.put("code",-4);
        ret.put("msg","用户名或密码有误");

        try {
            password = StrUtil.getMD5(DESEncryptUtil.desEncrypt(password,username+"admin"));
        } catch (Exception e) {
            ret.put("msg","密码错误");
            return JSONObject.fromObject(ret).toString();
        }


        jwtInfo.setUserName(username);
        jwtInfo.setPassword(password);
        Admin admin = adminService.login(jwtInfo.getUserName(),jwtInfo.getPassword());

        if(null != admin) {
            jwtInfo.setId(admin.getId().toString());
            ret.put("code",0);
            ret.put("msg","成功登入");
            Map<String,Object> rets = new HashMap<>();
            rets.put("token",JwtUtil.createJWT(jwtInfo));
            rets.put("userInfo",admin);
            ret.put("data",rets);
        }
        return JSONObject.fromObject(ret).toString();
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public boolean verify(String token){
        return JwtUtil.isVerify(token);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getId(String token) {
        try {
            Claims claims = JwtUtil.parseJWT(token);
            return (String) claims.get("id");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
