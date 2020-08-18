package com.jf.vo.video;

import com.jf.vo.PageRequest;

/**
 * @author luoyb
 * Created on 2019/9/17
 */
public class FindVideoProductRequest extends PageRequest {

    private Integer videoId;

    private Integer searchProductBrandId;
    private Integer productType1;
    private Integer productType2;
    private Integer productType3;
    private String searchStatus;
    private String searchProductActivityStatus;
    private String searchKeywrodType;
    private String searchKeywrod;
    private Integer searchShopProductCustomTypeId;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getSearchShopProductCustomTypeId() {
        return searchShopProductCustomTypeId;
    }

    public void setSearchShopProductCustomTypeId(Integer searchShopProductCustomTypeId) {
        this.searchShopProductCustomTypeId = searchShopProductCustomTypeId;
    }

    public Integer getSearchProductBrandId() {
        return searchProductBrandId;
    }

    public void setSearchProductBrandId(Integer searchProductBrandId) {
        this.searchProductBrandId = searchProductBrandId;
    }

    public Integer getProductType1() {
        return productType1;
    }

    public void setProductType1(Integer productType1) {
        this.productType1 = productType1;
    }

    public Integer getProductType2() {
        return productType2;
    }

    public void setProductType2(Integer productType2) {
        this.productType2 = productType2;
    }

    public Integer getProductType3() {
        return productType3;
    }

    public void setProductType3(Integer productType3) {
        this.productType3 = productType3;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getSearchProductActivityStatus() {
        return searchProductActivityStatus;
    }

    public void setSearchProductActivityStatus(String searchProductActivityStatus) {
        this.searchProductActivityStatus = searchProductActivityStatus;
    }

    public String getSearchKeywrodType() {
        return searchKeywrodType;
    }

    public void setSearchKeywrodType(String searchKeywrodType) {
        this.searchKeywrodType = searchKeywrodType;
    }

    public String getSearchKeywrod() {
        return searchKeywrod;
    }

    public void setSearchKeywrod(String searchKeywrod) {
        this.searchKeywrod = searchKeywrod;
    }
}
