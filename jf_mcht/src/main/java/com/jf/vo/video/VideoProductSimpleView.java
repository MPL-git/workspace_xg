package com.jf.vo.video;

/**
 * @author luoyb
 * Created on 2019/9/16
 */
public class VideoProductSimpleView {

    private Integer productId;
    private String productCode;
    private String name;
    private String mainImg;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
}
