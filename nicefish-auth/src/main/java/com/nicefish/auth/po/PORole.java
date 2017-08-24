package com.nicefish.auth.po;

import java.io.Serializable;

public class PORole implements Serializable{
   
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7672766478519142753L;

	private String roleId;

    private String roleName;

    private String roleDesc;

    private Integer type;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}