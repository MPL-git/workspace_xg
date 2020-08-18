package com.jf.vo.response;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
public class VideoShareView {

    private Integer videoId;
    private String videoTitle;
    private String description;
    private String webpageUrl;
    private String videoFirstFrame; //小视频第一帧
    private String videoCover;
    private String shopName;
    private String shopLogo;
    private Integer likeCount;
    private String likeCountDisplayName;
    private Integer commontCount;
    private String commentCountDisplayName;

    private String smallProceduresCode; //小程序二维码

    private String originalId; //小程序原始ID
    private String xcxShareType; //分享小程序的版本（0-正式，1-开发，2-体验）
    private String wxPath; //分享小程序页面的具体路径

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public Integer getCommontCount() {
        return commontCount;
    }

    public void setCommontCount(Integer commontCount) {
        this.commontCount = commontCount;
    }

    public String getCommentCountDisplayName() {
        return commentCountDisplayName;
    }

    public void setCommentCountDisplayName(String commentCountDisplayName) {
        this.commentCountDisplayName = commentCountDisplayName;
    }

    public String getWebpageUrl() {
        return webpageUrl;
    }

    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getXcxShareType() {
        return xcxShareType;
    }

    public void setXcxShareType(String xcxShareType) {
        this.xcxShareType = xcxShareType;
    }

    public String getWxPath() {
        return wxPath;
    }

    public void setWxPath(String wxPath) {
        this.wxPath = wxPath;
    }

    public String getLikeCountDisplayName() {
        return likeCountDisplayName;
    }

    public void setLikeCountDisplayName(String likeCountDisplayName) {
        this.likeCountDisplayName = likeCountDisplayName;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoFirstFrame() {
        return videoFirstFrame;
    }

    public void setVideoFirstFrame(String videoFirstFrame) {
        this.videoFirstFrame = videoFirstFrame;
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

    public String getSmallProceduresCode() {
        return smallProceduresCode;
    }

    public void setSmallProceduresCode(String smallProceduresCode) {
        this.smallProceduresCode = smallProceduresCode;
    }
}
