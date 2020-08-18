package com.jf.service.video;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ActivityCustom;
import com.jf.entity.VideoColumn;
import com.jf.entity.VideoCommentCustom;
import com.jf.entity.VideoCommentLike;
import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCustom;
import com.jf.entity.VideoLike;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.vo.request.video.VideoUserActionView;
import com.jf.vo.response.VideoColumnView;
import com.jf.vo.response.VideoCommentReplyView;
import com.jf.vo.response.VideoCommentView;
import com.jf.vo.response.VideoDetailView;
import com.jf.vo.response.VideoProductView;
import com.jf.vo.response.VideoShareView;
import com.jf.vo.response.VideoView;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Service
public class VideoConverter {

    public List<VideoColumnView> toVideoColumnViewList(List<VideoColumn> list) {
        List<VideoColumnView> viewList = Lists.newArrayList();
        viewList.add(new VideoColumnView(1, "我的关注", false));
        viewList.add(new VideoColumnView(2, "精选", true));
        if (!CollectionUtils.isEmpty(list)) {
            for (VideoColumn videoColumn : list) {
                VideoColumnView view = new VideoColumnView();
                BeanUtils.copyProperties(videoColumn, view);
                view.setType(3);
                viewList.add(view);
            }
        }
        return viewList;
    }

    public List<VideoView> toVideoViewList(List<VideoCustom> list, Map<Integer, VideoLike> videoLikeMap) {
        if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

        List<VideoView> viewList = Lists.newArrayList();
        for (VideoCustom videoCustom : list) {
            VideoView view = new VideoView();
            BeanUtils.copyProperties(videoCustom, view);
            view.setShopLogo(StringUtil.getPic(videoCustom.getShopLogo(), ""));
            view.setVideoCover(StringUtil.getPic(videoCustom.getVideoCover(), ""));
            view.setVideoUrl(StringUtil.getVideo(videoCustom.getVideoUrl()));
            view.setLiked(videoLikeMap.get(videoCustom.getId()) != null);
            view.setLikeCountDisplayName(StringUtil.decorateCount(videoCustom.getLikeCount()));
            viewList.add(view);
        }
        return viewList;
    }

    public VideoDetailView buildVideoDetailView(VideoCustom videoCustom, List<VideoProductDTO> videoProductDTOList, List<ActivityCustom> activityCustomList, VideoUserActionView videoUserActionView) {
        Map<Integer, ActivityCustom> productIdActivityMap = buildProductActivityMap(activityCustomList);

        return doBuildVideoDetailView(videoCustom, videoProductDTOList, productIdActivityMap, videoUserActionView);
    }

    private VideoDetailView doBuildVideoDetailView(VideoCustom videoCustom, List<VideoProductDTO> videoProductDTOList, Map<Integer, ActivityCustom> productActivityMap, VideoUserActionView videoUserActionView) {
        if (videoCustom == null) return new VideoDetailView();

        VideoDetailView detailView = new VideoDetailView();
        fillWithVideoInfo(detailView, videoCustom, videoUserActionView); //基本信息填充
        fillWithVideoProductList(detailView, videoProductDTOList, productActivityMap); //商品信息填充
        if (!CollectionUtils.isEmpty(videoProductDTOList)) {
            detailView.setProductCount(videoProductDTOList.size());
        }
        return detailView;
    }

    public List<VideoDetailView> buildVideoDetailViewList(List<VideoCustom> videoCustomList, List<VideoProductDTO> videoProductDTOList, List<ActivityCustom> activityCustomList, Map<Integer, VideoUserActionView> videoUserActionMap) {
        if (CollectionUtils.isEmpty(videoCustomList)) return Collections.emptyList();

        Map<Integer, List<VideoProductDTO>> videoProductMap = buildVideoProductMap(videoProductDTOList);
        Map<Integer, ActivityCustom> productActivityMap = buildProductActivityMap(activityCustomList);
        List<VideoDetailView> detailViewList = Lists.newArrayList();
        for (VideoCustom videoCustom : videoCustomList) {
            VideoDetailView detailView = doBuildVideoDetailView(videoCustom, videoProductMap.get(videoCustom.getId()), productActivityMap, videoUserActionMap.get(videoCustom.getId()));
            detailViewList.add(detailView);
        }
        return detailViewList;
    }

    private Map<Integer, List<VideoProductDTO>> buildVideoProductMap(List<VideoProductDTO> videoProductDTOList) {
        if (CollectionUtils.isEmpty(videoProductDTOList)) return Collections.emptyMap();

        Map<Integer, List<VideoProductDTO>> map = Maps.newHashMap();
        for (VideoProductDTO dto : videoProductDTOList) {
            List<VideoProductDTO> list = map.get(dto.getVideoId());
            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            list.add(dto);
            map.put(dto.getVideoId(), list);
        }
        return map;
    }

    private void fillWithVideoProductList(VideoDetailView detailView, List<VideoProductDTO> videoProductDTOList, Map<Integer, ActivityCustom> productActivityMap) {
        if (CollectionUtils.isEmpty(videoProductDTOList)) return;

        for (VideoProductDTO dto : videoProductDTOList) {
            VideoProductView view = new VideoProductView();
            view.setProductId(dto.getProductId());
            view.setName(dto.getName());
            view.setMainImg(StringUtil.getPic(dto.getMainImg(), ""));
            view.setStatus(dto.isOnline() ? "1" : "0");

            ActivityCustom activityCustom = productActivityMap.get(dto.getProductId());
            view.setMallPrice(activityCustom == null ? dto.getMallPrice() : BigDecimal.valueOf(activityCustom.getSalePrice()));
            detailView.getProductList().add(view);
        }
    }

    private Map<Integer, ActivityCustom> buildProductActivityMap(List<ActivityCustom> activityCustomList) {
        if (CollectionUtils.isEmpty(activityCustomList)) return Collections.emptyMap();

        Map<Integer, ActivityCustom> map = Maps.newHashMap();
        for (ActivityCustom activity : activityCustomList) {
            map.put(activity.getProductId(), activity);
        }
        return map;
    }

    private void fillWithVideoInfo(VideoDetailView detailView, VideoCustom videoCustom, VideoUserActionView videoUserActionView) {
        detailView.setId(videoCustom.getId());
        detailView.setTitle(videoCustom.getTitle());
        detailView.setDescription(videoCustom.getDescription());
        detailView.setMchtId(videoCustom.getMchtId());
        detailView.setShopName(videoCustom.getShopName());
        detailView.setCommentCount(videoCustom.getCommentCount());
        detailView.setLikeCount(videoCustom.getLikeCount());
        detailView.setLikeCountDisplayName(StringUtil.decorateCount(videoCustom.getLikeCount()));
        detailView.setCommentCountDisplayName(StringUtil.decorateCount(videoCustom.getCommentCount()));
        detailView.setShopLogo(StringUtil.getPic(videoCustom.getShopLogo(), ""));
        detailView.setVideoUrl(StringUtil.getVideo(videoCustom.getVideoUrl()));
        detailView.setVideoCover(StringUtil.getPic(videoCustom.getVideoCover(), ""));
        detailView.setVideoTime(videoCustom.getVideoTime());
        detailView.setVideoSize(videoCustom.getVideoSize());
        detailView.setVideoWidth(videoCustom.getVideoWidth());
        detailView.setVideoHeight(videoCustom.getVideoHeight());
        if (StringUtils.hasText(videoCustom.getVideoThumbnails())) {
            String firstFrameUrl = videoCustom.getVideoThumbnails().split(",")[0];
            detailView.setVideoFirstFrame(StringUtil.getPic(firstFrameUrl, ""));
        }

        if (videoUserActionView != null) {
            detailView.setAttention(videoUserActionView.isAttention());
            detailView.setLike(videoUserActionView.isLike());
            detailView.setCollect(videoUserActionView.isCollect());
        }
    }

    public List<Integer> extractProductId(List<VideoProductDTO> videoProductDTOList) {
        if (CollectionUtils.isEmpty(videoProductDTOList)) return Collections.emptyList();

        List<Integer> ids = Lists.newArrayList();
        for (VideoProductDTO dto : videoProductDTOList) {
            ids.add(dto.getProductId());
        }
        return ids;
    }

    public List<Integer> extractVideoId(List<VideoCustom> videoCustoms) {
        if (CollectionUtils.isEmpty(videoCustoms)) return Collections.emptyList();

        List<Integer> ids = Lists.newArrayList();
        for (VideoCustom videoCustom : videoCustoms) {
            ids.add(videoCustom.getId());
        }
        return ids;
    }

    public List<Integer> extractCommentId(List<VideoCommentCustom> list) {
        if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

        List<Integer> ids = Lists.newArrayList();
        for (VideoCommentCustom videoComment : list) {
            ids.add(videoComment.getId());
        }
        return ids;
    }

    public List<VideoCommentView> toVideoCommentViewList(List<VideoCommentCustom> videoCommentList, List<VideoCommentReply> replyList, List<VideoCommentLike> userLikeList) {
        if (CollectionUtils.isEmpty(videoCommentList)) return Collections.emptyList();

        Map<Integer, List<VideoCommentReply>> commentReplyMap = buildCommentIdReplyListMap(replyList);
        Map<Integer, VideoCommentLike> userCommentLikeMap = buildUserCommentLikeMap(userLikeList);
        List<VideoCommentView> viewList = Lists.newArrayList();
        boolean hasSetHot = false;
        for (VideoCommentCustom videoComment : videoCommentList) {
            VideoCommentView commentView = new VideoCommentView();
            BeanUtils.copyProperties(videoComment, commentView);
            commentView.setMemberAvatar(StringUtil.getPic(videoComment.getMemberAvatar(), ""));
            if (!hasSetHot) { //点赞最多且过30个赞标记为热评
                if (1 == videoComment.getLikeOrder() && videoComment.getLikedCount() > 30) {
                    commentView.setHot(true);
                    hasSetHot = true;
                } else {
                    commentView.setHot(false);
                }
            }
            commentView.setLiked(userCommentLikeMap.get(videoComment.getId()) != null);
            commentView.setLikeCountDisplayName(StringUtil.decorateCount(videoComment.getLikedCount()));
            commentView.setCreateDateDisplayName(DateUtil.decorateDate(videoComment.getCreateDate()));
            fillWithCommentReply(commentView, commentReplyMap.get(videoComment.getId()));

            viewList.add(commentView);
        }
        return viewList;
    }

    private void fillWithCommentReply(VideoCommentView commentView, List<VideoCommentReply> videoCommentReplies) {
        if (CollectionUtils.isEmpty(videoCommentReplies)) return;

        for (VideoCommentReply reply : videoCommentReplies) {
            VideoCommentReplyView view = new VideoCommentReplyView();
            view.setReplier(reply.getReplierNick());
            view.setContent(reply.getContent());
            commentView.getReplyList().add(view);
        }
    }

    private Map<Integer, VideoCommentLike> buildUserCommentLikeMap(List<VideoCommentLike> userlikeList) {
        if (CollectionUtils.isEmpty(userlikeList)) return Collections.emptyMap();

        Map<Integer, VideoCommentLike> map = Maps.newHashMap();
        for (VideoCommentLike like : userlikeList) {
            map.put(like.getVideoCommentId(), like);
        }
        return map;
    }

    private Map<Integer, List<VideoCommentReply>> buildCommentIdReplyListMap(List<VideoCommentReply> replyList) {
        if (CollectionUtils.isEmpty(replyList)) return Collections.emptyMap();

        Map<Integer, List<VideoCommentReply>> map = Maps.newHashMap();
        for (VideoCommentReply reply : replyList) {
            List<VideoCommentReply> list = map.get(reply.getVideoCommentId());
            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            list.add(reply);
            map.put(reply.getVideoCommentId(), list);
        }
        return map;
    }

    public VideoShareView toVideoShareView(VideoCustom video) {
        VideoShareView view = new VideoShareView();
        if (video == null) return view;

        view.setVideoId(video.getId());
        view.setVideoTitle(video.getTitle());
        view.setDescription(video.getDescription());
        view.setShopName(video.getShopName());
        view.setShopLogo(StringUtil.getPic(video.getShopLogo(), ""));
        view.setLikeCount(video.getLikeCount());
        view.setLikeCountDisplayName(StringUtil.decorateCount(video.getLikeCount()));
        view.setCommontCount(video.getCommentCount());
        view.setCommentCountDisplayName(StringUtil.decorateCount(video.getCommentCount()));
        view.setVideoCover(StringUtil.getPic(video.getVideoCover(), ""));
        view.setSmallProceduresCode(StringUtil.getPic(video.getVideoXcxCode(), ""));

        view.setOriginalId(Config.getProperty("originalId"));
        view.setXcxShareType(Config.getProperty("xcxShareType"));
        view.setWxPath("page/video/pages/full/index?videoid=" + video.getId());
        view.setWebpageUrl(Config.getProperty("mUrl") + "/xgbuy/views/index.html?redirect_url=" + view.getWxPath());

        if (StringUtils.hasText(video.getVideoThumbnails())) { //首帧
            String firstFrameUrl = video.getVideoThumbnails().split(",")[0];
            view.setVideoFirstFrame(StringUtil.getPic(firstFrameUrl, ""));
        }
        return view;
    }
}
