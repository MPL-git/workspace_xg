package com.jf.service.async;

import com.jf.common.constant.PageConst;
import com.jf.common.utils.StringUtils;
import com.jf.entity.Video;
import com.jf.entity.VideoExample;
import com.jf.service.VideoService;
import com.jf.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/10/18
 */
@Service
public class VideoAsyncService {

    @Autowired
    private VideoService videoService;
    @Autowired
    private WeixinService weixinService;

    @Transactional
    public boolean regenerateXcxCode(Video video) {
        String scene = StringUtils.buildMsg(PageConst.VIDEO_DETAIL_PAGE_PARAMS_TEMPLATE, video.getId());
        String videoXcxCode = weixinService.createAppletUnlimitQrcode(PageConst.VIDEO_DETAIL_PAGE, scene);
        if (!org.springframework.util.StringUtils.hasText(videoXcxCode)) {
            return false;
        }
        video.setVideoXcxCode(videoXcxCode);
        videoService.updateByPrimaryKey(video);
        return true;
    }

    public List<Video> findAuditPassVideo() {
        VideoExample example = new VideoExample();
        example.createCriteria().andDelFlagEqualTo("0").andAuditStatusEqualTo("5");
        return videoService.selectByExample(example);
    }

    public Video getById(Integer videoId) {
        return videoService.selectByPrimaryKey(videoId);
    }
}
