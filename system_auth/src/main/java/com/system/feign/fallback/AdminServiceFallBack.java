package com.system.feign.fallback;

import com.system.feign.IAdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceFallBack implements IAdminService {

    @Override
    public com.system.bean.Admin login(String account, String password) {
        return null;
    }
}
