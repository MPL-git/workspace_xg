package com.jf.entity;

import java.util.Date;

public class MchtBrandAptitudePicChg {
    private Integer id;

    private Integer brandChgId;

    private Integer mchtBrandAptitudeChgId;

    private String pic;

    private String archiveStatus;

    private Integer archiveBy;

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

    public Integer getBrandChgId() {
        return brandChgId;
    }

    public void setBrandChgId(Integer brandChgId) {
        this.brandChgId = brandChgId;
    }

    public Integer getMchtBrandAptitudeChgId() {
        return mchtBrandAptitudeChgId;
    }

    public void setMchtBrandAptitudeChgId(Integer mchtBrandAptitudeChgId) {
        this.mchtBrandAptitudeChgId = mchtBrandAptitudeChgId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
    }

    public Integer getArchiveBy() {
        return archiveBy;
    }

    public void setArchiveBy(Integer archiveBy) {
        this.archiveBy = archiveBy;
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