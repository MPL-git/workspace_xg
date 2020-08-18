package com.jf.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.ActivityCustom;
import com.jf.entity.Video;
import com.jf.entity.VideoColumn;
import com.jf.entity.VideoCommentCustom;
import com.jf.entity.VideoCommentLike;
import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCustom;
import com.jf.entity.VideoLike;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.service.ActivityService;
import com.jf.service.video.VideoAttentionService;
import com.jf.service.video.VideoColumnService;
import com.jf.service.video.VideoCommentService;
import com.jf.service.video.VideoConverter;
import com.jf.service.video.VideoLikeService;
import com.jf.service.video.VideoService;
import com.jf.vo.request.video.VideoUserActionView;
import com.jf.vo.request.video.AttentionVideoMchtRequest;
import com.jf.vo.request.video.CollectVideoRequest;
import com.jf.vo.request.video.CommentVideoRequest;
import com.jf.vo.request.video.FindTypeRelativeVideoRequest;
import com.jf.vo.request.video.FindVideoCommentRequest;
import com.jf.vo.request.video.FindVideoRequest;
import com.jf.vo.request.IdRequest;
import com.jf.vo.request.video.LikeVideoCommentRequest;
import com.jf.vo.request.video.LikeVideoRequest;
import com.jf.vo.request.video.LogVideoPlayRequest;
import com.jf.vo.request.video.TipOffVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Controller
public class VideoController extends BaseController {

    @Autowired
    private AppContext appContext;
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoConverter videoConverter;
    @Autowired
    private VideoColumnService videoColumnService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private VideoAttentionService videoAttentionService;
    @Autowired
    private VideoLikeService videoLikeService;
    @Autowired
    private VideoCommentService videoCommentService;

    /**
     * 小视频 栏目列表查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/Column/list", method = RequestMethod.POST)
    public ResponseMsg findVideoColumnList() {
        List<VideoColumn> videoColumns = videoColumnService.findAll();
        return ResponseMsg.success(videoConverter.toVideoColumnViewList(videoColumns));
    }

    /**
     * 小视频 视频列表查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/list", method = RequestMethod.POST)
    public ResponseMsg findVideoList() {
        FindVideoRequest request = appContext.getRequestAndValidate(FindVideoRequest.class);
        List<VideoCustom> videoList = Lists.newArrayList();
        if (request.getType() == 1) { //我的关注
            videoList.addAll(videoService.findMyAttentionMchtVideo(request.getMemberId(), request));
        } else if (request.getType() == 2) { //精选
            videoList.addAll(videoService.findRecommendVideo(request));
        } else if (request.getType() == 3) { //类目
            videoList.addAll(videoService.findByProductType1(request.getProductType1Id(), request));
        }
        Map<Integer, VideoLike> videoLikeMap = videoService.findMemberLikeSituation(videoConverter.extractVideoId(videoList), request.getMemberId());
        return ResponseMsg.success(videoConverter.toVideoViewList(videoList, videoLikeMap));
    }

    /**
     * 小视频 详情查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/detail", method = RequestMethod.POST)
    public ResponseMsg getVideo() {
        IdRequest request = appContext.getRequestAndValidate(IdRequest.class);

        VideoCustom videoCustom = videoService.getDetailById(request.getId());
        List<VideoProductDTO> videoProductDTOList = videoService.findVideoProduct(Lists.newArrayList(request.getId()));
        List<ActivityCustom> activityCustomList = activityService.findByProductIds(videoConverter.extractProductId(videoProductDTOList));
        VideoUserActionView videoUserActionView = videoService.getUserActionSituation(request.getMemberId(), request.getId());
        return ResponseMsg.success(videoConverter.buildVideoDetailView(videoCustom, videoProductDTOList, activityCustomList, videoUserActionView));
    }

    /**
     * 小视频 同品类视频的查询接口(小视频详情页中使用)
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/typeRelative/list", method = RequestMethod.POST)
    public ResponseMsg findByRelativeProductType() {
        FindTypeRelativeVideoRequest request = appContext.getRequestAndValidate(FindTypeRelativeVideoRequest.class);

        List<VideoCustom> videoCustoms = videoService.findByRelativeProductType(request.getVideoId(), request.getVideoIds(), request);
        List<Integer> videoIds = videoConverter.extractVideoId(videoCustoms);
        List<VideoProductDTO> videoProductDTOList = videoService.findVideoProduct(videoIds);
        List<ActivityCustom> activityCustomList = activityService.findByProductIds(videoConverter.extractProductId(videoProductDTOList));
        Map<Integer, VideoUserActionView> videoUserActionMap = videoService.findMemberActionSituation(videoIds, request.getMemberId());
        return ResponseMsg.success(videoConverter.buildVideoDetailViewList(videoCustoms, videoProductDTOList, activityCustomList, videoUserActionMap));
    }

    /**
     * 小视频 添加播放记录
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/logPlay", method = RequestMethod.POST)
    public ResponseMsg logVideoPlay() {
        LogVideoPlayRequest request = appContext.getRequestAndValidate(LogVideoPlayRequest.class);
        videoService.logVideoPlay(request);
        return ResponseMsg.success();
    }

    /**
     * 小视频 违规举报
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/video/tipOff", method = RequestMethod.POST)
    public ResponseMsg tipOffVideo() {
        TipOffVideoRequest request = appContext.getRequestAndValidate(TipOffVideoRequest.class);
        videoService.tipOffVideo(request);
        return ResponseMsg.success();
    }

    /**
     * 小视频 收藏/取消收藏
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/video/collect", method = RequestMethod.POST)
    public ResponseMsg collectVideo() {
        CollectVideoRequest request = appContext.getRequestAndValidate(CollectVideoRequest.class);
        videoService.collectVideo(request);
        return ResponseMsg.success();
    }

    /**
     * 小视频 商家 关注/取消关注
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/video/attentionMcht", method = RequestMethod.POST)
    public ResponseMsg attentionVideoMcht() {
        AttentionVideoMchtRequest request = appContext.getRequestAndValidate(AttentionVideoMchtRequest.class);
        videoAttentionService.attentionVideoMcht(request);
        return ResponseMsg.success();
    }

    /**
     * 小视频 点赞/取消点赞
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/video/like", method = RequestMethod.POST)
    public ResponseMsg likeVideo() {
        LikeVideoRequest request = appContext.getRequestAndValidate(LikeVideoRequest.class);
        int likeCount = videoLikeService.likeVideo(request);
        Map<String, Object> result = Maps.newHashMap();
        result.put("likeCount", likeCount);
        result.put("likeCountDisplayName", StringUtil.decorateCount(likeCount));
        return ResponseMsg.success(result);
    }

    /**
     * 小视频 获取分享信息
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/shareInfo", method = RequestMethod.POST)
    public ResponseMsg getShareInfo() {
        IdRequest request = appContext.getRequestAndValidate(IdRequest.class);
        VideoCustom videoCustom = videoService.getDetailById(request.getId());
        if (videoCustom != null && !StringUtils.hasText(videoCustom.getVideoXcxCode())) {
            videoService.getVideoXcxCode(videoCustom);
        }
        return ResponseMsg.success(videoConverter.toVideoShareView(videoCustom));
    }

    /**
     * 小视频 评论
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/video/comment", method = RequestMethod.POST)
    public ResponseMsg commentVideo() {
        CommentVideoRequest request = appContext.getRequestAndValidate(CommentVideoRequest.class);
        return ResponseMsg.success(videoCommentService.comment(request));
    }

    /**
     * 小视频 评论 点赞/取消点赞
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/video/comment/like", method = RequestMethod.POST)
    public ResponseMsg likeCommentVideo() {
        LikeVideoCommentRequest request = appContext.getRequestAndValidate(LikeVideoCommentRequest.class);
        int likeCount = videoCommentService.likeComment(request);
        Map<String, Object> result = Maps.newHashMap();
        result.put("likeCount", likeCount);
        result.put("likeCountDisplayName", StringUtil.decorateCount(likeCount));
        return ResponseMsg.success(result);
    }

    /**
     * 小视频 评论列表查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/comment/list", method = RequestMethod.POST)
    public ResponseMsg findVideoComment() {
        FindVideoCommentRequest request = appContext.getRequestAndValidate(FindVideoCommentRequest.class);

        List<VideoCommentCustom> videoCommentList = videoCommentService.findVideoComment(request.getVideoId(), request);
        List<Integer> commentIds = videoConverter.extractCommentId(videoCommentList);
        List<VideoCommentReply> replyList = videoCommentService.findVideoCommentReply(request.getVideoId(), commentIds);
        List<VideoCommentLike> userLikeList = videoCommentService.findMemberVideoCommentLike(request.getMemberId(), commentIds); //用户已点赞记录

        return ResponseMsg.success(videoConverter.toVideoCommentViewList(videoCommentList, replyList, userLikeList));
    }

    /**
     * 小视频 上架状态查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/video/checkStatus", method = RequestMethod.POST)
    public ResponseMsg checkVideoStatus() {
        IdRequest request = appContext.getRequestAndValidate(IdRequest.class);

        Video video = videoService.selectByPrimaryKey(request.getId());
        boolean online = video != null && StateConst.ONLINE.equals(video.getStatus());
        return ResponseMsg.success(online);
    }

}
