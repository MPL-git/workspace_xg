package com.jf.vo.request.video;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
public class LogVideoPlayRequest {

    @NotNull(message = "视频ID不能为空")
    private Integer videoId;

    private Integer memberId; //用户ID
    private String deviceId; //设备号

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
