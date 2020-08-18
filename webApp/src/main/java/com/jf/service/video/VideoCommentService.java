package com.jf.service.video;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.VideoCommentCustomMapper;
import com.jf.dao.VideoCommentLikeMapper;
import com.jf.dao.VideoCommentMapper;
import com.jf.dao.VideoCommentReplyMapper;
import com.jf.dao.VideoMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.Video;
import com.jf.entity.VideoComment;
import com.jf.entity.VideoCommentCustom;
import com.jf.entity.VideoCommentExample;
import com.jf.entity.VideoCommentLike;
import com.jf.entity.VideoCommentLikeExample;
import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCommentReplyExample;
import com.jf.service.ViolateWordService;
import com.jf.vo.enumeration.VideoCountType;
import com.jf.vo.request.PageRequest;
import com.jf.vo.request.video.CommentVideoRequest;
import com.jf.vo.request.video.LikeVideoCommentRequest;
import com.jf.vo.response.VideoCommentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
@Service
public class VideoCommentService extends AppBaseService<VideoComment, VideoCommentExample> {

    @Autowired
    private VideoCommentMapper videoCommentMapper;
    @Autowired
    private VideoCommentCustomMapper videoCommentCustomMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ViolateWordService violateWordService;
    @Autowired
    private VideoCommentReplyMapper videoCommentReplyMapper;
    @Autowired
    private VideoCommentLikeMapper videoCommentLikeMapper;
    @Autowired
    private VideoExtendService videoExtendService;

    @Autowired
    public void setMapper() {
        super.setDao(videoCommentMapper);
    }

    @Transactional
    public VideoCommentView comment(CommentVideoRequest request) {
        Video originVideo = videoMapper.selectByPrimaryKey(request.getVideoId());
        if (originVideo == null) {
            throw new BizException("评论失败，此视频已不存在");
        }

        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(request.getMemberId());
        String hideContent = StringUtil.filterEmoji(request.getContent()); //表情字符过滤
        String content = violateWordService.getProhibitedWordsFilter(hideContent); //敏感词汇过滤

        VideoComment videoComment = saveComment(originVideo, memberInfo, content, hideContent);
        int commentCount = videoExtendService.videoRelateCountAdd(VideoCountType.COMMENT_COUNT, request.getVideoId(), 1);
        return buildVideoCommentView(videoComment, commentCount);
    }

    private VideoCommentView buildVideoCommentView(VideoComment videoComment, int commentCount) {
        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(videoComment.getMemberId());

        VideoCommentView view = new VideoCommentView();
        view.setId(videoComment.getId());
        view.setVideoId(videoComment.getVideoId());
        view.setContent(videoComment.getContent());
        view.setCreateDate(videoComment.getCreateDate());
        view.setCreateDateDisplayName(DateUtil.decorateDate(videoComment.getCreateDate()));
        view.setLikeCount(0);
        view.setLikeCountDisplayName("0");
        view.setCommentCount(commentCount);
        view.setCommentCountDisplayName(StringUtil.decorateCount(commentCount));
        if (memberInfo != null) {
            view.setMemberAvatar(StringUtil.getPic(memberInfo.getPic(), ""));
            view.setMemberNick(memberInfo.getNick());
        }
        return view;
    }

    private VideoComment saveComment(Video video, MemberInfo memberInfo, String content, String hideContent) {
        VideoComment videoComment = new VideoComment();
        videoComment.setMchtId(video.getMchtId());
        videoComment.setVideoId(video.getId());
        videoComment.setMemberId(memberInfo.getId());
        videoComment.setMemberAvatar(memberInfo.getPic());
        videoComment.setMemberNick(memberInfo.getNick());
        videoComment.setContent(content);
        videoComment.setHideContent(hideContent);
        videoComment.setStatus(StateConst.SHOW); //默认显示
        videoComment.setAuditStatus("1"); //待审核
        videoComment.setLikedCount(0);
        videoComment.setReplyCount(0);
        videoComment.setCreateBy(memberInfo.getId());
        videoComment.setCreateDate(new Date());
        videoComment.setDelFlag(StateConst.FALSE);
        insert(videoComment);
        return videoComment;
    }

    public List<VideoCommentCustom> findVideoComment(Integer videoId, PageRequest pageRequest) {
        return videoCommentCustomMapper.findVideoComment(videoId, pageRequest);
    }

    public List<VideoCommentReply> findVideoCommentReply(Integer videoId, List<Integer> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) return Collections.emptyList();

        VideoCommentReplyExample example = new VideoCommentReplyExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andDelFlagEqualTo(StateConst.FALSE).andVideoCommentIdIn(commentIds);
        return videoCommentReplyMapper.selectByExample(example);
    }

    public List<VideoCommentLike> findMemberVideoCommentLike(Integer memberId, List<Integer> commentIds) {
        if (memberId == null || CollectionUtils.isEmpty(commentIds)) return Collections.emptyList();

        VideoCommentLikeExample example = new VideoCommentLikeExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo(StateConst.FALSE).andVideoCommentIdIn(commentIds);
        return videoCommentLikeMapper.selectByExample(example);
    }

    @Transactional
    public int likeComment(LikeVideoCommentRequest request) {
        VideoComment videoComment = videoCommentMapper.selectByPrimaryKey(request.getCommentId());
        if (videoComment == null) {
            throw new BizException("该评论已删除");
        }
        int increment = 1;
        if (2 == request.getType()) { //取消点赞
            cancelLikeComment(request.getMemberId(), request.getCommentId());
            increment = -1;
        } else { //点赞
            doLikeComment(request.getMemberId(), request.getCommentId(), videoComment.getVideoId());
        }
        videoComment.setLikedCount(videoComment.getLikedCount() + increment);
        updateByPrimaryKey(videoComment);
        return videoComment.getLikedCount();
    }

    private void cancelLikeComment(Integer memberId, Integer commentId) {
        VideoCommentLike originLike = getVideoCommentLike(commentId, memberId);
        if (originLike == null) {
            throw new BizException("您尚未点赞");
        }
        if (StateConst.TRUE.equals(originLike.getDelFlag())) {
            throw new BizException("已取消点赞");
        }

        originLike.setUpdateBy(memberId);
        originLike.setUpdateDate(new Date());
        originLike.setDelFlag(StateConst.TRUE);
        videoCommentLikeMapper.updateByPrimaryKey(originLike);
    }

    private void doLikeComment(Integer memberId, Integer commentId, Integer videoId) {
        VideoCommentLike originLike = getVideoCommentLike(commentId, memberId);
        if (originLike != null) {
            if (StateConst.FALSE.equals(originLike.getDelFlag())) {
                throw new BizException("已点赞");
            }
            originLike.setUpdateBy(memberId);
            originLike.setUpdateDate(new Date());
            originLike.setDelFlag(StateConst.FALSE);
            videoCommentLikeMapper.updateByPrimaryKey(originLike);
            return;
        }

        VideoCommentLike newLike = new VideoCommentLike();
        newLike.setVideoId(videoId);
        newLike.setVideoCommentId(commentId);
        newLike.setMemberId(memberId);
        newLike.setCreateBy(memberId);
        newLike.setCreateDate(new Date());
        newLike.setDelFlag(StateConst.FALSE);
        videoCommentLikeMapper.insert(newLike);
    }

    private VideoCommentLike getVideoCommentLike(Integer commentId, Integer memberId) {
        VideoCommentLikeExample example = new VideoCommentLikeExample();
        example.createCriteria().andVideoCommentIdEqualTo(commentId).andMemberIdEqualTo(memberId);
        List<VideoCommentLike> list = videoCommentLikeMapper.selectByExample(example);
        return CollectionUtil.isEmpty(list) ? null : list.get(0);
    }

}
