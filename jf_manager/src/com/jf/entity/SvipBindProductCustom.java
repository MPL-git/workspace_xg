package com.jf.entity;

import java.math.BigDecimal;

/**
 * @Description
 * @Author chengh
 * @Date 2020/7/10 14:25
 */
public class SvipBindProductCustom extends SvipBindProduct{
    private String pic;
    private String code;
    private String productTypeName;
    private String productBrandName;
    private String name;
    private String artNo;
    private BigDecimal tagPriceMin;
    private BigDecimal mallPriceMin;
    private BigDecimal salePriceMin;
    private BigDecimal tagPriceMax;
    private BigDecimal mallPriceMax;
    private BigDecimal salePriceMax;
    private BigDecimal svipDiscount;
    private String companyName;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public BigDecimal getTagPriceMin() {
        return tagPriceMin;
    }

    public void setTagPriceMin(BigDecimal tagPriceMin) {
        this.tagPriceMin = tagPriceMin;
    }

    public BigDecimal getMallPriceMin() {
        return mallPriceMin;
    }

    public void setMallPriceMin(BigDecimal mallPriceMin) {
        this.mallPriceMin = mallPriceMin;
    }

    public BigDecimal getSalePriceMin() {
        return salePriceMin;
    }

    public void setSalePriceMin(BigDecimal salePriceMin) {
        this.salePriceMin = salePriceMin;
    }

    public BigDecimal getTagPriceMax() {
        return tagPriceMax;
    }

    public void setTagPriceMax(BigDecimal tagPriceMax) {
        this.tagPriceMax = tagPriceMax;
    }

    public BigDecimal getMallPriceMax() {
        return mallPriceMax;
    }

    public void setMallPriceMax(BigDecimal mallPriceMax) {
        this.mallPriceMax = mallPriceMax;
    }

    public BigDecimal getSalePriceMax() {
        return salePriceMax;
    }

    public void setSalePriceMax(BigDecimal salePriceMax) {
        this.salePriceMax = salePriceMax;
    }

    public BigDecimal getSvipDiscount() {
        return svipDiscount;
    }

    public void setSvipDiscount(BigDecimal svipDiscount) {
        this.svipDiscount = svipDiscount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
