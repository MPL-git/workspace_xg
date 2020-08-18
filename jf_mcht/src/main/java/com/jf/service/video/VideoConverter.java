package com.jf.service.video;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jf.common.ext.query.Page;
import com.jf.common.utils.MapHelper;
import com.jf.entity.VideoComment;
import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCommentReplyExample;
import com.jf.entity.VideoExt;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.vo.video.FindVideoResponse;
import com.jf.vo.video.VideoCommentView;
import com.jf.vo.video.VideoProductSimpleView;
import com.jf.vo.video.VideoView;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author luoyb
 * Created on 2019/9/16
 */
@Service
public class VideoConverter {

    public FindVideoResponse toFindVideoResponse(Page<VideoExt> page, List<VideoProductDTO> productList, List<Integer> tipOffVideoIds) {
        FindVideoResponse response = new FindVideoResponse();
        response.setPageNumber(page.getPageNumber());
        response.setPageSize(page.getPageSize());
        response.setTotalPage(page.getTotalPage());
        response.setTotalRow(page.getTotalRow());
        if (!CollectionUtils.isEmpty(page.getList())) {
            Map<Integer, List<VideoProductDTO>> videoProductMap = MapHelper.toListMap(productList, new MapHelper.MapKey<VideoProductDTO, Integer>() {
                @Override
                public Integer key(VideoProductDTO source) {
                    return source.getId();
                }
            });
            Set<Integer> tipOffVideoIdSet = Sets.newHashSet(tipOffVideoIds);
            for (VideoExt videoExt : page.getList()) {
                VideoView videoView = new VideoView();
                BeanUtils.copyProperties(videoExt, videoView);
                fillWithImgList(videoView, videoExt.getVideoThumbnails());
                fillWithProductList(videoView, videoProductMap.get(videoExt.getId()));
                videoView.setTipOff(tipOffVideoIdSet.contains(videoExt.getId()));
                response.getList().add(videoView);
            }
        }
        return response;
    }

    private void fillWithProductList(VideoView videoView, List<VideoProductDTO> videoProductDTOs) {
        if (CollectionUtils.isEmpty(videoProductDTOs)) return;

        for (VideoProductDTO dto : videoProductDTOs) {
            VideoProductSimpleView view = new VideoProductSimpleView();
            BeanUtils.copyProperties(dto, view);
            videoView.getProductList().add(view);
        }
    }

    private void fillWithImgList(VideoView videoView, String videoThumbnails) {
        if (!StringUtils.hasText(videoThumbnails)) {
            return;
        }

        for (String img : videoThumbnails.split(",")) {
            videoView.getImgList().add(img);
        }
    }

    public List<Integer> extractVideoId(Page<VideoExt> page) {
        if (CollectionUtils.isEmpty(page.getList())) return Collections.emptyList();

        List<Integer> list = Lists.newArrayList();
        for (VideoExt videoExt : page.getList()) {
            list.add(videoExt.getId());
        }
        return list;
    }

    public List<VideoCommentView> toVideoCommentViews(List<VideoComment> videoCommentList, List<VideoCommentReply> replyList) {
        if (CollectionUtils.isEmpty(videoCommentList)) return Collections.emptyList();

        Map<Integer, VideoCommentReply> replyMap = MapHelper.toMap(replyList, new MapHelper.MapKey<VideoCommentReply, Integer>() {
            @Override
            public Integer key(VideoCommentReply source) {
                return source.getVideoCommentId();
            }
        });
        List<VideoCommentView> viewList = Lists.newArrayList();
        for (VideoComment videoComment : videoCommentList) {
            VideoCommentView view = new VideoCommentView();
            BeanUtils.copyProperties(videoComment, view);
            VideoCommentReply reply = replyMap.get(videoComment.getId());
            if (reply != null) {
                view.setReplyId(reply.getId());
                view.setReplyContent(reply.getContent());
            }
            viewList.add(view);
        }
        return viewList;
    }
}
