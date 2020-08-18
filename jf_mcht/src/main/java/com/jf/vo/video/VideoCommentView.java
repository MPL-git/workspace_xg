package com.jf.vo.video;

import com.jf.entity.VideoComment;

/**
 * @author luoyb
 * Created on 2019/9/19
 */
public class VideoCommentView extends VideoComment {

    private Integer replyId;

    private String replyContent;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
