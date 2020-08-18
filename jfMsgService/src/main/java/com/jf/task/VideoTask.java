package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.service.video.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author luoyb
 * Created on 2019/9/11
 */
@RegCondition
@Component
public class VideoTask {

    private static Logger logger = LoggerFactory.getLogger(VideoTask.class);

    @Autowired
    private VideoService videoService;

    /**
     * 小视频每日统计：
     * 1、昨日数据统计
     * 2、视频权重计算
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void statisticVideoWeight() {
        Date yesterday = DateUtil.addDay(new Date(), -1);

        logger.info("统计视频昨日数据：start");
        videoService.videoStatistic(yesterday);
        logger.info("统计视频昨日数据：end");

        logger.info("小视频权重计算：start");
        videoService.calculateVideoWeight(yesterday);
        logger.info("小视频权重计算：end");
    }

}
