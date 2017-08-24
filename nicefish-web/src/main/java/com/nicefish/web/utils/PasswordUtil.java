package com.nicefish.web.utils;

import com.nicefish.auth.po.POUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by zhongzhong on 2017/5/7.
 */
public class PasswordUtil {
    private static String hashAlgorithmName = "sha-512";
    private static int hashIterations = 1024;

    public static String encryptPassword(POUser user){
        return new SimpleHash(hashAlgorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUserName()),
                hashIterations
                ).toHex();
    }
}
