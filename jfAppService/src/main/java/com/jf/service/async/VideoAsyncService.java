package com.jf.service.async;

import com.jf.common.constant.Const;
import com.jf.common.utils.FileUtil;
import com.jf.entity.Video;
import com.jf.entity.VideoExample;
import com.jf.service.video.VideoService;
import com.jf.vo.FileSimpleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/11/11
 */
@Service
public class VideoAsyncService {

    private static Logger logger = LoggerFactory.getLogger(VideoAsyncService.class);

    @Autowired
    private VideoService videoService;

    /**
     * 找出宽或高为空的视频
     */
    public List<Video> findNeedInitWidthAndHeightVideo() {
        VideoExample example = new VideoExample();
        example.createCriteria().andDelFlagEqualTo(Const.FLAG_FALSE).andVideoWidthIsNull();
        example.or(example.createCriteria().andDelFlagEqualTo(Const.FLAG_FALSE).andVideoHeightIsNull());
        return videoService.selectByExample(example);
    }

    @Transactional
    public boolean initVideoWidthAndHeight(Video video) {
        if (!StringUtils.hasText(video.getVideoThumbnails())) {
            logger.info("视频ID{}未取得帧图", video.getId());
            return false;
        }
        String firstFramePath = video.getVideoThumbnails().split(",")[0];
        FileSimpleInfo fileSimpleInfo = FileUtil.getImgWidthAndHeight(FileUtil.defaultPath + firstFramePath);
        if (fileSimpleInfo == null) {
            logger.info("视频ID{}获取宽高失败", video.getId());
            return false;
        }
        video.setVideoWidth(fileSimpleInfo.getWidth());
        video.setVideoHeight(fileSimpleInfo.getHeight());
        videoService.updateByPrimaryKey(video);
        return true;
    }
}
