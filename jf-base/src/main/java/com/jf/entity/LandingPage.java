package com.jf.entity;

import java.util.Date;

public class LandingPage {
    private Integer id;

    private String name;

    private String androidChannel;

    private Integer iosActivityGroupId;

    private String iosActivityName;

    private String editorRecommend;

    private String applicationInformation;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAndroidChannel() {
        return androidChannel;
    }

    public void setAndroidChannel(String androidChannel) {
        this.androidChannel = androidChannel == null ? null : androidChannel.trim();
    }

    public Integer getIosActivityGroupId() {
        return iosActivityGroupId;
    }

    public void setIosActivityGroupId(Integer iosActivityGroupId) {
        this.iosActivityGroupId = iosActivityGroupId;
    }

    public String getIosActivityName() {
        return iosActivityName;
    }

    public void setIosActivityName(String iosActivityName) {
        this.iosActivityName = iosActivityName == null ? null : iosActivityName.trim();
    }

    public String getEditorRecommend() {
        return editorRecommend;
    }

    public void setEditorRecommend(String editorRecommend) {
        this.editorRecommend = editorRecommend == null ? null : editorRecommend.trim();
    }

    public String getApplicationInformation() {
        return applicationInformation;
    }

    public void setApplicationInformation(String applicationInformation) {
        this.applicationInformation = applicationInformation == null ? null : applicationInformation.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}