package com.nicefish.auth.dao;

import com.nicefish.auth.po.PORole;
import com.nicefish.web.utils.mybatis.BaseMapper;

import java.util.Set;

public interface PORoleMapper extends BaseMapper<PORole, String> {


    Set<String> findRoles(String username);
}