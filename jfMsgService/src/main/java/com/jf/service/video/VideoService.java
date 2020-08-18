package com.jf.service.video;

import com.jf.common.constant.Const;
import com.jf.common.utils.page.Pagers;
import com.jf.entity.Video;
import com.jf.entity.VideoPvStatistical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/11
 */
@Service
public class VideoService {

    @Autowired
    private VideoStatisticService videoStatisticService;

    /**
     * 视频各项数据的每日统计
     */
    public void videoStatistic(Date targetDay) {
        videoStatisticService.deleteTargetRecord(targetDay); //删除targetDay下的旧数据
        List<VideoPvStatistical> list = videoStatisticService.videoStatistic(targetDay);

        Pagers.executeAllPage(list, Const.PER_INSERT_SIZE, new Pagers.PageFunction<VideoPvStatistical>() {
            @Override
            public void execute(List<VideoPvStatistical> subList) {
                videoStatisticService.bulkInsert(subList);
            }
        });
    }

    /**
     * 视频权重计算
     */
    public void calculateVideoWeight(Date targetDay) {
        List<Video> list = videoStatisticService.videoWeightStatistic(targetDay);
        if (CollectionUtils.isEmpty(list)) return;

        final Date now = new Date();
        Pagers.executeAllPage(list, Const.PER_INSERT_SIZE, new Pagers.PageFunction<Video>() {
            @Override
            public void execute(List<Video> subList) {
                videoStatisticService.bulkUpdate(subList, now);
            }
        });
    }

}
