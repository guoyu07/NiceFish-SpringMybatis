package com.nicefish.service.impl;

import com.nicefish.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * Created by zhongzhong on 2017/5/13.
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
