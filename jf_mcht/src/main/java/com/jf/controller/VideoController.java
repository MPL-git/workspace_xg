package com.jf.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jf.common.base.PageResult;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.*;
import com.jf.dao.InformationMapper;
import com.jf.dao.VideoAuthorizedAccessoriesMapper;
import com.jf.entity.*;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.service.MchtProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ShopProductCustomTypeService;
import com.jf.service.video.VideoConverter;
import com.jf.service.video.VideoService;
import com.jf.vo.video.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class VideoController extends BaseController {

    @Resource
    private VideoService videoService;
    @Autowired
    private VideoConverter videoConverter;
    @Autowired
    private MchtProductBrandService mchtProductBrandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopProductCustomTypeService shopProductCustomTypeService;
    @Resource
    private InformationMapper informationMapper;
    @Resource
    private VideoAuthorizedAccessoriesMapper videoAuthorizedAccessoriesMapper;

    /**
     * 页面：视频专区
     */
    @RequestMapping(value = "/video/mgr/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        //model.put("auditStatusList", VideoAuditStatus.values());
        return "video/list";
    }

    /**
     * 页面：添加视频
     */
    @RequestMapping(value = "/video/mgr/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("videoSourceStatusList",DataDicUtil.getStatusList("bu_video", "video_source"));
        return "video/add";
    }

    /**
     * 页面：编辑视频
     */
    @RequestMapping(value = "/video/mgr/{videoId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "videoId") Integer videoId, Map<String, Object> model) {
        int mchtId = getMchtId();
        Video video = videoService.findById(videoId);
        if (video != null) {
            model.put("video", video);
            model.put("videoFirstFrame", video.getVideoThumbnails().split(",")[0]);
            model.put("fullVideoUrl", FileUtil.getVideo(video.getVideoUrl()));
            model.put("videoSourceStatusList",DataDicUtil.getStatusList("bu_video", "video_source"));
            //附件信息
            VideoAuthorizedAccessoriesExample videoAuthorizedAccessoriesExample = new VideoAuthorizedAccessoriesExample();
            videoAuthorizedAccessoriesExample.createCriteria().andDelFlagEqualTo("0").andVideoIdEqualTo(videoId);
            List<VideoAuthorizedAccessories> videoAuthorizedAccessorieList = videoAuthorizedAccessoriesMapper.selectByExample(videoAuthorizedAccessoriesExample);
            model.put("videoAuthorizedAccessorieList",videoAuthorizedAccessorieList);
        }
        List<Integer> selectedProductIdList = videoService.findSelectedProductIds(videoId, mchtId);
        if (!CollectionUtils.isEmpty(selectedProductIdList)) {
            fillWithProductInfo(model, mchtId, selectedProductIdList);
        }
        return "video/edit";
    }

    private void fillWithProductInfo(Map<String, Object> model, Integer mchtId, List<Integer> selectedProductIdList) {
        ProductCustomExample productCustomExample = new ProductCustomExample();
        ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
        productCustomCriteria.andMchtIdEqualTo(mchtId);
        productCustomExample.setOrderByClause("id desc");
        productCustomCriteria.andDelFlagEqualTo(Constant.FLAG_FALSE);
        productCustomCriteria.andAuditStatusEqualTo("2"); //审核通过
        productCustomCriteria.andSaleTypeEqualTo("1"); //品牌款
        productCustomCriteria.andIdIn(selectedProductIdList);
        List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
        List<VideoProductView> viewList = Lists.newArrayList();
        for (ProductCustom productCustom : productCustoms) {
            if (!StringUtil.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")) {
                productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
            }
            VideoProductView view = new VideoProductView();
            BeanUtils.copyProperties(productCustom, view);
            view.setSelected(true);
            viewList.add(view);
        }
        model.put("productList", viewList);
    }

    /**
     * 页面：选择商品
     */
    @RequestMapping(value = "/video/toSelectProduct", method = RequestMethod.GET)
    public String productIndex(Map<String, Object> model) {
        List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(getMchtId());
        model.put("productBrandList", productBrandList);
        List<ShopProductCustomType> shopProductCustomTypeList = shopProductCustomTypeService.findByMchtId(getMchtId());
        model.put("shopProductCustomTypeList", shopProductCustomTypeList);
        return "video/toSelectProduct";
    }

    /**
     * 页面：视频评论
     */
    @RequestMapping(value = "/video/mgr/comment/index", method = RequestMethod.GET)
    public String videoCommentIndex(Map<String, Object> model) {
        model.put("videoId", getParaToInt("videoId", null));
        return "video/commentList";
    }

    /**
     * 视频列表查询
     */
    @ResponseBody
    @RequestMapping(value = "/video/mgr/list", method = RequestMethod.POST)
    public ResponseMsg list() {
        String title = getPara("title");
        String dateBegin = getPara("createDateMoreOrEquals");
        String dateEnd = getPara("createDateLessOrEquals");
        Date createDateMoreOrEquals = DateUtil.stringToDate(dateBegin + DateUtil.TIME_START);
        Date createDateLessOrEquals = DateUtil.stringToDate(dateEnd + DateUtil.TIME_END);
        String auditStatus = getPara("auditStatus");
        String searchStatus = getPara("searchStatus"); //1表示全部，2表示查驳回状态

        if (!StringUtil.isBlank(searchStatus) && "2".equals(searchStatus)) {
            auditStatus = "4";
        }

        String productCode = getPara("productCode");
        String productName = getPara("productName");

        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", Constant.DEFAULT_PAGE_SIZE);
        MchtInfo mchtInfo = getMchtInfo();

        Map<String, Object> data = new HashMap<>();
        VideoExtExample example = new VideoExtExample();
        VideoExtExample.VideoExtCriteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(Constant.FLAG_FALSE);
        criteria.andMchtIdEqualTo(mchtInfo.getId());
        if (StrKit.notBlank(title)) {
            criteria.andTitleLike("%" + title.trim() + "%");
        }
        if (createDateMoreOrEquals != null) {
            criteria.andCreateDateGreaterThanOrEqualTo(createDateMoreOrEquals);
        }
        if (createDateLessOrEquals != null) {
            criteria.andCreateDateLessThanOrEqualTo(createDateLessOrEquals);
        }
        if (StrKit.notBlank(auditStatus)) {
            if (!StringUtil.isBlank(searchStatus) && "2".equals(searchStatus)) {
                criteria.andAuditStatusIn(Lists.newArrayList("4","6"));
            }else {
                if ("99".equals(auditStatus)) {
                    criteria.andAuditStatusIn(Lists.newArrayList("4", "6"));
                } else {
                    criteria.andAuditStatusEqualTo(auditStatus);
                }
            }
        }
        if (StrKit.notBlank(productCode) || StrKit.notBlank(productName)) {
            ProductExtExample productExtExample = new ProductExtExample();
            ProductExtExample.ProductExtCriteria productExtCriteria = productExtExample.createCriteria();
            if (StrKit.notBlank(productCode)) {
                productExtCriteria.andCodeEqualTo(productCode.trim());
            }
            if (StrKit.notBlank(productName)) {
                productExtCriteria.andNameLike("%" + productName.trim() + "%");
            }
            example.setProductExtExample(productExtExample);
        }

        example.getQueryObject().setPageNumber(pageNumber);
        example.getQueryObject().setPageSize(pageSize);
        example.getQueryObject().addSort("id", "desc");
        Page<VideoExt> page = videoService.paginateMgr(example);
        List<Integer> videoIds = videoConverter.extractVideoId(page);
        List<VideoProductDTO> productList = videoService.findByVideoIds(videoIds);
        List<Integer> tipOffVideoIds = videoService.findTipOffVideoIds(videoIds);

        data.put("page", videoConverter.toFindVideoResponse(page, productList, tipOffVideoIds));
        return ResponseMsg.success(data);
    }

    /**
     * 保存视频
     */
    @ResponseBody
    @RequestMapping(value = "/video/mgr/save", method = RequestMethod.POST)
    public ResponseMsg save(@RequestBody @Valid SaveVideoRequest request) {
        if (request.getVideoId() != null) {
            videoService.updateVideo(request);
        } else {
            videoService.createVideo(request);
        }
        return ResponseMsg.success();
    }

    /**
     * 删除视频
     */
    @ResponseBody
    @RequestMapping(value = "/video/{videoId}/mgr/delete", method = RequestMethod.POST)
    public ResponseMsg delete(@PathVariable("videoId") Integer videoId) {
        videoService.deleteVideo(videoId);
        return ResponseMsg.success();
    }

    /**
     * 提交审核
     */
    @ResponseBody
    @RequestMapping(value = "/video/{videoId}/mgr/submitAudit", method = RequestMethod.POST)
    public ResponseMsg submitAudit(@PathVariable("videoId") Integer videoId) {
        videoService.submitAudit(videoId);
        return ResponseMsg.success();
    }

    /**
     * 下架
     */
    @ResponseBody
    @RequestMapping(value = "/video/{videoId}/mgr/offline", method = RequestMethod.POST)
    public ResponseMsg videoOffline(@PathVariable("videoId") Integer videoId) {
        videoService.updateVideoStatus(videoId, 0);
        return ResponseMsg.success();
    }

    /**
     * 上架
     */
    @ResponseBody
    @RequestMapping(value = "/video/{videoId}/mgr/online", method = RequestMethod.POST)
    public ResponseMsg videoOnline(@PathVariable("videoId") Integer videoId) {
        videoService.updateVideoStatus(videoId, 1);
        return ResponseMsg.success();
    }

    /**
     * 商品列表查询
     */
    @RequestMapping(value = "/video/toSelectProduct/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg getProductList(FindVideoProductRequest request) {
        int mchtId = getMchtId();
        ProductCustomExample productCustomExample = new ProductCustomExample();
        ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
        productCustomCriteria.andMchtIdEqualTo(mchtId);
        productCustomExample.setOrderByClause("id desc");
        productCustomCriteria.andDelFlagEqualTo(Constant.FLAG_FALSE);
        productCustomCriteria.andSaleTypeEqualTo("1"); //品牌款
        productCustomCriteria.andAuditStatusEqualTo("2"); //审核通过
        productCustomCriteria.andStatusEqualTo("1"); //上架

        if (request.getSearchProductBrandId() != null) {
            productCustomCriteria.andBrandIdEqualTo(request.getSearchProductBrandId());
        }

        if (request.getProductType3() != null) {// 第三级分类
            productCustomCriteria.andProductTypeAll(request.getProductType3());
        } else if (request.getProductType2() != null) {
            productCustomCriteria.andProductTypeAll(request.getProductType2());
        } else if (request.getProductType1() != null) {
            productCustomCriteria.andProductTypeAll(request.getProductType1());
        }

        if (request.getSearchShopProductCustomTypeId() != null) { //商家分类
            if (request.getSearchShopProductCustomTypeId() != -1) {
                productCustomCriteria.andShopProductCustomTypeIdEqualTo(String.valueOf(request.getSearchShopProductCustomTypeId()));
            } else {
                productCustomCriteria.andShopProductCustomTypeIdEqualNull();
            }
        }

        if (StringUtils.hasText(request.getSearchKeywrod())) {
            switch (request.getSearchKeywrodType()) {
                case "1":
                    productCustomCriteria.andNameLike("%" + request.getSearchKeywrod() + "%");
                    break;
                case "2":
                    productCustomCriteria.andArtNoLike("%" + request.getSearchKeywrod() + "%");
                    break;
                case "3":
                    productCustomCriteria.andCodeLike("%" + request.getSearchKeywrod() + "%");
                    break;
                case "4":
                    productCustomCriteria.andRemarksLike("%" + request.getSearchKeywrod() + "%");
                    break;
                case "5":
                    try {
                        Integer id = Integer.valueOf(request.getSearchKeywrod());
                        productCustomCriteria.andActivityIdEqualTo(id);
                    } catch (NumberFormatException e) {
                        productCustomCriteria.andActivityIdEqualTo(0);
                    }
                    break;
                default:
                    break;
            }
        }
        int totalCount = productService.countProductCustomByExample(productCustomExample);

        productCustomExample.setLimitStart(request.getOffset());
        productCustomExample.setLimitSize(request.getPageSize());
        List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);

        Set<Integer> selectedProductIdSet = Sets.newHashSet();
        if (request.getVideoId() != null) {
            selectedProductIdSet.addAll(videoService.findSelectedProductIds(request.getVideoId(), mchtId));
        }
        List<VideoProductView> viewList = Lists.newArrayList();
        for (ProductCustom productCustom : productCustoms) {
            if (!StringUtil.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")) {
                productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
            }
            VideoProductView view = new VideoProductView();
            BeanUtils.copyProperties(productCustom, view);
            view.setSelected(selectedProductIdSet.contains(productCustom.getId()));
            viewList.add(view);
        }
        Map<String, Object> returnData = Maps.newHashMap();
        returnData.put("Rows", viewList);
        returnData.put("Total", totalCount);
        return ResponseMsg.success(returnData);
    }

    /**
     * 视频评论列表查询
     */
    @ResponseBody
    @RequestMapping(value = "/video/mgr/comment/list", method = RequestMethod.POST)
    public ResponseMsg commentList(FindVideoCommentRequest request) {
        int mchtId = getMchtId();
        int totalCount = videoService.countComment(request.getVideoId(), mchtId);
        if (totalCount == 0) {
            return ResponseMsg.success(PageResult.empty());
        }
        List<VideoComment> videoCommentList = videoService.findComment(mchtId, request);
        List<VideoCommentReply> replyList = videoService.findCommentReply(ListHelper.extractIds(videoCommentList, new ListHelper.IdExtractor<VideoComment>() {
            @Override
            public Integer getId(VideoComment source) {
                return source.getId();
            }
        }));
        List<VideoCommentView> viewList = videoConverter.toVideoCommentViews(videoCommentList, replyList);
        return ResponseMsg.success(PageResult.of(totalCount, viewList));
    }

    /**
     * 视频评论隐藏
     */
    @ResponseBody
    @RequestMapping(value = "/video/mgr/comment/{commentId}/hide", method = RequestMethod.POST)
    public ResponseMsg hideComment(@PathVariable("commentId") Integer commentId) {
        videoService.hideComment(commentId);
        return ResponseMsg.success();
    }

    /**
     * 视频评论回复/修改
     */
    @ResponseBody
    @RequestMapping(value = "/video/mgr/comment/reply", method = RequestMethod.POST)
    public ResponseMsg replyComment( @RequestBody @Valid ReplyVideoCommentRequest request) {
        videoService.replyComment(request);
        return ResponseMsg.success();
    }

    /**
     * 视频上传协议页面
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/video/videoUploadAgreement")
    public String toAgreement(Model model, HttpServletRequest request) {
        InformationExample e = new InformationExample();
        e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdEqualTo(265);//匹配id
        List<Information> informations = informationMapper.selectByExampleWithBLOBs(e);
        if(informations!=null && informations.size()>0){
            model.addAttribute("content", informations.get(0).getContent());
        }
        return "video/videoUploadAgreement";
    }


}
