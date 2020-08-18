package com.jf.vo.request.allowance;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/5/28
 */
public class ExchangeAllowanceRequest {

    private Integer allowanceInfoId;
    @NotNull(message = "购物津贴设置ID不能为空")
    private Integer id;

    public Integer getAllowanceInfoId() {
        return allowanceInfoId;
    }

    public void setAllowanceInfoId(Integer allowanceInfoId) {
        this.allowanceInfoId = allowanceInfoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
