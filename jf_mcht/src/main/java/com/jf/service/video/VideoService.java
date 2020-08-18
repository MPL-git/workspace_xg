package com.jf.service.video;

import com.jf.biz.ProductBiz;
import com.jf.biz.VideoBiz;
import com.jf.common.SiteContext;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ListHelper;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.vo.video.FindVideoCommentRequest;
import com.jf.vo.video.ReplyVideoCommentRequest;
import com.jf.vo.video.SaveVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class VideoService extends VideoBiz {

    @Autowired
    private ProductBiz productBiz;
    @Autowired
    private VideoExtendMapper videoExtendMapper;
    @Autowired
    private VideoCustomMapper videoCustomMapper;
    @Autowired
    private VideoProductMapper videoProductMapper;
    @Autowired
    private SiteContext siteContext;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private VideoCommentMapper videoCommentMapper;
    @Autowired
    private VideoCommentReplyMapper videoCommentReplyMapper;
    @Autowired
    private VideoTipOffMapper videoTipOffMapper;
    @Autowired
    private VideoAuthorizedAccessoriesMapper videoAuthorizedAccessoriesMapper;

    /**
     * 商家管理列表
     */
    public Page<VideoExt> paginateMgr(VideoExtExample example) {
        if (example.getProductExtExample() != null) {
            List<Integer> productIdList = productBiz.listId(example.getProductExtExample());
            if (productIdList.size() == 0) {
                return new Page<>(example.getQueryObject().getPageNumber(), example.getQueryObject().getPageSize());
            }

            VideoExtExample.VideoExtCriteria criteria = (VideoExtExample.VideoExtCriteria) example.getOredCriteria().get(0);
            criteria.andProductIdIn(productIdList);
        }

        return paginate(example);
    }

    public List<VideoProductDTO> findByVideoIds(List<Integer> videoIds) {
        if (CollectionUtils.isEmpty(videoIds)) return Collections.emptyList();

        return videoCustomMapper.findVideoProduct(videoIds);
    }

    public List<Integer> findSelectedProductIds(Integer videoId, Integer mchtId) {
        VideoProductExample example = new VideoProductExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andMchtIdEqualTo(mchtId).andDelFlagEqualTo(Const.FLAG_FALSE);
        example.setOrderByClause("seq_no");
        List<VideoProduct> videoProductList = videoProductMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(videoProductList)) return Collections.emptyList();
        return ListHelper.extractIds(videoProductList, new ListHelper.IdExtractor<VideoProduct>() {
            @Override
            public Integer getId(VideoProduct source) {
                return source.getProductId();
            }
        });
    }

    @Transactional
    public void submitAudit(Integer videoId) {
        int mchtId = siteContext.getMchtId();
        Video originVideo = selectByPrimaryKey(videoId);
        if (originVideo == null || Const.FLAG_TRUE.equals(originVideo.getDelFlag()) || originVideo.getMchtId() != mchtId) {
            throw new BizException("视频不存在");
        }
        if (!"1".equals(originVideo.getAuditStatus())) {
            throw new BizException("状态异常，请刷新后重试");
        }
        originVideo.setAuditTime(new Date());//审核时间
        originVideo.setAuditStatus("2");
        originVideo.setUpdateBy(mchtId);
        originVideo.setUpdateDate(new Date());
        updateByPrimaryKey(originVideo);
    }

    @Transactional
    public void deleteVideo(Integer videoId) {
        int mchtId = siteContext.getMchtId();
        Video originVideo = selectByPrimaryKey(videoId);
        if (originVideo == null || Const.FLAG_TRUE.equals(originVideo.getDelFlag()) || originVideo.getMchtId() != mchtId) {
            throw new BizException("视频不存在");
        }
        if ("3".equals(originVideo.getAuditStatus())) {  //优化后通过上架后状态5，都不显示删除了。
            throw new BizException("此视频无法删除");
        }

        originVideo.setDelFlag(Const.FLAG_TRUE);
        originVideo.setUpdateBy(mchtId);
        originVideo.setUpdateDate(new Date());
        updateByPrimaryKey(originVideo);

        VideoExtend videoExtend = new VideoExtend();
        videoExtend.setDelFlag(Const.FLAG_TRUE);
        VideoExtendExample example = new VideoExtendExample();
        example.createCriteria().andVideoIdEqualTo(videoId);
        videoExtendMapper.updateByExampleSelective(videoExtend, example);
    }

    @Transactional
    public void updateVideoStatus(Integer videoId, int type) {
        int mchtId = siteContext.getMchtId();
        Video originVideo = selectByPrimaryKey(videoId);
        if (originVideo == null || Const.FLAG_TRUE.equals(originVideo.getDelFlag()) || originVideo.getMchtId() != mchtId) {
            throw new BizException("视频不存在");
        }
        if (!"5".equals(originVideo.getAuditStatus())) {  //现在是法务通过才可以下架了，不是3
            throw new BizException("视频尚未审核通过");
        }
        originVideo.setMchtId(mchtId);
        originVideo.setUpdateDate(new Date());

        if (type == 1) {
            onlineVideo(originVideo);
        } else {
            offlineVideo(originVideo);
        }
    }

    private void onlineVideo(Video originVideo) {
        if ("1".equals(originVideo.getAuditStatus())) {
            throw new BizException("视频已上架");
        }

        VideoTipOffExample example = new VideoTipOffExample();
        example.createCriteria().andVideoIdEqualTo(originVideo.getId()).andDelFlagEqualTo(Const.FLAG_FALSE).andAuditStatusEqualTo("2");
        List<VideoTipOff> tipOffList = videoTipOffMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(tipOffList)) {
            throw new BizException("被举报过的视频无法重新上架");
        }

        originVideo.setStatus("1");
        updateByPrimaryKey(originVideo);
    }

    private void offlineVideo(Video originVideo) {
        if ("0".equals(originVideo.getAuditStatus())) {
            throw new BizException("视频已下架");
        }
        originVideo.setStatus("0");
        updateByPrimaryKey(originVideo);
    }

    @Transactional
    public void createVideo(SaveVideoRequest request) {
        int mchtId = siteContext.getMchtId();
        Product product = productMapper.selectByPrimaryKey(request.getProductIds().get(0)); //取首个商品

        Video video = new Video();
        video.setTitle(request.getTitle());
        video.setDescription(request.getDescription());
        video.setVideoUrl(request.getVideoUrl());
        video.setVideoCover(request.getVideoCover());
        video.setVideoThumbnails(request.getVideoThumbnails());
        video.setVideoTime((int) request.getVideoTime());
        video.setVideoSize((int) (request.getVideoSize()));
        video.setVideoWidth(request.getVideoWidth());
        video.setVideoHeight(request.getVideoHeight());
        video.setStatus("0"); //下架
        video.setAuditStatus("1"); //待提审
        video.setDelFlag(Const.FLAG_FALSE);
        video.setMchtId(mchtId);
        video.setProductTypeId(product.getProductTypeId());
        video.setCreateBy(mchtId);
        video.setCreateDate(new Date());
        video.setManualWeicht(0);
        video.setSeasonWeight(pickMaxSeasonWeight(request.getProductIds()));
        video.setCollectWeight(0);
        video.setCommentWeight(0);
        video.setLikeWeicht(0);
        video.setPlayWeight(0);
        video.setTotalWeicht(video.getManualWeicht() + video.getSeasonWeight() + video.getCollectWeight() + video.getCommentWeight() + video.getLikeWeicht() + video.getPlayWeight());
        video.setVideoSource(request.getVideoSource());
        insert(video);
        saveVideoExtend(video);
        saveVideoProduct(request.getProductIds(), video.getId(), mchtId);
        if (request.getVideoAuthorizedAccessoriesList()!=null){
        saveVideoAuthorizedAccessories(video.getId(),request.getVideoAuthorizedAccessoriesList());
        }
    }

   //添加附件
    private void saveVideoAuthorizedAccessories( Integer videoId,  List<VideoAuthorizedAccessories> VideoAuthorizedAccessoriesList) {
        VideoAuthorizedAccessories videoAuthorizedAccessories = new VideoAuthorizedAccessories();
        for (VideoAuthorizedAccessories authorizedAccessories : VideoAuthorizedAccessoriesList) {
            if (authorizedAccessories.getFileName().length() > 64) {
                throw new BizException("附件文件名过长，请修改后再次上传");
            }
            videoAuthorizedAccessories.setVideoId(videoId);
            videoAuthorizedAccessories.setFileName(authorizedAccessories.getFileName());
            videoAuthorizedAccessories.setFilePath(authorizedAccessories.getFilePath());
            videoAuthorizedAccessories.setCreateDate(new Date());
            videoAuthorizedAccessories.setCreateBy(0);
            videoAuthorizedAccessories.setDelFlag("0");
            videoAuthorizedAccessoriesMapper.insert(videoAuthorizedAccessories);
        }


    }





    @Autowired
    private ProductWeightMapper productWeightMapper;

    private Integer pickMaxSeasonWeight(List<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) return 0;

        ProductWeightExample example = new ProductWeightExample();
        example.createCriteria().andProductIdIn(productIds).andDelFlagEqualTo(Const.FLAG_FALSE);
        List<ProductWeight> list = productWeightMapper.selectByExample(example);
        Integer maxSeasonWeight = 0;
        for (ProductWeight productWeight : list) {
            if (productWeight.getSeasonWeight() != null && productWeight.getSeasonWeight() > maxSeasonWeight) {
                maxSeasonWeight = productWeight.getSeasonWeight();
            }
        }
        return maxSeasonWeight;
    }

    private void saveVideoExtend(Video video) {
        VideoExtend videoExtend = new VideoExtend();
        videoExtend.setVideoId(video.getId());
        videoExtend.setMchtId(video.getMchtId());
        videoExtend.setDelFlag(Const.FLAG_FALSE);
        videoExtend.setCollectCount(0);
        videoExtend.setPlayCount(0);
        videoExtend.setCommentCount(0);
        videoExtend.setLikeCount(0);
        videoExtend.setCreateBy(video.getMchtId());
        videoExtend.setCreateDate(new Date());
        videoExtendMapper.insert(videoExtend);
    }

    private void saveVideoProduct(List<Integer> productIds, Integer videoId, Integer mchtId) {
        if (CollectionUtils.isEmpty(productIds)) return;

        Date now = new Date();
        int index = 1;
        for (Integer productId : productIds) {
            VideoProduct videoProduct = new VideoProduct();
            videoProduct.setVideoId(videoId);
            videoProduct.setMchtId(mchtId);
            videoProduct.setProductId(productId);
            videoProduct.setCreateBy(mchtId);
            videoProduct.setCreateDate(now);
            videoProduct.setDelFlag(Const.FLAG_FALSE);
            videoProduct.setSeqNo(index++);
            videoProductMapper.insert(videoProduct);
        }
    }

    @Transactional
    public void updateVideo(SaveVideoRequest request) {
        int mchtId = siteContext.getMchtId();
        Video originVideo = selectByPrimaryKey(request.getVideoId());
        if (originVideo == null || Const.FLAG_TRUE.equals(originVideo.getDelFlag())) {
            throw new BizException("视频不存在");
        }
        if (originVideo.getMchtId() != mchtId) {
            throw new BizException("您无权操作该视频");
        }
        if ("1".equals(originVideo.getStatus())) {
            throw new BizException("请先下架该视频");
        }
        if ("3".equals(originVideo.getAuditStatus())) {
            throw new BizException("此视频无法修改");
        }

        Product product = productMapper.selectByPrimaryKey(request.getProductIds().get(0)); //取首个商品
        originVideo.setTitle(request.getTitle());
        originVideo.setDescription(request.getDescription());
        originVideo.setVideoCover(request.getVideoCover());
        originVideo.setVideoUrl(request.getVideoUrl());
        originVideo.setVideoThumbnails(request.getVideoThumbnails());
        originVideo.setVideoSize((int) (request.getVideoSize()));
        originVideo.setVideoTime((int) request.getVideoTime());
        originVideo.setVideoWidth(request.getVideoWidth());
        originVideo.setVideoHeight(request.getVideoHeight());
        if ("4".equals(originVideo.getAuditStatus()) || "3".equals(originVideo.getAuditStatus()) || "6".equals(originVideo.getAuditStatus())) {
            originVideo.setAuditStatus("2"); //待审核
        } else {
            originVideo.setAuditStatus("1"); //待提审
        }
        originVideo.setProductTypeId(product.getProductTypeId());
        originVideo.setUpdateBy(mchtId);
        originVideo.setUpdateDate(new Date());
        //修改視頻來源
        originVideo.setVideoSource(request.getVideoSource());
        updateByPrimaryKey(originVideo);

        bulkDeleteVideoProduct(originVideo.getId());
        saveVideoProduct(request.getProductIds(), originVideo.getId(), mchtId);
        //修改附件

        bulkDeleteVideoAuthorizedAccessories(originVideo.getId());
        if (request.getVideoAuthorizedAccessoriesList()!=null){
        saveVideoAuthorizedAccessories(request.getVideoId(), request.getVideoAuthorizedAccessoriesList());
        }
    }

    private void bulkDeleteVideoAuthorizedAccessories(Integer id) {
//        VideoAuthorizedAccessoriesExample example = new VideoAuthorizedAccessoriesExample();
//        example.createCriteria().andVideoIdEqualTo(id);
//        videoAuthorizedAccessoriesMapper.deleteByExample(example);
        VideoAuthorizedAccessoriesExample videoAuthorizedAccessoriesExample = new VideoAuthorizedAccessoriesExample();
        videoAuthorizedAccessoriesExample.createCriteria().andDelFlagEqualTo("0").andVideoIdEqualTo(id);
        VideoAuthorizedAccessories videoAuthorizedAccessories = new VideoAuthorizedAccessories();
        videoAuthorizedAccessories.setDelFlag("1");
        videoAuthorizedAccessoriesMapper.updateByExampleSelective(videoAuthorizedAccessories,videoAuthorizedAccessoriesExample);
    }


    private void bulkDeleteVideoProduct(Integer id) {
        VideoProductExample example = new VideoProductExample();
        example.createCriteria().andVideoIdEqualTo(id);
        videoProductMapper.deleteByExample(example);
    }

    public int countComment(Integer videoId, int mchtId) {
        if (videoId == null) return 0;

        VideoCommentExample example = new VideoCommentExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andMchtIdEqualTo(mchtId).andDelFlagEqualTo(Const.FLAG_FALSE);
        return videoCommentMapper.countByExample(example);
    }

    public List<VideoComment> findComment(int mchtId, FindVideoCommentRequest request) {
        VideoCommentExample example = new VideoCommentExample();
        VideoCommentExample.Criteria criteria = example.createCriteria();
        criteria.andVideoIdEqualTo(request.getVideoId()).andMchtIdEqualTo(mchtId).andDelFlagEqualTo(Const.FLAG_FALSE);
        if (DateUtil.validateDate(request.getDateBegin())) {
            criteria.andCreateDateGreaterThanOrEqualTo(DateUtil.stringToDate(request.getDateBegin() + DateUtil.TIME_START));
        }
        if (DateUtil.validateDate(request.getDateEnd())) {
            criteria.andCreateDateLessThanOrEqualTo(DateUtil.stringToDate(request.getDateEnd() + DateUtil.TIME_END));
        }
        example.setOrderByClause("create_date desc");
        example.setLimitStart(request.getOffset());
        example.setLimitSize(request.getPageSize());
        return videoCommentMapper.selectByExample(example);
    }

    @Transactional
    public void hideComment(Integer commentId) {
        VideoComment originVideoComment = videoCommentMapper.selectByPrimaryKey(commentId);
        int mchtId = siteContext.getMchtId();
        if (originVideoComment == null || Const.FLAG_TRUE.equals(originVideoComment.getDelFlag()) || mchtId != originVideoComment.getMchtId()) {
            throw new BizException("评论不存在");
        }
        if ("0".equals(originVideoComment.getStatus())) {
            throw new BizException("评论已隐藏");
        }

        originVideoComment.setStatus("0");
        originVideoComment.setUpdateBy(mchtId);
        originVideoComment.setUpdateDate(new Date());
        videoCommentMapper.updateByPrimaryKey(originVideoComment);

        videoCommentCountAdd(originVideoComment.getVideoId(), -1);
    }


    @Transactional
    public void videoCommentCountAdd(Integer videoId, int increment) {
        VideoExtendExample example = new VideoExtendExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andDelFlagEqualTo(Const.FLAG_FALSE);
        List<VideoExtend> originExtends = videoExtendMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(originExtends)) return;

        VideoExtend originExtend = originExtends.get(0);
        VideoExtend newExtend = new VideoExtend();
        newExtend.setId(originExtend.getId());
        newExtend.setCommentCount(originExtend.getCommentCount() + increment);
        videoExtendMapper.updateByPrimaryKeySelective(newExtend);
    }

    @Transactional
    public void replyComment(ReplyVideoCommentRequest request) {
        VideoComment originVideoComment = videoCommentMapper.selectByPrimaryKey(request.getCommentId());
        MchtInfoCustom mchtInfo = siteContext.getSessionMchtInfo();
        if (originVideoComment == null || Const.FLAG_TRUE.equals(originVideoComment.getDelFlag()) || !mchtInfo.getId().equals(originVideoComment.getMchtId())) {
            throw new BizException("评论不存在");
        }

        VideoCommentReply originReply = getVideoCommentReply(request.getCommentId());
        if (request.getReplyId() == null) { //添加回复
            if (originReply != null) {
                throw new BizException("您已回复过该条评论");
            }

            VideoCommentReply reply = new VideoCommentReply();
            reply.setVideoId(originVideoComment.getVideoId());
            reply.setVideoCommentId(request.getCommentId());
            reply.setReplier("1"); //店家
            reply.setReplierId(mchtInfo.getId());
            reply.setReplierAvatar(mchtInfo.getShopLogo());
            reply.setReplierNick(mchtInfo.getShopName());
            reply.setContent(request.getContent());
            reply.setDelFlag(Const.FLAG_FALSE);
            reply.setCreateBy(mchtInfo.getId());
            reply.setCreateDate(new Date());
            videoCommentReplyMapper.insert(reply);
        } else { //修改回复
            if (originReply == null) {
                throw new BizException("您尚未回复评论");
            }
            originReply.setContent(request.getContent());
            originReply.setUpdateBy(mchtInfo.getId());
            originReply.setUpdateDate(new Date());
            videoCommentReplyMapper.updateByPrimaryKey(originReply);
        }


    }

    private VideoCommentReply getVideoCommentReply(Integer commentId) {
        VideoCommentReplyExample example = new VideoCommentReplyExample();
        example.createCriteria().andVideoCommentIdEqualTo(commentId).andDelFlagEqualTo(Const.FLAG_FALSE);
        List<VideoCommentReply> list = videoCommentReplyMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public List<VideoCommentReply> findCommentReply(List<Integer> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) return Collections.emptyList();

        VideoCommentReplyExample example = new VideoCommentReplyExample();
        example.createCriteria().andVideoCommentIdIn(commentIds).andDelFlagEqualTo(Const.FLAG_FALSE);
        return videoCommentReplyMapper.selectByExample(example);
    }

    public List<Integer> findTipOffVideoIds(List<Integer> videoIds) {
        if (CollectionUtils.isEmpty(videoIds)) return Collections.emptyList();

        return videoCustomMapper.findTipOffVideoIds(videoIds);
    }
}
