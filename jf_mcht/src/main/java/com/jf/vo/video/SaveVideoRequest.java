package com.jf.vo.video;

import com.jf.entity.VideoAuthorizedAccessories;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/18
 */
public class SaveVideoRequest {

    private Integer videoId;
    @NotBlank(message = "视频标题不能为空")
    private String title;
    @NotBlank(message = "视频描述不能为空")
    private String description;
    @NotBlank(message = "视频封面不能为空")
    private String videoCover;
    @NotBlank(message = "请先上传小视频")
    private String videoUrl;
    @NotBlank(message = "视频取帧不能为空")
    private String videoThumbnails; //取帧，用,分隔
    @NotNull(message = "视频时长不能为空")
    private Double videoTime; //单位：秒
    @NotNull(message = "视频大小不能为空")
    private Double videoSize; //单位：B
    private Integer videoWidth;
    private Integer videoHeight;
    @NotEmpty(message = "请选择至少一个商品")
    private List<Integer> productIds;
    @NotBlank(message = "请选择视频的来源")
    private String videoSource;


    private List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList; //视频附件：附件名,附件路径

    public List<VideoAuthorizedAccessories> getVideoAuthorizedAccessoriesList() {
        return videoAuthorizedAccessoriesList;
    }

    public void setVideoAuthorizedAccessoriesList(List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList) {
        this.videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesList;
    }


    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoThumbnails() {
        return videoThumbnails;
    }

    public void setVideoThumbnails(String videoThumbnails) {
        this.videoThumbnails = videoThumbnails;
    }

    public double getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(double videoTime) {
        this.videoTime = videoTime;
    }

    public double getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(double videoSize) {
        this.videoSize = videoSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

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
}

