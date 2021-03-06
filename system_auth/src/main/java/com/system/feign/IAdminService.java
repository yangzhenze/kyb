package com.system.feign;

import com.system.bean.Admin;
import com.system.feign.config.FeignLogConfiguration;
import com.system.feign.fallback.AdminServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zzy
 * 服务调用
 * value:调用服务名称 fallback:断路由后的回调 configuration:日志配置
 */
@FeignClient(value = "system-admin",fallback = AdminServiceFallBack.class,configuration = FeignLogConfiguration.class)
public interface IAdminService {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    Admin login(@RequestParam("account") String account,@RequestParam("password") String password);

}
