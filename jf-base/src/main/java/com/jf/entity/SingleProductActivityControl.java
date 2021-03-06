package com.jf.entity;

import java.util.Date;

public class SingleProductActivityControl {
    private Integer id;

    private String type;

    private String productTypeIds;

    private String showToMchtIds;

    private String hideToMchtIds;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getProductTypeIds() {
        return productTypeIds;
    }

    public void setProductTypeIds(String productTypeIds) {
        this.productTypeIds = productTypeIds == null ? null : productTypeIds.trim();
    }

    public String getShowToMchtIds() {
        return showToMchtIds;
    }

    public void setShowToMchtIds(String showToMchtIds) {
        this.showToMchtIds = showToMchtIds == null ? null : showToMchtIds.trim();
    }

    public String getHideToMchtIds() {
        return hideToMchtIds;
    }

    public void setHideToMchtIds(String hideToMchtIds) {
        this.hideToMchtIds = hideToMchtIds == null ? null : hideToMchtIds.trim();
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