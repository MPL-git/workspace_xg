package com.jf.vo.response.couponrain;


/**
 * @author luoyb
 * Created on 2019/9/26
 */
public class CouponRainView {

    private Integer id;
    private String pic;
    private Long startTime; //时间戳到毫秒
    private Long endTime;
    private Integer gameSeconds; //单位秒
    private String bombPercent; //0-1范围，如0.35
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getGameSeconds() {
        return gameSeconds;
    }

    public void setGameSeconds(Integer gameSeconds) {
        this.gameSeconds = gameSeconds;
    }

    public String getBombPercent() {
        return bombPercent;
    }

    public void setBombPercent(String bombPercent) {
        this.bombPercent = bombPercent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
