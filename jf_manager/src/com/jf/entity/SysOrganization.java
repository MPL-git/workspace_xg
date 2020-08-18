package com.jf.entity;

import java.util.Date;

public class SysOrganization {
    private Integer orgId;

    private String orgName;

    private String orgDesc;

    private Integer parentId;

    private Date createDate;

    private String status;

    private Date statusDate;

    private Integer createStaffId;

    private Integer modifyStaffId;

    private String dutyStaffName;

    private String addr;

    private String linkPhone;

    private String mobilePhone;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public String getDutyStaffName() {
        return dutyStaffName;
    }

    public void setDutyStaffName(String dutyStaffName) {
        this.dutyStaffName = dutyStaffName == null ? null : dutyStaffName.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }
}