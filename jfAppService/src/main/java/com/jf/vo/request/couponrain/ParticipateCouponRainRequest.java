package com.jf.vo.request.couponrain;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/26
 */
public class ParticipateCouponRainRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "红包雨活动ID不能为空")
    private Integer couponRainId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCouponRainId() {
        return couponRainId;
    }

    public void setCouponRainId(Integer couponRainId) {
        this.couponRainId = couponRainId;
    }
}
