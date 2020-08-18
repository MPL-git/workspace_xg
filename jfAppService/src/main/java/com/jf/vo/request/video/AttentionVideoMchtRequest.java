package com.jf.vo.request.video;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
public class AttentionVideoMchtRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "商家ID不能为空")
    private Integer mchtId;
    @NotNull(message = "操作类型不能为空")
    @Min(value = 1,message = "操作类型不正确")
    @Max(value = 2,message = "操作类型不正确")
    private Integer type; //2、取消关注；1、关注

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }
}
