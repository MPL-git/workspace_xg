package com.jf.vo;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2020/5/26
 */
public class PreContext {

    private BigDecimal memberAllowance = BigDecimal.ZERO; //用户剩余可用津贴
    private BigDecimal usedAllowance = BigDecimal.ZERO; //本单使用津贴

    /**
     * 获取剩余津贴
     */
    public BigDecimal getRestAllowance() {
        return memberAllowance.subtract(usedAllowance);
    }

    public BigDecimal getUsedAllowance() {
        return usedAllowance;
    }

    public void setUsedAllowance(BigDecimal usedAllowance) {
        this.usedAllowance = usedAllowance;
    }

    public BigDecimal getMemberAllowance() {
        return memberAllowance;
    }

    public void setMemberAllowance(BigDecimal memberAllowance) {
        this.memberAllowance = memberAllowance;
    }
}
