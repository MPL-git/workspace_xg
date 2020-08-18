package com.jf.entity;

import java.util.Date;

public class MallCategory {
    private Integer id;

    private String categoryName;

    private String productType1Ids;

    private String status;

    private String adPic;

    private String adLinkType;

    private String adLinkValue;

    private Integer seqNo;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getProductType1Ids() {
        return productType1Ids;
    }

    public void setProductType1Ids(String productType1Ids) {
        this.productType1Ids = productType1Ids == null ? null : productType1Ids.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic == null ? null : adPic.trim();
    }

    public String getAdLinkType() {
        return adLinkType;
    }

    public void setAdLinkType(String adLinkType) {
        this.adLinkType = adLinkType == null ? null : adLinkType.trim();
    }

    public String getAdLinkValue() {
        return adLinkValue;
    }

    public void setAdLinkValue(String adLinkValue) {
        this.adLinkValue = adLinkValue == null ? null : adLinkValue.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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