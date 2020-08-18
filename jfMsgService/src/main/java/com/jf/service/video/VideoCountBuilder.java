package com.jf.service.video;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.entity.VideoPvStatistical;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/11
 */
public class VideoCountBuilder {

    private final Map<Integer, VideoPvStatistical> videoStatisticMap = Maps.newHashMap();
    private final String targetDate;

    private VideoCountBuilder(String targetDate) {
        this.targetDate = targetDate;
    }

    public static VideoCountBuilder of(String targetDate) {
        return new VideoCountBuilder(targetDate);
    }

    public void setPlayCount(Integer videoId, int totalCount) {
        VideoPvStatistical video = getVideo(videoId);
        video.setPlayCount(totalCount);
        videoStatisticMap.put(videoId, video);
    }

    public void setCommentCount(Integer videoId, int totalCount) {
        VideoPvStatistical video = getVideo(videoId);
        video.setCommentCount(totalCount);
        videoStatisticMap.put(videoId, video);
    }

    public void setCollectCount(Integer videoId, int totalCount) {
        VideoPvStatistical video = getVideo(videoId);
        video.setCollectCount(totalCount);
        videoStatisticMap.put(videoId, video);
    }

    public void setLikeCount(Integer videoId, int totalCount) {
        VideoPvStatistical video = getVideo(videoId);
        video.setLikeCount(totalCount);
        videoStatisticMap.put(videoId, video);
    }

    private VideoPvStatistical getVideo(Integer videoId) {
        VideoPvStatistical video = videoStatisticMap.get(videoId);
        if (video == null) {
            video = new VideoPvStatistical();
            video.setVideoId(videoId);
            video.setStatisticalDate(targetDate);
            video.setCreateDate(new Date());
            video.setDelFlag("0");
        }
        return video;
    }

    public List<VideoPvStatistical> toList() {
        return Lists.newArrayList(videoStatisticMap.values());
    }

    public String getTargetDate() {
        return targetDate;
    }

    public Map<Integer, VideoPvStatistical> getVideoStatisticMap() {
        return videoStatisticMap;
    }

}
