package com.jf.vo.request.video;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
public class CommentVideoRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "视频ID不能为空")
    private Integer videoId;
    @NotBlank(message = "评论内容不能为空")
    @Length(max = 200,message = "评论字数不能超过200个字")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
