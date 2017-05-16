package com.nicefish.service.impl;

import com.nicefish.auth.TokenUtils;
import com.nicefish.exception.InvalidTokenException;
import com.nicefish.exception.TokenExpiredException;
import com.nicefish.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * TokenService实现类
 * </p>
 *
 * @author zhongzhong
 */

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public void validateToken(String token) {

        if(StringUtils.isEmpty(token)){
            throw new InvalidTokenException();
        }

        try{
            Claims claims = TokenUtils.getClaims(token);

            String userId = claims.getSubject();

            Date exp = claims.getExpiration();
            if(exp.getTime() < System.currentTimeMillis()){
                throw new TokenExpiredException();
            }


        }catch (SignatureException e){
            throw new InvalidTokenException(e);
        }

    }
}
