package com.jf.vo.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/3/10
 */
public class FindMchtShopProductRequest extends PageRequest {

    @NotNull(message = "商家ID不能为空")
    private Integer mchtId;
    private Integer productType3; //三级类目ID
    @NotNull(message = "排序类型不能为空")
    @Range(min = 0,max = 3,message = "排序类型不正确")
    private Integer orderType; //0默认 1新品 2价格升序 3价格降序

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getProductType3() {
        return productType3;
    }

    public void setProductType3(Integer productType3) {
        this.productType3 = productType3;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
