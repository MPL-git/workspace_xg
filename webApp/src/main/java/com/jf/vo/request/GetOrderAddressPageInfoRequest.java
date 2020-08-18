package com.jf.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/8/4
 */
public class GetOrderAddressPageInfoRequest {

    @NotNull(message = "母订单ID不能为空")
    private Integer combineOrderId;

    public Integer getCombineOrderId() {
        return combineOrderId;
    }

    public void setCombineOrderId(Integer combineOrderId) {
        this.combineOrderId = combineOrderId;
    }
}
