package com.jf.vo.response;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
public class MemberCouponCountView {

    private Integer type; //1平台优惠券 2其他优惠券
    private String name;
    private Integer count;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
