package com.nicefish.auth;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 * <p>生成token的工具类</p>
 *
 * @author zhongzhong
 */
public class TokenUtils {
    private static final Key key = MacProvider.generateKey();

    public static String generate(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static Object getBody(String token){
        Jwt jwt = Jwts.parser().parse(token);
        return jwt.getBody();
    }

}
