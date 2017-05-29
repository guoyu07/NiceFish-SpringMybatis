package com.nicefish.auth.service;


import java.util.Set;

public interface RoleService {

    /**
     * 根据用户名查询角色集合
     * @param username String 用户名
     * @return List 角色集合
     */
    Set<String> findRoles(String username);
}
