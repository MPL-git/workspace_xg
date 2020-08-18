package com.jf.vo.request;


import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/3/9
 */
public class FindMchtShopProductTypeRequest {

    @NotNull(message = "商家ID不能为空")
    private Integer mchtId;

    private Integer productType2;

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getProductType2() {
        return productType2;
    }

    public void setProductType2(Integer productType2) {
        this.productType2 = productType2;
    }
}
