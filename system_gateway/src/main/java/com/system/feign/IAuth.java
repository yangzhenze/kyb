package com.system.feign;

import com.system.feign.config.FeignLogConfiguration;
import com.system.feign.fallback.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "system-auth",fallback = Auth.class,configuration = FeignLogConfiguration.class)
public interface IAuth {

    @RequestMapping(value = "auth/verify", method = RequestMethod.GET)
    boolean verify(@RequestParam("token") String token);

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    String getId(@RequestParam("token") String token);
}
