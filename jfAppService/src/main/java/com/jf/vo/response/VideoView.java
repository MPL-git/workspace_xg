package com.jf.vo.response;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class VideoView {

    private Integer id;
    private String videoUrl;
    private String videoCover;
    private String title;
    private String shopLogo;
    private String shopName;
    private Integer likeCount;
    private String likeCountDisplayName;
    private boolean liked;

    public String getLikeCountDisplayName() {
        return likeCountDisplayName;
    }

    public void setLikeCountDisplayName(String likeCountDisplayName) {
        this.likeCountDisplayName = likeCountDisplayName;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
