package com.jf.entity;

import java.util.Date;

public class AdInfo {
    private Integer id;

    private Integer sourceProductTypeId;

    private String type;

    private String catalog;

    private String position;

    private String pic;

    private String iosPic;

    private String androidPic;

    private String linkType;

    private Integer linkId;

    private String linkUrl;

    private String showChannel;

    private String status;

    private Date autoUpDate;

    private Date autoDownDate;

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

    public Integer getSourceProductTypeId() {
        return sourceProductTypeId;
    }

    public void setSourceProductTypeId(Integer sourceProductTypeId) {
        this.sourceProductTypeId = sourceProductTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog == null ? null : catalog.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getIosPic() {
        return iosPic;
    }

    public void setIosPic(String iosPic) {
        this.iosPic = iosPic == null ? null : iosPic.trim();
    }

    public String getAndroidPic() {
        return androidPic;
    }

    public void setAndroidPic(String androidPic) {
        this.androidPic = androidPic == null ? null : androidPic.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getShowChannel() {
        return showChannel;
    }

    public void setShowChannel(String showChannel) {
        this.showChannel = showChannel == null ? null : showChannel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getAutoUpDate() {
        return autoUpDate;
    }

    public void setAutoUpDate(Date autoUpDate) {
        this.autoUpDate = autoUpDate;
    }

    public Date getAutoDownDate() {
        return autoDownDate;
    }

    public void setAutoDownDate(Date autoDownDate) {
        this.autoDownDate = autoDownDate;
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