package com.jf.vo.video;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/18
 */
public class VideoSplitInfo {

    private String videoUrl; //相对地址
    private String fullVideoUrl;
    private long videoTime; //时长，单位：秒
    private int frameLength;
    private final List<String> framePicList = Lists.newArrayList(); //截帧图片地址
    private String videoThumbnails;
    private String firstFrame; //第一帧
    private Integer videoWidth;
    private Integer videoHeight;

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

    public String getFullVideoUrl() {
        return fullVideoUrl;
    }

    public void setFullVideoUrl(String fullVideoUrl) {
        this.fullVideoUrl = fullVideoUrl;
    }

    public String getFirstFrame() {
        return firstFrame;
    }

    public void setFirstFrame(String firstFrame) {
        this.firstFrame = firstFrame;
    }

    public String getVideoThumbnails() {
        return videoThumbnails;
    }

    public void setVideoThumbnails(String videoThumbnails) {
        this.videoThumbnails = videoThumbnails;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getFrameLength() {
        return frameLength;
    }

    public void setFrameLength(int frameLength) {
        this.frameLength = frameLength;
    }

    public long getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(long videoTime) {
        this.videoTime = videoTime;
    }

    public List<String> getFramePicList() {
        return framePicList;
    }
}
