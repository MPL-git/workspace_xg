package com.jf.vo.request.video;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
public class VideoUserActionView {

    private Integer videoId;

    private boolean attention; //已关注
    private boolean collect; //已收藏
    private boolean like; //已点赞

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean attention) {
        this.attention = attention;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
