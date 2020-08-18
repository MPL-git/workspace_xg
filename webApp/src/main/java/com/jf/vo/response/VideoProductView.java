package com.jf.vo.response;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class VideoProductView {

    private Integer productId;
    private String name;
    private String mainImg;
    private BigDecimal mallPrice;
    private String status; //0、已下架 1、已上架

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public BigDecimal getMallPrice() {
        return mallPrice;
    }

    public void setMallPrice(BigDecimal mallPrice) {
        this.mallPrice = mallPrice;
    }
}
