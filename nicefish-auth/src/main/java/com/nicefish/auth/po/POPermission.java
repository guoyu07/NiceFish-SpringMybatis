package com.nicefish.auth.po;

import java.io.Serializable;

public class POPermission implements Serializable{
    
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8166245858070993682L;

	private String permissionId;

    private String permissionCode;

    private String permissionDesc;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc == null ? null : permissionDesc.trim();
    }
}