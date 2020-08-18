package com.jf.vo.request.video;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
public class CollectVideoRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "视频ID不能为空")
    private Integer videoId;
    @NotNull(message = "操作类型不能为空")
    @Min(value = 1,message = "操作类型不正确")
    @Max(value = 2,message = "操作类型不正确")
    private Integer type; //2、取消收藏；1、收藏

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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
}
