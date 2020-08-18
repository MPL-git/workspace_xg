package com.jf.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/10/10
 */
public class CheckRecSvipPracticeRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
