package com.jf.vo.request.video;

import com.jf.vo.request.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
public class FindTypeRelativeVideoRequest extends PageRequest {

    @NotNull(message = "初始小视频ID不能为空")
    private Integer videoId;

    private String videoIds; //已查询过的小视频IDs，用,隔开

    private Integer memberId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(String videoIds) {
        this.videoIds = videoIds;
    }
}
