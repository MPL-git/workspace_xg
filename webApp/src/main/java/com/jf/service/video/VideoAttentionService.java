package com.jf.service.video;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.VideoAttentionMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.VideoAttention;
import com.jf.entity.VideoAttentionExample;
import com.jf.vo.request.video.AttentionVideoMchtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
@Service
public class VideoAttentionService extends AppBaseService<VideoAttention, VideoAttentionExample> {

    @Autowired
    private VideoAttentionMapper videoAttentionMapper;
    @Autowired
    private MchtInfoMapper mchtInfoMapper;

    @Autowired
    public void setMapper() {
        super.setDao(videoAttentionMapper);
    }

    /**
     * 关注或取消关注
     *
     * @param request
     */
    @Transactional
    public void attentionVideoMcht(AttentionVideoMchtRequest request) {
        MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(request.getMchtId());
        if (mchtInfo == null) {
            throw new BizException("该商家不存在");
        }
        if (2 == request.getType()) {
            cancelAttentionVideoMcht(request.getMchtId(), request.getMemberId());
        }else {
            doAttentionVideoMcht(request.getMchtId(), request.getMemberId());
        }
    }

    private void cancelAttentionVideoMcht(Integer mechtId, Integer memberId) {
        VideoAttention originVideoAttention = getVideoAttention(mechtId, memberId);
        if (originVideoAttention == null) {
            throw new BizException("您尚未关注");
        }

        if (StateConst.FALSE.equalsIgnoreCase(originVideoAttention.getStatus())) {
            throw new BizException("已取消关注");
        }

        //更新
        originVideoAttention.setStatus(StateConst.FALSE);
        originVideoAttention.setUpdateBy(mechtId);
        originVideoAttention.setUpdateDate( new Date());
        updateByPrimaryKey(originVideoAttention);
    }

    private void doAttentionVideoMcht(Integer mechtId, Integer memberId) {
        VideoAttention originVideoAttention = getVideoAttention(mechtId, memberId);
        Date now = new Date();
        if (originVideoAttention != null) {
            if (StateConst.TRUE.equalsIgnoreCase(originVideoAttention.getStatus())) {
                throw new BizException("已关注");
            }

            //更新
            originVideoAttention.setStatus(StateConst.TRUE);
            originVideoAttention.setUpdateBy(mechtId);
            originVideoAttention.setUpdateDate(now);
            updateByPrimaryKey(originVideoAttention);
            return;
        }

        //新增
        VideoAttention videoAttention = new VideoAttention();
        videoAttention.setMchtId(mechtId);
        videoAttention.setMemberId(memberId);
        videoAttention.setStatus(StateConst.TRUE); //关注
        videoAttention.setAttentionTime(now);
        videoAttention.setCreateBy(memberId);
        videoAttention.setCreateDate(now);
        videoAttention.setDelFlag(StateConst.FALSE);
        insert(videoAttention);
    }

    private VideoAttention getVideoAttention(Integer mechtId, Integer memberId) {
        VideoAttentionExample example = new VideoAttentionExample();
        example.createCriteria().andMchtIdEqualTo(mechtId).andMemberIdEqualTo(memberId).andDelFlagEqualTo(StateConst.FALSE);
        return selectOneByExample(example);
    }

}
