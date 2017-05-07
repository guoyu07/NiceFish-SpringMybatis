package com.nicefish.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 *
 * <p>生成token的工具类</p>
 * @author zhongzhong
 */
public class TokenBuilder {
    private static  final Key key = MacProvider.generateKey();

    public static String generate(String username){
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
