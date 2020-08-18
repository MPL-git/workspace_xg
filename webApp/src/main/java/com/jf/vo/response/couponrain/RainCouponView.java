package com.jf.vo.response.couponrain;


/**
 * @author luoyb
 * Created on 2019/12/12
 */
public class RainCouponView {

    private int type; //1、积分 2、商品券 3、其他券
    private int amountType; //1、积分 2、面额 3、折扣

    private Integer integral;
    private String denomination; //面额
    private String discount; //折扣

    private String displayName;

    public int getAmountType() {
        return amountType;
    }

    public void setAmountType(int amountType) {
        this.amountType = amountType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
