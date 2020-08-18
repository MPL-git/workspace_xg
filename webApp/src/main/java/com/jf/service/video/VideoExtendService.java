package com.jf.service.video;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.VideoExtendMapper;
import com.jf.entity.VideoExtend;
import com.jf.entity.VideoExtendExample;
import com.jf.vo.enumeration.VideoCountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
@Service
public class VideoExtendService extends AppBaseService<VideoExtend, VideoExtendExample> {

    @Autowired
    private VideoExtendMapper videoExtendMapper;

    @Autowired
    public void setMapper() {
        super.setDao(videoExtendMapper);
    }

    /**
     * 记录视频相关统计数，加1 或 减1
     *
     * @param type      统计类型
     * @param videoId   视频ID
     * @param increment 增长数
     *
     * @return 返回统计后的数值
     */
    @Transactional
    public int videoRelateCountAdd(VideoCountType type, Integer videoId, int increment) {
        VideoExtendExample example = new VideoExtendExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andDelFlagEqualTo(StateConst.FALSE);
        VideoExtend originExtend = selectOneByExample(example);
        if (originExtend == null) return 0;

        int result = 0;
        VideoExtend newExtend = new VideoExtend();
        newExtend.setId(originExtend.getId());
        if (VideoCountType.PLAY_COUNT == type) {
            result = originExtend.getPlayCount() + increment;
            newExtend.setPlayCount(result);
        } else if (VideoCountType.LIKE_COUNT == type) {
            result = originExtend.getLikeCount() + increment;
            newExtend.setLikeCount(result);
        } else if (VideoCountType.COMMENT_COUNT == type) {
            result = originExtend.getCommentCount() + increment;
            newExtend.setCommentCount(result);
        } else if (VideoCountType.COLLECT_COUNT == type) {
            result = originExtend.getCollectCount() + increment;
            newExtend.setCollectCount(result);
        }
        videoExtendMapper.updateByPrimaryKeySelective(newExtend);
        return result;
    }

    public VideoExtend findByVideoId(Integer videoId) {
        VideoExtendExample example = new VideoExtendExample();
        example.createCriteria().andVideoIdEqualTo(videoId);
        return selectOneByExample(example);
    }
}
