package com.jf.controller;

import com.jf.common.utils.DateUtil;
import com.jf.service.video.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author luoyb
 * Created on 2019/9/12
 */
@Controller
public class VideoController {

    private static Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    /**
     * 手动执行统计，用于测试，如：
     * POST：XXXX/video/manual/statistic?targetDay=2019-09-12
     */
    @ResponseBody
    @RequestMapping(value = "/video/manual/statistic", method = RequestMethod.POST)
    public String videoStatistic(HttpServletRequest request) {
        Date targetDay;
        try {
            String targetDayStr = request.getParameter("targetDay");
            targetDay = DateUtil.getDate(targetDayStr,"yyyy-MM-dd");
        } catch (Exception e) {
            return "日期格式不正确";
        }

        try {
            videoService.videoStatistic(targetDay);
            videoService.calculateVideoWeight(targetDay);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "统计失败";
        }
        return "统计成功";
    }

}
