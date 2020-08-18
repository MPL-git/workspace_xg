package com.jf.vo.request.video;

import com.jf.vo.request.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
public class FindVideoCommentRequest extends PageRequest {

    private Integer memberId;
    @NotNull(message = "视频ID不能为空")
    private Integer videoId;

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
}
