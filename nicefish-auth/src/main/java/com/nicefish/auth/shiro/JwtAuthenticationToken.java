package com.nicefish.auth.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>
 * 认证Token
 * </p>
 *
 * @author zhongzhong
 */
public class JwtAuthenticationToken implements AuthenticationToken {
    private String userId;
    private String token;

    public JwtAuthenticationToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
