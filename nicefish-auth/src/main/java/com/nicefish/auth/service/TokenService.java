package com.nicefish.auth.service;

/**
 * <p>
 * TokenService用于校验token是否有效
 * </p>
 *
 * @author zhongzhong
 */
public interface TokenService {
    /**
     * 校验token的方法
     *
     * @param token jwt token
     */
    void validateToken(String token);
}
