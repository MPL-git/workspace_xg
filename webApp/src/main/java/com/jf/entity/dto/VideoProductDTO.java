package com.jf.entity.dto;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
public class VideoProductDTO {

    private Integer videoId;
    private Integer productId;
    private String name;
    private String mainImg;
    private BigDecimal mallPrice;

    private String mchtStatus; //0 未启用（默认状态） 1 正常 2 业务暂停 3 关闭
    private String mchtProductBrandStatus; //0申请中 1正常 2暂停 3 关闭 4 驳回申请

    public boolean isOnline() {
        return "1".equals(mchtStatus) && "1".equals(mchtProductBrandStatus);
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getMchtStatus() {
        return mchtStatus;
    }

    public void setMchtStatus(String mchtStatus) {
        this.mchtStatus = mchtStatus;
    }

    public String getMchtProductBrandStatus() {
        return mchtProductBrandStatus;
    }

    public void setMchtProductBrandStatus(String mchtProductBrandStatus) {
        this.mchtProductBrandStatus = mchtProductBrandStatus;
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
