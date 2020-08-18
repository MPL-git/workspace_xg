package com.jf.entity;

import java.math.BigDecimal;

public class MemberAllowanceCustom extends MemberAllowance{
    private BigDecimal sumAllowanceAmount;
    private BigDecimal sumUsageAmount;

    public BigDecimal getSumAllowanceAmount() {
        return sumAllowanceAmount;
    }

    public void setSumAllowanceAmount(BigDecimal sumAllowanceAmount) {
        this.sumAllowanceAmount = sumAllowanceAmount;
    }

    public BigDecimal getSumUsageAmount() {
        return sumUsageAmount;
    }

    public void setSumUsageAmount(BigDecimal sumUsageAmount) {
        this.sumUsageAmount = sumUsageAmount;
    }
}
