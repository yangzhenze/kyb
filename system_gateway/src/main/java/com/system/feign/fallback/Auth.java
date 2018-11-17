package com.system.feign.fallback;

import com.system.feign.IAuth;
import org.springframework.stereotype.Service;

@Service
public class Auth implements IAuth {
    @Override
    public boolean verify(String token) {
        return false;
    }

    @Override
    public String getId(String token) {
        return null;
    }
}
