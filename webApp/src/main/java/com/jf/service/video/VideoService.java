package com.jf.service.video;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberRemindMapper;
import com.jf.dao.VideoCustomMapper;
import com.jf.dao.VideoLikeMapper;
import com.jf.dao.VideoMapper;
import com.jf.dao.VideoPlayRecordMapper;
import com.jf.dao.VideoTipOffMapper;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.Video;
import com.jf.entity.VideoCustom;
import com.jf.entity.VideoExample;
import com.jf.entity.VideoLike;
import com.jf.entity.VideoLikeExample;
import com.jf.entity.VideoPlayRecord;
import com.jf.entity.VideoTipOff;
import com.jf.entity.dto.ProductLevelTypeDTO;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.service.MemberRemindService;
import com.jf.vo.enumeration.VideoCountType;
import com.jf.vo.request.PageRequest;
import com.jf.vo.request.video.CollectVideoRequest;
import com.jf.vo.request.video.LogVideoPlayRequest;
import com.jf.vo.request.video.TipOffVideoRequest;
import com.jf.vo.request.video.VideoUserActionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Service
public class VideoService extends AppBaseService<Video, VideoExample> {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoCustomMapper videoCustomMapper;
    @Autowired
    private VideoTipOffMapper videoTipOffMapper;
    @Autowired
    private MemberRemindService memberRemindService;
    @Autowired
    private VideoExtendService videoExtendService;
    @Autowired
    private VideoPlayRecordMapper videoPlayRecordMapper;
    @Autowired
    private VideoLikeMapper videoLikeMapper;
    @Autowired
    private MemberRemindMapper memberRemindMapper;

    @Autowired
    public void setMapper() {
        super.setDao(videoMapper);
    }

    /**
     * 视频专区 我的关注
     * 查询关注店铺的所有小视频
     */
    public List<VideoCustom> findMyAttentionMchtVideo(Integer memberId, PageRequest pageRequest) {
        if (memberId == null) return Collections.emptyList();

        return videoCustomMapper.findMyAttentionMchtVideo(memberId, pageRequest);
    }

    /**
     * 视频专区 精选
     * 查询所有小视频按精选值正序、权重倒叙
     */
    public List<VideoCustom> findRecommendVideo(PageRequest pageRequest) {
        return videoCustomMapper.findRecommendVideo(pageRequest);
    }

    /**
     * 视频专区 类目栏目
     * 查询主营类目为指定类目的所有商家的小视频
     */
    public List<VideoCustom> findByProductType1(Integer productType1Id, PageRequest pageRequest) {
        return videoCustomMapper.findByProductType1(productType1Id, pageRequest);
    }

    public VideoCustom getById(Integer videoId) {
        return videoCustomMapper.getDetail(videoId);
    }

    /**
     * 视频详情页 产品类目相关视频查询,规则如下：初始视频第一个商品类目（三级类目）type3
     * 先查询type3相关的小视频，然后是相关二级类目type2、再是一级类目type1，再是其他一级类目视频，且同级类目随机获取
     *
     * @param videoId     初始视频ID
     * @param videoIds    已查询视频ID集合
     * @param pageRequest 分页信息
     */
    public List<VideoCustom> findByRelativeProductType(Integer videoId, String videoIds, PageRequest pageRequest) {
        ProductLevelTypeDTO productLevelTypeDTO = videoCustomMapper.getVideoProductLevelType(videoId);
        if (productLevelTypeDTO == null) return Collections.emptyList();

        Map<String, Object> params = Maps.newHashMap();
        params.put("type1", productLevelTypeDTO.getType1()); //一级类目
        params.put("type2", productLevelTypeDTO.getType2()); //二级类目
        params.put("type3", productLevelTypeDTO.getType3()); //三级类目
        params.put("videoIds", splitVideoIds(videoId, videoIds)); //已查询过的Video
        params.put("offset", pageRequest.getOffset());
        params.put("fetchSize", pageRequest.getPageSize());
        return videoCustomMapper.findByRelativeProductType(params);
    }

    private List<Integer> splitVideoIds(Integer videoId, String videoIds) {
        if (!StringUtils.hasText(videoIds)) {
            return Lists.newArrayList(videoId);
        }
        Set<Integer> idSet = Sets.newHashSet();
        for (String id : videoIds.split(",")) {
            idSet.add(Integer.valueOf(id));
        }
        idSet.add(videoId);
        return Lists.newArrayList(idSet);
    }

    public List<VideoProductDTO> findVideoProduct(List<Integer> videoIds) {
        if (CollectionUtils.isEmpty(videoIds)) return Collections.emptyList();

        return videoCustomMapper.findVideoProduct(videoIds);
    }

    private Video getAndValidate(Integer videoId) {
        Video video = videoMapper.selectByPrimaryKey(videoId);
        if (video == null || StateConst.TRUE.equalsIgnoreCase(video.getDelFlag()) || StateConst.OFFLINE.equalsIgnoreCase(video.getStatus())) {
            throw new BizException("该视频已不存在或已下架");
        }
        return video;
    }

    @Transactional
    public void tipOffVideo(TipOffVideoRequest request) {
        Video video = getAndValidate(request.getVideoId());

        VideoTipOff videoTipOff = new VideoTipOff();
        videoTipOff.setVideoId(request.getVideoId());
        videoTipOff.setMchtId(video.getMchtId());
        videoTipOff.setMemberId(request.getMemberId());
        videoTipOff.setMatter(String.valueOf(request.getType()));
        videoTipOff.setPics(StringUtil.trimAllUrls(request.getImgs()));
        videoTipOff.setAuditStatus("1"); //待审核
        videoTipOff.setMatterRemark(request.getDescription());
        videoTipOff.setCreateBy(request.getMemberId());
        videoTipOff.setCreateDate(new Date());
        videoTipOff.setDelFlag(StateConst.FALSE);
        videoTipOffMapper.insert(videoTipOff);
    }

    @Transactional
    public void collectVideo(CollectVideoRequest request) {
        getAndValidate(request.getVideoId());

        int increment = 1;
        MemberRemind originMemberRemind = memberRemindService.getMemberRemind(request.getMemberId(), "4", request.getVideoId());
        if (2 == request.getType()) { //取消收藏
            if (originMemberRemind == null) {
                throw new BizException("您尚未收藏");
            }

            increment = -1;
            memberRemindService.deleteByPrimaryKey(originMemberRemind.getId());
        } else { //收藏
            if (originMemberRemind != null) {
                throw new BizException("您已收藏");
            }
            MemberRemind memberRemind = new MemberRemind();
            memberRemind.setMemberId(request.getMemberId());
            memberRemind.setRemindType("4"); //视频
            memberRemind.setRemindId(request.getVideoId());
            memberRemind.setCreateBy(request.getMemberId());
            memberRemind.setCreateDate(new Date());
            memberRemind.setDelFlag(StateConst.FALSE);
            memberRemindService.insert(memberRemind);
        }

        videoExtendService.videoRelateCountAdd(VideoCountType.COLLECT_COUNT, request.getVideoId(), increment);
    }

    @Transactional
    public void logVideoPlay(LogVideoPlayRequest request) {
        Video originVideo = videoMapper.selectByPrimaryKey(request.getVideoId());
        if (originVideo == null) return;

        videoExtendService.videoRelateCountAdd(VideoCountType.PLAY_COUNT, request.getVideoId(), 1);
        VideoPlayRecord record = new VideoPlayRecord();
        record.setVideoId(request.getVideoId());
        record.setMemberId(request.getMemberId());
        record.setDeviceNumber(request.getDeviceId());
        record.setCreateBy(request.getMemberId());
        record.setCreateDate(new Date());
        record.setDelFlag(StateConst.FALSE);
        videoPlayRecordMapper.insert(record);
    }

    public VideoUserActionView getUserActionSituation(Integer memberId, Integer videoId) {
        if (memberId == null) return null;

        Map<Integer, VideoUserActionView> map = findMemberActionSituation(Lists.newArrayList(videoId), memberId);
        return map.get(videoId);
    }

    /**
     * 批量查询用户对视频的点赞情况
     *
     * @param videoIds 视频ID集合
     * @param memberId 用户ID
     */
    public Map<Integer, VideoLike> findMemberLikeSituation(List<Integer> videoIds, Integer memberId) {
        if (CollectionUtils.isEmpty(videoIds) || memberId == null) return Collections.emptyMap();

        Map<Integer, VideoLike> likeMap = findUserVideoLike(videoIds, memberId);

        Map<Integer, VideoLike> map = Maps.newHashMap();
        for (Integer videoId : videoIds) {
            VideoLike videoLike = likeMap.get(videoId);
            if (videoLike != null) {
                map.put(videoId, videoLike);
            }
        }
        return map;
    }

    /**
     * 批量查询用户对视频的点赞、关注、收藏等情况
     *
     * @param videoIds 视频ID集合
     * @param memberId 用户ID
     */
    public Map<Integer, VideoUserActionView> findMemberActionSituation(List<Integer> videoIds, Integer memberId) {
        if (CollectionUtils.isEmpty(videoIds) || memberId == null) return Collections.emptyMap();

        Map<Integer, VideoCustom> attentionMap = findUserVideoAttention(videoIds, memberId);
        Map<Integer, MemberRemind> collectMap = findUserVideoCollect(videoIds, memberId);
        Map<Integer, VideoLike> likeMap = findUserVideoLike(videoIds, memberId);

        Map<Integer, VideoUserActionView> map = Maps.newHashMap();
        for (Integer videoId : videoIds) {
            VideoUserActionView view = new VideoUserActionView();
            view.setVideoId(videoId);
            view.setAttention(attentionMap.get(videoId) != null);
            view.setCollect(collectMap.get(videoId) != null);
            view.setLike(likeMap.get(videoId) != null);
            map.put(videoId, view);
        }
        return map;
    }

    private Map<Integer, MemberRemind> findUserVideoCollect(List<Integer> videoIds, Integer memberId) {
        MemberRemindExample example = new MemberRemindExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andRemindTypeEqualTo("4").andRemindIdIn(videoIds).andDelFlagEqualTo(StateConst.FALSE);
        List<MemberRemind> list = memberRemindMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();

        Map<Integer, MemberRemind> map = Maps.newHashMap();
        for (MemberRemind memberRemind : list) {
            map.put(memberRemind.getRemindId(), memberRemind);
        }
        return map;
    }

    private Map<Integer, VideoCustom> findUserVideoAttention(List<Integer> videoIds, Integer memberId) {
        List<VideoCustom> list = videoCustomMapper.findUserVideoAttentionSituation(memberId, videoIds);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();

        Map<Integer, VideoCustom> map = Maps.newHashMap();
        for (VideoCustom videoCustom : list) {
            map.put(videoCustom.getId(), videoCustom);
        }
        return map;
    }

    private Map<Integer, VideoLike> findUserVideoLike(List<Integer> videoIds, Integer memberId) {
        VideoLikeExample example = new VideoLikeExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo(StateConst.FALSE).andVideoIdIn(videoIds);
        List<VideoLike> list = videoLikeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();

        Map<Integer, VideoLike> map = Maps.newHashMap();
        for (VideoLike videoLike : list) {
            map.put(videoLike.getVideoId(), videoLike);
        }
        return map;
    }


}
