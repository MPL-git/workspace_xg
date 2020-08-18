package com.jf.vo.response.couponrain;


/**
 * @author luoyb
 * Created on 2019/12/12
 */
public class GetCouponRainResponse {

    private boolean rain;
    private CouponRainView couponRain;
    private long systemCurrentTime;
    private String rainType; //P.普通红包 S.圣诞红包
    private String noRainTip; //rain为false时的提示

    public String getNoRainTip() {
        return noRainTip;
    }

    public void setNoRainTip(String noRainTip) {
        this.noRainTip = noRainTip;
    }

    public String getRainType() {
        return rainType;
    }

    public void setRainType(String rainType) {
        this.rainType = rainType;
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
