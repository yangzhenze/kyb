package com.system.feign.fallback;

import com.system.feign.IAdminService;
import org.springframework.stereotype.Service;

/**
 * 断熔(异常)后的方法返回
 */
@Service
public class AdminServiceFallBack implements IAdminService {

    @Override
    public com.system.bean.Admin login(String account, String password) {
        return null;
    }
}
