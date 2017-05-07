package com.nicefish.utils;

import java.util.UUID;

/**
 * Created by zhongzhong on 2017/5/7.
 */
public class UuidUtils {
    public static final String generate(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
