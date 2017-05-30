package com.nicefish.dao;

import com.nicefish.po.PORole;
import com.nicefish.utils.BaseMapper;

import java.util.Set;

public interface PORoleMapper extends BaseMapper<PORole, String>{


    Set<String> findRoles(String username);
}