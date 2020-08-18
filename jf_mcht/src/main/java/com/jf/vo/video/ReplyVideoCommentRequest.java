package com.jf.vo.video;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/19
 */
public class ReplyVideoCommentRequest {

    private Integer replyId;
    @NotNull(message = "评论ID不能为空")
    private Integer commentId;
    @NotEmpty(message = "评论内容不能为空")
    private String content;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
