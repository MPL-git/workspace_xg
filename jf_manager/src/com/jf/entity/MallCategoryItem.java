package com.jf.entity;

import java.util.Date;

public class MallCategoryItem {
    private Integer id;

    private Integer mallCategoryModuleId;

    private String status;

    private String itemName;

    private String itemPic;

    private String itemLinkType;

    private String itemLinkValue;

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

    public Integer getMallCategoryModuleId() {
        return mallCategoryModuleId;
    }

    public void setMallCategoryModuleId(Integer mallCategoryModuleId) {
        this.mallCategoryModuleId = mallCategoryModuleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic == null ? null : itemPic.trim();
    }

    public String getItemLinkType() {
        return itemLinkType;
    }

    public void setItemLinkType(String itemLinkType) {
        this.itemLinkType = itemLinkType == null ? null : itemLinkType.trim();
    }

    public String getItemLinkValue() {
        return itemLinkValue;
    }

    public void setItemLinkValue(String itemLinkValue) {
        this.itemLinkValue = itemLinkValue == null ? null : itemLinkValue.trim();
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