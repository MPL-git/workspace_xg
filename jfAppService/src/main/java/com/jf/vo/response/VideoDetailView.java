package com.jf.vo.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class VideoDetailView {

    private Integer id; //视频ID
    private String title;
    private String description;
    private Integer mchtId;
    private String shopName;
    private String shopLogo;
    private String videoUrl;
    private String videoCover;
    private String videoFirstFrame; //小视频第一帧
    private Integer videoTime; //单位：秒
    private Integer videoSize; //单位：KB
    private Integer videoWidth;
    private Integer videoHeight;

    private int productCount;
    private int commentCount;
    private int likeCount;
    private String commentCountDisplayName = "0";
    private String likeCountDisplayName = "0";

    private boolean attention; //已关注
    private boolean collect; //已收藏
    private boolean like; //已点赞

    private final List<VideoProductView> productList = Lists.newArrayList();

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getVideoFirstFrame() {
        return videoFirstFrame;
    }

    public void setVideoFirstFrame(String videoFirstFrame) {
        this.videoFirstFrame = videoFirstFrame;
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

    public void setLikeCountDisplayName(String likeCountDisplayName) {
        this.likeCountDisplayName = likeCountDisplayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public Integer getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Integer videoTime) {
        this.videoTime = videoTime;
    }

    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public List<VideoProductView> getProductList() {
        return productList;
    }
}
