package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeExample;
import com.jf.entity.ConfigSpecialMcht;
import com.jf.entity.ConfigSpecialMchtExample;
import com.jf.entity.ConfigSpecialMchtStatusLog;
import com.jf.entity.ConfigSpecialMchtStatusLogExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponAddtaskConfig;
import com.jf.entity.CouponAddtaskConfigExample;
import com.jf.entity.CouponCombineRecLimit;
import com.jf.entity.CouponCombineRecLimitCustom;
import com.jf.entity.CouponCombineRecLimitCustomExample;
import com.jf.entity.CouponCombineRecLimitDtl;
import com.jf.entity.CouponCombineRecLimitDtlExample;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponCustomExample;
import com.jf.entity.CouponExample;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberCouponCustomExample;
import com.jf.entity.MemberGroup;
import com.jf.entity.MemberGroupExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.service.ActivityAreaService;
import com.jf.service.BrandteamTypeService;
import com.jf.service.ConfigSpecialMchtService;
import com.jf.service.ConfigSpecialMchtStatusLogService;
import com.jf.service.CouponAddTaskConfigService;
import com.jf.service.CouponCombineRecLimitDtlService;
import com.jf.service.CouponCombineRecLimitService;
import com.jf.service.CouponExchangeCodeService;
import com.jf.service.CouponService;
import com.jf.service.MallCategoryService;
import com.jf.service.MemberCouponService;
import com.jf.service.MemberGroupService;
import com.jf.service.ProductTypeService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffRoleService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponExchangeCodeService couponExchangeCodeService;

    @Autowired
    private ActivityAreaService activityAreaService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private SysStaffRoleService sysStaffRoleService;

    @Autowired
    private MemberGroupService memberGroupService;

    @Autowired
    private MemberCouponService memberCouponService;

    @Autowired
    private BrandteamTypeService brandteamTypeService;

    @Autowired
    private MallCategoryService mallCategoryService;

    @Autowired
    private CouponAddTaskConfigService couponAddTaskConfigService;

    @Autowired
    private CouponCombineRecLimitService couponCombineRecLimitService;

    @Autowired
    private CouponCombineRecLimitDtlService couponCombineRecLimitDtlService;

    @Autowired
    private ConfigSpecialMchtService configSpecialMchtService;

    @Autowired
    private ConfigSpecialMchtStatusLogService configSpecialMchtStatusLogService;

    @Autowired
    private SysStaffInfoService sysStaffInfoService;

    /**
     * @Title couponManager
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年2月8日 下午4:14:19
     */
    @RequestMapping("/coupon/couponManager.shtml")
    public ModelAndView couponManager(HttpServletRequest request, String statusPage) {
        ModelAndView m = new ModelAndView();
        if (!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //1.创建推广会场（游离码）
            if (!StringUtil.isEmpty(request.getParameter("activityAreaId"))) {
                CouponExample couponExample = new CouponExample();
                couponExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(Integer.parseInt(request.getParameter("activityAreaId")));
                List<CouponCustom> couponCustomList = couponService.selectCouponCustomByExample(couponExample);
                m.addObject("couponCustomList", couponCustomList);
                m.addObject("activityAreaId", request.getParameter("activityAreaId"));
            }
            m.setViewName("/activityAreaNew/promotionActivityArea/couponExchangeList");
        } else if (!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //2.创建推广会场（追加发行量）
            if (!StringUtil.isEmpty(request.getParameter("couponId"))) {
                CouponExample couponExample = new CouponExample();
                couponExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("couponId")));
                List<CouponCustom> couponCustomList = couponService.selectCouponCustomByExample(couponExample);
                if (couponCustomList != null && couponCustomList.size() > 0) {
                    m.addObject("couponCustom", couponCustomList.get(0));
                }
            }
            m.setViewName("/activityAreaNew/promotionActivityArea/couponExchange");
        } else if (!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //3.创建推广（游离码管理）
            m.addObject("preferentialTypeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE")); //特惠类型
            m.addObject("createStaffList", activityAreaService.getCreateByList()); //获取30天内已创建人
            m.addObject("statusList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "STATUS")); //启用状态
            ProductTypeExample productTypeExample = new ProductTypeExample();
            productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
            productTypeExample.setOrderByClause(" seq_no");
            List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
            m.addObject("productTypeList", productTypeList); //1级类目
            m.setViewName("/activityAreaNew/promotionActivityArea/couponList");
        }
        return m;
    }

    /**
     * @Title updateGrantQuantity
     * @Description TODO(追加发行量)
     * @author pengl
     * @date 2018年2月8日 下午4:14:02
     */
    @ResponseBody
    @RequestMapping("/coupon/updateGrantQuantity.shtml")
    public Map<String, Object> updateGrantQuantity(HttpServletRequest request, Integer couponId, Integer grantQuantity) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = null;
        String msg = null;
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            Coupon coupon = couponService.selectByPrimaryKey(couponId);
            Coupon couponNew = new Coupon();
            couponNew.setId(coupon.getId());
            Integer sumGrantQuantity = coupon.getGrantQuantity() + grantQuantity;
            couponNew.setGrantQuantity(sumGrantQuantity);
            couponNew.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
            couponNew.setUpdateDate(new Date());
            couponService.updateByPrimaryKeySelective(couponNew);
            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = sumGrantQuantity.toString();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
        }
        map.put(this.JSON_RESULT_CODE, code);
        map.put(this.JSON_RESULT_MESSAGE, msg);
        return map;
    }

    /**
     * @Title getCouponList
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年2月9日 上午11:38:49
     */
    @ResponseBody
    @RequestMapping("/coupon/getCouponList.shtml")
    public Map<String, Object> getCouponList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<CouponCustom> dataList = null;
        Integer totalCount = 0;
        try {
            CouponCustomExample couponCustomExample = new CouponCustomExample();
            CouponCustomExample.CouponCustomCriteria couponCustomCriteria = couponCustomExample.createCriteria();
            couponCustomExample.setLimitSize(page.getLimitSize());
            couponCustomExample.setLimitStart(page.getLimitStart());
            couponCustomExample.setOrderByClause(" id desc");
            couponCustomCriteria.andDelFlagEqualTo("0");
            if (!StringUtil.isEmpty(request.getParameter("statusPage")) && "3".equals(request.getParameter("statusPage"))) { //3.创建推广（游离码管理）
                couponCustomCriteria.andActivityArea();
            }
            //会场ID
            if (!StringUtil.isEmpty(request.getParameter("activityAreaId"))) {
                couponCustomCriteria.andActivityAreaIdEqualTo(Integer.parseInt(request.getParameter("activityAreaId")));
            }
            //会场名称
            if (!StringUtil.isEmpty(request.getParameter("activityAreaname"))) {
                couponCustomCriteria.andActivityAreaNameLike("%" + request.getParameter("activityAreaname") + "%");
            }
            //促销方式
            if (!StringUtil.isEmpty(request.getParameter("preferentialType"))) {
                couponCustomCriteria.andActivityAreaPreferentialTypeEqualTo(request.getParameter("preferentialType"));
            }
            //活动开始日期
            if (!StringUtil.isEmpty(request.getParameter("activityBeginTime"))) {
                String activityBeginTimeA = request.getParameter("activityBeginTime") + " 00:00:00";
                String activityBeginTimeB = request.getParameter("activityBeginTime") + " 23:59:59";
                couponCustomCriteria.andActivityBeginTimeBetween(activityBeginTimeA, activityBeginTimeB);
            }
            //活动结束日期
            if (!StringUtil.isEmpty(request.getParameter("activityEndTime"))) {
                String activityEndTimeA = request.getParameter("activityEndTime") + " 00:00:00";
                String activityEndTimeB = request.getParameter("activityEndTime") + " 23:59:59";
                couponCustomCriteria.andActivityEndTimeBetween(activityEndTimeA, activityEndTimeB);
            }
            //会场创建人
            if (!StringUtil.isEmpty(request.getParameter("createBy"))) {
                couponCustomCriteria.andActivityAreaCreateByEqualTo(Integer.parseInt(request.getParameter("createBy")));
            }
            //启用状态
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                couponCustomCriteria.andActivityAreaStatusEqualTo(request.getParameter("status"));
            }
            //类目
            if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
                couponCustomCriteria.andProductTypeGroupFindInSet(request.getParameter("productTypeId"));
            }
            totalCount = couponService.countCouponCustomByExample(couponCustomExample);
            dataList = couponService.selectCouponCustomByExample(couponCustomExample);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     * @Title svipCouponManager
     * @Description TODO(每月优惠券列表管理)
     * @author pengl
     * @date 2019年3月5日 下午5:03:05
     */
    @RequestMapping("/coupon/svipCouponManager.shtml")
    public ModelAndView svipCouponManager(HttpServletRequest request, String statusPage) {
        ModelAndView m = new ModelAndView("/member/svip/getSvipCouponList");
        String staffId = this.getSessionStaffBean(request).getStaffID();
        SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
        staffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffId)).andRoleIdEqualTo(Const.ROLE_ID_84);
        List<SysStaffRole> staffRoleList = sysStaffRoleService.selectByExample(staffRoleExample);
        boolean managerFlag = false;
        if (!CollectionUtils.isEmpty(staffRoleList)) {
            managerFlag = true;
        }
        m.addObject("managerFlag", managerFlag); //管理权限
        m.addObject("statusList", DataDicUtil.getTableStatus("BU_COUPON", "STATUS")); //启用状态
        m.addObject("recLimitTypeList", DataDicUtil.getTableStatus("BU_COUPON", "REC_LIMIT_TYPE")); //限领方式
        return m;
    }

    /**
     * @Title getSvipCouponList
     * @Description TODO(每月优惠券列表)
     * @author pengl
     * @date 2019年3月5日 下午5:41:09
     */
    @ResponseBody
    @RequestMapping("/coupon/getSvipCouponList.shtml")
    public Map<String, Object> getSvipCouponList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<CouponCustom> dataList = null;
        Integer totalCount = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CouponCustomExample couponCustomExample = new CouponCustomExample();
            CouponCustomExample.CouponCustomCriteria couponCustomCriteria = couponCustomExample.createCriteria();
            couponCustomCriteria.andDelFlagEqualTo("0").andRecTypeEqualTo("4");
            if (!StringUtil.isEmpty(request.getParameter("id"))) {
                couponCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                couponCustomCriteria.andStatusEqualTo(request.getParameter("status"));
            }
            if (!StringUtil.isEmpty(request.getParameter("recBeginDate"))) {
                couponCustomCriteria.andRecBeginDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("recBeginDate") + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("recEndDate"))) {
                couponCustomCriteria.andRecEndDateLessThanOrEqualTo(sdf.parse(request.getParameter("recEndDate") + " 23:59:59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("name"))) {
                String name = request.getParameter("name");
                couponCustomCriteria.andNameLike("%" + name + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("recLimitType"))) {
                couponCustomCriteria.andRecLimitTypeEqualTo(request.getParameter("recLimitType"));
            }
            couponCustomExample.setLimitSize(page.getLimitSize());
            couponCustomExample.setLimitStart(page.getLimitStart());
            couponCustomExample.setOrderByClause(" id desc");
            totalCount = couponService.countCouponCustomByExample(couponCustomExample);
            dataList = couponService.selectCouponCustomByExample(couponCustomExample);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     * @Title marketingCouponAdd
     * @Description TODO(创建优惠劵)
     * @author pengl
     * @date 2019年3月5日 下午8:19:05
     */
    @RequestMapping("/coupon/svipCouponAddManager.shtml")
    public ModelAndView marketingCouponAdd(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/member/svip/svipCouponAdd");
        ProductTypeExample pte = new ProductTypeExample();
        pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
        List<ProductType> productTypes = productTypeService.selectByExample(pte);
        m.addObject("productTypes", productTypes);

        MemberGroupExample memberGroupExample = new MemberGroupExample();
        memberGroupExample.createCriteria().andDelFlagEqualTo("0");
        List<MemberGroup> memberGroups = memberGroupService.selectByExample(memberGroupExample);
        m.addObject("memberGroups", memberGroups);

        m.addObject("expiryTypes", DataDicUtil.getStatusList("BU_COUPON", "EXPIRY_TYPE"));

        m.addObject("recTypes", DataDicUtil.getStatusList("BU_COUPON", "REC_TYPE"));

        m.addObject("conditionTypeS", DataDicUtil.getStatusList("BU_COUPON", "CONITION_TYPE"));


        BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();//新品牌团一级类目
        brandteamTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
        List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample);
        m.addObject("brandteamTypes", brandteamTypes);

        MallCategoryExample mallCategoryExample = new MallCategoryExample();//商城一级类目
        mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
        List<MallCategory> mallCategories = mallCategoryService.selectByExample(mallCategoryExample);
        m.addObject("mallCategories", mallCategories);


        List<String> linkTypeNames = new ArrayList<String>();
        linkTypeNames.add("会场ID");
        linkTypeNames.add("活动ID");
        linkTypeNames.add("商品ID");
        linkTypeNames.add("外部链接");
        linkTypeNames.add("无链接");
        linkTypeNames.add("栏目");
        linkTypeNames.add("自建页面ID");
        linkTypeNames.add("淘宝优选关键字");
        linkTypeNames.add("品牌团一级类目");
        linkTypeNames.add("商家店铺");
        linkTypeNames.add("品牌特卖一级分类");
        linkTypeNames.add("淘宝优选频道");
        linkTypeNames.add("新品牌团二级分类");
        linkTypeNames.add("有好货二级分类");
        linkTypeNames.add("有好货品牌ID");
        linkTypeNames.add("潮鞋上新二级分类");
        linkTypeNames.add("潮鞋上新品牌ID");
        linkTypeNames.add("潮流男装二级分类");
        linkTypeNames.add("潮流男装品牌ID");
        linkTypeNames.add("断码特惠二级分类");
        linkTypeNames.add("断码特惠品牌ID");
        linkTypeNames.add("运动鞋服二级分类");
        linkTypeNames.add("运动鞋服品牌ID");
        linkTypeNames.add("潮流美妆二级分类");
        linkTypeNames.add("潮流美妆品牌ID");
        linkTypeNames.add("食品超市二级分类");
        linkTypeNames.add("食品超市品牌ID");
        linkTypeNames.add("商城一级分类");
        linkTypeNames.add("优惠券ID");

        JSONArray linkTypeJa = new JSONArray();
        for (int i = 1; i <= linkTypeNames.size(); i++) {
            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11 || i == 28) {
                JSONObject jo = new JSONObject();
                jo.put("linkType", i);
                jo.put("linkTypeName", linkTypeNames.get(i - 1));
                linkTypeJa.add(jo);
            } else {
                continue;
            }
        }
        m.addObject("linkTypeList", linkTypeJa);

        List<String> linkValueNames = new ArrayList<String>();
        linkValueNames.add("新用户专享");
        linkValueNames.add("单品爆款");
        linkValueNames.add("限时抢购");
        linkValueNames.add("新用户秒杀");
        linkValueNames.add("积分商城");
        linkValueNames.add("断码清仓");
        linkValueNames.add("签到");
        linkValueNames.add("砍价");
        linkValueNames.add("邀请免单");
        linkValueNames.add("有好货");
        linkValueNames.add("每日好店");
        linkValueNames.add("新品牌团");
        linkValueNames.add("新星计划");
        linkValueNames.add("淘宝优选");
        linkValueNames.add("潮鞋上新");
        linkValueNames.add("潮流男装");
        linkValueNames.add("断码特惠");
        linkValueNames.add("运动鞋服");
        linkValueNames.add("潮流美妆");
        linkValueNames.add("食品超市");
        linkValueNames.add("大学生创业");
        linkValueNames.add("SVIP");
        JSONArray linkValueJa = new JSONArray();
        for (int j = 1; j <= linkValueNames.size(); j++) {
            JSONObject jo = new JSONObject();
            jo.put("linkValue", j);
            jo.put("linkValueName", linkValueNames.get(j - 1));
            linkValueJa.add(jo);
        }
        m.addObject("linkValueList", linkValueJa);
        return m;
    }

    /**
     * @Title svipCouponAdd
     * @Description TODO(保存创建优惠劵)
     * @author pengl
     * @date 2019年3月5日 下午8:37:58
     */
    @RequestMapping("/coupon/svipCouponAdd.shtml")
    public ModelAndView svipCouponAdd(HttpServletRequest request, Coupon coupon, String linkValue, String linkValue0, String linkValue1, String linkValue2, String linkValue3, String productTypeId, String discount, String days, String addCount, String minimum1) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = null;
        String msg = null;
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            coupon.setNeedIntegral(0);
            coupon.setRecEach(0);
            coupon.setCreateBy(staffId);
            coupon.setCreateDate(date);
            if (!StringUtil.isEmpty(request.getParameter("recBeginDate"))) {
                coupon.setRecBeginDate(dateFormat.parse(request.getParameter("recBeginDate") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("recEndDate"))) {
                coupon.setRecEndDate(dateFormat.parse(request.getParameter("recEndDate") + ":59"));
            }
			/*if(StringUtils.isEmpty(coupon.getExpiryType()) ) {
				coupon.setExpiryType("2"); //相对时间
			}*/
            if ("1".equals(coupon.getExpiryType())) {
                if (!StringUtil.isEmpty(request.getParameter("expiryBeginDate"))) {
                    coupon.setExpiryBeginDate(dateFormat.parse(request.getParameter("expiryBeginDate") + ":00"));
                }

                if (!StringUtil.isEmpty(request.getParameter("expiryEndDate"))) {
                    coupon.setExpiryEndDate(dateFormat.parse(request.getParameter("expiryEndDate") + ":59"));
                }
            }
			/*if(!StringUtils.isEmpty(coupon.getTypeIds())) { //2.品类券
				coupon.setCouponType("2");
			}else{ //1.全场通用
				coupon.setCouponType("1");
			}*/
            if (!StringUtils.isEmpty(coupon.getCouponType())) {
                if (coupon.getCouponType().equals("2")) {//2.品类券
                    coupon.setCouponType("2");
                    if (!StringUtils.isEmpty(productTypeId)) {
                        coupon.setTypeIds(productTypeId);
                    }
                } else {//1.全场通用
                    coupon.setCouponType("1");
                }
            }
            coupon.setPreferentialType("1");
            if (!StringUtils.isEmpty(linkValue)) {
                coupon.setLinkValue(linkValue);
            } else if (!StringUtils.isEmpty(linkValue0)) {
                coupon.setLinkValue(linkValue0);
            } else if (!StringUtils.isEmpty(linkValue1)) {
                coupon.setLinkValue(linkValue1);
            } else if (!StringUtils.isEmpty(linkValue2)) {
                coupon.setLinkValue(linkValue2);
            } else if (!StringUtils.isEmpty(linkValue3)) {
                coupon.setLinkValue(linkValue3);
            }

            if (!StringUtils.isEmpty(discount)) {
                BigDecimal discount1 = new BigDecimal(discount).divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_DOWN);
                coupon.setDiscount(discount1);
            }
            if (!StringUtil.isEmpty(minimum1)) {
                coupon.setMinimum(new BigDecimal(minimum1));
            }
            if (request.getParameter("recLimitType").equals("2")) {
                coupon.setRecEach(Integer.valueOf(request.getParameter("recEach")));
            }
            if (request.getParameter("preferentialType").equals("2")) {
                coupon.setPreferentialType("2");
            } else {
                coupon.setPreferentialType("1");
            }
            couponService.insertSelective(coupon);

            if (!StringUtils.isEmpty(days)) {
                if (days.equals("1")) {//追加日期
                    CouponAddtaskConfig couponAddtaskConfig = new CouponAddtaskConfig();
                    couponAddtaskConfig.setCouponId(coupon.getId());
                    if (!StringUtil.isEmpty(request.getParameter("beginDate"))) {
                        couponAddtaskConfig.setBeginDate(dateFormat.parse(request.getParameter("beginDate") + ":00"));
                    }

                    if (!StringUtil.isEmpty(request.getParameter("endDate"))) {
                        couponAddtaskConfig.setEndDate(dateFormat.parse(request.getParameter("endDate") + ":59"));
                    }
                    couponAddtaskConfig.setAddCount(Integer.valueOf(addCount));
                    couponAddtaskConfig.setCreateBy(staffId);
                    couponAddtaskConfig.setCreateDate(new Date());
                    couponAddtaskConfig.setStatus("1");
                    couponAddtaskConfig.setDelFlag("0");
                    couponAddTaskConfigService.insert(couponAddtaskConfig);
                }

            }

            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * @Title svipCouponEditManager
     * @Description TODO(管理优惠劵)
     * @author pengl
     * @date 2019年3月5日 下午7:55:39
     */
    @RequestMapping("/coupon/svipCouponEditManager.shtml")
    public ModelAndView svipCouponEditManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/member/svip/svipCouponEdit");
        CouponCustom couponCustom = couponService.selectCouponCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
        if (couponCustom.getMaxDiscountMoney() == null) {
            couponCustom.setMaxDiscountMoney(new BigDecimal(0));
        }
        m.addObject("couponCustom", couponCustom);
        m.addObject("couponStatus", DataDicUtil.getStatusList("BU_COUPON", "STATUS"));
        ProductTypeExample pte = new ProductTypeExample();
        pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
        List<ProductType> productTypes = productTypeService.selectByExample(pte);
        m.addObject("productTypes", productTypes);

        MemberGroupExample memberGroupExample = new MemberGroupExample();
        memberGroupExample.createCriteria().andDelFlagEqualTo("0");
        List<MemberGroup> memberGroups = memberGroupService.selectByExample(memberGroupExample);
        m.addObject("memberGroups", memberGroups);

        m.addObject("expiryTypes", DataDicUtil.getStatusList("BU_COUPON", "EXPIRY_TYPE"));
        m.addObject("recTypes", DataDicUtil.getStatusList("BU_COUPON", "REC_TYPE"));


        List<String> linkTypeNames = new ArrayList<String>();
        linkTypeNames.add("会场ID");
        linkTypeNames.add("活动ID");
        linkTypeNames.add("商品ID");
        linkTypeNames.add("外部链接");
        linkTypeNames.add("无链接");
        linkTypeNames.add("栏目");
        linkTypeNames.add("自建页面ID");
        linkTypeNames.add("淘宝优选关键字");
        linkTypeNames.add("品牌团一级类目");
        linkTypeNames.add("商家店铺");
        linkTypeNames.add("品牌特卖一级分类");
        linkTypeNames.add("淘宝优选频道");
        linkTypeNames.add("新品牌团二级分类");
        linkTypeNames.add("有好货二级分类");
        linkTypeNames.add("有好货品牌ID");
        linkTypeNames.add("潮鞋上新二级分类");
        linkTypeNames.add("潮鞋上新品牌ID");
        linkTypeNames.add("潮流男装二级分类");
        linkTypeNames.add("潮流男装品牌ID");
        linkTypeNames.add("断码特惠二级分类");
        linkTypeNames.add("断码特惠品牌ID");
        linkTypeNames.add("运动鞋服二级分类");
        linkTypeNames.add("运动鞋服品牌ID");
        linkTypeNames.add("潮流美妆二级分类");
        linkTypeNames.add("潮流美妆品牌ID");
        linkTypeNames.add("食品超市二级分类");
        linkTypeNames.add("食品超市品牌ID");
        linkTypeNames.add("商城一级分类");
        linkTypeNames.add("优惠券ID");

        JSONArray linkTypeJa = new JSONArray();
        for (int i = 1; i <= linkTypeNames.size(); i++) {
            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11 || i == 28) {
                JSONObject jo = new JSONObject();
                jo.put("linkType", i);
                jo.put("linkTypeName", linkTypeNames.get(i - 1));
                linkTypeJa.add(jo);
            } else {
                continue;
            }
        }
        m.addObject("linkTypeList", linkTypeJa);

        List<String> linkValueNames = new ArrayList<String>();
        linkValueNames.add("新用户专享");
        linkValueNames.add("单品爆款");
        linkValueNames.add("限时抢购");
        linkValueNames.add("新用户秒杀");
        linkValueNames.add("积分商城");
        linkValueNames.add("断码清仓");
        linkValueNames.add("签到");
        linkValueNames.add("砍价");
        linkValueNames.add("邀请免单");
        linkValueNames.add("有好货");
        linkValueNames.add("每日好店");
        linkValueNames.add("新品牌团");
        linkValueNames.add("新星计划");
        linkValueNames.add("淘宝优选");
        linkValueNames.add("潮鞋上新");
        linkValueNames.add("潮流男装");
        linkValueNames.add("断码特惠");
        linkValueNames.add("运动鞋服");
        linkValueNames.add("潮流美妆");
        linkValueNames.add("食品超市");
        linkValueNames.add("大学生创业");
        linkValueNames.add("SVIP");
        JSONArray linkValueJa = new JSONArray();
        for (int j = 1; j <= linkValueNames.size(); j++) {
            JSONObject jo = new JSONObject();
            jo.put("linkValue", j);
            jo.put("linkValueName", linkValueNames.get(j - 1));
            linkValueJa.add(jo);
        }
        m.addObject("linkValueList", linkValueJa);

        CouponAddtaskConfigExample couponAddtaskConfigExample = new CouponAddtaskConfigExample();
        couponAddtaskConfigExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(couponCustom.getId());
        List<CouponAddtaskConfig> couponAddtaskConfigs = couponAddTaskConfigService.selectByExample(couponAddtaskConfigExample);
        if (couponAddtaskConfigs != null && couponAddtaskConfigs.size() > 0) {

            m.addObject("couponAddtaskConfigs", couponAddtaskConfigs.get(0));
        }

        BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();//新品牌团一级类目
        brandteamTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
        List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample);
        m.addObject("brandteamTypes", brandteamTypes);

        MallCategoryExample mallCategoryExample = new MallCategoryExample();//商城一级类目
        mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
        List<MallCategory> mallCategories = mallCategoryService.selectByExample(mallCategoryExample);
        m.addObject("mallCategories", mallCategories);

        if (couponCustom.getDiscount() != null) {
            String Discount1 = couponCustom.getDiscount().toString();//折扣价
            BigDecimal Discount = new BigDecimal(Discount1).multiply(new BigDecimal(10)).setScale(1);
            m.addObject("Discount", Discount);
        }

        CouponCombineRecLimitDtlExample combineRecLimitDtlExample = new CouponCombineRecLimitDtlExample();
        combineRecLimitDtlExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(couponCustom.getId());
        List<CouponCombineRecLimitDtl> combineRecLimitDtls = couponCombineRecLimitDtlService.selectByExample(combineRecLimitDtlExample);
        if (combineRecLimitDtls.size() > 0) {//关联优惠券组
            CouponCombineRecLimitCustom combineRecLimitCustom = couponCombineRecLimitService.selectByCustomPrimaryKey(combineRecLimitDtls.get(0).getCouponCombineRecLimitId());
            m.addObject("CouponIdgroup", combineRecLimitCustom.getCouponIdgroup());
        }

        m.addObject("conditionTypeS", DataDicUtil.getStatusList("BU_COUPON", "CONITION_TYPE"));

        return m;
    }

    /**
     * @Title svipCouponEdit
     * @Description TODO(修改管理优惠劵)
     * @author pengl
     * @date 2019年3月6日 下午2:12:04
     */
    @RequestMapping("/coupon/svipCouponEdit.shtml")
    public ModelAndView svipCouponEdit(HttpServletRequest request, Coupon coupon, String linkValue, String linkValue0, String linkValue1, String linkValue2, String linkValue3, String productTypeId, String discount, String days, String addCount, String minimum1) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = null;
        String msg = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            coupon.setPreferentialType("1");
            coupon.setUpdateBy(staffId);
            coupon.setUpdateDate(new Date());
            if (!StringUtil.isEmpty(request.getParameter("recBeginDate"))) {
                coupon.setRecBeginDate(dateFormat.parse(request.getParameter("recBeginDate") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("recEndDate"))) {
                coupon.setRecEndDate(dateFormat.parse(request.getParameter("recEndDate") + ":59"));
            }

            if ("1".equals(coupon.getExpiryType())) {
                if (!StringUtil.isEmpty(request.getParameter("expiryBeginDate"))) {
                    coupon.setExpiryBeginDate(dateFormat.parse(request.getParameter("expiryBeginDate") + " 00:00:00"));
                }

                if (!StringUtil.isEmpty(request.getParameter("expiryEndDate"))) {
                    coupon.setExpiryEndDate(dateFormat.parse(request.getParameter("expiryEndDate") + " 23:59:59"));
                }
            }

            if (!StringUtils.isEmpty(coupon.getCouponType())) {
                if (coupon.getCouponType().equals("2")) {//2.品类券
                    coupon.setCouponType("2");
                    if (!StringUtils.isEmpty(productTypeId)) {
                        coupon.setTypeIds(productTypeId);
                    }
                } else {//1.全场通用
                    coupon.setCouponType("1");
                }
            }


            if (!StringUtils.isEmpty(linkValue) && (coupon.getLinkType().equals("1") || coupon.getLinkType().equals("2") || coupon.getLinkType().equals("3") || coupon.getLinkType().equals("4") || coupon.getLinkType().equals("7"))) {
                coupon.setLinkValue(linkValue);
            } else if (!StringUtils.isEmpty(linkValue0) && coupon.getLinkType().equals("6")) {
                coupon.setLinkValue(linkValue0);
            } else if (!StringUtils.isEmpty(linkValue1) && coupon.getLinkType().equals("11")) {
                coupon.setLinkValue(linkValue1);
            } else if (!StringUtils.isEmpty(linkValue2) && coupon.getLinkType().equals("9")) {
                coupon.setLinkValue(linkValue2);
            } else if (!StringUtils.isEmpty(linkValue3) && coupon.getLinkType().equals("28")) {
                coupon.setLinkValue(linkValue3);
            }

            if (!StringUtils.isEmpty(discount)) {
                BigDecimal Discount = new BigDecimal(discount).divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_DOWN);
                coupon.setDiscount(Discount);
            }

            if (!StringUtil.isEmpty(minimum1)) {
                coupon.setMinimum(new BigDecimal(minimum1));
            }
            if (request.getParameter("preferentialType").equals("2")) {
                coupon.setPreferentialType("2");
            } else {
                coupon.setPreferentialType("1");
            }
            couponService.updateByPrimaryKeySelective(coupon);

            if (!StringUtils.isEmpty(days)) {
                if (days.equals("1")) {//追加日期

                    CouponAddtaskConfigExample couponAddtaskConfigExample = new CouponAddtaskConfigExample();
                    couponAddtaskConfigExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(coupon.getId());
                    List<CouponAddtaskConfig> couponAddtaskConfigs = couponAddTaskConfigService.selectByExample(couponAddtaskConfigExample);
                    if (couponAddtaskConfigs.size() > 0) {

                        CouponAddtaskConfig couponAddtaskConfig = new CouponAddtaskConfig();
                        if (!StringUtil.isEmpty(request.getParameter("beginDate"))) {
                            couponAddtaskConfig.setBeginDate(dateFormat.parse(request.getParameter("beginDate") + ":00"));
                        }

                        if (!StringUtil.isEmpty(request.getParameter("endDate"))) {
                            couponAddtaskConfig.setEndDate(dateFormat.parse(request.getParameter("endDate") + ":59"));
                        }
                        couponAddtaskConfig.setAddCount(Integer.valueOf(addCount));
                        couponAddtaskConfig.setUpdateBy(staffId);
                        couponAddtaskConfig.setUpdateDate(new Date());
                        couponAddTaskConfigService.updateByExampleSelective(couponAddtaskConfig, couponAddtaskConfigExample);
                    } else {

                        CouponAddtaskConfig couponAddtaskConfig = new CouponAddtaskConfig();
                        couponAddtaskConfig.setCouponId(coupon.getId());
                        if (!StringUtil.isEmpty(request.getParameter("beginDate"))) {
                            couponAddtaskConfig.setBeginDate(dateFormat.parse(request.getParameter("beginDate") + ":00"));
                        }

                        if (!StringUtil.isEmpty(request.getParameter("endDate"))) {
                            couponAddtaskConfig.setEndDate(dateFormat.parse(request.getParameter("endDate") + ":59"));
                        }
                        couponAddtaskConfig.setAddCount(Integer.valueOf(addCount));
                        couponAddtaskConfig.setCreateBy(staffId);
                        couponAddtaskConfig.setCreateDate(new Date());
                        couponAddtaskConfig.setStatus("1");
                        couponAddtaskConfig.setDelFlag("0");
                        couponAddTaskConfigService.insert(couponAddtaskConfig);
                    }

                }

            }
            couponService.updateByPrimaryKeySelective(coupon);
            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * @Title svipCouponDetailManager
     * @Description TODO(优惠券明细列表管理)
     * @author pengl
     * @date 2019年3月6日 下午2:23:15
     */
    @RequestMapping("/coupon/svipCouponDetailManager.shtml")
    public ModelAndView svipCouponDetailManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/member/svip/svipCouponDetailList");
        m.addObject("memberCouponStatus", DataDicUtil.getStatusList("BU_MEMBER_COUPON", "STATUS"));
        m.addObject("couponId", request.getParameter("id"));
        return m;
    }

    /**
     * @Title svipCouponDetailList
     * @Description TODO(优惠券明细列表)
     * @author pengl
     * @date 2019年3月6日 下午2:23:48
     */
    @ResponseBody
    @RequestMapping("/coupon/svipCouponDetailList.shtml")
    public Map<String, Object> svipCouponDetailList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<MemberCouponCustom> dataList = null;
        Integer totalCount = 0;
        try {
            MemberCouponCustomExample memberCouponCustomExample = new MemberCouponCustomExample();
            MemberCouponCustomExample.MemberCouponCustomCriteria memberCouponCustomCriteria = memberCouponCustomExample.createCriteria();
            memberCouponCustomCriteria.andDelFlagEqualTo("0").andReceiveTypeIn(Arrays.asList("5", "6")).andIsGiveEqualTo("0");
            memberCouponCustomCriteria.andCouponIdEqualTo(Integer.valueOf(request.getParameter("couponId")));
            if (!StringUtil.isEmpty(request.getParameter("nick"))) {
                String nick = request.getParameter("nick");
                memberCouponCustomCriteria.andNickLike(nick);
            }
            if (!StringUtil.isEmpty(request.getParameter("mobile"))) {
                String mobile = request.getParameter("mobile");
                memberCouponCustomCriteria.andMobileEqualTo(mobile);
            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                String status = request.getParameter("status");
                memberCouponCustomCriteria.andStatusEqualTo(status);
            }
            memberCouponCustomExample.setOrderByClause(" a.id desc");
            memberCouponCustomExample.setLimitStart(page.getLimitStart());
            memberCouponCustomExample.setLimitSize(page.getLimitSize());
            dataList = memberCouponService.selectMemberCouponCustomByExample(memberCouponCustomExample);
            totalCount = memberCouponService.countMemberCouponCustomByExample(memberCouponCustomExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }


    //优惠劵组合限领表
    @RequestMapping("/marketing/coupon/couponCombineRecLimit.shtml")
    public ModelAndView couponCombineRecLimit(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/marketing/coupon/couponCombineRecLimit");
        return m;
    }


    //优惠劵组合限领列表数据
    @ResponseBody
    @RequestMapping("/coupon/couponcombinereclimitdata.shtml")
    public Map<String, Object> couponcombinereclimitdata(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<CouponCombineRecLimitCustom> dataList = null;
        Integer totalCount = 0;
        try {
            CouponCombineRecLimitCustomExample couponCombineRecLimitCustomExample = new CouponCombineRecLimitCustomExample();
            CouponCombineRecLimitCustomExample.CouponCombineRecLimitCriteria couponCombineRecLimitCriteria = couponCombineRecLimitCustomExample.createCriteria();
            couponCombineRecLimitCriteria.andDelFlagEqualTo("0");
            couponCombineRecLimitCustomExample.setOrderByClause("create_date desc");

            if (!StringUtil.isEmpty(request.getParameter("name"))) {
                String name = request.getParameter("name");
                couponCombineRecLimitCriteria.andNameEqualTo(name);
            }
            if (!StringUtil.isEmpty(request.getParameter("couponId"))) {
                String couponId = request.getParameter("couponId");
                couponCombineRecLimitCriteria.andcouponIdEqualTo(Integer.valueOf(couponId));
            }
            couponCombineRecLimitCustomExample.setLimitStart(page.getLimitStart());
            couponCombineRecLimitCustomExample.setLimitSize(page.getLimitSize());
            dataList = couponCombineRecLimitService.selectByCouponCombineRecLimitCustomExample(couponCombineRecLimitCustomExample);
            totalCount = couponCombineRecLimitService.countByCouponCombineRecLimitCustomExample(couponCombineRecLimitCustomExample);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }


    //创建优惠劵组合限领
    @RequestMapping("/coupon/addcouponcombinereclimit.shtml")
    public ModelAndView editcouponcombinereclimit(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/marketing/coupon/editCouponCombineRecLimit");
        String id = request.getParameter("Id");
        if (id != null) {
            CouponCombineRecLimitCustom combineRecLimitCustom = couponCombineRecLimitService.selectByCustomPrimaryKey(Integer.valueOf(id));
            m.addObject("combineRecLimitCustom", combineRecLimitCustom);
        }
        return m;
    }


    //保存,修改优惠券组合限领
    @RequestMapping(value = "/couPon/edItCouponCombinereclimit.shtml")
    @ResponseBody
    public Map<String, Object> edItCouponCombinereclimit(HttpServletRequest request, CouponCombineRecLimit couponCombineRecLimit, String couponId) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String returnCode = "0000";
        String returnMsg = "操作成功！";
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());

            List<Integer> couponidList = new ArrayList<Integer>();

            String[] couponIdlist = couponId.split(",");

            for (String couponiString : couponIdlist) {
                couponidList.add(Integer.valueOf(couponiString));
            }

            Integer couponIdlinInteger = null;
            if (couponIdlist.length > 0) {
                couponIdlinInteger = Integer.parseInt(couponIdlist[0]);
            }

            CouponExample couponExample = new CouponExample();
            couponExample.createCriteria().andDelFlagEqualTo("0").andRecLimitTypeNotEqualTo("3").andIdEqualTo(couponIdlinInteger);
            List<Coupon> CouponList = couponService.selectByExample(couponExample);


            CouponExample couponExample1 = new CouponExample();
            couponExample1.createCriteria().andDelFlagEqualTo("0").andRecLimitTypeNotEqualTo("3").andIdIn(couponidList);
            List<Coupon> couponList = couponService.selectByExample(couponExample1);
            List<Integer> couponids = new ArrayList<Integer>();
            if (CouponList.size() > 0) {
                for (Coupon coupon : couponList) {
                    if (coupon.getRecLimitType().equals(CouponList.get(0).getRecLimitType()) && coupon.getRecEach().equals(CouponList.get(0).getRecEach())) {
                        couponids.add(coupon.getId());
                    }
                }

            }


            if (couponCombineRecLimit.getId() == null) {

                if (couponids.size() == couponIdlist.length) {
                    couponCombineRecLimit.setCreateBy(staffId);
                    couponCombineRecLimit.setCreateDate(new Date());
                    couponCombineRecLimit.setDelFlag("0");
                    couponCombineRecLimitService.insertSelective(couponCombineRecLimit);

                    for (Integer couponid : couponids) {
                        CouponCombineRecLimitDtl combineRecLimitDtl = new CouponCombineRecLimitDtl();
                        combineRecLimitDtl.setCouponCombineRecLimitId(couponCombineRecLimit.getId());
                        combineRecLimitDtl.setCouponId(couponid);
                        combineRecLimitDtl.setCreateBy(staffId);
                        combineRecLimitDtl.setCreateDate(new Date());
                        combineRecLimitDtl.setDelFlag("0");
                        couponCombineRecLimitDtlService.insert(combineRecLimitDtl);
                    }


                } else {
                    resMap.put("returnCode", "9999");
                    resMap.put("returnMsg", "优惠券ID必须存在且限领类型和限领数量必须一致~!（限领方式不能为不限）");
                    return resMap;
                }


            } else {

                if (couponids.size() == couponIdlist.length) {

                    CouponCombineRecLimit couponCombineRecLimit2 = couponCombineRecLimitService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
                    couponCombineRecLimit2.setUpdateBy(staffId);
                    couponCombineRecLimit2.setUpdateDate(new Date());
                    couponCombineRecLimit2.setName(couponCombineRecLimit.getName());
                    couponCombineRecLimitService.updateByPrimaryKeySelective(couponCombineRecLimit2);

                    //先删除
                    CouponCombineRecLimitDtlExample combineRecLimitDtlExample = new CouponCombineRecLimitDtlExample();
                    combineRecLimitDtlExample.createCriteria().andDelFlagEqualTo("0").andCouponCombineRecLimitIdEqualTo(couponCombineRecLimit2.getId());
                    CouponCombineRecLimitDtl combineRecLimitDtl = new CouponCombineRecLimitDtl();
                    combineRecLimitDtl.setDelFlag("1");
                    couponCombineRecLimitDtlService.updateByExampleSelective(combineRecLimitDtl, combineRecLimitDtlExample);

                    for (Integer couponid : couponids) {//添加
                        CouponCombineRecLimitDtl combineRecLimitDtl1 = new CouponCombineRecLimitDtl();
                        combineRecLimitDtl1.setCouponCombineRecLimitId(couponCombineRecLimit2.getId());
                        combineRecLimitDtl1.setCouponId(couponid);
                        combineRecLimitDtl1.setCreateBy(staffId);
                        combineRecLimitDtl1.setCreateDate(new Date());
                        combineRecLimitDtl1.setDelFlag("0");
                        couponCombineRecLimitDtlService.insert(combineRecLimitDtl1);

                    }


                } else {
                    resMap.put("returnCode", "9999");
                    resMap.put("returnMsg", "优惠券ID必须存在且限领类型和限领数量必须一致~!（限领方式不能为不限）");
                    return resMap;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            returnCode = "4004";
            returnMsg = "系统错误！";
        }
        resMap.put("returnCode", returnCode);
        resMap.put("returnMsg", returnMsg);
        return resMap;
    }


    //删除优惠劵组合
    @RequestMapping(value = "/coupon/CouponCombinereclimitdata.shtml")
    @ResponseBody
    public Map<String, Object> couponCombinereclimitdata(HttpServletRequest request, HttpServletResponse response, String id) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            String couponCombinereclimitId = request.getParameter("id");
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());

            CouponCombineRecLimit couponCombineRecLimit2 = couponCombineRecLimitService.selectByPrimaryKey(Integer.valueOf(couponCombinereclimitId));
            couponCombineRecLimit2.setUpdateBy(staffId);
            couponCombineRecLimit2.setUpdateDate(new Date());
            couponCombineRecLimit2.setDelFlag("1");
            couponCombineRecLimitService.updateByPrimaryKey(couponCombineRecLimit2);

            CouponCombineRecLimitDtlExample combineRecLimitDtlExample = new CouponCombineRecLimitDtlExample();
            combineRecLimitDtlExample.createCriteria().andDelFlagEqualTo("0").andCouponCombineRecLimitIdEqualTo(Integer.valueOf(couponCombinereclimitId));

            CouponCombineRecLimitDtl combineRecLimitDtl = new CouponCombineRecLimitDtl();
            combineRecLimitDtl.setUpdateBy(staffId);
            combineRecLimitDtl.setUpdateDate(new Date());
            combineRecLimitDtl.setDelFlag("1");
            couponCombineRecLimitDtlService.updateByExampleSelective(combineRecLimitDtl, combineRecLimitDtlExample);

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }

        return resMap;
    }

    /**
     * 特殊商家管理
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/specialMcht.shtml")
    public ModelAndView specialMcht(HttpServletRequest request, Model model) {
        ModelAndView m = new ModelAndView("/marketing/coupon/specialMcht");
        //查询主营类目
        ProductTypeExample productTypeExample = new ProductTypeExample();
        productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
        List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
        model.addAttribute("productTypeList", productTypes);
        return m;
    }

    /**
     * 特殊商家列表数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/specialMchtList.shtml")
    @ResponseBody
    public Map<String, Object> specialMchtList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<>();
        Object totalCount = 0;
        try {
            String mchtCode = request.getParameter("mchtCode");
            String name = request.getParameter("name");
            String productType = request.getParameter("productType");
            String fee = request.getParameter("fee");
            String type = request.getParameter("type");//用来确认前端要查的是未添加的商家
            Map<String, Object> parameMap = new HashMap<>();
            parameMap.put("limitStart", page.getLimitStart());
            parameMap.put("limitSize", page.getLimitSize());
            //如果是查询未添加的,把已经添加的id去除掉
            if (!StringUtils.isEmpty(type)) {
                ConfigSpecialMchtExample where = new ConfigSpecialMchtExample();
                where.createCriteria().andDelFlagEqualTo("0");
                List<ConfigSpecialMcht> configSpecialMchts = configSpecialMchtService.selectByExample(where);
                List<Integer> notIds = new ArrayList<>();
                if (configSpecialMchts.size() > 0) {
                    for (ConfigSpecialMcht configSpecialMcht : configSpecialMchts) {
                        Integer mchtId = configSpecialMcht.getMchtId();
                        notIds.add(mchtId);
                        parameMap.put("notIds", notIds);
                    }
                }
            }

            if (!StringUtils.isEmpty(mchtCode) && mchtCode.length() > 0) {
                parameMap.put("mchtCode", mchtCode);
            }
            if (!StringUtils.isEmpty(productType) && !"0".equals(productType)) {
                parameMap.put("productType", Integer.parseInt(productType));
            }
            //转换成decimal类型到数据库比较大小
            if (!StringUtils.isEmpty(fee) && fee.length() > 0) {
                BigDecimal bigDecimal = new BigDecimal(fee);
                bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
                parameMap.put("fee", bigDecimal);
            }
            //如果公司名称能搜索到数据,则返回公司名称的数据,如果搜不到,搜店铺名称的数据,如果都没就没数据
            if (!StringUtils.isEmpty(name) && name.length() > 0) {
                String companyName = "%" + name + "%";
                parameMap.put("companyName", companyName);
                List<Map<String, Object>> companyNameMchts;
                if (!StringUtils.isEmpty(type)) {
                    companyNameMchts = configSpecialMchtService.selectNotSpecialMchtList(parameMap);
                    totalCount = configSpecialMchtService.selectNotSpecialMchtListCount(parameMap);
                    //给前端判断返回的是未添加的还是添加的
                    for (Map<String, Object> companyNameMcht : companyNameMchts) {
                        companyNameMcht.put("type", "added");
                    }
                } else {
                    companyNameMchts = configSpecialMchtService.selectSpecialMchtList(parameMap);
                    totalCount = configSpecialMchtService.selectSpecialMchtListCount(parameMap);
                }
                if (companyNameMchts.size() > 0) {
                    resMap.put("Total", totalCount);
                    resMap.put("Rows", companyNameMchts);
                } else {
                    String shopName = "%" + name + "%";
                    parameMap.put("shopName", shopName);
                    List<Map<String, Object>> shopNameMchts;
                    if (!StringUtils.isEmpty(type)) {
                        shopNameMchts = configSpecialMchtService.selectNotSpecialMchtList(parameMap);
                        totalCount = configSpecialMchtService.selectNotSpecialMchtListCount(parameMap);
                        //给前端判断返回的是未添加的还是添加的
                        for (Map<String, Object> shopNameMcht : shopNameMchts) {
                            shopNameMcht.put("type", "added");
                        }
                    } else {
                        shopNameMchts = configSpecialMchtService.selectSpecialMchtList(parameMap);
                        totalCount = configSpecialMchtService.selectSpecialMchtListCount(parameMap);
                    }
                    if (shopNameMchts.size() > 0) {
                        resMap.put("Total", totalCount);
                        resMap.put("Rows", shopNameMchts);
                    }
                }
            } else {
                List<Map<String, Object>> specialMchts;
                if (!StringUtils.isEmpty(type)) {
                    specialMchts = configSpecialMchtService.selectNotSpecialMchtList(parameMap);
                    totalCount = configSpecialMchtService.selectNotSpecialMchtListCount(parameMap);
                    //给前端判断返回的是未添加的还是添加的
                    for (Map<String, Object> specialMcht : specialMchts) {
                        specialMcht.put("type", "added");
                    }
                } else {
                    specialMchts = configSpecialMchtService.selectSpecialMchtList(parameMap);
                    totalCount = configSpecialMchtService.selectSpecialMchtListCount(parameMap);
                }
                resMap.put("Rows", specialMchts);
                resMap.put("Total", totalCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 添加特殊商家页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/addSpecialMchtPage.shtml")
    public ModelAndView addSpecialMchtPage(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/marketing/coupon/addSpecialMchtPage");
        return m;
    }

    /**
     * 查询单个商家数据回显
     *
     * @param request
     * @return
     */
    @RequestMapping("/couPon/getMchtInfo.shtml")
    @ResponseBody
    public Map<String, Object> getMchtInfo(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            String mchtCode = request.getParameter("mchtCode");
            if (mchtCode.length() > 0 && !StringUtils.isEmpty(mchtCode)) {
                Map<String, Object> mchtInfoCustom = configSpecialMchtService.selectByMchtCode(mchtCode);
                resMap.put("mchtInfoCustom", mchtInfoCustom);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "系统繁忙");
        }
        return resMap;
    }

    /**
     * 添加商家为特殊商家
     *
     * @param request
     * @return
     */
    @RequestMapping("/couPon/addSpecialMcht.shtml")
    @ResponseBody
    public Map<String, Object> addSpecialMcht(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            String mchtIdStr = request.getParameter("mchtId");
            String mchtCode = request.getParameter("mchtCode");
            String companyName = request.getParameter("companyName");
            String shopName = request.getParameter("shopName");
            String productType = request.getParameter("productType");
            Integer mchtId = 0;
            if (!StringUtils.isEmpty(mchtIdStr)) {
                mchtId = Integer.valueOf(mchtIdStr);
                //执行添加特殊商家
                setSpecialMcht(mchtId,staffID,resMap);
            } else {
                boolean flag = true;
                if (mchtCode.length() > 0 && !StringUtils.isEmpty(mchtCode)) {
                    Map<String, Object> mchtInfoCustom = configSpecialMchtService.selectByMchtCode(mchtCode);
                    //判断商家序号是否正确
                    if (mchtInfoCustom != null && mchtInfoCustom.size() > 0) {
                        //判断公司名称是否正确
                        if (mchtInfoCustom.get("companyName") != null) {
                            if (!StringUtils.isEmpty(companyName)) {
                                if (companyName.equals(mchtInfoCustom.get("companyName"))) {
                                    System.out.println("公司名称正确");
                                } else {
                                    flag = false;
                                    resMap.put("returnCode", "4004");
                                    resMap.put("returnMsg", "请先输入正确的公司名称");
                                }
                            } else {
                                flag = false;
                                resMap.put("returnCode", "4004");
                                resMap.put("returnMsg", "请先输入公司名称");
                            }
                        }
                        //判断商家名称是否正确
                        if (mchtInfoCustom.get("shopName") != null) {
                            if (!StringUtils.isEmpty(shopName)) {
                                if (shopName.equals(mchtInfoCustom.get("shopName"))) {
                                    System.out.println("商家名称正确");
                                } else {
                                    flag = false;
                                    resMap.put("returnCode", "4004");
                                    resMap.put("returnMsg", "请先输入正确的商家名称");
                                }
                            } else {
                                flag = false;
                                resMap.put("returnCode", "4004");
                                resMap.put("returnMsg", "请先输入商家名称");
                            }
                        }
                        //判断商家主营类目是否正确
                        if (mchtInfoCustom.get("productType") != null) {
                            if (!StringUtils.isEmpty(productType)) {
                                if (productType.equals(mchtInfoCustom.get("productType"))) {
                                    System.out.println("商家主营类目正确");
                                } else {
                                    flag = false;
                                    resMap.put("returnCode", "4004");
                                    resMap.put("returnMsg", "请先输入正确的商家主营类目");
                                }
                            } else {
                                flag = false;
                                resMap.put("returnCode", "4004");
                                resMap.put("returnMsg", "请先输入商家主营类目");
                            }
                        }
                    } else {
                        flag = false;
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", "请先输入正确的商家序号");
                    }
                    if (flag) {
                        mchtId = (Integer) mchtInfoCustom.get("id");
                        //执行添加特殊商家
                        setSpecialMcht(mchtId,staffID,resMap);
                    }
                } else {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "请先输入商家序号");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "系统繁忙");
        }
        return resMap;
    }

    /**
     * 批量添加特殊商家页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/batchAddSpecialMchtPage.shtml")
    @ResponseBody
    public Map<String, Object> batchAddSpecialMchtPage(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        String ids = request.getParameter("ids");
        String[] mchtIds = ids.split(",");
        String mchtIdSuccess = "";
        String mchtIdError = "";

        for (String mchtIdStr : mchtIds) {
            Integer mchtId = Integer.valueOf(mchtIdStr);
            try {
                Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
                ConfigSpecialMchtExample where = new ConfigSpecialMchtExample();
                where.createCriteria().andMchtIdEqualTo(mchtId);
                List<ConfigSpecialMcht> configSpecialMchts = configSpecialMchtService.selectByExample(where);
                //如果在特殊商家表已经存在只是删除状态,就修改状态,如果没有就直接新建添加
                if (configSpecialMchts.size() > 0) {
                    for (ConfigSpecialMcht configSpecialMcht : configSpecialMchts) {
                        String delFlag = configSpecialMcht.getDelFlag();
                        if ("0".equals(delFlag)) {
                            resMap.put("returnCode", "4004");
                            resMap.put("returnMsg", mchtId + "商家已经是特殊商家,不能重复添加");
                        } else {
                            configSpecialMcht.setDelFlag("0");
                            configSpecialMcht.setUpdateBy(Integer.valueOf(staffID));
                            configSpecialMcht.setUpdateDate(new Date());
                            configSpecialMchtService.updateByPrimaryKeySelective(configSpecialMcht);
                            //添加日志
                            setLog(mchtId, "1", staffID);
                            mchtIdSuccess += mchtId + ",";
                        }
                    }
                } else {
                    ConfigSpecialMcht configSpecialMcht = new ConfigSpecialMcht();
                    //设置新建数据
                    configSpecialMcht.setMchtId(mchtId);
                    configSpecialMcht.setCreateBy(Integer.valueOf(staffID));
                    configSpecialMcht.setCreateDate(new Date());
                    configSpecialMcht.setDelFlag("0");
                    configSpecialMcht.setUpdateBy(Integer.valueOf(staffID));
                    configSpecialMcht.setUpdateDate(new Date());
                    int i = configSpecialMchtService.insertSelective(configSpecialMcht);
                    //添加日志
                    setLog(mchtId, "1", staffID);
                    mchtIdSuccess += mchtId + ",";
                }
            } catch (Exception e) {
                mchtIdError += mchtId;
                resMap.put("returnCode", "4004");
                resMap.put("mchtIdSuccess", mchtIdSuccess);
                resMap.put("mchtIdError", mchtIdError);
            }
        }
        return resMap;
    }

    /**
     * 删除特殊商家
     *
     * @param request
     * @return
     */
    @RequestMapping("/couPon/delSpecialMcht.shtml")
    @ResponseBody
    public Map<String, Object> delSpecialMcht(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            Integer id = Integer.valueOf(request.getParameter("id"));
            ConfigSpecialMcht configSpecialMcht = configSpecialMchtService.selectByPrimaryKey(id);
            if (configSpecialMcht != null) {
                if (!"1".equals(configSpecialMcht.getDelFlag())) {
                    configSpecialMcht.setDelFlag("1");
                    configSpecialMcht.setUpdateBy(Integer.valueOf(staffID));
                    configSpecialMcht.setUpdateDate(new Date());
                    configSpecialMchtService.updateByPrimaryKeySelective(configSpecialMcht);
                    //添加日志
                    setLog(configSpecialMcht.getMchtId(), "2", staffID);
                } else {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "所选商家不是特殊商家");
                }
            } else {
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", "所选商家不是特殊商家");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "系统繁忙");
        }
        return resMap;
    }

    /**
     * 批量删除特殊商家
     *
     * @param request
     * @return
     */
    @RequestMapping("/couPon/batchDelSpecialMcht.shtml")
    @ResponseBody
    public Map<String, Object> batchDelSpecialMcht(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        String ids = request.getParameter("ids");
        String[] delIds = ids.split(",");
        String idSuccess = "";
        String idError = "";

        for (String idstr : delIds) {
            if ("null".equals(idstr)) {
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", "所选商家不是特殊商家");
            } else {
                Integer id = Integer.valueOf(idstr);
                try {
                    Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
                    ConfigSpecialMcht configSpecialMcht = configSpecialMchtService.selectByPrimaryKey(id);
                    if (configSpecialMcht != null) {
                        if (!"1".equals(configSpecialMcht.getDelFlag())) {
                            configSpecialMcht.setDelFlag("1");
                            configSpecialMcht.setUpdateBy(Integer.valueOf(staffID));
                            configSpecialMcht.setUpdateDate(new Date());
                            configSpecialMchtService.updateByPrimaryKeySelective(configSpecialMcht);
                            //添加日志
                            setLog(configSpecialMcht.getMchtId(), "2", staffID);
                            idSuccess += id + ",";
                        } else {
                            resMap.put("returnCode", "4004");
                            resMap.put("returnMsg", "所选商家不是特殊商家");
                            idError += id + ",";
                        }
                    } else {
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", id + "不是特殊商家");
                        idError += id + ",";
                    }
                } catch (Exception e) {
                    idError += id + ",";
                    resMap.put("returnCode", "4004");
                    resMap.put("idError", idSuccess);
                    resMap.put("idError", idError);
                }
            }
        }
        return resMap;
    }


    /**
     * 商家日志页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/specialMchtStatusLogPage.shtml")
    public ModelAndView specialMchtStatusLogPage(HttpServletRequest request) {
        String rtPage = "/marketing/coupon/specialMchtStatusLogPage";// 重定向
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
        resMap.put("mchtId", mchtId);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 查询商家日志
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/specialMchtStatusLogs.shtml")
    @ResponseBody
    public Map<String, Object> specialMchtStatusLogs(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<>();
        Object totalCount = 0;
        try {
            Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
            ConfigSpecialMchtStatusLogExample where = new ConfigSpecialMchtStatusLogExample();
            ConfigSpecialMchtStatusLogExample.Criteria criteria = where.createCriteria();
            criteria.andMchtIdEqualTo(mchtId);
            where.setLimitStart(page.getLimitStart());
            where.setLimitSize(page.getLimitSize());
            List<ConfigSpecialMchtStatusLog> specialMchtStatusLogs = configSpecialMchtStatusLogService.selectByExample(where);
            List<Map<String, Object>> rows = new ArrayList<>();
            for (ConfigSpecialMchtStatusLog specialMchtStatusLog : specialMchtStatusLogs) {
                Map<String, Object> map = new HashMap<>();
                if (specialMchtStatusLog.getUpdateBy() != null) {
                    SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(specialMchtStatusLog.getUpdateBy());
                    map.put("updateBy", sysStaffInfo.getStaffName());
                }
                map.put("updateDate", specialMchtStatusLog.getUpdateDate());
                map.put("status", specialMchtStatusLog.getStatus());
                rows.add(map);
            }
            totalCount = configSpecialMchtStatusLogService.countByExample(where);
            resMap.put("Total", totalCount);
            resMap.put("Rows", rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 添加日志提取方法
     *
     * @param mchtId
     * @param staffID
     */
    public void setLog(Integer mchtId, String status, Integer staffID) {
        //添加日志
        ConfigSpecialMchtStatusLog specialMchtStatusLog = new ConfigSpecialMchtStatusLog();
        specialMchtStatusLog.setMchtId(mchtId);
        specialMchtStatusLog.setStatus(status);
        specialMchtStatusLog.setCreateBy(staffID);
        specialMchtStatusLog.setCreateDate(new Date());
        specialMchtStatusLog.setUpdateBy(staffID);
        specialMchtStatusLog.setUpdateDate(new Date());
        specialMchtStatusLog.setDelFlag("0");
        configSpecialMchtStatusLogService.insertSelective(specialMchtStatusLog);
    }

    /**
     * 添加特殊商家
     * @param mchtId
     * @param staffID
     * @param resMap
     */
    public void setSpecialMcht(Integer mchtId,Integer staffID, Map<String, Object> resMap) {
        //添加特殊商家
        ConfigSpecialMchtExample where = new ConfigSpecialMchtExample();
        where.createCriteria().andMchtIdEqualTo(mchtId);
        List<ConfigSpecialMcht> configSpecialMchts = configSpecialMchtService.selectByExample(where);
        //如果在特殊商家表已经存在只是删除状态,就修改状态,如果没有就直接新建添加
        if (configSpecialMchts.size() > 0) {
            for (ConfigSpecialMcht configSpecialMcht : configSpecialMchts) {
                String delFlag = configSpecialMcht.getDelFlag();
                if ("0".equals(delFlag)) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "商家已经是特殊商家,不能重复添加");
                } else {
                    configSpecialMcht.setDelFlag("0");
                    configSpecialMcht.setUpdateBy(Integer.valueOf(staffID));
                    configSpecialMcht.setUpdateDate(new Date());
                    configSpecialMchtService.updateByPrimaryKeySelective(configSpecialMcht);
                    //添加日志
                    setLog(mchtId, "1", staffID);
                }
            }
        } else {
            ConfigSpecialMcht configSpecialMcht = new ConfigSpecialMcht();
            //设置新建数据
            configSpecialMcht.setMchtId(mchtId);
            configSpecialMcht.setCreateBy(Integer.valueOf(staffID));
            configSpecialMcht.setCreateDate(new Date());
            configSpecialMcht.setDelFlag("0");
            configSpecialMcht.setUpdateBy(Integer.valueOf(staffID));
            configSpecialMcht.setUpdateDate(new Date());
            int i = configSpecialMchtService.insertSelective(configSpecialMcht);
            //添加日志
            setLog(mchtId, "1", staffID);
        }
    }
}
