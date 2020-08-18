package com.jf.entity.dto;

import com.jf.entity.CouponRain;

/**
 * @author luoyb
 * Created on 2020/7/4
 */
public class CouponRainDTO extends CouponRain {

    private Integer joinCount;
    private Integer shareCount;

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }
}
