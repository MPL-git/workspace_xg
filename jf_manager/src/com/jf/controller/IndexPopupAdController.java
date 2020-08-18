package com.jf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.common.ext.core.ABaseController;
import com.jf.entity.*;
import com.jf.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.vo.Page;

@Controller
public class IndexPopupAdController extends BaseController {

    private static final long serialVersionUID = 1L;

    @Resource
    private IndexPopupAdService indexPopupAdService;

    @Resource
    private ProductTypeService productTypeService;

    @Resource
    private BrandteamTypeService brandteamTypeService;

    @Resource
    private ProductService productService;

    @Resource
    private MchtInfoService mchtInfoService;

    @Resource
    private MallCategoryService mallCategoryService;

    @Resource
    private ActivityAreaService activityAreaService;

    @Resource
    private ActivityService activityService;

    @Resource
    private CouponService couponService;

    @Resource
    private CustomPageService customPageService;

    @Resource
    private MemberLabelGroupService memberLabelGroupService;

    //首页广告弹窗
    @RequestMapping("/homepagePopup/indexPopupAd.shtml")
    public ModelAndView indexPopupAd(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        m.setViewName("/homepagePopup/indexPopupAdList");
        return m;
    }

    //首页广告弹窗数据
    @ResponseBody
    @RequestMapping("/homepagePopup/indexPopupAddata.shtml")
    public Map<String, Object> indexPopupAddata(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<IndexPopupAdCustom> dataList = null;
        Integer totalCount = 0;
        try {
            IndexPopupAdCustomExample indexPopupAdCustomExample = new IndexPopupAdCustomExample();
            indexPopupAdCustomExample.createCriteria().andDelFlagEqualTo("0");

            indexPopupAdCustomExample.setLimitStart(page.getLimitStart());
            indexPopupAdCustomExample.setLimitSize(page.getLimitSize());
            totalCount = indexPopupAdService.indexPopupAdcountByExample(indexPopupAdCustomExample);
            dataList = indexPopupAdService.indexPopupAdselectByExample(indexPopupAdCustomExample);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }


    //添加广告弹窗界面
    @RequestMapping(value = "/homepagePopup/editIndexPopupAd.shtml")
    public ModelAndView editIndexPopupAd(HttpServletRequest request) {
        String rtPage = "/homepagePopup/editIndexPopupAd";
        Map<String, Object> resMap = new HashMap<String, Object>();


        //上架的品牌团名称
        BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
        brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
        List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample);
        resMap.put("brandteamTypes", brandteamTypes);

        //九大类目
        ProductTypeExample example = new ProductTypeExample();
        example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
        List<ProductType> productTypes = productTypeService.selectByExample(example);
        resMap.put("productTypes", productTypes);

        //商城一级分类
        MallCategoryExample mallCategoryExample = new MallCategoryExample();
        mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
        List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample);
        resMap.put("mallCategorys", mallCategorys);


        String id = request.getParameter("id");
        if (!StringUtil.isEmpty(id)) {
            IndexPopupAdCustom indexPopupAdCustom = indexPopupAdService.indexPopupAdselectByPrimaryKey(Integer.valueOf(id));

            if ("3".equals(indexPopupAdCustom.getLinkType())) {
                Product product = productService.selectByPrimaryKey(Integer.parseInt(indexPopupAdCustom.getLinkContent()));
                if (product != null) {
                    indexPopupAdCustom.setLinkContent(product.getCode());
                }
            }
            if ("10".equals(indexPopupAdCustom.getLinkType())) {//将店铺id转为code存入
                MchtInfo mcht = mchtInfoService.selectByPrimaryKey(Integer.parseInt(indexPopupAdCustom.getLinkContent()));
                if (mcht != null) {
                    indexPopupAdCustom.setLinkContent(mcht.getMchtCode());
                }
            }

            resMap.put("indexPopupAdCustom", indexPopupAdCustom);
        }
		/*ProductTypeExample productTypeExample=new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypes",productTypes);//一级类目
*/
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 选择完后提交到编辑广告页面
     */
    @RequestMapping(value = "/homepagePopup/addIndexPopupData.shtml")
    @ResponseBody
    public ModelAndView addIndexPopupData(HttpServletRequest request, IndexPopupAd indexPopupAd, String id, String linkContent) {
        String rtPage = "/homepagePopup/editIndexPopupAd";
        Map<String, Object> map = new HashMap<String, Object>();
        //String selectGroup = request.getParameter("selectGroup");
        //if (indexPopupAd.getId()!=null){
        //    IndexPopupAd indexPopupAd2 = indexPopupAdService.selectByPrimaryKey(indexPopupAd.getId());
        //    resMap.put("indexPopupAdCustom", indexPopupAd2);
        //}else {
        //    resMap.put("indexPopupAdCustom", indexPopupAd);
        //}
        Map<String, Object> resMap = tranData(request, indexPopupAd, id, linkContent, map);
        //上架的品牌团名称
        BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
        brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
        List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample);
        resMap.put("brandteamTypes", brandteamTypes);

        //九大类目
        ProductTypeExample example = new ProductTypeExample();
        example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
        List<ProductType> productTypes = productTypeService.selectByExample(example);
        resMap.put("productTypes", productTypes);

        //商城一级分类
        MallCategoryExample mallCategoryExample = new MallCategoryExample();
        mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
        List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample);
        resMap.put("mallCategorys", mallCategorys);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 选择用户标签分组
     */
    @RequestMapping("/homepagePopup/selectGroupList.shtml")
    public ModelAndView selectGroupList(HttpServletRequest request, IndexPopupAd indexPopupAd, String id, String linkContent) {
        String rtPage = "homepagePopup/selectGroupList";
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resMap = tranData(request, indexPopupAd, id, linkContent, map);
        return new ModelAndView(rtPage, resMap);
    }

    //页面跳转传送的回显数据
    public Map<String, Object> tranData(HttpServletRequest request, IndexPopupAd indexPopupAd, String id, String linkContent, Map<String, Object> resMap) {
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String DownDate = request.getParameter("downDate");
            String UpDate = request.getParameter("upDate");
            if (indexPopupAd.getId() == null) {//添加
                indexPopupAd.setCreateBy(staffId);
                indexPopupAd.setCreateDate(new Date());
                indexPopupAd.setDelFlag("0");
                indexPopupAd.setStatus("0");

                if ("3".equals(indexPopupAd.getLinkType())) {
                    String pcode = indexPopupAd.getLinkContent();
                    ProductExample productExample = new ProductExample();
                    productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode + "");
                    List<Product> products = productService.selectByExample(productExample);
                    if (products.size() > 0 && products != null) {
                        Product product = products.get(0);
                        indexPopupAd.setLinkContent(product.getId() + "");
                    }

                }
                if ("10".equals(indexPopupAd.getLinkType())) {//将店铺code转成主键ID 存入
                    String mcode = indexPopupAd.getLinkContent();
                    MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
                    mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode + "");
                    List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
                    if (mchtInfoCustoms.size() > 0 && mchtInfoCustoms != null) {
                        MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
                        indexPopupAd.setLinkContent(mchtInfoCustom.getId() + "");
                    }
                }
                if (!"".equals(DownDate) && !StringUtils.isEmpty(DownDate)) {
                    indexPopupAd.setDownDate(sdf.parse(DownDate));
                }
                if (!"".equals(UpDate) && !StringUtils.isEmpty(UpDate)) {
                    indexPopupAd.setUpDate(sdf.parse(UpDate));
                }
                resMap.put("indexPopupAdCustom", indexPopupAd);

            } else {//更新{returnCode=0, returnMsg=请输入正确的优惠券ID

                IndexPopupAd indexPopupAd2 = indexPopupAdService.selectByPrimaryKey(Integer.valueOf(id));
                indexPopupAd2.setUpdateDate(new Date());
                indexPopupAd2.setUpdateBy(staffId);

                if ("3".equals(indexPopupAd.getLinkType())) {
                    String pcode = indexPopupAd.getLinkContent();
                    ProductExample productExample = new ProductExample();
                    productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode + "");
                    List<Product> products = productService.selectByExample(productExample);
                    if (products.size() > 0 && products != null) {
                        Product product = products.get(0);
                        indexPopupAd.setLinkContent(product.getId() + "");
                    }

                } else if ("10".equals(indexPopupAd.getLinkType())) {//将店铺code转成主键ID 存入
                    String mcode = indexPopupAd.getLinkContent();
                    MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
                    mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode + "");
                    List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
                    if (mchtInfoCustoms.size() > 0 && mchtInfoCustoms != null) {
                        MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
                        indexPopupAd.setLinkContent(mchtInfoCustom.getId() + "");
                    }
                } else {
                    indexPopupAd2.setLinkContent(linkContent);
                }

                indexPopupAd2.setPopupCount(indexPopupAd.getPopupCount());
                indexPopupAd2.setPopupDesc(indexPopupAd.getPopupDesc());
                indexPopupAd2.setPic(indexPopupAd.getPic());
                indexPopupAd2.setStatus(indexPopupAd.getStatus());
                indexPopupAd2.setDay(indexPopupAd.getDay());
                indexPopupAd2.setPopupCount(indexPopupAd.getPopupCount());
                indexPopupAd2.setSelectGroup(indexPopupAd.getSelectGroup());
                indexPopupAd2.setLinkType(indexPopupAd.getLinkType());
                //indexPopupAd2.setDownDate(sdf.parse(DownDate));
                //indexPopupAd2.setUpDate(sdf.parse(UpDate));
                resMap.put("indexPopupAdCustom", indexPopupAd2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    @RequestMapping("/homepagePopup/setGroups.shtml")
    @ResponseBody
    public Map<String, Object> addGroups(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        List<MemberLabelGroupCustom> dataList = null;
        try {
            String selectGroup = request.getParameter("selectGroup");
            if (!StringUtils.isEmpty(selectGroup)) {
                String[] idStrs = selectGroup.split(",");
                dataList = new ArrayList<>();
                for (String idStr : idStrs) {
                    MemberLabelGroupCustom memberLabelGroupCustom = memberLabelGroupService.selectMemberLabelGroupCustomPrimaryKey(Integer.parseInt(idStr));
                    dataList.add(memberLabelGroupCustom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "获取分组数据失败");
        }
        resMap.put("groups", dataList);
        return resMap;
    }


    /**
     * 广告添加用户分组
     *
     * @param request
     * @return
     */
    @RequestMapping("/homepagePopup/addGroup.shtml")
    @ResponseBody
    public Map<String, Object> addGroup(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            String id = request.getParameter("id");
            String selectGroup = request.getParameter("selectGroup");
            if ("".equals(selectGroup) || StringUtils.isEmpty(selectGroup)) {
                selectGroup = id;
            } else {
                selectGroup += "," + id;
            }
            resMap.put("selectGroup", selectGroup);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "添加分组失败");
        }
        return resMap;
    }

    /**
     * 广告保存前的检查
     *
     * @param indexPopupAd
     * @return
     */
    public Map<String, String> MngAdMngCheckSubmit(IndexPopupAd indexPopupAd) {
        Map<String, String> resMap = new HashMap<String, String>();
        if ("1".equals(indexPopupAd.getLinkType())) {
            ActivityAreaExample activityAreaExample = new ActivityAreaExample();
            ActivityAreaExample.Criteria activityAreaCriteria = activityAreaExample.createCriteria();
            /*activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdEqualTo(adInfo.getLinkId());*/
            activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(indexPopupAd.getLinkContent()));
            Integer totalCount = activityAreaService.countByExample(activityAreaExample);
            if (totalCount == 0) {
                resMap.put("YN", "0");
                resMap.put("msg", "会场不存在！");
                return resMap;
            }
        }
        if ("2".equals(indexPopupAd.getLinkType())) {
            ActivityExample activityExample = new ActivityExample();
            ActivityExample.Criteria activityCriteria = activityExample.createCriteria();
            /*activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdEqualTo(adInfo.getLinkId());*/
            activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(indexPopupAd.getLinkContent()));
            Integer totalCount = activityService.countByExample(activityExample);
            if (totalCount == 0) {
                resMap.put("YN", "0");
                resMap.put("msg", "活动不存在或未审核通过！");
                return resMap;
            }
        }
        if ("3".equals(indexPopupAd.getLinkType())) {
            /*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
            ProductCustomExample productCustomExample = new ProductCustomExample();
            productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(indexPopupAd.getLinkContent());
            List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
            if (productCustoms == null || productCustoms.size() <= 0) {
                resMap.put("YN", "0");
                resMap.put("msg", "商品不存在,或已下架");
                return resMap;

            }/*else{
				String productActivityStatus=productCustom.getProductActivityStatus();
				if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
					resMap.put("YN", "0");
					resMap.put("msg", "请检查商品的报名活动状态！");
					return resMap;
				}
			}*/
        }
        if ("7".equals(indexPopupAd.getLinkType())) {//自建页面
            /*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
            CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(indexPopupAd.getLinkContent()));
            if (customPage == null || !"0".equals(customPage.getDelFlag())) {
                resMap.put("YN", "0");
                resMap.put("msg", "自建页面不存在");
                return resMap;

            }
        }

        if ("29".equals(indexPopupAd.getLinkType())) {

            try {
                if (!StringUtils.isEmpty(indexPopupAd.getLinkContent())) {
                    List<Integer> couponIdList = new ArrayList<Integer>();
                    String[] couponIdsArray = indexPopupAd.getLinkContent().split(",");
                    for (String couponIdStr : couponIdsArray) {
                        couponIdList.add(Integer.parseInt(couponIdStr));
                    }
                    if (couponIdList.size() > 1) {//输入的是多个优惠券ID
                        CouponExample example = new CouponExample();
                        example.createCriteria().andDelFlagEqualTo("0").andIdIn(couponIdList);
                        int count = couponService.countByExample(example);
                        if (couponIdList.size() != count) {
                            resMap.put("YN", "0");
                            resMap.put("msg", "请输入正确的优惠券ID");
                            return resMap;
                        }
                    } else {//输入的是单个优惠券ID
                        CouponExample example = new CouponExample();
                        CouponExample.Criteria c = example.createCriteria();
                        c.andDelFlagEqualTo("0");
                        c.andIdEqualTo(Integer.parseInt(indexPopupAd.getLinkContent()));
                        List<Coupon> coupons = couponService.selectByExample(example);
                        if (coupons == null || coupons.size() == 0) {
                            resMap.put("YN", "0");
                            resMap.put("msg", "请输入正确的优惠券ID");
                            return resMap;
                        }
                    }
                } else {//没有优惠券ID
                    resMap.put("YN", "0");
                    resMap.put("msg", "请输入正确优惠券ID");
                    return resMap;
                }


            } catch (Exception e) {//输入的优惠券ID不是数字
                // TODO: handle exception
                e.printStackTrace();
                resMap.put("returnCode", "0");
                resMap.put("returnMsg", "请输入正确的优惠券ID");
                return resMap;
            }

        }
        if ("10".equals(indexPopupAd.getLinkType())) {
            MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
            mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(indexPopupAd.getLinkContent());
            Integer totalCount = mchtInfoService.countByExample(mchtInfoExample);
            if (totalCount == 0) {
                resMap.put("YN", "0");
                resMap.put("msg", "商家店铺不存在！");
                return resMap;

            }
        }

        resMap.put("YN", "1");
        return resMap;
    }


    //保存
    @RequestMapping(value = "/homepagePopup/saveIndexPopupAd.shtml")
    @ResponseBody
    public Map<String, Object> saveIndexPopupAd(HttpServletRequest request, IndexPopupAd indexPopupAd, String id, String linkContent, String linkContent4, String linkContent5) {
        /*String rtPage = "/success/success";*/
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        String code = null;
        String msg = null;
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String DownDate = request.getParameter("downDate");
            String UpDate = request.getParameter("upDate");
            String selectGroup = request.getParameter("selectGroup");
            if (indexPopupAd.getId() == null) {//添加

                Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(indexPopupAd);
                if ("0".equals(mngAdMngCheckSubmit.get("YN")) || "0".equals(mngAdMngCheckSubmit.get("returnCode"))) {
                    if ("0".equals(mngAdMngCheckSubmit.get("YN"))) {
                        resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
                        resMap.put(this.JSON_RESULT_MESSAGE, mngAdMngCheckSubmit.get("msg"));
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
                    } else {
                        resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
                        resMap.put(this.JSON_RESULT_MESSAGE, mngAdMngCheckSubmit.get("msg"));
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", mngAdMngCheckSubmit.get("returnMsg"));
                    }
                } else {

                    indexPopupAd.setCreateBy(staffId);
                    indexPopupAd.setCreateDate(new Date());
                    indexPopupAd.setDelFlag("0");
                    indexPopupAd.setStatus("0");
				 /*if (!StringUtil.isEmpty(linkContent4) && indexPopupAd.getLinkType().equals("6")) {
					 indexPopupAd.setLinkContent(linkContent4);
				 }*/
				/* if (!StringUtil.isEmpty(linkContent5) && indexPopupAd.getLinkType().equals("5")) {
					 indexPopupAd.setLinkContent(linkContent5);
				 }*/

                    if ("3".equals(indexPopupAd.getLinkType())) {
                        String pcode = indexPopupAd.getLinkContent();
                        ProductExample productExample = new ProductExample();
                        productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode + "");
                        List<Product> products = productService.selectByExample(productExample);
                        if (products.size() > 0 && products != null) {
                            Product product = products.get(0);
                            indexPopupAd.setLinkContent(product.getId() + "");
                        }

                    }
                    if ("10".equals(indexPopupAd.getLinkType())) {//将店铺code转成主键ID 存入
                        String mcode = indexPopupAd.getLinkContent();
                        MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
                        mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode + "");
                        List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
                        if (mchtInfoCustoms.size() > 0 && mchtInfoCustoms != null) {
                            MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
                            indexPopupAd.setLinkContent(mchtInfoCustom.getId() + "");
                        }
                    }
                    if (StringUtils.isEmpty(selectGroup) && "".equals(selectGroup)){
                        indexPopupAd.setSelectGroup(null);
                    }else {
                        indexPopupAd.setSelectGroup(selectGroup);
                    }
                    indexPopupAd.setDownDate(sdf.parse(DownDate));
                    indexPopupAd.setUpDate(sdf.parse(UpDate));
                    indexPopupAdService.insert(indexPopupAd);
                    code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
                    msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
                }
            } else {//更新{returnCode=0, returnMsg=请输入正确的优惠券ID
                Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(indexPopupAd);
                if ("0".equals(mngAdMngCheckSubmit.get("YN")) || "0".equals(mngAdMngCheckSubmit.get("returnCode"))) {
                    if ("0".equals(mngAdMngCheckSubmit.get("YN"))) {
                        resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
                        resMap.put(this.JSON_RESULT_MESSAGE, mngAdMngCheckSubmit.get("msg"));
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
                    } else {
                        resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
                        resMap.put(this.JSON_RESULT_MESSAGE, mngAdMngCheckSubmit.get("msg"));
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", mngAdMngCheckSubmit.get("returnMsg"));
                    }
                } else {

                    IndexPopupAd indexPopupAd2 = indexPopupAdService.selectByPrimaryKey(Integer.valueOf(id));
                    indexPopupAd2.setUpdateDate(new Date());
                    indexPopupAd2.setUpdateBy(staffId);
	        	/*if (!StringUtil.isEmpty(linkContent4) && indexPopupAd.getLinkType().equals("6")) {
	        		indexPopupAd2.setLinkContent(linkContent4);
				 }else */
	        	/* if (!StringUtil.isEmpty(linkContent5) && indexPopupAd.getLinkType().equals("5")) {
					 indexPopupAd2.setLinkContent(linkContent5);
				 }else {
					 indexPopupAd2.setLinkContent(linkContent);
				}*/

                    if ("3".equals(indexPopupAd.getLinkType())) {
                        String pcode = indexPopupAd.getLinkContent();
                        ProductExample productExample = new ProductExample();
                        productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode + "");
                        List<Product> products = productService.selectByExample(productExample);
                        if (products.size() > 0 && products != null) {
                            Product product = products.get(0);
                            indexPopupAd.setLinkContent(product.getId() + "");
                        }

                    } else if ("10".equals(indexPopupAd.getLinkType())) {//将店铺code转成主键ID 存入
                        String mcode = indexPopupAd.getLinkContent();
                        MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
                        mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode + "");
                        List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
                        if (mchtInfoCustoms.size() > 0 && mchtInfoCustoms != null) {
                            MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
                            indexPopupAd.setLinkContent(mchtInfoCustom.getId() + "");
                        }
                    } else {
                        indexPopupAd2.setLinkContent(linkContent);
                    }
                    if (StringUtils.isEmpty(selectGroup) && "".equals(selectGroup)){
                        indexPopupAd2.setSelectGroup(null);
                    }else {
                        indexPopupAd2.setSelectGroup(selectGroup);
                    }
                    indexPopupAd2.setPopupCount(indexPopupAd.getPopupCount());
                    indexPopupAd2.setPopupDesc(indexPopupAd.getPopupDesc());
                    indexPopupAd2.setPic(indexPopupAd.getPic());
                    indexPopupAd2.setStatus(indexPopupAd.getStatus());
                    indexPopupAd2.setDay(indexPopupAd.getDay());
                    indexPopupAd2.setDownDate(sdf.parse(DownDate));
                    indexPopupAd2.setUpDate(sdf.parse(UpDate));
                    indexPopupAd2.setLinkType(indexPopupAd.getLinkType());
                    //indexPopupAdService.updateByPrimaryKeySelective(indexPopupAd2);
                    indexPopupAdService.updateByPrimaryKey(indexPopupAd2);

                    code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
                    msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);

        return resMap;
    }


    //删除
    @RequestMapping(value = "/homepagePopup/delIndexPopupAd.shtml")
    @ResponseBody
    public Map<String, Object> delIndexPopupAd(HttpServletRequest request, HttpServletResponse response, String id) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());

            IndexPopupAd indexPopupAd = indexPopupAdService.selectByPrimaryKey(Integer.valueOf(id));
            indexPopupAd.setUpdateBy(staffId);
            indexPopupAd.setUpdateDate(new Date());
            indexPopupAd.setDelFlag("1");
            indexPopupAdService.updateByPrimaryKey(indexPopupAd);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }

        return resMap;
    }


    //上下架
    @RequestMapping(value = "/homepagePopup/changeIndexPopupAd.shtml")
    @ResponseBody
    public Map<String, Object> changeIndexPopupAd(HttpServletRequest request, HttpServletResponse response, String id, String status) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());

            IndexPopupAd indexPopupAd = indexPopupAdService.selectByPrimaryKey(Integer.valueOf(id));
            indexPopupAd.setUpdateBy(staffId);
            indexPopupAd.setUpdateDate(new Date());
            indexPopupAd.setStatus(status);
            indexPopupAdService.updateByPrimaryKey(indexPopupAd);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }

        return resMap;
    }

}
