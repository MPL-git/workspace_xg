package com.jf.controller.async;

import com.jf.common.utils.StringUtils;
import com.jf.entity.Video;
import com.jf.service.async.VideoAsyncService;
import com.jf.vo.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author luoyb
 * Created on 2019/10/18
 */
@Controller
public class VideoAsyncController {

    private static Logger logger = LoggerFactory.getLogger(VideoAsyncController.class);

    @Autowired
    private VideoAsyncService videoAsyncService;

    /**
     * 使用说明：
     * 本方法用于小视频脏数据处理：重新生成小视频的小程序码
     */
    @ResponseBody
    @RequestMapping(value = "/async/video/xcxCode/regenerate.shtml", method = RequestMethod.PUT)
    public ResponseMsg regenerateVideoXcxCode(HttpServletRequest request) {
        String videoId = request.getParameter("videoId");
        if (!StringUtils.isBlank(videoId)) {
            Video video = videoAsyncService.getById(Integer.parseInt(videoId));
            if (video == null) {
                return ResponseMsg.error("找不到该小视频");
            }
            boolean success = videoAsyncService.regenerateXcxCode(video);
            return ResponseMsg.success(StringUtils.buildMsg("本次重新生成个数：{}，失败个数：{}", success ? 1 : 0, success ? 0 : 1));
        }

        //批量重新生成
        List<Video> videoList = videoAsyncService.findAuditPassVideo();
        if (CollectionUtils.isEmpty(videoList)) {
            return ResponseMsg.success("无需要重新生成小程序码的小视频");
        }
        int failCount = 0;
        for (Video video : videoList) {
            try {
                boolean success = videoAsyncService.regenerateXcxCode(video);
                if (!success) {
                    failCount++;
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                failCount++;
            }
        }
        return ResponseMsg.success(StringUtils.buildMsg("本次重新生成个数：{}，失败个数：{}", videoList.size() - failCount, failCount));
    }

}
