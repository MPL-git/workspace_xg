package com.jf.vo.response;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
public class VideoCommentView {

    private Integer id;
    private Integer videoId;
    private String memberAvatar;
    private String memberNick;
    private String content;
    private Integer likeCount;
    private String likeCountDisplayName;
    private Date createDate;
    private String createDateDisplayName;
    private Integer commentCount;
    private String commentCountDisplayName;

    private boolean hot;
    private boolean liked;

    private final List<VideoCommentReplyView> replyList = Lists.newArrayList();

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getCommentCountDisplayName() {
        return commentCountDisplayName;
    }

    public void setCommentCountDisplayName(String commentCountDisplayName) {
        this.commentCountDisplayName = commentCountDisplayName;
    }

    public String getLikeCountDisplayName() {
        return likeCountDisplayName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateDisplayName() {
        return createDateDisplayName;
    }

    public void setCreateDateDisplayName(String createDateDisplayName) {
        this.createDateDisplayName = createDateDisplayName;
    }

    public void setLikeCountDisplayName(String likeCountDisplayName) {
        this.likeCountDisplayName = likeCountDisplayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<VideoCommentReplyView> getReplyList() {
        return replyList;
    }
}
