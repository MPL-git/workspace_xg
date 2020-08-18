package com.jf.entity;

import java.util.Date;

public class WeixinXcxSprDtl {
    private Integer id;

    private Integer sprPlanId;

    private String sprType;

    private String sprValue;

    private String sprUrl;

    private String sprQrCode;

    private String pic;

    private String isEffect;

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

    public Integer getSprPlanId() {
        return sprPlanId;
    }

    public void setSprPlanId(Integer sprPlanId) {
        this.sprPlanId = sprPlanId;
    }

    public String getSprType() {
        return sprType;
    }

    public void setSprType(String sprType) {
        this.sprType = sprType == null ? null : sprType.trim();
    }

    public String getSprValue() {
        return sprValue;
    }

    public void setSprValue(String sprValue) {
        this.sprValue = sprValue == null ? null : sprValue.trim();
    }

    public String getSprUrl() {
        return sprUrl;
    }

    public void setSprUrl(String sprUrl) {
        this.sprUrl = sprUrl == null ? null : sprUrl.trim();
    }

    public String getSprQrCode() {
        return sprQrCode;
    }

    public void setSprQrCode(String sprQrCode) {
        this.sprQrCode = sprQrCode == null ? null : sprQrCode.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect == null ? null : isEffect.trim();
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