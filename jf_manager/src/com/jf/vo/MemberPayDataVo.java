package com.jf.vo;

/**
 * 用户消费数据
 */
public class MemberPayDataVo {

    private int memberId;
    private int orderCount;
    private float totalPayAmount;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public float getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(float totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }
}
