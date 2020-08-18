package com.jf.vo.request.allowance;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class GetAllowancePopInfoRequest {

    @NotNull(message = "活动会场ID不能为空")
    private Integer activityAreaId;

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
    }
}
