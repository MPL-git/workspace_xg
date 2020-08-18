package com.jf.vo.request.couponrain;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/26
 */
public class SettleCouponRainRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "游戏记录ID不能为空")
    private Integer recordId;
    @NotNull(message = "本场游戏积分不能为空")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}
