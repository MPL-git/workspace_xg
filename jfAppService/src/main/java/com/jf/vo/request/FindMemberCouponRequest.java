package com.jf.vo.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
public class FindMemberCouponRequest extends PageRequest{

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "券类型不能为空")
    @Range(min = 1,max = 2,message = "券类型不正确")
    private Integer type; //1平台优惠券 2其他优惠券
    @NotNull(message = "券状态不能为空")
    @Range(min = 1,max = 3,message = "券状态不正确")
    private Integer status; //1未过期 2已使用 3已过期

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
