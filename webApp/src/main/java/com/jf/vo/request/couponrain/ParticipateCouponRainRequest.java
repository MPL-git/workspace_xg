package com.jf.vo.request.couponrain;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
public class ParticipateCouponRainRequest {

    @NotNull(message = "红包雨活动ID不能为空")
    private Integer couponRainId;

    public Integer getCouponRainId() {
        return couponRainId;
    }

    public void setCouponRainId(Integer couponRainId) {
        this.couponRainId = couponRainId;
    }
}
