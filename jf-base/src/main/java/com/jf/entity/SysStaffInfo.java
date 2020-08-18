package com.jf.entity;

import java.util.Date;

public class SysStaffInfo {
    private Integer staffId;

    private String staffCode;

    private String staffPass;

    private String staffName;

    private String mobilePhone;

    private String email;

    private Integer orgId;

    private String isManagement;

    private Date createDate;

    private String status;

    private Date statusDate;

    private Integer createStaffId;

    private Integer modifyStaffId;

    private String certType;

    private String certNbr;

    private String sexType;

    private Date birthday;

    private Integer loginErrorCount;

    private String subordinateStaffIds;

    private String remark;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass == null ? null : staffPass.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getIsManagement() {
        return isManagement;
    }

    public void setIsManagement(String isManagement) {
        this.isManagement = isManagement == null ? null : isManagement.trim();
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

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertNbr() {
        return certNbr;
    }

    public void setCertNbr(String certNbr) {
        this.certNbr = certNbr == null ? null : certNbr.trim();
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType == null ? null : sexType.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public String getSubordinateStaffIds() {
        return subordinateStaffIds;
    }

    public void setSubordinateStaffIds(String subordinateStaffIds) {
        this.subordinateStaffIds = subordinateStaffIds == null ? null : subordinateStaffIds.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}