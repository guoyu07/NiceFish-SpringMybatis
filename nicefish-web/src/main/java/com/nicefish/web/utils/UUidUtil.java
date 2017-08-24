package com.nicefish.web.utils;

import java.util.UUID;

/**
 * Created by zhongzhong on 2017/5/7.
 */
public class UUidUtil {
    public static final String generate(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
