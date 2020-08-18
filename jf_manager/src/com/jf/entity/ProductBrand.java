package com.jf.entity;

import java.util.Date;

public class ProductBrand {
    private Integer id;

    private String name;

    private String nameZh;

    private String nameEn;

    private String logo;

    private String productTypeGroup;

    private String tmkTypeGroup;

    private String tmkType;

    private String catalog;

    private String status;

    private String grade;

    private String letterIndex;

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

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh == null ? null : nameZh.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getProductTypeGroup() {
        return productTypeGroup;
    }

    public void setProductTypeGroup(String productTypeGroup) {
        this.productTypeGroup = productTypeGroup == null ? null : productTypeGroup.trim();
    }

    public String getTmkTypeGroup() {
        return tmkTypeGroup;
    }

    public void setTmkTypeGroup(String tmkTypeGroup) {
        this.tmkTypeGroup = tmkTypeGroup == null ? null : tmkTypeGroup.trim();
    }

    public String getTmkType() {
        return tmkType;
    }

    public void setTmkType(String tmkType) {
        this.tmkType = tmkType == null ? null : tmkType.trim();
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog == null ? null : catalog.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getLetterIndex() {
        return letterIndex;
    }

    public void setLetterIndex(String letterIndex) {
        this.letterIndex = letterIndex == null ? null : letterIndex.trim();
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