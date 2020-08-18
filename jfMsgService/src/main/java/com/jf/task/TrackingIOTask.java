package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.enumerate.RuntimeEnv;
import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.service.TrackingIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/11/15
 */
@RegCondition(level = RuntimeEnv.PROD)
@Component
public class TrackingIOTask {

    private static Logger logger = LoggerFactory.getLogger(TrackingIOTask.class);
    private static final Integer PER_PUSH_SIZE = 5000;

    @Autowired
    private TrackingIOService trackingIOService;

    /**
     * 今日头条加购用户上报热云：
     * 1、今日头条用户 且 有加购行为（添加购物车）的用户
     * 2、每个用户每日仅推送一次
     * <p>
     * ps：替换{@link CombineOrderExtendTask#commitPayInfoToThackingio()} 推送内容
     */
    @Scheduled(cron = "0 3/30 * * * ?")
    public void pushDailyAddShoppingCartToTrackingIO() {
        logger.info("任务【今日头条加购用户上报热云】开始：");
        try {
            Date now = new Date();
            //往前推3分钟，修正统计数据
            Date searchBeginDate = DateUtil.addMinute(now, -33);
            Date searchEndDate = DateUtil.addMinute(now, -3);
            String targetDate = DateUtil.getStandardDate(DateUtil.addMinute(now, -10)); //防止昨日11点33分后的数据统计到今日

            //IOS
            tkioIOSOrAndroid(targetDate, searchBeginDate, searchEndDate, Const.REG_CLIENT_IOS);
            //Android
            tkioIOSOrAndroid(targetDate, searchBeginDate, searchEndDate, Const.REG_CLIENT_ANDROID);

        } finally {
            logger.info("任务【今日头条加购用户上报热云】结束！");
        }
    }

    public void tkioIOSOrAndroid(String targetDate, Date searchBeginDate, Date searchEndDate, String regClient) {
        logger.info("{}任务【今日头条加购用户上报热云】开始：=============={}==============", regClient, regClient);
        int restCount = trackingIOService.countLatestAddShoppingCartHeadlineUser(targetDate, searchBeginDate, searchEndDate, regClient);
        if (restCount <= 0) {
            logger.info("{}无加购用户，退出任务。", regClient);
            return;
        }
        int successCount = 0;
        List<Integer> newMemberIds;
        do {
            restCount -= PER_PUSH_SIZE;
            newMemberIds = trackingIOService.findLatestAddShoppingCartHeadlineUser(targetDate, searchBeginDate, searchEndDate, PER_PUSH_SIZE, regClient);
            for (Integer memberId : newMemberIds) {
                boolean success = trackingIOService.pushAddShoppingCartHeadlineUser(targetDate, memberId, regClient);
                if (success) {
                    successCount++;
                }
            }
        } while (restCount > 0);
        logger.info("{}成功推送：{}条", regClient, successCount);
        logger.info("{}任务【今日头条加购用户上报热云】结束：=============={}==============", regClient, regClient);
    }

}
