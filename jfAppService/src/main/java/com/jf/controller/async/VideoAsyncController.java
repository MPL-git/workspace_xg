package com.jf.controller.async;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.Video;
import com.jf.service.async.VideoAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/11/11
 */
@Controller
public class VideoAsyncController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(VideoAsyncController.class);

    @Autowired
    private VideoAsyncService videoAsyncService;


    @ResponseBody
    @RequestMapping(value = "/async/video/widthAndHeight/init",method = RequestMethod.PUT)
    public ResponseMsg initVideoWidthAndHeight() {
        List<Video> videoList = videoAsyncService.findNeedInitWidthAndHeightVideo();
        if (CollectionUtils.isEmpty(videoList)) {
            return ResponseMsg.success("不存在宽高未取的小视频");
        }
        int failCount = 0;
        for (Video video : videoList) {
            try {
                boolean success = videoAsyncService.initVideoWidthAndHeight(video);
                if (!success) {
                    failCount++;
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                failCount++;
            }
        }
        return ResponseMsg.success(StringUtil.buildMsg("本次宽高初始化成功的小视频个数：{}，失败个数：{}", videoList.size() - failCount, failCount));
    }

}
