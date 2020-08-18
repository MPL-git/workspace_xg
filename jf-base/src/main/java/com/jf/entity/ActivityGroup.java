package com.jf.entity;

import java.util.Date;

public class ActivityGroup {
    private Integer id;

    private String groupName;

    private String signPic;

    private String productWkPic;

    private String productWkPicM;

    private String priceWkPic;

    private String priceFontColor;

    private Integer productWkLinkId;

    private String productWkPosition;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getSignPic() {
        return signPic;
    }

    public void setSignPic(String signPic) {
        this.signPic = signPic == null ? null : signPic.trim();
    }

    public String getProductWkPic() {
        return productWkPic;
    }

    public void setProductWkPic(String productWkPic) {
        this.productWkPic = productWkPic == null ? null : productWkPic.trim();
    }

    public String getProductWkPicM() {
        return productWkPicM;
    }

    public void setProductWkPicM(String productWkPicM) {
        this.productWkPicM = productWkPicM == null ? null : productWkPicM.trim();
    }

    public String getPriceWkPic() {
        return priceWkPic;
    }

    public void setPriceWkPic(String priceWkPic) {
        this.priceWkPic = priceWkPic == null ? null : priceWkPic.trim();
    }

    public String getPriceFontColor() {
        return priceFontColor;
    }

    public void setPriceFontColor(String priceFontColor) {
        this.priceFontColor = priceFontColor == null ? null : priceFontColor.trim();
    }

    public Integer getProductWkLinkId() {
        return productWkLinkId;
    }

    public void setProductWkLinkId(Integer productWkLinkId) {
        this.productWkLinkId = productWkLinkId;
    }

    public String getProductWkPosition() {
        return productWkPosition;
    }

    public void setProductWkPosition(String productWkPosition) {
        this.productWkPosition = productWkPosition == null ? null : productWkPosition.trim();
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