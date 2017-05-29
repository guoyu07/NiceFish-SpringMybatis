package com.nicefish.auth.po;

import java.io.Serializable;

public class POUserRole implements Serializable{
    
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1310594236879402003L;

	private String id;

    private String userId;

    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}