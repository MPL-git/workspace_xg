package com.jf.service.video;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.dao.VideoLikeMapper;
import com.jf.dao.VideoMapper;
import com.jf.entity.Video;
import com.jf.entity.VideoLike;
import com.jf.entity.VideoLikeExample;
import com.jf.vo.enumeration.VideoCountType;
import com.jf.vo.request.video.LikeVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
@Service
public class VideoLikeService extends AppBaseService<VideoLike, VideoLikeExample> {

    @Autowired
    private VideoLikeMapper videoLikeMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoExtendService videoExtendService;

    @Autowired
    public void setMapper() {
        super.setDao(videoLikeMapper);
    }

    @Transactional
    public int likeVideo(LikeVideoRequest request) {
        Video video = videoMapper.selectByPrimaryKey(request.getVideoId());
        if (video == null) {
            throw new BizException("视频不存在");
        }

        int increment = 1;
        if (2 == request.getType()) { //取消点赞
            unLikeVideo(request.getVideoId(), request.getMemberId());
            increment = -1;
        } else { //点赞
            doLikeVideo(request.getVideoId(), request.getMemberId());
        }
        return videoExtendService.videoRelateCountAdd(VideoCountType.LIKE_COUNT, request.getVideoId(), increment);
    }

    private void unLikeVideo(Integer videoId, Integer memberId) {
        VideoLike originVideoLike = getVideoLike(videoId, memberId);
        if (originVideoLike == null) {
            throw new BizException("您尚未点赞");
        }
        if (StateConst.TRUE.equals(originVideoLike.getDelFlag())) {
            throw new BizException("已取消点赞");
        }

        originVideoLike.setDelFlag(StateConst.TRUE);
        originVideoLike.setUpdateBy(memberId);
        originVideoLike.setUpdateDate(new Date());
        updateByPrimaryKey(originVideoLike);
    }

    private void doLikeVideo(Integer videoId, Integer memberId) {
        VideoLike originVideoLike = getVideoLike(videoId, memberId);
        if (originVideoLike != null) {
            if (StateConst.FALSE.equals(originVideoLike)) {
                throw new BizException("已点赞");
            }
            //更新
            originVideoLike.setDelFlag(StateConst.FALSE);
            originVideoLike.setUpdateBy(memberId);
            originVideoLike.setUpdateDate(new Date());
            updateByPrimaryKey(originVideoLike);
            return;
        }

        //新增
        VideoLike videoLike = new VideoLike();
        videoLike.setVideoId(videoId);
        videoLike.setMemberId(memberId);
        videoLike.setCreateBy(memberId);
        videoLike.setCreateDate(new Date());
        videoLike.setDelFlag(StateConst.FALSE);
        insert(videoLike);
    }

    private VideoLike getVideoLike(Integer videoId, Integer memberId) {
        VideoLikeExample example = new VideoLikeExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andMemberIdEqualTo(memberId);
        return selectOneByExample(example);
    }
}
