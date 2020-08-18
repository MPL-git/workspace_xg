package com.jf.vo.response.integrallottery;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class LotterySettingsView {

    @NotNull(message = "数据格式异常")
    private Integer position;
    @NotBlank(message = "数据格式异常")
    private String type; //类型: 1.积分 2.优惠券 3.商品
    private boolean noStock;

    //积分
    private Integer integral;

    //商品券
    private Integer couponId;
    private String denomination;

    //商品
    private Integer productId;
    private String productName;
    private String productPic;

    public boolean isNoStock() {
        return noStock;
    }

    public void setNoStock(boolean noStock) {
        this.noStock = noStock;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }
}
