package com.harry.web.model;

public class Permission {
    private Integer id;

    private String permissionName;

    private String roleId;

    public Permission(Integer id, String permissionName, String roleId) {
        this.id = id;
        this.permissionName = permissionName;
        this.roleId = roleId;
    }

    public Permission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}