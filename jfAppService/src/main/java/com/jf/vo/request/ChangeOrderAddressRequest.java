package com.jf.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/8/4
 */
public class ChangeOrderAddressRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "母订单ID不能为空")
    private Integer combineOrderId;
    @NotNull(message = "收货地址ID不能为空")
    private Integer addressId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCombineOrderId() {
        return combineOrderId;
    }

    public void setCombineOrderId(Integer combineOrderId) {
        this.combineOrderId = combineOrderId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
