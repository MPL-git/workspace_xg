package com.jf.entity;

public class IntellectualPropertyRightAppealCustom extends IntellectualPropertyRightAppeal {

	private String mobile;//权利人电话

    private Integer complainId;

    private String complainStatus;
	
	private String appealTypeDesc;//投诉类型

    private String statusDesc;

    private String complainStatusDesc;

    private String appealReasonDesc;//投诉理由

    private String complainReason;//申诉理由

    private String picUrls;//图片路径json

    private String allowShowMobile;//允许被投诉方查看0 不允许 1 允许'

    private String complainRemarks;//备注给商家的驳回原因

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getComplainId() {
        return complainId;
    }

    public void setComplainId(Integer complainId) {
        this.complainId = complainId;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public String getAppealTypeDesc() {
        return appealTypeDesc;
    }

    public void setAppealTypeDesc(String appealTypeDesc) {
        this.appealTypeDesc = appealTypeDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getComplainStatusDesc() {
        return complainStatusDesc;
    }

    public void setComplainStatusDesc(String complainStatusDesc) {
        this.complainStatusDesc = complainStatusDesc;
    }

    public String getAppealReasonDesc() {
        return appealReasonDesc;
    }

    public void setAppealReasonDesc(String appealReasonDesc) {
        this.appealReasonDesc = appealReasonDesc;
    }

    public String getComplainReason() {
        return complainReason;
    }

    public void setComplainReason(String complainReason) {
        this.complainReason = complainReason;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public String getAllowShowMobile() {
        return allowShowMobile;
    }

    public void setAllowShowMobile(String allowShowMobile) {
        this.allowShowMobile = allowShowMobile;
    }

    public String getComplainRemarks() {
        return complainRemarks;
    }

    public void setComplainRemarks(String complainRemarks) {
        this.complainRemarks = complainRemarks;
    }
}