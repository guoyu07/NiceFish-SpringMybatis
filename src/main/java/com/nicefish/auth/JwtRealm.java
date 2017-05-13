package com.nicefish.auth;

import com.nicefish.service.TokenService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;

import javax.annotation.Resource;

/**
 * Created by zhongzhong on 2017/5/13.
 */
public class JwtRealm extends AbstractRealm {

    @Resource
    private TokenService tokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token !=null && token instanceof JwtAuthenticationToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken)token;

        //验证token是否有效
        tokenService.validateToken(jwtToken.getToken());

        return new SimpleAccount(jwtToken.getPrincipal(),jwtToken.getCredentials(),getName());
    }
}
