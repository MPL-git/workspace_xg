package com.jf.vo.response;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2020/3/17
 */
public class CouponSimpleView {

    private Integer id;
    private String couponTag; //店铺券 商品券 新人券
    private String preferentialType; // 1固定面额 2 折扣
    private BigDecimal denomination; // 面额
    private BigDecimal discount; //折扣（如：5 表示5折）
    private String useDesc; //使用消费描述
    private BigDecimal minimum; //使用消费金额
    private String limitDesc; //使用限制说明

    private String expiryDesc; //有效时间描述
//    private String expireType; //有效期类型（1绝对时间 2相对时间）
//    private Long expiryBeginDate; //有效起始时间
//    private Long expiryEndDate; //有效结束时间
//    private Integer expiryDay; //有效时长（天）

    private Integer status; //1可领取 2已领取 3已领完

    public String getExpiryDesc() {
        return expiryDesc;
    }

    public void setExpiryDesc(String expiryDesc) {
        this.expiryDesc = expiryDesc;
    }

    public String getPreferentialType() {
        return preferentialType;
    }

    public void setPreferentialType(String preferentialType) {
        this.preferentialType = preferentialType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getLimitDesc() {
        return limitDesc;
    }

    public void setLimitDesc(String limitDesc) {
        this.limitDesc = limitDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponTag() {
        return couponTag;
    }

    public void setCouponTag(String couponTag) {
        this.couponTag = couponTag;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public String getUseDesc() {
        return useDesc;
    }

    public void setUseDesc(String useDesc) {
        this.useDesc = useDesc;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
