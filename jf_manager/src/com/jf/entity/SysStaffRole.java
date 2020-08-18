package com.jf.entity;

import java.util.Date;

public class SysStaffRole {
    private Integer staffRoleId;

    private Integer staffId;

    private Integer roleId;

    private Date createDate;

    private String status;

    private Date statusDate;

    private Integer createStaffId;

    private Integer modifyStaffId;

    public Integer getStaffRoleId() {
        return staffRoleId;
    }

    public void setStaffRoleId(Integer staffRoleId) {
        this.staffRoleId = staffRoleId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
    }

    public Integer getModifyStaffId() {
        return modifyStaffId;
    }

    public void setModifyStaffId(Integer modifyStaffId) {
        this.modifyStaffId = modifyStaffId;
    }
}