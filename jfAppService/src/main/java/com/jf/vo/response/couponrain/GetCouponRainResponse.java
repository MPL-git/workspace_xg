package com.jf.vo.response.couponrain;


/**
 * @author luoyb
 * Created on 2019/9/26
 */
public class GetCouponRainResponse {

    private boolean rain;
    private CouponRainView couponRain;
    private long systemCurrentTime;
    private String couponRainPage;

    public String getCouponRainPage() {
        return couponRainPage;
    }

    public void setCouponRainPage(String couponRainPage) {
        this.couponRainPage = couponRainPage;
    }

    public long getSystemCurrentTime() {
        return systemCurrentTime;
    }

    public void setSystemCurrentTime(long systemCurrentTime) {
        this.systemCurrentTime = systemCurrentTime;
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }

    public CouponRainView getCouponRain() {
        return couponRain;
    }

    public void setCouponRain(CouponRainView couponRain) {
        this.couponRain = couponRain;
    }

}
