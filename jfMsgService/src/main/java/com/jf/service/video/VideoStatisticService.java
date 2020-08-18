package com.jf.service.video;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.dao.VideoCustomMapper;
import com.jf.dao.VideoMapper;
import com.jf.dao.VideoPvStatisticalMapper;
import com.jf.entity.Video;
import com.jf.entity.VideoPvStatistical;
import com.jf.entity.VideoPvStatisticalExample;
import com.jf.entity.dto.SimpleCountDTO;
import com.jf.entity.dto.Top10VideoLast7DayCount;
import com.jf.entity.dto.VideoWeightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/11
 */
@Service
public class VideoStatisticService {

    @Autowired
    private VideoCustomMapper videoCustomMapper;
    @Autowired
    private VideoPvStatisticalMapper videoPvStatisticalMapper;
    @Autowired
    private VideoMapper videoMapper;

    /**
     * 视频每日统计
     */
    public List<VideoPvStatistical> videoStatistic(Date targetDay) {
        VideoCountBuilder builder = VideoCountBuilder.of(DateUtil.getStandardDate(targetDay));

        Date dateBegin = DateUtil.getDateAfterAndBeginTime(targetDay, 0);
        Date dateEnd = DateUtil.getDateAfterAndEndTime(targetDay, 0);
        fillWithPlayCount(dateBegin, dateEnd, builder);
        fillWithLikeCount(dateBegin, dateEnd, builder);
        fillWithCommentCount(dateBegin, dateEnd, builder);
        fillWithCollectCount(dateBegin, dateEnd, builder);
        return builder.toList();
    }

    @Transactional
    public void deleteTargetRecord(Date targetDay) {
        VideoPvStatisticalExample example = new VideoPvStatisticalExample();
        example.createCriteria().andStatisticalDateEqualTo(DateUtil.getStandardDate(targetDay));
        videoPvStatisticalMapper.deleteByExample(example);
    }

    @Transactional
    public void bulkInsert(List<VideoPvStatistical> list) {
        for (VideoPvStatistical videoPvStatistical : list) {
            videoPvStatisticalMapper.insert(videoPvStatistical);
        }
    }

    /**
     * 权重统计
     */
    public List<Video> videoWeightStatistic(Date targetDay) {
        String endDateStr = DateUtil.getStandardDate(targetDay);
        String beginDateStr = DateUtil.getStandardDate(DateUtil.addDay(targetDay, -6));

        List<Integer> top10VideoIds = videoCustomMapper.findPlayTop10Video(beginDateStr, endDateStr);
        if (CollectionUtils.isEmpty(top10VideoIds)) {
            return Collections.emptyList();
        }

        Top10VideoLast7DayCount top10VideoLast7DayCount = videoCustomMapper.getTop10VideoLast7DayCount(top10VideoIds, beginDateStr, endDateStr);
        top10VideoLast7DayCount.setDenominator(top10VideoIds.size());

        //统计 播放、点赞、评论、收藏权重
        List<Video> videoList = buildUpdateVideo(top10VideoLast7DayCount, endDateStr, beginDateStr);
        //统计 季节、人工 权重
        fillWithSeasonAndManualWeight(videoList);
        return videoList;
    }

    @Transactional
    public void bulkUpdate(List<Video> list, Date weightTime) {
        for (Video video : list) {
            video.setWeightTime(weightTime);
            int totalWeight = NumberUtil.addAll(video.getPlayWeight(), video.getLikeWeicht(), video.getCommentWeight(), video.getCollectWeight()
                    , video.getSeasonWeight(), video.getManualWeicht());
            video.setTotalWeicht(totalWeight);
            videoMapper.updateByPrimaryKeySelective(video);
        }
    }

    private void fillWithPlayCount(Date dateBegin, Date dateEnd, VideoCountBuilder builder) {
        List<SimpleCountDTO> list = videoCustomMapper.statisticDailyPlayCount(dateBegin, dateEnd);
        if (CollectionUtils.isEmpty(list)) return;

        for (SimpleCountDTO dto : list) {
            builder.setPlayCount(dto.getId(), dto.getTotalCount());
        }
    }

    private void fillWithLikeCount(Date dateBegin, Date dateEnd, VideoCountBuilder builder) {
        List<SimpleCountDTO> list = videoCustomMapper.statisticDailyLikeCount(dateBegin, dateEnd);
        if (CollectionUtils.isEmpty(list)) return;

        for (SimpleCountDTO dto : list) {
            builder.setLikeCount(dto.getId(), dto.getTotalCount());
        }
    }

    private void fillWithCommentCount(Date dateBegin, Date dateEnd, VideoCountBuilder builder) {
        List<SimpleCountDTO> list = videoCustomMapper.statisticDailyCommentCount(dateBegin, dateEnd);
        if (CollectionUtils.isEmpty(list)) return;

        for (SimpleCountDTO dto : list) {
            builder.setCommentCount(dto.getId(), dto.getTotalCount());
        }
    }

    private void fillWithCollectCount(Date dateBegin, Date dateEnd, VideoCountBuilder builder) {
        List<SimpleCountDTO> list = videoCustomMapper.statisticDailyCollectCount(dateBegin, dateEnd);
        if (CollectionUtils.isEmpty(list)) return;

        for (SimpleCountDTO dto : list) {
            builder.setCollectCount(dto.getId(), dto.getTotalCount());
        }
    }

    private void fillWithSeasonAndManualWeight(List<Video> videoList) {
        if (CollectionUtils.isEmpty(videoList)) return;

        Map<Integer, VideoWeightDTO> videoSeasonAndManualWeightMap = buildVideoSeasonAndManualWeightMap();
        for (Video video : videoList) {
            VideoWeightDTO videoWeightDTO = videoSeasonAndManualWeightMap.get(video.getId());
            if (videoWeightDTO == null) continue;

            video.setSeasonWeight(videoWeightDTO.getSeasonWeight());
            video.setManualWeicht(videoWeightDTO.getManualWeicht());
        }
    }

    private Map<Integer, VideoWeightDTO> buildVideoSeasonAndManualWeightMap() {
        List<VideoWeightDTO> list = videoCustomMapper.findVideoSeasonAndManualWeight();

        if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();
        Map<Integer, VideoWeightDTO> map = Maps.newHashMap();
        for (VideoWeightDTO dto : list) {
            map.put(dto.getId(), dto);
        }
        return map;
    }

    private List<Video> buildUpdateVideo(Top10VideoLast7DayCount top10VideoLast7DayCount, String endDateStr, String beginDateStr) {
        List<VideoPvStatistical> videoPvList = videoCustomMapper.countVideoLast7Day(beginDateStr, endDateStr);
        int playAvg = top10VideoLast7DayCount.getPlayAvg();
        int likeAvg = top10VideoLast7DayCount.getLikeAvg();
        int commentAvg = top10VideoLast7DayCount.getCommentAvg();
        int collectAvg = top10VideoLast7DayCount.getCollectAvg();
        List<Video> videoList = Lists.newArrayList();
        for (VideoPvStatistical pvStatistical : videoPvList) {
            Video video = new Video();
            video.setId(pvStatistical.getVideoId());
            video.setPlayWeight(calculateWeight(pvStatistical.getPlayCount(), playAvg));
            video.setLikeWeicht(calculateWeight(pvStatistical.getLikeCount(), likeAvg));
            video.setCommentWeight(calculateWeight(pvStatistical.getCommentCount(), commentAvg));
            video.setCollectWeight(calculateWeight(pvStatistical.getCollectCount(), collectAvg));
            videoList.add(video);
        }
        return videoList;
    }

    private int calculateWeight(Integer count, int avg) {
        if (count == null) return 0;
        int result = count * 100 / avg;
        return result > 100 ? 100 : result;
    }

}
