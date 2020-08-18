package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.enums.ActivityStatusEnum;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MemberAllowanceUsageMapper;
import com.jf.dao.TopFieldModuleFieldMapper;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName ActivityAreaNewController
 * @Description TODO(新版会场)
 * @author pengl
 * @date 2018年1月22日 上午10:45:36getActivityAreaList
 */
@SuppressWarnings("serial")
@Controller
public class ActivityAreaNewController extends BaseController {

	@Autowired
	private ActivityAreaService activityAreaService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private MemberGroupService memberGroupService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private ActivityGroupSerivce activityGroupSerivce;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private FullCutService fullCutService;
	
	@Autowired
	private FullGiveService fullGiveService;
	
	@Autowired
	private FullDiscountService fullDiscountService;
	
	@Autowired
	private ActivityAreaTempletPparamService activityAreaTempletPparamService;
	
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private CouponExchangeCodeService couponExchangeCodeService;
	
	@Autowired
	private ActivityAreaDecorateService activityAreaDecorateService;
	
	@Autowired
	private DecorateAreaService decorateAreaService;
	
	@Autowired
	private DecorateModuleService decorateModuleService;
	
	@Autowired
	private ModuleItemService moduleItemService;
	
	@Autowired
	private ProductPicService productPicService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SeckillTimeService seckillTimeService;
	
	@Autowired
	private BgModuleCategoryService bgModuleCategoryService;
	
	@Autowired
	private TopFieldModuleFieldMapper topFieldModuleFieldMapper;

	@Autowired
	private ActivityAreaPreSellRuleService activityAreaPreSellRuleService;
	
	@Autowired
	private TopFieldModuleFieldService topFieldModuleFieldService;

	@Autowired
	private SeckillModuleColorSettingService  seckillModuleColorSettingService;

	@Autowired
	private CouponModuleTimeService couponModuleTimeService;

	@Autowired
	private ActivityAreaModuleService activityAreaModuleService;

	@Autowired
	private ActivityAreaSettingService activityAreaSettingService;

	@Autowired
	private CustomPageService customPageService;

	@Autowired
	private AllowanceInfoService allowanceInfoService;

	@Autowired
    private AllowanceSettingService allowanceSettingService;

	@Autowired
	private AllowanceSettingIntegralExchangeService allowanceSettingIntegralExchangeService;

	@Autowired
	private MemberAllowanceService memberAllowanceService;

	@Autowired
	private MemberAllowanceUsageMapper memberAllowanceUsageMapper;
	/**
	 * 
	 * @Title activityAreaManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月22日 下午1:42:55
	 */
	@RequestMapping("/activityAreaNew/activityAreaManager.shtml")
	public ModelAndView activityAreaManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		m.addObject("preferentialTypeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE")); //特惠类型
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //创建会场
			m.addObject("typeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "TYPE")); //类型
			m.addObject("createStaffList", activityAreaService.getCreateByList()); //获取30天内已创建人
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "STATUS")); //启用状态
			m.setViewName("/activityAreaNew/createActivityAreaList");
		}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //查看会场
			m.addObject("typeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "TYPE")); //类型
			m.setViewName("/activityAreaNew/seeActivityAreaList");
		}else if(!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //会场专员审核
			m.addObject("typeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "TYPE")); //类型
			m.setViewName("/activityAreaNew/auditActivityAreaList");
		}else if(!StringUtil.isEmpty(statusPage) && "4".equals(statusPage)) { //会场排期审核
			m.addObject("typeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "TYPE")); //类型
			m.setViewName("/activityAreaNew/scheduleActivityAreaList");
		}else if(!StringUtil.isEmpty(statusPage) && "5".equals(statusPage)) { //会场装修
			m.addObject("typeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "TYPE")); //类型
			m.setViewName("/activityAreaNew/decorationActivityAreaList");
		}else if(!StringUtil.isEmpty(statusPage) && "6".equals(statusPage)) { //创建推广会场
			m.addObject("createStaffList", activityAreaService.getCreateByList()); //获取30天内已创建人
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "STATUS")); //启用状态
			m.setViewName("/activityAreaNew/promotionActivityArea/createPromotionActivityAreaList");
		}else if(!StringUtil.isEmpty(statusPage) && "7".equals(statusPage)) { //推广会场商品管理
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
			productTypeExample.setOrderByClause(" seq_no");
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList); //1级类目
			m.addObject("createStaffList", activityAreaService.getCreateByList()); //获取30天内已创建人
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "STATUS")); //启用状态
			m.setViewName("/activityAreaNew/promotionActivityArea/promotionActivityAreaList");
		}
		m.addObject("statusPage", statusPage);
		return m;
	}
	
	/**
	 * 
	 * @Title getActivityAreaList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月22日 下午3:26:49
	 */
	@ResponseBody
	@RequestMapping("/activityAreaNew/getActivityAreaList.shtml")
	public Map<String, Object> getActivityAreaList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityAreaCustom> dataList = null;
		Integer totalCount = 0;
		try {
			String statusPage = request.getParameter("statusPage");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActivityAreaCustomExample activityAreaCustomExample = new ActivityAreaCustomExample();
			ActivityAreaCustomExample.ActivityAreaCustomCriteria activityAreaCustomCriteria = activityAreaCustomExample.createCriteria();
			activityAreaCustomCriteria.andDelFlagEqualTo("0");
			activityAreaCustomCriteria.andSourceEqualTo("1"); //会场
			activityAreaCustomExample.setLimitSize(page.getLimitSize());
			activityAreaCustomExample.setLimitStart(page.getLimitStart());
			if (!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //创建会场
				List<String> typeList = new ArrayList<String>();
				typeList.add("1");
				typeList.add("2");
				activityAreaCustomCriteria.andTypeIn(typeList); //会场类型 in （品牌会场、单品会场）
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			if (!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //查看会场
				List<String> typeList = new ArrayList<String>();
				typeList.add("1");
				typeList.add("2");
				activityAreaCustomCriteria.andTypeIn(typeList); //会场类型 in （品牌会场、单品会场）
				activityAreaCustomCriteria.andStatusEqualTo("1"); //会场启用状态=启用
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			if (!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //会场专员审核
				List<String> typeList = new ArrayList<String>();
				typeList.add("1");
				typeList.add("2");
				activityAreaCustomCriteria.andTypeIn(typeList); //会场类型 in （品牌会场、单品会场）
				activityAreaCustomCriteria.andStatusEqualTo("1"); //会场启用状态=启用
				activityAreaCustomCriteria.andActivityEndTimeGreaterThan(new Date()); //活动结束时间>NOW
				activityAreaCustomCriteria.andLimitMchtNumGreaterThan(0); //报名限额>0
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			if (!StringUtil.isEmpty(statusPage) && "4".equals(statusPage)) { //会场排期审核
				List<String> typeList = new ArrayList<String>();
				typeList.add("1");
				typeList.add("2");
				activityAreaCustomCriteria.andTypeIn(typeList); //会场类型 in （品牌会场、单品会场）
				activityAreaCustomCriteria.andStatusEqualTo("1"); //会场启用状态=启用
				activityAreaCustomCriteria.andActivityEndTimeGreaterThan(new Date()); //活动结束时间>NOW
				activityAreaCustomCriteria.andLimitMchtNumGreaterThan(0); //报名限额>0
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			if (!StringUtil.isEmpty(statusPage) && "5".equals(statusPage)) { //会场装修
				activityAreaCustomCriteria.andStatusEqualTo("1"); //会场启用状态=启用
				activityAreaCustomCriteria.andActivityEndTimeGreaterThan(new Date()); //活动结束时间>NOW
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			if (!StringUtil.isEmpty(statusPage) && "6".equals(statusPage)) { //创建推广会场
				activityAreaCustomCriteria.andTypeEqualTo("3"); //会场类型  3.推广会场
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			if (!StringUtil.isEmpty(statusPage) && "7".equals(statusPage)) { //推广会场商品管理
				activityAreaCustomCriteria.andTypeEqualTo("3"); //会场类型  3.推广会场
				activityAreaCustomCriteria.andActivityEndTimeGreaterThan(new Date()); //活动结束时间>NOW
				activityAreaCustomCriteria.andStatusEqualTo("1"); //会场启用状态=启用
				activityAreaCustomExample.setOrderByClause(" ba.id desc"); 
			}
			//会场ID
			if(!StringUtil.isEmpty(request.getParameter("activityAreaId"))) { 
				activityAreaCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("activityAreaId")));
			}
			//会场名称
			if(!StringUtil.isEmpty(request.getParameter("name"))) { 
				activityAreaCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			//促销方式
			if(!StringUtil.isEmpty(request.getParameter("preferentialType"))) { 
				activityAreaCustomCriteria.andPreferentialTypeEqualTo(request.getParameter("preferentialType"));
			}
			//活动开始日期
			if(!StringUtil.isEmpty(request.getParameter("activityBeginTime"))) {
				String activityBeginTimeA = request.getParameter("activityBeginTime") + " 00:00:00";
				String activityBeginTimeB = request.getParameter("activityBeginTime") + " 23:59:59";
				activityAreaCustomCriteria.andActivityBeginTimeBetween(sdf.parse(activityBeginTimeA), sdf.parse(activityBeginTimeB));
			}
			//活动结束日期
			if(!StringUtil.isEmpty(request.getParameter("activityEndTime"))) {
				String activityEndTimeA = request.getParameter("activityEndTime") + " 00:00:00";
				String activityEndTimeB = request.getParameter("activityEndTime") + " 23:59:59";
				activityAreaCustomCriteria.andActivityEndTimeBetween(sdf.parse(activityEndTimeA), sdf.parse(activityEndTimeB));
			}
			//类型
			if(!StringUtil.isEmpty(request.getParameter("type"))) {
				activityAreaCustomCriteria.andTypeEqualTo(request.getParameter("type"));
			}
			//会场创建人
			if(!StringUtil.isEmpty(request.getParameter("createBy"))) {
				activityAreaCustomCriteria.andCreateByEqualTo(Integer.parseInt(request.getParameter("createBy")));
			}
			//启用状态
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				activityAreaCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			//类目
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				activityAreaCustomCriteria.andProductTypeGroupFindInSet(request.getParameter("productTypeId"));
			}
			//是否预付会场
			if(!StringUtil.isEmpty(request.getParameter("isPreSell"))) {
				if("1".equals(request.getParameter("isPreSell"))){
					activityAreaCustomCriteria.andIsPreSellEqualTo(request.getParameter("isPreSell"));
				}else if("0".equals(request.getParameter("isPreSell"))){
					activityAreaCustomCriteria.andNotIsPreSellActivityArea(request.getParameter("isPreSell"));
				}
			}
			totalCount = activityAreaService.countByCustomExample(activityAreaCustomExample);
			dataList = activityAreaService.selectByCustomExample(activityAreaCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateActivityAreaStatus   
	 * @Description TODO(修改会场启动状态)   
	 * @author pengl
	 * @date 2018年1月23日 下午2:08:47
	 */
	@ResponseBody
	@RequestMapping("/activityAreaNew/updateActivityAreaStatus.shtml")
	public Map<String, Object> updateActivityAreaStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if (!StringUtil.isEmpty(request.getParameter("activityAreaId"))) {
				ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(request.getParameter("activityAreaId")));
				if ((!StringUtil.isEmpty(activityArea.getPushMchtType()) && activityArea.getPushMchtType().equals("3")) && (activityArea.getPreferentialType().equals("3") || activityArea.getPreferentialType().equals("4"))) {
					statusCode = "9999";
					message = "SPOP商家不允许参与“满赠和多买优惠”活动，不可启用！";
					map.put("statusCode", statusCode);
					map.put("message", message);
					return map;
					
				}else {
					
				ActivityArea activityAreaNew = new ActivityArea();
				activityAreaNew.setId(activityArea.getId());
				if(!StringUtil.isEmpty(request.getParameter("status")) && "1".equals(request.getParameter("status")) && !StringUtil.isEmpty(activityArea.getType())) {
					SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
					SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
					sysParamCfgCriteria.andParamCodeEqualTo("ACTIVITY_TEMPLET_PREFIX");
					sysParamCfgExample.setLimitSize(1);
					List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
					SysParamCfg sysParamCfg = sysParamCfgs.get(0);
					String defaultName = ""; 
					if ("1".equals(activityArea.getType())){ //品牌会场
						defaultName = "brand_def.html";
					}else if ("2".equals(activityArea.getType())){ //单品会场
						defaultName = "single_def.html";
					}else if("3".equals(activityArea.getType())) { //推广会场
						defaultName = "single_def.html";
					}
					activityAreaNew.setTempletSuffix(sysParamCfg.getParamValue()+defaultName);
				}
				activityAreaNew.setStatus(request.getParameter("status")); //会场状态   0：未启用   1：启用
				activityAreaService.updateByPrimaryKeySelective(activityAreaNew); //修改活动会场
				if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "1".equals(activityArea.getPreferentialType())) { //优惠券
					if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
						String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
						for (int i = 0; i < preferentialIdGroups.length; i++) {
							Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
							coupon.setStatus(request.getParameter("status")); //状态   0：未启用   1：启用
							coupon.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
							coupon.setUpdateDate(new Date());
							couponService.updateByPrimaryKeySelective(coupon);
						}
					}
				}
			  }
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title addOrUpdateOrShowActivityAreaManager   
	 * @Description TODO(创建、修改、查看 会场)   
	 * @author pengl
	 * @date 2018年1月23日 下午4:08:33
	 */
	@RequestMapping("/activityAreaNew/addOrUpdateOrShowActivityAreaManager.shtml")
	public ModelAndView addOrUpdateOrShowActivityAreaManager(HttpServletRequest request, String activityAreaId, String statusPage) {
		ModelAndView m = new ModelAndView();
		m.addObject("pushMchtTypeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "PUSH_MCHT_TYPE")); //推送商家类型
		List<MemberGroup> memberGroupList=memberGroupService.selectMemberGroupList(); //会员限制
		m.addObject("memberGroupList", memberGroupList);
		String activityAreaType = request.getParameter("activityAreaType"); //会场类型  3.推广会场
		if(!StringUtil.isEmpty(activityAreaType) && "3".equals(activityAreaType)) {
			m.addObject("activityAreaType", activityAreaType); //3.推广会场
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
			productTypeExample.setOrderByClause(" seq_no");
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList); //1级类目
		}else {
			ActivityGroupExample activityGroupExample = new ActivityGroupExample();
			activityGroupExample.createCriteria().andDelFlagEqualTo("0");
			activityGroupExample.setOrderByClause(" id desc");
			activityGroupExample.setLimitStart(0);
			activityGroupExample.setLimitSize(50);
			List<ActivityGroup> activityGroupList = activityGroupSerivce.selectByExample(activityGroupExample); //调用特卖分组ID最大50条
			m.addObject("activityGroupList", activityGroupList); //调用特卖分组ID最大50条
		}
		
		if(!StringUtil.isEmpty(activityAreaId)) {
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
			m.addObject("activityArea", activityArea); //会场
			if(activityArea != null && !StringUtil.isEmpty(activityArea.getMchtIdGroup())) {
				String[] str = activityArea.getMchtIdGroup().split(",");
				StringBuffer mchtIdGroupCode = new StringBuffer();
				for (int i = 0; i < str.length; i++) {
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(str[i]));
					if(i != 0) {
						mchtIdGroupCode.append(",");
					}
					mchtIdGroupCode.append(mchtInfo.getMchtCode());
				}
				m.addObject("mchtIdGroupCode", mchtIdGroupCode.toString()); 
			}
			if("1".equals(activityArea.getIsPreSell())){
                ActivityAreaPreSellRuleExample activityAreaPreSellRuleExample = new ActivityAreaPreSellRuleExample();
                activityAreaPreSellRuleExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityArea.getId());
				List<ActivityAreaPreSellRule> activityAreaPreSellRules = activityAreaPreSellRuleService.selectByExample(activityAreaPreSellRuleExample);
				m.addObject("activityAreaPreSellRule", activityAreaPreSellRules.get(0));
			}
		}
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //创建或修改会场
			try {
				InputStream stream = ActivityAreaNewController.class.getResourceAsStream("/base_config.properties");
				Properties properties = new Properties();
				properties.load(stream);
				String contextPathStr = properties.getProperty("CONTEXTPATH");
				stream.close();
				m.addObject("contextPathStr", contextPathStr); //查看图片路径
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			m.setViewName("/activityAreaNew/addOrUpdateActivityArea");
		}else { //查看会场
			m.setViewName("/activityAreaNew/showActivityArea");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateActivityArea   
	 * @Description TODO(创建、修改 会场)   
	 * @author pengl
	 * @date 2018年1月23日 下午5:36:02
	 */
	@RequestMapping("/activityAreaNew/addOrUpdateActivityArea.shtml")
	public ModelAndView addOrUpdateActivityArea(HttpServletRequest request, ActivityArea activityArea, ActivityAreaPreSellRule activityAreaPreSellRule) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActivityArea activityAreaNew = new ActivityArea();
			String enrollBeginTimeStr = request.getParameter("enrollBeginTimeStr");
			String enrollEndTimeStr = request.getParameter("enrollEndTimeStr");
			
			if(!StringUtil.isEmpty(enrollBeginTimeStr)) 
				activityAreaNew.setEnrollBeginTime(sdf.parse(enrollBeginTimeStr));
			if(!StringUtil.isEmpty(enrollEndTimeStr)) 
				activityAreaNew.setEnrollEndTime(sdf.parse(enrollEndTimeStr));
			activityAreaNew.setName(activityArea.getName());
			activityAreaNew.setDescription(activityArea.getDescription());
			activityAreaNew.setBenefitPoint(activityArea.getBenefitPoint());
			activityAreaNew.setEntryPic(activityArea.getEntryPic());
			activityAreaNew.setPic(activityArea.getPic());
			activityAreaNew.setLimitMchtNum(activityArea.getLimitMchtNum());
			/*if(activityArea.getPushMchtType() == null) {
				activityAreaNew.setPushMchtType("");
			}else {
				activityAreaNew.setPushMchtType(activityArea.getPushMchtType());
			}*/
			if (!StringUtil.isEmpty(activityArea.getPushMchtType())) {
				activityAreaNew.setPushMchtType(activityArea.getPushMchtType());
			}
			activityAreaNew.setMchtIdGroup(activityArea.getMchtIdGroup());
			activityAreaNew.setMinMemberGroup(activityArea.getMinMemberGroup());
			activityAreaNew.setActivityGroupId(activityArea.getActivityGroupId());
			activityAreaNew.setProductTypeGroup(activityArea.getProductTypeGroup());
			activityAreaNew.setAreaLabel(activityArea.getAreaLabel());
			if (!StringUtil.isEmpty(activityArea.getAreaLabelPic())) {
				activityAreaNew.setAreaLabelPic(activityArea.getAreaLabelPic());
			}
			if(StringUtil.isEmpty(activityArea.getPreferentialType())) {
				activityAreaNew.setPreferentialType("0"); //优惠默认为：无
			}
			activityAreaNew.setPurchaseLimits(activityArea.getPurchaseLimits());
			if("1".equals(activityArea.getPurchaseLimits())){//1.限购
				activityAreaNew.setPurchaseLimitsQuantity(activityArea.getPurchaseLimitsQuantity());
			}else{
				activityAreaNew.setPurchaseLimitsQuantity(0);
			}
            activityAreaNew.setIsPreSell(activityArea.getIsPreSell());
            activityAreaNew.setShareIntegralType(activityArea.getShareIntegralType());
            activityAreaNew.setMinShareIntegral(activityArea.getMinShareIntegral());
            activityAreaNew.setMaxShareIntegral(activityArea.getMaxShareIntegral());
            activityAreaNew.setActivitySharePic(activityArea.getActivitySharePic());
            activityAreaNew.setActivityShareDesc(activityArea.getActivityShareDesc());
			if(activityArea.getId() == null) { //添加会场
				activityAreaNew.setType(activityArea.getType());
				activityAreaNew.setPreferentialType("0");
				activityAreaNew.setSource("1");
				activityAreaNew.setMchtId(0);
				activityAreaNew.setStatus("0");
				activityAreaNew.setPreheatFlag("2");
				activityAreaNew.setCreateBy(staffId);
				activityAreaNew.setCreateDate(date);
				activityAreaNew.setUpdateDate(date);
				if("1".equals(activityArea.getIsPreSell())){//1.预售会场
                    activityAreaPreSellRule.setCreateBy(staffId);
                    activityAreaPreSellRule.setCreateDate(date);
                    activityAreaPreSellRule.setDelFlag("0");
					activityAreaService.addPresellactivityArea(activityAreaNew,activityAreaPreSellRule);
				}else{
					activityAreaService.insertSelective(activityAreaNew);
				}

			}else { //修改会场
				activityAreaNew.setId(activityArea.getId());
				activityAreaNew.setUpdateBy(staffId);
				activityAreaNew.setUpdateDate(date);
				ActivityArea oldActivityArea = activityAreaService.selectByPrimaryKey(activityArea.getId());
				if("1".equals(activityArea.getIsPreSell())){//1.预售会场
                    activityAreaService.updatePresellactivityArea(activityAreaNew,activityAreaPreSellRule,staffId,date);
                }else{
                    activityAreaService.updateByPrimaryKeySelective(activityAreaNew);
                }
                if("1".equals(oldActivityArea.getShareIntegralType()) || "2".equals(oldActivityArea.getShareIntegralType())){
					if("0".equals(activityAreaNew.getShareIntegralType())) {
						activityAreaService.updateShareIntegralIsNull(activityAreaNew.getId());
					}
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title validateEnrollEndTime   
	 * @Description TODO(报名时间验证)   
	 * @author pengl
	 * @date 2018年1月26日 下午3:30:34
	 */
	@ResponseBody
	@RequestMapping("/activityAreaNew/validateEnrollEndTime.shtml")
	public Map<String, Object> validateEnrollEndTime(Integer activityAreaId, Date enrollEndTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "";
		try {
			if (activityAreaId != null && enrollEndTime != null) {
				ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
				if(activityArea != null && activityArea.getActivityEndTime() != null) {
					if(enrollEndTime.getTime() >= activityArea.getActivityEndTime().getTime()) {
						code = "999";
						msg = "报名截止时间必须小于活动截止时间！";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = "999";
			msg = "系统错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title activityMemberInfoList   
	 * @Description TODO(选择商家列表)   
	 * @author pengl
	 * @date 2018年1月26日 下午3:57:09
	 */
	@RequestMapping("/activityAreaNew/activityMemberInfoList.shtml")
	public ModelAndView activityMemberInfoList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/activityAreaNew/mchtInfoList");
		if(!StringUtil.isEmpty(request.getParameter("mchtIdGroupCode"))) {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			List<String> mchtCodeList = new ArrayList<String>();
			String[] mchtCodeS = request.getParameter("mchtIdGroupCode").split(",");
			for(String mchtCode : mchtCodeS) {
				mchtCodeList.add(mchtCode);
			}
			mchtInfoCustomCriteria.andMchtCodeIn(mchtCodeList);
			List<MchtInfoCustom> dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
			String mchtIdGroup = "";
			String mchtIdGroupCode = "";
			for(MchtInfoCustom mchtInfoCustom : dataList) {
				if("".equals(mchtIdGroup)) {
					mchtIdGroup = mchtInfoCustom.getId().toString();
				}else {
					mchtIdGroup += "," + mchtInfoCustom.getId().toString();
				}
				if("".equals(mchtIdGroupCode)) {
					mchtIdGroupCode = mchtInfoCustom.getMchtCode();
				}else {
					mchtIdGroupCode += "," + mchtInfoCustom.getMchtCode();
				}
			}
			m.addObject("mchtIdGroup", mchtIdGroup);
			m.addObject("mchtIdGroupCode", mchtIdGroupCode);
		}
		
		return m;
	}
	
	/**
	 * 
	 * @Title memberInfoList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月26日 下午4:31:51
	 */
	@ResponseBody
	@RequestMapping("/activityAreaNew/memberInfoList.shtml")
	public Map<String, Object> memberInfoList(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtInfoCustom> dataList = new ArrayList<MchtInfoCustom>();
		Integer totalCount = 0;
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("mchtIdGroup"))) {
				List<Integer> mchtIdList = new ArrayList<Integer>();
				String[] mchtIdS = request.getParameter("mchtIdGroup").split(",");
				for(String mchtId : mchtIdS) {
					mchtIdList.add(Integer.parseInt(mchtId));
				}
				mchtInfoCustomCriteria.andIdIn(mchtIdList);
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtInfoCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
			totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title activityManager   
	 * @Description TODO(官方会场列表中获取活动列表)   
	 * @author pengl
	 * @date 2018年1月30日 下午6:31:17
	 */
	@RequestMapping("/activityAreaNew/activityManager.shtml")
	public ModelAndView activityManager(HttpServletRequest request, String statusPage, Integer activityAreaId) {
		ModelAndView m = new ModelAndView();
		m.addObject("activityAreaId", activityAreaId); //活动会场ID
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList); //1级类目
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //专员
			List<SysStaffInfo> sysStaffInfoList = new ArrayList<SysStaffInfo>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("operate_audit_by", "operate_audit_by"); //运营审核人员
			List<Map<String, Object>> operateAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> operateAuditByMap : operateAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) operateAuditByMap.get("operate_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //专员审核人
			m.addObject("operateAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "OPERATE_AUDIT_STATUS")); //运营专员审核状态
			m.setViewName("/activityAreaNew/activityOperateList");
		}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //排期
			List<SysStaffInfo> sysStaffInfoList = new ArrayList<SysStaffInfo>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coo_audit_by", "coo_audit_by"); //运营总监
			List<Map<String, Object>> cooAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> cooAuditByMap : cooAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) cooAuditByMap.get("coo_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //排期审核人
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "COO_AUDIT_STATUS")); //运营总监审核状态
			m.setViewName("/activityAreaNew/activityCooList");
		}else { //查看
			m.addObject("statusList", ActivityStatusEnum.list);	// 活动状态
			m.addObject("operateAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "OPERATE_AUDIT_STATUS")); //运营专员审核状态
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "COO_AUDIT_STATUS")); //运营总监审核状态
			m.setViewName("/activityAreaNew/activitySeeList");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title activityList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月31日 上午9:54:36
	 */
	@ResponseBody
	@RequestMapping("/activityAreaNew/activityList.shtml")
	public Map<String, Object> activityList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityNewCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActivityCustomExample activityCustomExample = new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
			activityCustomCriteria.andDelFlagEqualTo("0");
			activityCustomCriteria.andActivityAreaBySourceEqualTo("1"); //会场
			if(!StringUtil.isEmpty(request.getParameter("statusAudit")) && request.getParameter("statusAudit").equals("1")) { //排期 专员审核状态未通过不显示，活动结束了不显示
				activityCustomCriteria.andOperateAuditStatusEqualTo("2");
				activityCustomCriteria.andActivityEndTimeGreaterThanNow();
			}
			//特卖id
			if(!StringUtil.isEmpty(request.getParameter("activityId"))) { 
				activityCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("activityId")));
			}
			//特卖名称
			if(!StringUtil.isEmpty(request.getParameter("name"))) { 
				activityCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			//促销方式
			if(!StringUtil.isEmpty(request.getParameter("preferentialType"))) { 
				activityCustomCriteria.andPreferentialTypeEqualTo(request.getParameter("preferentialType"));
			}
			//商家名称
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) { 
				activityCustomCriteria.andShopNameOrCompanyNameLike(request.getParameter("mchtName"));
			}
			//商家序号
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) { 
				activityCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			//活动状态
			if(!StringUtil.isEmpty(request.getParameter("status"))) { 
				if(Integer.parseInt(request.getParameter("status")) > 4) {
					activityCustomCriteria.andStatusEqualTo("6");
					activityCustomCriteria.andStatusCustom(Integer.parseInt(request.getParameter("status")));
				}else {
					activityCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				}
			}
			//专员审核人
			if(!StringUtil.isEmpty(request.getParameter("operateAuditBy"))) { 
				activityCustomCriteria.andOperateAuditByEqualTo(Integer.parseInt(request.getParameter("operateAuditBy")));
			}
			//专员审核状态
			if(!StringUtil.isEmpty(request.getParameter("operateAuditStatus"))) { 
				activityCustomCriteria.andOperateAuditStatusEqualTo(request.getParameter("operateAuditStatus"));
			}
			//排期审核人
			if(!StringUtil.isEmpty(request.getParameter("cooAuditBy"))) { 
				activityCustomCriteria.andCooAuditByEqualTo(Integer.parseInt(request.getParameter("cooAuditBy")));
			}
			//排期审核状态
			if(!StringUtil.isEmpty(request.getParameter("cooAuditStatus"))) { 
				activityCustomCriteria.andCooAuditStatusEqualTo(request.getParameter("cooAuditStatus"));
			}
			//设计审核人
			if(!StringUtil.isEmpty(request.getParameter("designAuditBy"))) { 
				activityCustomCriteria.andDesignAuditByEqualTo(Integer.parseInt(request.getParameter("designAuditBy")));
			}
			//设计审核状态
			if(!StringUtil.isEmpty(request.getParameter("designAuditStatus"))) { 
				activityCustomCriteria.andDesignAuditStatusEqualTo(request.getParameter("designAuditStatus"));
			}
			//类目
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				activityCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
			}
			//品牌
			if(!StringUtil.isEmpty(request.getParameter("productBrandId"))) {
				activityCustomCriteria.andProductBrandIdEqualTo(Integer.parseInt(request.getParameter("productBrandId")));
			}
			//期望日期
			if(!StringUtil.isEmpty(request.getParameter("expectedStartTime"))) {
				String expectedStartBeginTime = request.getParameter("expectedStartTime") + " 00:00:00";
				String expectedStartEndTime = request.getParameter("expectedStartTime") + " 23:59:59";
				activityCustomCriteria.andExpectedStartTimeBetween(sdf.parse(expectedStartBeginTime), sdf.parse(expectedStartEndTime));
			}
			//活动开始日期
			if(!StringUtil.isEmpty(request.getParameter("activityBeginTime"))) {
				String activityBeginTimeA = request.getParameter("activityBeginTime") + " 00:00:00";
				String activityBeginTimeB = request.getParameter("activityBeginTime") + " 23:59:59";
				activityCustomCriteria.andActivityBeginTimeBetween(activityBeginTimeA, activityBeginTimeB);
			}
			//活动结束日期
			if(!StringUtil.isEmpty(request.getParameter("activityEndTime"))) {
				String activityEndTimeA = request.getParameter("activityEndTime") + " 00:00:00";
				String activityEndTimeB = request.getParameter("activityEndTime") + " 23:59:59";
				activityCustomCriteria.andActivityEndTimeBetween(activityEndTimeA, activityEndTimeB);
			}
			//查看日期
			if(!StringUtil.isEmpty(request.getParameter("seeDateTime"))) {
				String seeDateTimeA = request.getParameter("seeDateTime") + " 00:00:00";
				String seeDateTimeB = request.getParameter("seeDateTime") + " 23:59:59";
				activityCustomCriteria.andActivityBeginEndTime(seeDateTimeA, seeDateTimeB);
			}
			//分组
			if(!StringUtil.isEmpty(request.getParameter("activityGroupId"))) {
				activityCustomCriteria.andActivityGroupIdEqualTo(Integer.parseInt(request.getParameter("activityGroupId")));
			}
			//活动会场ID
			if(!StringUtil.isEmpty(request.getParameter("activityAreaId"))) {
				activityCustomCriteria.andActivityAreaIdEqualTo(Integer.parseInt(request.getParameter("activityAreaId")));
			}
			activityCustomExample.setOrderByClause(" ba.submit_time desc");
			activityCustomExample.setLimitSize(page.getLimitSize());
			activityCustomExample.setLimitStart(page.getLimitStart());
			totalCount = activityService.countActivityCustomByExample(activityCustomExample);
			dataList = activityService.selectByCustomExample(activityCustomExample);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title showOrAuditActivity   
	 * @Description TODO(查看或审核活动页面)   
	 * @author pengl
	 * @date 2018年1月31日 上午10:58:31
	 */
	@RequestMapping("/activityAreaNew/showOrAuditActivity.shtml")
	public ModelAndView showOrAuditActivity(HttpServletRequest request, Integer activityId, String statusPage, String statusAudit) {
		ModelAndView m = new ModelAndView();
		ActivityNewCustom activityNewCustom = activityService.selectByCustomPrimaryKey(activityId);
		m.addObject("activityNewCustom", activityNewCustom);
		m.addObject("statusAudit", statusAudit); //审核活动  1：专员审核   2：排期审核
		
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList); //1级类目
		
		if(activityNewCustom.getProductTypeId() != null) {
			ProductTypeExample productTypeSecondExample = new ProductTypeExample();
			productTypeSecondExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andParentIdEqualTo(activityNewCustom.getProductTypeId());
			productTypeSecondExample.setOrderByClause(" seq_no");
			List<ProductType> productTypeSecondList = productTypeService.selectByExample(productTypeSecondExample);
			m.addObject("productTypeSecondList", productTypeSecondList); //2级类目
		}
		
		ProductBrandExample productBrandExample = new ProductBrandExample();
		ProductBrandExample.Criteria productBrandCriteria = productBrandExample.createCriteria();
		productBrandCriteria.andDelFlagEqualTo("0");
		List<ProductBrand> productBrandList = productBrandService.selectByExample(productBrandExample);
		m.addObject("productBrandList", productBrandList); //品牌
	
		if(!StringUtil.isEmpty(statusPage)) {
			if("1".equals(statusPage)) { //查看
				m.setViewName("/activityAreaNew/showActivity");
			}else if("2".equals(statusPage)) { //审核
				try {
					if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("2")) {
						if(activityNewCustom.getActivityBeginTime() != null && activityNewCustom.getActivityEndTime() != null && "6".equals(activityNewCustom.getStatus())) {
							Date date = new Date();
							if(activityNewCustom.getActivityBeginTime().getTime() <= date.getTime() && date.getTime() <= activityNewCustom.getActivityEndTime().getTime() ) {
								m.addObject("activityStatus", "13"); //活动中
							}
						} 
					}
					
					if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("1")) {
						Activity activity = new Activity();
						activity.setId(activityNewCustom.getId());
						activity.setStatus("3"); //活动审核更新为=审核中
						activityService.updateByPrimaryKeySelective(activity);
					}
					
					InputStream stream = ActivityNewController.class.getResourceAsStream("/base_config.properties");
					Properties properties = new Properties();
					properties.load(stream);
					String contextPathStr = properties.getProperty("CONTEXTPATH");
					stream.close();
					m.addObject("contextPathStr", contextPathStr); //查看图片路径
				
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				m.setViewName("/activityAreaNew/auditActivity");
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title auditActivity   
	 * @Description TODO(审核活动)   
	 * @author pengl
	 * @date 2018年1月31日 上午11:01:03
	 */
	@RequestMapping("/activityAreaNew/auditActivity.shtml")
	public ModelAndView auditActivity(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			activityAreaService.auditActivity(request, staffBean);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title activityAreaPreferentialManager   
	 * @Description TODO(优惠与时间)   
	 * @author pengl
	 * @date 2018年1月31日 下午6:23:40
	 */
	@RequestMapping("/activityAreaNew/activityAreaPreferentialManager.shtml")
	public ModelAndView activityAreaPreferentialManager(HttpServletRequest request, Integer activityAreaId) {
		ModelAndView m = new ModelAndView();
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
		m.addObject("activityArea", activityArea); //活动会场
		if(activityArea.getEnrollEndTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.addObject("enrollEndTime", sdf.format(activityArea.getEnrollEndTime())); //报名截止时间
		}
		m.addObject("preferentialTypeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE")); //特惠类型
		if(activityArea != null) {
			String recQuantity = "";
			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "1".equals(activityArea.getPreferentialType())) { //1：优惠劵
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
					String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
					List<Coupon> couponList = new ArrayList<Coupon>();
					for (int i = 0; i < preferentialIdGroups.length; i++) {
						Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
						if(coupon.getNeedIntegral() == null) {
							coupon.setNeedIntegral(0);
						}
						couponList.add(coupon);
						if(i == 0) {
							Map<String, Object> copuonMap=new HashMap<String, Object>();
							copuonMap.put("recLimitType", coupon.getRecLimitType());
							copuonMap.put("recEach", coupon.getRecEach());
							copuonMap.put("belong", coupon.getBelong());
							m.addObject("copuonMap", copuonMap);
						}
						if(coupon.getRecQuantity() > 0) {
							if("".equals(recQuantity)) {
								recQuantity = coupon.getRecQuantity()==null?"":coupon.getRecQuantity().toString();
							}else {
								recQuantity += "," + coupon.getRecQuantity()==null?"":coupon.getRecQuantity().toString();
							}
						}
					}
					if(couponList.size() > 0) {
						m.addObject("couponList", JSONArray.fromObject(couponList).toString()); //优惠券
					}
				}
				if(!"".equals(recQuantity)) {
					m.setViewName("/activityAreaNew/showActivityAreaPreferential"); //查看优惠与时间
				}
				m.addObject("recQuantity", recQuantity); //判断如果有优惠券被领取的，这优惠信息和活动时间是不允许修改
			}
			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "2".equals(activityArea.getPreferentialType())) { //2：满减
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
					FullCut fullCut = fullCutService.selectByPrimaryKey(Integer.parseInt(activityArea.getPreferentialIdGroup()));
					m.addObject("fullCut", fullCut); //满减
					if(!StringUtil.isEmpty(fullCut.getLadderFlag()) && "1".equals(fullCut.getLadderFlag())) { //是阶梯
						if(!StringUtil.isEmpty(fullCut.getRule())) {
							List<Map<String, Object>> fullCutListMap = new ArrayList<Map<String,Object>>();
							String [] rule = fullCut.getRule().split("\\|");
							for (int i = 0; i < rule.length; i++) {
								String [] rules = rule[i].split(",");
								Map<String, Object> fullCutMap = new HashMap<String, Object>();
								fullCutMap.put("fullName", rules[0]);
								fullCutMap.put("reduceName", rules[1]);
								fullCutListMap.add(fullCutMap);
							}
							if(fullCutListMap.size() > 0) {
								m.addObject("fullCutList", JSONArray.fromObject(fullCutListMap).toString());
							}
						}
					}else { //否阶梯
						if(!StringUtils.isEmpty(fullCut.getRule())) { //规则串
							String [] rules = fullCut.getRule().split(",");
							m.addObject("fullName", rules[0]);
							m.addObject("reduceName", rules[1]);
						}
					}
				}
			}

			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "3".equals(activityArea.getPreferentialType())) { //3：满赠
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
					FullGive fullGive = fullGiveService.selectByPrimaryKey(Integer.parseInt(activityArea.getPreferentialIdGroup()));
					m.addObject("fullGive", fullGive); //满赠
				}
			}
			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "4".equals(activityArea.getPreferentialType())) { //4：多买优惠
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
					FullDiscount fullDiscount = fullDiscountService.selectByPrimaryKey(Integer.parseInt(activityArea.getPreferentialIdGroup()));
					m.addObject("fullDiscount", fullDiscount); //多买优惠
					if(!StringUtil.isEmpty(fullDiscount.getType()) && ("1".equals(fullDiscount.getType()) || "2".equals(fullDiscount.getType()) || "4".equals(fullDiscount.getType()))) { //1：满M件减N件         2：M元任选N件    4: 第M件N折
						if(!StringUtil.isEmpty(fullDiscount.getRule())) { //规则串
							Map<String, Object> fullDiscountMap = new HashMap<String, Object>();
							String [] ruleDiscount = fullDiscount.getRule().split(",");
							if("1".equals(fullDiscount.getType())) {
								fullDiscountMap.put("fullOfOne", ruleDiscount[0]);
								fullDiscountMap.put("fullGiftsOneName", ruleDiscount[1]);
							}else if("2".equals(fullDiscount.getType())){
								fullDiscountMap.put("fullOfTwo", ruleDiscount[0]);
								fullDiscountMap.put("fullGiftsTwoName", ruleDiscount[1]);
							}else if("4".equals(fullDiscount.getType())){
								fullDiscountMap.put("fullOfFour", ruleDiscount[0]);
								fullDiscountMap.put("fullGiftsFourName", ruleDiscount[1]);
							}
							m.addObject("fullDiscountMap", fullDiscountMap);
						}
					}
					if(!StringUtil.isEmpty(fullDiscount.getType()) && "3".equals(fullDiscount.getType())) { //3：M件N折
						if(!StringUtil.isEmpty(fullDiscount.getRule())) { //规则串
							List<Map<String, Object>> fullDiscountListMap = new ArrayList<Map<String, Object>>();
							String [] ruleFullDiscount = fullDiscount.getRule().split("\\|");
							for (int i = 0; i < ruleFullDiscount.length; i++) {
								String [] fullRule = ruleFullDiscount[i].split(",");
								Map<String, Object> fullGiscountThree = new HashMap<String, Object>();
								fullGiscountThree.put("fullGiscountThree", fullRule[0]);
								fullGiscountThree.put("fullGiscountThreeName", fullRule[1]);
								fullDiscountListMap.add(fullGiscountThree);
							}
							if(fullDiscountListMap.size() > 0) {
								m.addObject("fullDiscountList", JSONArray.fromObject(fullDiscountListMap).toString());
							}
						}
					}
				}
			}
			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "5".equals(activityArea.getPreferentialType())) { //5：购物津贴
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
					AllowanceInfo allowanceInfo = allowanceInfoService.selectByPrimaryKey(Integer.parseInt(activityArea.getPreferentialIdGroup()));
					m.addObject("allowanceInfo", allowanceInfo); //满减
					if(!StringUtil.isEmpty(allowanceInfo.getLadderFlag()) && "1".equals(allowanceInfo.getLadderFlag())) { //是阶梯
						if(!StringUtil.isEmpty(allowanceInfo.getRule())) {
							List<Map<String, Object>> allowanceInfoListMap = new ArrayList<Map<String,Object>>();
							String [] rule = allowanceInfo.getRule().split("\\|");
							for (int i = 0; i < rule.length; i++) {
								String [] rules = rule[i].split(",");
								Map<String, Object> allowanceInfoMap = new HashMap<String, Object>();
								allowanceInfoMap.put("fullMoney", rules[0]);
								allowanceInfoMap.put("reduceMoney", rules[1]);
								allowanceInfoListMap.add(allowanceInfoMap);
							}
							if(allowanceInfoListMap.size() > 0) {
								m.addObject("fullCutList", JSONArray.fromObject(allowanceInfoListMap).toString());
							}
						}
					}else { //否阶梯,是累加的
						if(!StringUtils.isEmpty(allowanceInfo.getRule())) { //规则串
							String [] rules = allowanceInfo.getRule().split(",");
							m.addObject("fullMoney", rules[0]);
							m.addObject("reduceMoney", rules[1]);
						}
					}
				}
			}
			if("".equals(recQuantity)) {
				if(StringUtil.isEmpty(request.getParameter("statusPage"))) {
					Date date = new Date();
					if(activityArea.getActivityEndTime() != null && activityArea.getActivityEndTime().getTime() < date.getTime()) { //活动已结束的，只能查看，不允许修改
						m.setViewName("/activityAreaNew/showActivityAreaPreferential"); //查看优惠与时间
					}else { 
						m.setViewName("/activityAreaNew/updateActivityAreaPreferential"); //修改优惠与时间
					}
				}else {
					m.setViewName("/activityAreaNew/showActivityAreaPreferential"); //查看优惠与时间
				}
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title updateActivityAreaPreferential   
	 * @Description TODO(修改活动会场优惠与时间)   
	 * @author pengl
	 * @date 2018年2月1日 上午11:40:55
	 */
	@RequestMapping("/activityAreaNew/updateActivityAreaPreferential.shtml")
	public ModelAndView updateActivityAreaPreferential(HttpServletRequest request) {
		String rtPage = "/success/success";
		StaffBean staffBean = this.getSessionStaffBean(request);
		Map<String, Object> resMap = activityAreaService.updateActivityAreaPreferential(request, staffBean);
		return new ModelAndView(rtPage, resMap);
	}
	
	
	@RequestMapping("/activityAreaNew/choiceCouponList.shtml")
	public ModelAndView choiceCouponList(HttpServletRequest request,HttpServletResponse response, String preferentialIdGroup){
		ModelAndView m = new ModelAndView("/activityAreaNew/choiceCouponList");
		m.addObject("preferentialIdGroup", preferentialIdGroup);
		return m;
	}
	
	@RequestMapping("/activityAreaNew/choiceCouponListData.shtml")
	@ResponseBody
	public Map<String, Object> choiceCouponListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<Coupon> dataList = null;
		Integer totalCount =0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CouponExample couponExample=new CouponExample();
		CouponExample.Criteria criteria=couponExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		try {
			if(!StringUtil.isEmpty(request.getParameter("preferentialIdGroup"))){
				String[] preferentialIdGroups = request.getParameter("preferentialIdGroup").split(",");
				List<Integer> preferentialIdGroupList = new ArrayList<Integer>();
				for(String preferentialIdGroup : preferentialIdGroups) {
					preferentialIdGroupList.add(Integer.parseInt(preferentialIdGroup));
					criteria.andIdIn(preferentialIdGroupList);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("couponName"))){
				criteria.andNameLike("%"+request.getParameter("couponName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("couponCreateDate"))){
				criteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("couponCreateDate")+" 00:00:00"));
				criteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("couponCreateDate")+" 23:59:59"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		couponExample.setLimitSize(page.getLimitSize());
		couponExample.setLimitStart(page.getLimitStart());
		dataList=couponService.selectByExample(couponExample);
		totalCount=couponService.countByExample(couponExample);
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateTempletType   
	 * @Description TODO(装修会场)   
	 * @author pengl
	 * @date 2018年2月6日 下午2:49:03
	 */
	@RequestMapping("/activityAreaNew/updateTempletTypeManager.shtml")
	public ModelAndView updateTempletTypeManager(HttpServletRequest request, Integer activityAreaId, String type, String templetType,String isPreSell) {
		ModelAndView m = new ModelAndView("/activityAreaNew/updateTempletType");
		m.addObject("activityAreaId", activityAreaId);
		m.addObject("type", type); //会场类型 1.品牌活动 	2.单品活动
		m.addObject("templetType", templetType); //模板类型(1默认模板 2代码模板 3自定义模板)
		m.addObject("isPreSell", isPreSell); //是否预付会场(0不是 1是)
		return m;
	}
	
	/**
	 * 
	 * @Title updateTempletType   
	 * @Description TODO(修改模板类型)   
	 * @author pengl
	 * @date 2018年2月6日 下午3:31:08
	 */
	@ResponseBody
	@RequestMapping("/activityAreaNew/updateTempletType.shtml")
	public ModelAndView updateTempletType(HttpServletRequest request, Integer activityAreaId, String type, String templetType) {
		String rtPage = "/success/success";
		StaffBean staffBean = this.getSessionStaffBean(request);
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = null;
		try {
			activityAreaService.updateTempletType(request, staffBean, activityAreaId, type, templetType);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title activityAreaTemplet   
	 * @Description TODO(装修模版管理)   
	 * @author pengl
	 * @date 2018年2月6日 下午5:51:03
	 */
	@RequestMapping("/activityAreaNew/activityAreaTemplet.shtml")
	public ModelAndView activityAreaTemplet(HttpServletRequest request, Integer activityAreaId, String templetType,String isPreSell) {
		ModelAndView m = new ModelAndView();
		m.addObject("isPreSell", isPreSell);
		StaffBean staffBean = this.getSessionStaffBean(request);
		Date date = new Date();
		ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
		String mUrl = resource.getString("mUrl");
		m.addObject("mUrl", mUrl);
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
		sysParamCfgCriteria.andParamCodeEqualTo("ACTIVITY_TEMPLET_PREFIX");
		sysParamCfgExample.setLimitSize(1);
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		SysParamCfg sysParamCfg = sysParamCfgs.get(0);
		if("1".equals(templetType)) { //默认模板
			String defaultName = ""; 
			if(activityArea.getType() != null && "1".equals(activityArea.getType())) { //会场类型(1品牌活动 2单品活动)
				defaultName = "brand_def.html";
				ActivityExample example = new ActivityExample();
				example.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
				ActivityExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andStatusEqualTo("6");
				criteria.andActivityAreaIdEqualTo(activityArea.getId());
				List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(example);
				m.addObject("activityCustoms", activityCustoms);
				m.setViewName("/activityAreaNew/brandDesignList");
			}else if(activityArea.getType() != null && "2".equals(activityArea.getType())) {
				defaultName = "single_def.html";
				List<ActivityProductCustom> activityProductCustoms = activityProductService.getProductsByActivityAreaId(activityAreaId);
				for (ActivityProductCustom activityProductCustom:activityProductCustoms) {
					activityProductCustom.setProductPic(FileUtil.getMiddleImageName(activityProductCustom.getProductPic()));
				}
				m.addObject("activityProductCustoms", activityProductCustoms);
				m.setViewName("/activityAreaNew/singleProductDesignList");
			}
			activityArea.setTempletSuffix(sysParamCfg.getParamValue()+defaultName);
		}else if("2".equals(templetType)) { //代码模板
			activityArea.setTempletSuffix(sysParamCfg.getParamValue()+"single_day.html");
			ActivityAreaTempletPparamExample activityAreaTempletPparamExample = new ActivityAreaTempletPparamExample();
			activityAreaTempletPparamExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityArea.getId());
			List<ActivityAreaTempletPparam> activityAreaTempletPparamList = activityAreaTempletPparamService.selectByExample(activityAreaTempletPparamExample);
			if(activityAreaTempletPparamList == null || activityAreaTempletPparamList.size() == 0) {
				ActivityAreaTempletPparam activityAreaTempletPparam = new ActivityAreaTempletPparam();
				activityAreaTempletPparam.setName(activityArea.getName());
				activityAreaTempletPparam.setActivityAreaId(activityArea.getId());
				activityAreaTempletPparam.setSuffix("single_day.html");
				activityAreaTempletPparam.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
				activityAreaTempletPparam.setCreateDate(date);
				activityAreaTempletPparam.setUpdateDate(date);
				activityAreaTempletPparam.setDelFlag("0");
				activityAreaTempletPparamService.insertSelective(activityAreaTempletPparam); //新增会场模板参数
				m.addObject("activityAreaTempletPparam", activityAreaTempletPparam);
			}else {
				m.addObject("activityAreaTempletPparam", activityAreaTempletPparamList.get(0));
			}
			m.setViewName("/activityAreaTempletPparam/updateActivityAreaTempletPparam"); //修改代码模板
		}else if(templetType.equals("3")){//3.自定义模板
			ActivityAreaDecorateExample example = new ActivityAreaDecorateExample();
			ActivityAreaDecorateExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andActivityAreaIdEqualTo(activityArea.getId());
			List<ActivityAreaDecorate> activityAreaDecorates = activityAreaDecorateService.selectByExample(example);
			if(activityAreaDecorates!=null && activityAreaDecorates.size()>0){
				m.addObject("decorateInfoId", activityAreaDecorates.get(0).getDecorateInfoId());
				DecorateAreaExample dae = new DecorateAreaExample();
				dae.setOrderByClause("IFNULL(seq_no,99999) ASC,id asc");
				DecorateAreaExample.Criteria daec = dae.createCriteria();
				daec.andDelFlagEqualTo("0");
				daec.andDecorateInfoIdEqualTo(activityAreaDecorates.get(0).getDecorateInfoId());
				List<DecorateAreaCustom> decorateAreaCustoms = decorateAreaService.selectDecorateAreaCustomByExample(dae);
				if(decorateAreaCustoms!=null && decorateAreaCustoms.size()>0){
					for(DecorateAreaCustom decorateAreaCustom:decorateAreaCustoms){
						DecorateModuleExample dme = new DecorateModuleExample();
						dme.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
						DecorateModuleExample.Criteria dmec = dme.createCriteria();
						dmec.andDelFlagEqualTo("0");
						dmec.andDecorateAreaIdEqualTo(decorateAreaCustom.getId());
						List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
						for(DecorateModuleCustom decorateModuleCustom:decorateModuleCustoms){
							if(decorateModuleCustom.getModuleType().equals("2")){//商品
								ModuleItemExample mie = new ModuleItemExample();
								mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
								ModuleItemExample.Criteria miec = mie.createCriteria();
								miec.andDelFlagEqualTo("0");
								miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
								miec.andItemTypeEqualTo("1");//1.商品 2.特卖
								int count = moduleItemService.countByExample(mie);
								decorateModuleCustom.setCount(count);
							}else if(decorateModuleCustom.getModuleType().equals("3")){//特卖
								ModuleItemExample mie = new ModuleItemExample();
								mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
								ModuleItemExample.Criteria miec = mie.createCriteria();
								miec.andDelFlagEqualTo("0");
								miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
								miec.andItemTypeEqualTo("2");//1.商品 2.特卖
								int count = moduleItemService.countByExample(mie);
								decorateModuleCustom.setCount(count);
							}else if(decorateModuleCustom.getModuleType().equals("6")){//秒杀
								String seckillType = "1";
								String dataSource ="1";
								SeckillModuleColorSettingExample seckillModuleColorSettingExample = new SeckillModuleColorSettingExample();
								seckillModuleColorSettingExample.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
								List<SeckillModuleColorSetting> seckillModuleColorSettings = seckillModuleColorSettingService.selectByExample(seckillModuleColorSettingExample);
								if(seckillModuleColorSettings!=null && seckillModuleColorSettings.size()>0){
									dataSource = seckillModuleColorSettings.get(0).getDataSource();
									if(dataSource!=null && dataSource!=""){
										seckillType = dataSource;
									}
								}

								SeckillTimeExample ste = new SeckillTimeExample();
								SeckillTimeExample.Criteria stec = ste.createCriteria();
								stec.andDelFlagEqualTo("0");
								stec.andSeckillTypeEqualTo(seckillType);//1.限时秒杀  2.会场秒杀 STORY #1517 再改#1780
								stec.andStatusEqualTo("1");
								ste.setOrderByClause("start_hours,start_min asc");
								List<SeckillTime> seckillTimes = seckillTimeService.selectByExample(ste);
								decorateModuleCustom.setSeckillTimes(seckillTimes);
								m.addObject("dataSource", dataSource);
							}else if(decorateModuleCustom.getModuleType().equals("7")){//必购
								BgModuleCategoryExample bmce = new BgModuleCategoryExample();
								bmce.setOrderByClause("seq_no asc");
								BgModuleCategoryExample.Criteria bmcec = bmce.createCriteria();
								bmcec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
								bmcec.andDelFlagEqualTo("0");
								List<BgModuleCategoryCustom> bgModuleCategoryCustoms = bgModuleCategoryService.selectBgModuleCategoryCustomByExample(bmce);
								decorateModuleCustom.setBgModuleCategoryCustoms(bgModuleCategoryCustoms);
							}else if(decorateModuleCustom.getModuleType().equals("10")){//固定顶部栏
								TopFieldModuleFieldExample tfmfe = new TopFieldModuleFieldExample();
								tfmfe.setOrderByClause("seq_no asc");
								//tfmfe.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
								tfmfe.createCriteria().andDecorateModuleIdEqualTo(decorateModuleCustom.getId()).andSeqNoNotEqualTo(-1);//排序值-1相当于删除
								List<TopFieldModuleField> topFieldModuleFields = topFieldModuleFieldMapper.selectByExample(tfmfe);
								decorateModuleCustom.setTopFieldModuleFields(topFieldModuleFields);
							}else if(decorateModuleCustom.getModuleType().equals("15")){//领券秒杀模块
								//领券秒杀时间节点
								CouponModuleTimeExample couponModuleTimeExample = new CouponModuleTimeExample();
								couponModuleTimeExample.setOrderByClause("ABS(start_hours) ,ABS(start_min) ");
								couponModuleTimeExample.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
								List<CouponModuleTime> couponModuleTimeList = couponModuleTimeService.selectByExample(couponModuleTimeExample);
								decorateModuleCustom.setCouponModuleTimeList(couponModuleTimeList);
							}
						}
						decorateAreaCustom.setDecorateModuleCustoms(decorateModuleCustoms);
					}
				}
				m.addObject("decorateAreaCustoms", decorateAreaCustoms);
			}else{
				DecorateInfo decorateInfo = new DecorateInfo();
				decorateInfo.setDelFlag("0");
				decorateInfo.setCreateDate(new Date());
				decorateInfo.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				decorateInfo.setDecorateName(activityArea.getName()+"的自定义模板");
				
				ActivityAreaDecorate activityAreaDecorate = new ActivityAreaDecorate();
				activityAreaDecorate.setDelFlag("0");
				activityAreaDecorate.setCreateDate(new Date());
				activityAreaDecorate.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activityAreaDecorate.setActivityAreaId(activityAreaId);
				
				activityAreaDecorateService.save(decorateInfo,activityAreaDecorate);
				m.addObject("decorateInfoId", decorateInfo.getId());
			}
			m.setViewName("/activityAreaTempletPparam/customTemplate"); 
			m.addObject("templetSuffix", "/xgbuy/views/app.html?redirect_url=activity/templet/brand_decorate.html");
			m.addObject("showType", "34");
		}
		m.addObject("activityArea", activityArea);
		return m;
	}

	/**
	 * 秒杀模块设置页面
	 * @Title
	 * @Description TODO
	 */
/*	@RequestMapping("/activityAreaNew/setSpike.shtml")
	public ModelAndView setSpike(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/activityAreaTempletPparam/setSpike");
		String decorateAreaId = request.getParameter("decorateAreaId");
		if(!StringUtils.isEmpty(decorateAreaId)){
			DecorateArea decorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(decorateAreaId));
			m.addObject("decorateArea", decorateArea);
		}
		m.addObject("decorateInfoId", request.getParameter("decorateInfoId"));
		return m;
	}
	*/
	/**
	 * 分区页面
	 * @Title    
	 * @Description TODO
	 */
	@RequestMapping("/activityAreaNew/editDecorateArea.shtml")
	public ModelAndView editDecorateArea(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/activityAreaTempletPparam/editDecorateArea");
		String decorateAreaId = request.getParameter("decorateAreaId");
		if(!StringUtils.isEmpty(decorateAreaId)){
			DecorateArea decorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(decorateAreaId));
			m.addObject("decorateArea", decorateArea);
		}
		m.addObject("decorateInfoId", request.getParameter("decorateInfoId"));
		return m;
	}
	
	/**
	 * 保存分区名称   
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/saveDecorateArea.shtml")
	@ResponseBody
	public Map<String, Object> saveDecorateArea(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateAreaId = request.getParameter("decorateAreaId");
		DecorateArea decorateArea = new DecorateArea();
		if(!StringUtils.isEmpty(decorateAreaId)){
			decorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(decorateAreaId));
		}else{
			decorateArea.setDelFlag("0");
			decorateArea.setCreateDate(new Date());
			decorateArea.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			decorateArea.setDecorateInfoId(Integer.parseInt(request.getParameter("decorateInfoId")));
			DecorateAreaExample dae = new DecorateAreaExample();
			DecorateAreaExample.Criteria daec = dae.createCriteria();
			daec.andDelFlagEqualTo("0");
			daec.andDecorateInfoIdEqualTo(Integer.parseInt(request.getParameter("decorateInfoId")));
			List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
			decorateArea.setSeqNo(decorateAreas.size()+1);
		}
		decorateArea.setAreaName(request.getParameter("areaName"));
		decorateAreaService.save(decorateArea);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 删除分区   
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/deleteDecorateArea.shtml")
	@ResponseBody
	public Map<String, Object> deleteDecorateArea(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateAreaId = request.getParameter("decorateAreaId");
		if(!StringUtils.isEmpty(decorateAreaId)){
			decorateAreaService.deleteDecorateArea(Integer.parseInt(decorateAreaId));
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "删除成功");
		return resMap;
	}
	
	/**
	 * 上移下移分区
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activityAreaNew/moveDecorateArea.shtml")
	@ResponseBody
	public Map<String, Object> moveDecorateArea(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String prevId = request.getParameter("prevId");
			String nextId = request.getParameter("nextId");
			if(!StringUtils.isEmpty(prevId) && !StringUtils.isEmpty(nextId)){
				DecorateArea prevDecorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(prevId));
				DecorateArea nextDecorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(nextId));
				if(!StringUtils.isEmpty(prevDecorateArea.getSeqNo()) && !StringUtils.isEmpty(nextDecorateArea.getSeqNo())){
					int prevSeqNo = prevDecorateArea.getSeqNo();
					int nextSeqNo = nextDecorateArea.getSeqNo();
					prevDecorateArea.setSeqNo(nextSeqNo);
					nextDecorateArea.setSeqNo(prevSeqNo);
				}else{
					prevDecorateArea.setSeqNo(Integer.parseInt(request.getParameter("prevSeqNo")));
					nextDecorateArea.setSeqNo(Integer.parseInt(request.getParameter("nextSeqNo")));
				}
				decorateAreaService.update(prevDecorateArea,nextDecorateArea);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 插入图片模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertPicModule.shtml")
	@ResponseBody
	public Map<String, Object> insertPicModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType("1");
		String picPath = request.getParameter("picPath");
		if(!StringUtil.isEmpty(picPath)){
			dm.setPic(picPath);
		}
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}

	
	
	
	/**
	 * 一次插入多个图片模块 带图片
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertPicsModule.shtml")
	@ResponseBody
	public Map<String, Object> insertPicsModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		String topPic = request.getParameter("topPic");
		if(!StringUtil.isEmpty(topPic)){
			String[] splitPic = topPic.split(",");
			for (int i = 0; i < splitPic.length; i++) {
				DecorateArea da = new DecorateArea();
				da.setDelFlag("0");
				da.setCreateDate(new Date());
				da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
				DecorateAreaExample dae = new DecorateAreaExample();
				DecorateAreaExample.Criteria daec = dae.createCriteria();
				daec.andDelFlagEqualTo("0");
				daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
				List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
				da.setSeqNo(decorateAreas.size()+1);
				DecorateModule dm = new DecorateModule();
				dm.setDelFlag("0");
				dm.setCreateDate(new Date());
				dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				dm.setModuleType("1");
				
				String picPath = splitPic[i];
				if(!StringUtil.isEmpty(picPath)){
					dm.setPic(picPath);
				}
				decorateAreaService.save(da,dm);
			}
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	
	
	/**
	 * 插入滑动图片模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertSlidePicModule.shtml")
	@ResponseBody
	public Map<String, Object> insertSlidePicModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType("9");
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 插入固定底部栏（导航）模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertBottomBarModule.shtml")
	@ResponseBody
	public Map<String, Object> insertBottomBarModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		da.setSeqNo(999999);
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType("8");
		dm.setSeqNo(999999);
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 插入固定顶部栏模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertTopFieldModule.shtml")
	@ResponseBody
	public Map<String, Object> insertTopFieldModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		da.setSeqNo(999999);
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType("10");
		dm.setFieldFontColor("#333333");
		dm.setFieldSelectFontColor("#FF0000");
		dm.setFieldBgColor("#CCCCCC");
		dm.setOpenFontColor("#333333");
		dm.setOpenFieldBgColor("#DDDDDD");
		dm.setOpenBtnBgColor("#EEEEEE");
		dm.setOpenBtnArrowColor("1");//1.黑色
		dm.setSeqNo(999999);
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		//插入一条空数据
		TopFieldModuleField tfmf = new TopFieldModuleField();
		tfmf.setDecorateModuleId(dm.getId());
		tfmf.setFieldName("");
		tfmf.setLinkDecorateAreaId(-1);
		tfmf.setCreateDate(new Date());
		tfmf.setDelFlag("1");
		tfmf.setSeqNo(0);
		topFieldModuleFieldService.insertSelective(tfmf);
		return resMap;
	}
	
	/**
	 * 插入商品模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertProductModule.shtml")
	@ResponseBody
	public Map<String, Object> insertProductModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType("2");
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 插入特卖模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertActivityModule.shtml")
	@ResponseBody
	public Map<String, Object> insertActivityModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType(request.getParameter("moduleType"));
		dm.setShowNum(Integer.parseInt(request.getParameter("showNum")));
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 预览页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activityAreaNew/previewDecorateInfo.shtml")
	public ModelAndView previewDecorateInfo(HttpServletRequest request) {
		String rtPage = "/activityAreaTempletPparam/previewDecorateInfo";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String activityAreaId = request.getParameter("activityAreaId");
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
		resMap.put("topPic", activityArea.getTopPic());
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateAreaExample dae = new DecorateAreaExample();
		dae.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateAreaCustom> decorateAreaCustoms = decorateAreaService.selectDecorateAreaCustomByExample(dae);
		for(DecorateAreaCustom decorateAreaCustom:decorateAreaCustoms){
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreaCustom.getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			if(decorateModuleCustoms!=null && decorateModuleCustoms.size()>0){
				for(DecorateModuleCustom decorateModuleCustom:decorateModuleCustoms){
					List<String> pics = new ArrayList<String>();
					HashMap<String,Object> paramMap = new HashMap<String,Object>();
					if(decorateModuleCustom.getModuleType().equals("1") || decorateModuleCustom.getModuleType().equals("8") || decorateModuleCustom.getModuleType().equals("9")){//图片
						pics.add(decorateModuleCustom.getPic());
						decorateModuleCustom.setPics(pics);
					}else if(decorateModuleCustom.getModuleType().equals("2")){//商品
						ModuleItemCustomExample mice = new ModuleItemCustomExample();
						mice.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
						ModuleItemCustomExample.ModuleItemCustomCriteria micc = mice.createCriteria();
						micc.andDelFlagEqualTo("0");
						micc.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
						micc.andItemTypeEqualTo("1");
						micc.andProductActivityStatusEqualTo("4");//活动中
						List<ModuleItemCustom> moduleItemCustoms = moduleItemService.selectModuleItemCustomByExample(mice);
						decorateModuleCustom.setModuleItemCustoms(moduleItemCustoms);
					}else if(decorateModuleCustom.getModuleType().equals("3")){//特卖
						List<Integer> activityIds = moduleItemService.getIdsByModuleId(decorateModuleCustom.getId());
						if(activityIds!=null && activityIds.size()>0){
							ActivityExample ae = new ActivityExample();
							ActivityExample.Criteria aec = ae.createCriteria();
							aec.andDelFlagEqualTo("0");
							aec.andIdIn(activityIds);
							aec.andStatusEqualTo("6");
							List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(ae);
							decorateModuleCustom.setActivityCustoms(activityCustoms);
						}
					}
				}
			}
			decorateAreaCustom.setDecorateModuleCustoms(decorateModuleCustoms);
		}
		resMap.put("decorateAreaCustoms", decorateAreaCustoms);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 编辑海报图页面
	 * @Title    
	 * @Description TODO
	 */
	@RequestMapping("/activityAreaNew/editTopPic.shtml")
	public ModelAndView editTopPic(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/activityAreaTempletPparam/editTopPic");
		String activityAreaId = request.getParameter("activityAreaId");
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
		m.addObject("topPic", activityArea.getTopPic());
		m.addObject("activityAreaId", activityAreaId);
		return m;
	}
	
	/**
	 * 
	 * @Title saveTopPic   
	 * @Description TODO(保存装修页头部图)   
	 * @author pengl
	 * @date 2018年2月7日 上午11:26:33
	 */
	@RequestMapping(value="/activityAreaNew/saveTopPic.shtml")
	@ResponseBody
	public Map<String, Object> saveTopPic(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String activityAreaId = request.getParameter("activityAreaId");
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
		String topPic = request.getParameter("topPic");
		if(StringUtils.isEmpty(topPic)){
			activityArea.setTopPic("");
		}else{
			activityArea.setTopPic(topPic);
		}
		activityAreaService.updateByPrimaryKeySelective(activityArea);
		resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_SUCCESS.getStateCode());
		return resMap;
	}
	
	/**
	 * 
	 * @Title saveSort   
	 * @Description TODO(保存排序值-品牌)   
	 * @author pengl
	 * @date 2018年2月7日 上午11:24:47
	 */
	@RequestMapping(value="/activityAreaNew/saveSort.shtml")
	@ResponseBody
	public Map<String, Object> saveSort(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String sortIds = request.getParameter("sortIds");
		List<Activity> activitys = new ArrayList<Activity>();
		if(!StringUtils.isEmpty(sortIds)) {
			String[] sortIdsArray = sortIds.split("\\|");
			for(int i=0;i<sortIdsArray.length;i++) {
				String sortId = sortIdsArray[i];
				String[] sortIdArray = sortId.split(",");
				String activityId = sortIdArray[0];
				String sort = sortIdArray[1];
				Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(activityId));
				if(!sort.equals("-1")) {
					activity.setSeqNo(Integer.parseInt(sort));
				}else {
					activity.setSeqNo(null);
				}
				activity.setUpdateDate(new Date());
				activity.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activitys.add(activity);
			}
		}
		if(activitys != null && activitys.size() > 0) {
			activityService.updateActivityList(activitys);
			resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_SUCCESS.getStateCode());
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title saveActivityProductSort   
	 * @Description TODO(保存排序值-单品)   
	 * @author pengl
	 * @date 2018年2月7日 上午11:24:24
	 */
	@RequestMapping(value="/activityAreaNew/saveActivityProductSort.shtml")
	@ResponseBody
	public Map<String, Object> saveActivityProductSort(HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String sortIds = request.getParameter("sortIds");
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("data", sortIds);
		map.put("staffID", staffID);
		activityProductService.saveSort(map);
		resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_SUCCESS.getStateCode());
		return resMap;
	}
	
	/**
	 * 
	 * @Title promotionActivityAreaPreferentialManager   
	 * @Description TODO(推广会场优惠与时间)   
	 * @author pengl
	 * @date 2018年2月8日 上午11:45:04
	 */
	@RequestMapping("/activityAreaNew/promotionActivityAreaPreferentialManager.shtml")
	public ModelAndView promotionActivityAreaPreferentialManager(HttpServletRequest request, Integer activityAreaId) {
		ModelAndView m = new ModelAndView();
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
		m.addObject("activityArea", activityArea); //活动会场
		if(activityArea.getEnrollEndTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.addObject("enrollEndTime", sdf.format(activityArea.getEnrollEndTime())); //报名截止时间
		}
		if(activityArea != null) {
			String couponExchangeCodeStatus = "";
			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "1".equals(activityArea.getPreferentialType())) { //1：优惠劵
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
					String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
					List<Coupon> couponList = new ArrayList<Coupon>();
					for (int i = 0; i < preferentialIdGroups.length; i++) {
						Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
						couponList.add(coupon);
						CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
						couponExchangeCodeExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(coupon.getId());
						List<CouponExchangeCode> couponExchangeCodeList = couponExchangeCodeService.selectByExample(couponExchangeCodeExample);
						if(couponExchangeCodeList != null && couponExchangeCodeList.size() > 0) {
							if("".equals(couponExchangeCodeStatus)) {
								couponExchangeCodeStatus = couponExchangeCodeList.size()+"";
							}else {
								couponExchangeCodeStatus += "," + couponExchangeCodeList.size();
							}
						}
					}
					if(couponList.size() > 0) {
						m.addObject("couponList", JSONArray.fromObject(couponList).toString()); //优惠券
					}
				}
				if(!"".equals(couponExchangeCodeStatus)) {
					m.setViewName("/activityAreaNew/promotionActivityArea/showPromotionPreferential"); //查看优惠与时间
				}
			}
			if("".equals(couponExchangeCodeStatus)) {
				if(StringUtil.isEmpty(request.getParameter("statusPage"))) {
					Date date = new Date();
					if(activityArea.getActivityEndTime() != null && activityArea.getActivityEndTime().getTime() < date.getTime()) { //活动已结束的，只能查看，不允许修改
						m.setViewName("/activityAreaNew/promotionActivityArea/showPromotionPreferential"); //查看优惠与时间
					}else { 
						m.setViewName("/activityAreaNew/promotionActivityArea/updatePromotionPreferential"); //修改优惠与时间
					}
				}else {
					m.setViewName("/activityAreaNew/promotionActivityArea/showPromotionPreferential"); //查看优惠与时间
				}
			}
		}
		return m;
	}
	
	/**
	 * 插入秒杀模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertSecondKillModule.shtml")
	@ResponseBody
	public Map<String, Object> insertSecondKillModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType(request.getParameter("moduleType"));
		decorateAreaService.save(da,dm);
		//创建时 设置秒杀模块默认背景色
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer decorateModuleId = dm.getId();
		String moduleType = dm.getModuleType();
		decorateAreaService.insertCouponColor(decorateModuleId,moduleType,staffId);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 插入必购TOP榜模块  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/insertNeedBuyModule.shtml")
	@ResponseBody
	public Map<String, Object> insertNeedBuyModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType(request.getParameter("moduleType"));
		decorateAreaService.save(da,dm);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 保存必购模块分类
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/saveBgModuleCategory.shtml")
	@ResponseBody
	public Map<String, Object> saveBgModuleCategory(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String decorateModuleId = request.getParameter("decorateModuleId");
		String categoryName = request.getParameter("categoryName");
		String productCodes = request.getParameter("productCodes");
		String[] productCodeArray  = productCodes.split(",");
		String productIds = "";
		String errorCode = "";
		BgModuleCategoryExample bgModuleCategoryExample = new BgModuleCategoryExample();
		BgModuleCategoryExample.Criteria criteria = bgModuleCategoryExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
		criteria.andCategoryNameEqualTo(categoryName);
		List<BgModuleCategory> bgModuleCategorys = bgModuleCategoryService.selectByExample(bgModuleCategoryExample);
		if(bgModuleCategorys!=null && bgModuleCategorys.size()>0){
			if(StringUtils.isEmpty(id)){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "类目名称重复，无法添加");
				return resMap;
			}else{
				if(!bgModuleCategorys.get(0).getId().equals(Integer.parseInt(id))){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "类目名称重复，无法添加");
					return resMap;
				}
			}
		}
		
		for(int i=0;i<productCodeArray.length;i++){
			if(!StringUtils.isEmpty(productCodeArray[i])){
				ProductExample pe = new ProductExample();
				ProductExample.Criteria pec = pe.createCriteria();
				pec.andDelFlagEqualTo("0");
				pec.andCodeEqualTo(productCodeArray[i]);
				List<Product> products = productService.selectByExample(pe);
				if(products == null || products.size() == 0){
					errorCode+=productCodeArray[i]+",";
				}else{
					if(i!=productCodeArray.length-1){
						productIds+=products.get(0).getId()+",";
					}else{
						productIds+=products.get(0).getId();
					}
				}
			}
		}
		if(!StringUtils.isEmpty(errorCode)){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "商品ID有误，错误的商品id为："+errorCode);
			return resMap;
		}
		BgModuleCategory bmc = new BgModuleCategory();
		if(StringUtils.isEmpty(id)){
			bmc.setDecorateModuleId(Integer.parseInt(decorateModuleId));
			bmc.setCreateDate(new Date());
			bmc.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			bmc.setDelFlag("0");
			Integer seqNo = bgModuleCategoryService.getMaxSeqNo(Integer.parseInt(decorateModuleId));
			if(seqNo!=null){
				bmc.setSeqNo(seqNo+1);
			}else{
				bmc.setSeqNo(1);
			}
		}else{
			bmc = bgModuleCategoryService.selectByPrimaryKey(Integer.parseInt(id));
		}
		bmc.setCategoryName(categoryName);
		bmc.setProductIds(productIds);
		bmc.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		bmc.setUpdateDate(new Date());
		bgModuleCategoryService.save(bmc);
		resMap.put("id", bmc.getId());
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 保存固定顶部栏模块栏目
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/saveTopFieldModuleField.shtml")
	@ResponseBody
	public Map<String, Object> saveTopFieldModuleField(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String decorateModuleId = request.getParameter("decorateModuleId");
		String fieldName = request.getParameter("fieldName");
		String index = request.getParameter("index");
		String linkDecorateAreaId = request.getParameter("linkDecorateAreaId");
		//String index = request.getParameter("index");
		TopFieldModuleFieldExample topFieldModuleFieldExample = new TopFieldModuleFieldExample();
		TopFieldModuleFieldExample.Criteria criteria = topFieldModuleFieldExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
		criteria.andFieldNameEqualTo(fieldName);
		List<TopFieldModuleField> topFieldModuleFields = topFieldModuleFieldMapper.selectByExample(topFieldModuleFieldExample);
		if(topFieldModuleFields!=null && topFieldModuleFields.size()>0){
			if(StringUtils.isEmpty(id)){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "栏目名称重复，无法添加");
				return resMap;
			}else{
				if(!topFieldModuleFields.get(0).getId().equals(Integer.parseInt(id))){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "栏目名称重复，无法添加");
					return resMap;
				}
			}
		}
		DecorateArea decorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(linkDecorateAreaId));
		boolean inDecorateInfo = true;
		if(decorateArea == null){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "分区ID有误，错误的分区id为："+linkDecorateAreaId);
			return resMap;
		}else{
			String decorateInfoId = request.getParameter("decorateInfoId");
			DecorateAreaExample dae = new DecorateAreaExample();
			dae.createCriteria().andDelFlagEqualTo("0").andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
			List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
			for(DecorateArea da:decorateAreas){
				if(da.getId().equals(decorateArea.getId())){
					inDecorateInfo = true;
					break;
				}else{
					inDecorateInfo = false;
					continue;
				}
			}
		}
		if(!inDecorateInfo){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "分区ID有误，不在当前会场装修里，错误的分区id为："+linkDecorateAreaId);
			return resMap;
		}
		TopFieldModuleFieldExample example = new TopFieldModuleFieldExample();
		TopFieldModuleFieldExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
		c.andLinkDecorateAreaIdEqualTo(Integer.parseInt(linkDecorateAreaId));
		List<TopFieldModuleField> topFieldModuleFieldList = topFieldModuleFieldMapper.selectByExample(example);
		TopFieldModuleField tfmf = new TopFieldModuleField();
		if(StringUtils.isEmpty(id)){
			if(topFieldModuleFieldList!=null && topFieldModuleFieldList.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "分区ID有误，分区ID不可出现重复");
				return resMap;
			}
			tfmf.setDecorateModuleId(Integer.parseInt(decorateModuleId));
			tfmf.setCreateDate(new Date());
			tfmf.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			tfmf.setDelFlag("0");
		}else{
			if(topFieldModuleFieldList!=null && topFieldModuleFieldList.size()>0){
				if(!topFieldModuleFieldList.get(0).getId().equals(Integer.parseInt(id))){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "分区ID有误，分区ID不可出现重复");
					return resMap;
				}
			}
			tfmf = topFieldModuleFieldMapper.selectByPrimaryKey(Integer.parseInt(id));
		}
		if(!StringUtil.isEmpty(index)){
			tfmf.setSeqNo(Integer.parseInt(index));
		}
		tfmf.setFieldName(fieldName);
		tfmf.setLinkDecorateAreaId(Integer.parseInt(linkDecorateAreaId));
		tfmf.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		tfmf.setUpdateDate(new Date());
		tfmf.setDelFlag("0");
		if(tfmf.getId()!=null){
			topFieldModuleFieldMapper.updateByPrimaryKeySelective(tfmf);
		}else{
			topFieldModuleFieldMapper.insertSelective(tfmf);
		}
		resMap.put("id", tfmf.getId());
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 删除必购模块分类  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/deleteBgModuleCategory.shtml")
	@ResponseBody
	public Map<String, Object> deleteBgModuleCategory(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		BgModuleCategory bgModuleCategory = bgModuleCategoryService.selectByPrimaryKey(Integer.parseInt(id));
		bgModuleCategory.setDelFlag("1");
		bgModuleCategory.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		bgModuleCategory.setUpdateDate(new Date());
		bgModuleCategoryService.deleteAndSort(bgModuleCategory);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 删除固定顶部栏模块栏目
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/deleteTopFieldModuleField.shtml")
	@ResponseBody
	public Map<String, Object> deleteTopFieldModuleField(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		TopFieldModuleField topFieldModuleField = topFieldModuleFieldMapper.selectByPrimaryKey(Integer.parseInt(id));
		topFieldModuleField.setDelFlag("1");
		topFieldModuleField.setSeqNo(-1);//删除
		topFieldModuleField.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		topFieldModuleField.setUpdateDate(new Date());
		topFieldModuleFieldMapper.updateByPrimaryKeySelective(topFieldModuleField);

		TopFieldModuleFieldExample topFieldModuleFieldExample = new TopFieldModuleFieldExample();
		topFieldModuleFieldExample.createCriteria().andIdGreaterThan(Integer.parseInt(id));
		List<TopFieldModuleField> topFieldModuleFields = topFieldModuleFieldService.selectByExample(topFieldModuleFieldExample);
		for (TopFieldModuleField topFieldModule : topFieldModuleFields){
			topFieldModule.setSeqNo(topFieldModule.getSeqNo()-1);
			topFieldModuleFieldService.updateByPrimaryKeySelective(topFieldModule);
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 必购模块分类  上下移
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/changeBgModuleCategorySeqNo.shtml")
	@ResponseBody
	public Map<String, Object> changeBgModuleCategorySeqNo(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String prevId = request.getParameter("prevId");
		String nextId = request.getParameter("nextId");
		BgModuleCategory prevBgModuleCategory = bgModuleCategoryService.selectByPrimaryKey(Integer.parseInt(prevId));
		BgModuleCategory nextBgModuleCategory = bgModuleCategoryService.selectByPrimaryKey(Integer.parseInt(nextId));
		Integer prevSeqNo = prevBgModuleCategory.getSeqNo();
		Integer nextSeqNo = nextBgModuleCategory.getSeqNo();
		prevBgModuleCategory.setSeqNo(nextSeqNo);
		nextBgModuleCategory.setSeqNo(prevSeqNo);
		prevBgModuleCategory.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		prevBgModuleCategory.setUpdateDate(new Date());
		nextBgModuleCategory.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		nextBgModuleCategory.setUpdateDate(new Date());
		bgModuleCategoryService.updateBgModuleCategory(prevBgModuleCategory,nextBgModuleCategory);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 获取模块数量
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/getModuleCount.shtml")
	@ResponseBody
	public Map<String, Object> getModuleCount(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		String moduleType = request.getParameter("moduleType");
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("decorateInfoId", Integer.parseInt(decorateInfoId));
		map.put("moduleType", moduleType);
		Integer count = decorateModuleService.getModuleCount(map);
		resMap.put("count", count);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}
	
	/**
	 * 向上移动 TopFieldModuleField
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/moveUpTopFieldModuleField.shtml")
	@ResponseBody
	public Map<String, Object> moveUpTopFieldModuleField(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String prevId = request.getParameter("prevId");
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(prevId) && !StringUtils.isEmpty(id)){
				TopFieldModuleField one = topFieldModuleFieldService.selectByPrimaryKey(Integer.parseInt(id));
				TopFieldModuleField two = topFieldModuleFieldService.selectByPrimaryKey(Integer.parseInt(prevId));
				int temp = two.getSeqNo();
				two.setSeqNo(one.getSeqNo());
				one.setSeqNo(temp);
				topFieldModuleFieldService.updateByPrimaryKeySelective(one);
				topFieldModuleFieldService.updateByPrimaryKeySelective(two);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 向下移动 TopFieldModuleField
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/moveDownTopFieldModuleField.shtml")
	@ResponseBody
	public Map<String, Object> moveDownTopFieldModuleField(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String nextId = request.getParameter("nextId");
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(nextId) && !StringUtils.isEmpty(id)){
				TopFieldModuleField one = topFieldModuleFieldService.selectByPrimaryKey(Integer.parseInt(id));
				TopFieldModuleField two = topFieldModuleFieldService.selectByPrimaryKey(Integer.parseInt(nextId));
				int temp = two.getSeqNo();
				two.setSeqNo(one.getSeqNo());
				one.setSeqNo(temp);
				topFieldModuleFieldService.updateByPrimaryKeySelective(one);
				topFieldModuleFieldService.updateByPrimaryKeySelective(two);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	//设置预售规则
	@RequestMapping(value = "/activityAreaNew/setAreaActivityPreSellRule.shtml")
	public ModelAndView setAreaActivityPreSellRule(HttpServletRequest request ) {
		ModelAndView m = new ModelAndView();
		String type = request.getParameter("type");
		String activityPriceLimit = request.getParameter("activityPriceLimit");
		String depositLimit = request.getParameter("depositLimit");
		String minRate = request.getParameter("minRate");
		String maxRate = request.getParameter("maxRate");
		String offsetMultiple = request.getParameter("offsetMultiple");
		if(!StringUtils.isEmpty(activityPriceLimit)){
			m.addObject("type",type);
		}
		if(!StringUtils.isEmpty(activityPriceLimit)){
			m.addObject("activityPriceLimit",activityPriceLimit);
		}
		if(!StringUtils.isEmpty(depositLimit)){
			m.addObject("depositLimit",depositLimit);
		}
		if(!StringUtils.isEmpty(minRate)){
			m.addObject("minRate",minRate);
		}
		if(!StringUtils.isEmpty(maxRate)){
			m.addObject("maxRate",maxRate);
		}
		if(!StringUtils.isEmpty(offsetMultiple)){
			m.addObject("offsetMultiple",offsetMultiple);
		}
		m.setViewName("/activityAreaNew/setAreaActivityPreSellRule");
		return m;
	}
	
	
	
	/**
	 * 固定顶部模块新增 TopFieldModuleField
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/countTopFieldModuleField.shtml")
	@ResponseBody
	public Map<String, Object> countTopFieldModuleField(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
		
			String decorateModuleId = request.getParameter("decorateModuleId");
			TopFieldModuleFieldExample example = new TopFieldModuleFieldExample();
			example.createCriteria().andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
			int seqCount = topFieldModuleFieldService.countByExample(example );
			resMap.put("seqCount", seqCount);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	
	/**
	 * 固定顶部模块新增 TopFieldModuleField
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/activityAreaNew/addTopFieldModuleField.shtml")
	@ResponseBody
	public Map<String, Object> addTopFieldModuleField(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String seqNo = request.getParameter("seqNo");
			String decorateModuleId = request.getParameter("decorateModuleId");
			TopFieldModuleField tfmf = new TopFieldModuleField();
			tfmf.setDecorateModuleId(Integer.parseInt(decorateModuleId));
			tfmf.setFieldName("");
			tfmf.setLinkDecorateAreaId(-1);
			tfmf.setCreateDate(new Date());
			tfmf.setDelFlag("1");
			if(!StringUtil.isEmpty(seqNo)){
				tfmf.setSeqNo(Integer.parseInt(seqNo));
			}
			topFieldModuleFieldService.insertSelective(tfmf);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}


	/**
	 * 插入领券秒杀模块
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/insertCouponSecondKillModule.shtml")
	@ResponseBody
	public Map<String, Object> insertCouponSecondKillModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateArea da = new DecorateArea();
		da.setDelFlag("0");
		da.setCreateDate(new Date());
		da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		da.setDecorateInfoId(Integer.parseInt(decorateInfoId));
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		da.setSeqNo(decorateAreas.size()+1);
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType(request.getParameter("moduleType"));
		decorateAreaService.save(da,dm);
		//创建时,设置默认的领券秒杀背景色
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer decorateModuleId = dm.getId();
		String moduleType = dm.getModuleType();
		decorateAreaService.insertCouponColor(decorateModuleId,moduleType,staffId);

		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		return resMap;
	}

	/**
	 * 设置领券秒杀背景色
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/settingColor.shtml")
	public ModelAndView colorSetting(HttpServletRequest request){
		ModelAndView m = new ModelAndView();
		String decorateModuleId = request.getParameter("decorateModuleId");
		String moduleType = request.getParameter("moduleType");
		m.setViewName("/activityAreaNew/settingColorEdit");
		m.addObject("decorateModuleId",decorateModuleId);
		m.addObject("moduleType",moduleType);
		SeckillModuleColorSetting seckillModuleColorSetting =null;
		SeckillModuleColorSettingExample seckillModuleColorSettingExample = new SeckillModuleColorSettingExample();
		seckillModuleColorSettingExample.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
		List<SeckillModuleColorSetting> seckillModuleColorSettingList = seckillModuleColorSettingService.selectByExample(seckillModuleColorSettingExample);
		if(seckillModuleColorSettingList.size()>0 && seckillModuleColorSettingList !=null){
			 seckillModuleColorSetting = seckillModuleColorSettingList.get(0);
		}
		m.addObject("seckillModuleColorSetting",seckillModuleColorSetting);

		return m;

	}


	/**
	 * 保存领券秒杀背景色
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/saveSettingColor.shtml")
	@ResponseBody
	public Map<String, Object> saveSettingColor(HttpServletRequest request,SeckillModuleColorSetting seckillModuleColorSetting){
		Map<String,Object> resMap = new HashMap<>();

		try {
			if(seckillModuleColorSetting!=null){
				seckillModuleColorSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				seckillModuleColorSetting.setUpdateDate(new Date());
				seckillModuleColorSettingService.updateByPrimaryKeySelective(seckillModuleColorSetting);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
	}


	/**
	 * 设置领券秒杀时间点
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/settingTime.shtml")
	public ModelAndView settingTime(HttpServletRequest request){
		ModelAndView m = new ModelAndView();
		String decorateModuleId = request.getParameter("decorateModuleId");
		m.setViewName("/activityAreaNew/settingTimeEdit");
		m.addObject("decorateModuleId",decorateModuleId);

		CouponModuleTimeExample couponModuleTimeExample = new CouponModuleTimeExample();
		couponModuleTimeExample.setOrderByClause("ABS(start_hours) ,ABS(start_min) ");
		couponModuleTimeExample.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
		List<CouponModuleTime> couponModuleTimeList = couponModuleTimeService.selectByExample(couponModuleTimeExample);
		m.addObject("couponModuleTimeList",couponModuleTimeList);
		return m;

	}

	/**
	 * 保存并检查领券秒杀时间点是否重复
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/checkPointOfTime.shtml")
	@ResponseBody
	public Map<String, Object> checkPointOfTime(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateModuleId = request.getParameter("decorateModuleId");
		String startHours = request.getParameter("startHours");
		String startMin = request.getParameter("startMin");
		try {
		//couponModuleTimeExampleForNum查询数量
		CouponModuleTimeExample couponModuleTimeExampleForNum = new CouponModuleTimeExample();
		CouponModuleTimeExample.Criteria criteriaForNum = couponModuleTimeExampleForNum.createCriteria();
		criteriaForNum.andDelFlagEqualTo("0");
		//couponModuleTimeExample查询有没有重复
		CouponModuleTimeExample couponModuleTimeExample =new CouponModuleTimeExample();
		CouponModuleTimeExample.Criteria criteria = couponModuleTimeExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if(!StringUtil.isEmpty(startHours)){
			criteria.andStartHoursEqualTo(startHours);
		}
		if(!StringUtil.isEmpty(startMin)){
			criteria.andStartMinEqualTo(startMin);
		}
		if(!StringUtil.isEmpty(decorateModuleId)){
			criteria.andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
			criteriaForNum.andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
		}

			int count = couponModuleTimeService.countByExample(couponModuleTimeExample);
			int countForNum = couponModuleTimeService.countByExample(couponModuleTimeExampleForNum);
			if(count>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "该时间点已经存在,不可重复添加");
			}/*else if(countForNum>=5){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "最多只能添加5个时间点");
			}*/else {
				int staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				couponModuleTimeService.save(startHours,startMin,decorateModuleId,staffId);
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "保存成功");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}

	/**
	 * 删除秒杀模块的时间点
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/delCouponTime.shtml")
	@ResponseBody
	public Map<String, Object> delCouponTime(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<>();
		String couponModuleTimeId = request.getParameter("couponModuleTimeId");
		try {
			CouponModuleTime couponModuleTime = couponModuleTimeService.selectByPrimaryKey(Integer.parseInt(couponModuleTimeId));
			couponModuleTime.setDelFlag("1");
			couponModuleTimeService.updateByPrimaryKeySelective(couponModuleTime);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
	}


	/**
	 * 主会场模块
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/mainVenueModule.shtml")
	public ModelAndView mainVenueModule(HttpServletRequest request){
		ModelAndView m = new ModelAndView();
		m.setViewName("/activityAreaNew/mainVenueModuleList");
		return m;
	}

	/**
	 * 主会场模块数据
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/mainVenueModuleData.shtml")
	@ResponseBody
	public Map<String, Object> mainVenueModuleData(HttpServletRequest request,Page page){

		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityAreaModule> dataList = new ArrayList<ActivityAreaModule>();
		Integer totalCount = 0;
		try {
			ActivityAreaModuleExample activityAreaModuleExample = new ActivityAreaModuleExample();
			activityAreaModuleExample.setOrderByClause("IFNULL(seq_no, 99999999999),id");
			/*activityAreaModuleExample.setOrderByClause("seq_no desc");*/
			activityAreaModuleExample.createCriteria().andDelFlagEqualTo("0");
			activityAreaModuleExample.setLimitSize(page.getLimitSize());
			activityAreaModuleExample.setLimitStart(page.getLimitStart());

			dataList = activityAreaModuleService.selectByExample(activityAreaModuleExample);
			totalCount = activityAreaModuleService.countByExample(activityAreaModuleExample);

		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 设置会场
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/settingUpVenue.shtml")
	public ModelAndView settingUpVenue(HttpServletRequest request){
		ModelAndView m = new ModelAndView();
		ActivityAreaSettingExample activityAreaSettingExample = new ActivityAreaSettingExample();
		activityAreaSettingExample.createCriteria().andDelFlagEqualTo("0");
		List<ActivityAreaSetting> activityAreaSettings = activityAreaSettingService.selectByExample(activityAreaSettingExample);
		if(activityAreaSettings.size()>0 && activityAreaSettings != null){
			m.addObject("activityAreaSetting",activityAreaSettings.get(0));
		}

		m.setViewName("/activityAreaNew/settingUpVenueList");
		return m;
	}


	/**
	 * 保存设置住会场数据
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/SaveUpVenue.shtml")
	@ResponseBody
	public Map<String, Object> SaveUpVenue(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String bottomBgPic = request.getParameter("bottomBgPic");
		try {
			if(!StringUtil.isEmpty(id)){
				ActivityAreaSetting activityAreaSetting = activityAreaSettingService.selectByPrimaryKey(Integer.parseInt(id));
				activityAreaSetting.setName(name);
				activityAreaSetting.setBottomBgPic(bottomBgPic);
				activityAreaSetting.setUpdateDate(new Date());
				activityAreaSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activityAreaSettingService.updateByPrimaryKeySelective(activityAreaSetting);
			}else{
				ActivityAreaSetting aas = new ActivityAreaSetting();
				aas.setBottomBgPic(bottomBgPic);
				aas.setName(name);
				aas.setCreateDate(new Date());
				aas.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				aas.setDelFlag("0");
				activityAreaSettingService.insertSelective(aas);
			}


		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
	}


	/**
	 * 编辑模块
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/editModule.shtml")
	public ModelAndView editModule(HttpServletRequest request){
		ModelAndView m = new ModelAndView();
		String id = request.getParameter("id");
		ActivityAreaModule activityAreaModule = activityAreaModuleService.selectByPrimaryKey(Integer.parseInt(id));
		if( activityAreaModule != null){
			m.addObject("activityAreaModule",activityAreaModule);
		}
		m.setViewName("/activityAreaNew/editModule");
		return m;
	}

	/**
	 * 保存编辑模块模块
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/saveEditModule.shtml")
	@ResponseBody
	public Map<String, Object> saveEditModule(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String defaultModulePic = request.getParameter("defaultModulePic");
		String selectedModulePic = request.getParameter("selectedModulePic");
		String linkType = request.getParameter("linkType");
		String linkValue = request.getParameter("linkValue");
		try {
			if ("1".equals(linkType)&&!StringUtil.isEmpty(linkValue)){//会场
				ActivityAreaExample activityAreaExample =new ActivityAreaExample();
				ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
				activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
				Integer totalCount=activityAreaService.countByExample(activityAreaExample);
				if (totalCount==0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "会场不存在");
					return resMap;
				}
			}
			if ("4".equals(linkType)&&!StringUtil.isEmpty(linkValue)){//自建页面
				CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(customPage==null || !"0".equals(customPage.getDelFlag())){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "自建页面不存在");
					return resMap;

				}
			}
			if(!StringUtil.isEmpty(id)){
				ActivityAreaModule activityAreaModule = activityAreaModuleService.selectByPrimaryKey(Integer.parseInt(id));
				activityAreaModule.setName(name);
				activityAreaModule.setDefaultModulePic(defaultModulePic);
				activityAreaModule.setSelectedModulePic(selectedModulePic);
				activityAreaModule.setLinkType(linkType);
				activityAreaModule.setLinkValue(linkValue);
				activityAreaModule.setUpdateDate(new Date());
				activityAreaModule.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activityAreaModuleService.updateByPrimaryKeySelective(activityAreaModule);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
	}

	/**
	 * 修改模块上下架状态
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/changeModuleStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeModuleStatus(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<>();
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		try {
			if(!StringUtil.isEmpty(id)){
				ActivityAreaModuleExample activityAreaModuleExample = new ActivityAreaModuleExample();
				activityAreaModuleExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
				int count = activityAreaModuleService.countByExample(activityAreaModuleExample);
				if("0".equals(status)&&count<=3){//下架
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "最少需要有三个模块,不能再下架了");
					return resMap;
				}
				ActivityAreaModule activityAreaModule = activityAreaModuleService.selectByPrimaryKey(Integer.parseInt(id));
				if("1".equals(status)){//上架
					String name = activityAreaModule.getName();
					String defaultModulePic = activityAreaModule.getDefaultModulePic();
					String selectedModulePic = activityAreaModule.getSelectedModulePic();
					String linkType = activityAreaModule.getLinkType();
					if(StringUtil.isEmpty(name) || StringUtil.isEmpty(defaultModulePic) || StringUtil.isEmpty(selectedModulePic) || StringUtil.isEmpty(linkType)){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "请完善模块信息后再上架");
						return resMap;
					}
				}

				activityAreaModule.setStatus(status);
				activityAreaModule.setUpdateDate(new Date());
				activityAreaModule.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activityAreaModuleService.updateByPrimaryKeySelective(activityAreaModule);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
	}


	/**
	 * 修改模块排序值
	 * @Title
	 * @Description
	 */
	@RequestMapping(value="/activityAreaNew/saveSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveSeqNo(HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<>();
		String id = request.getParameter("id");
		String seqNo = request.getParameter("seqNo");
		try {
			if(!StringUtil.isEmpty(id)){
				ActivityAreaModule activityAreaModule = activityAreaModuleService.selectByPrimaryKey(Integer.parseInt(id));
				if("-1".equals(seqNo)){
					activityAreaModule.setSeqNo(null);
				}else{
					activityAreaModule.setSeqNo(Integer.parseInt(seqNo));
				}
				activityAreaModule.setUpdateDate(new Date());
				activityAreaModule.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activityAreaModuleService.updateByPrimaryKey(activityAreaModule);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
	}


    /**
     * 购物津贴设置页面
     * @return
     */
	@RequestMapping("/shopping/allowanceSetting.shtml")
	public  ModelAndView  shoppingAllowanceSetting(){
		ModelAndView m = new ModelAndView();
		m.setViewName("/activityAreaNew/shoppingAllowance");
		return m;
	}


    @RequestMapping("/shopping/allowanceSettingList.shtml")
    @ResponseBody
    public Map<String, Object> shoppingAllowanceSettingList(HttpServletRequest request, Page page){
        Map<String,Object> resMap = new HashMap<String,Object>();
        int totalCount = 0;
        List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        try {
            totalCount=allowanceSettingService.countAllowanceSettingList(paramMap);
            paramMap.put("limitStart", page.getLimitStart());
            paramMap.put("limitSize", page.getLimitSize());
            resList=allowanceSettingService.selectAllowanceSettingList(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", resList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     * 新增(编辑)购物津贴设置页面
     * @return
     */
	@RequestMapping("/shopping/addAllowanceSetting.shtml")
	public ModelAndView addAllowanceSetting(HttpServletRequest request){
		ModelAndView m = new ModelAndView();
		String id = request.getParameter("id");
		if (!StringUtil.isEmpty(id)){
			m.addObject("allowanceSetting",allowanceSettingService.selectByPrimaryKey(Integer.parseInt(id)));
			AllowanceSettingIntegralExchangeExample example = new AllowanceSettingIntegralExchangeExample();
			example.createCriteria().andAllowanceIdEqualTo(Integer.parseInt(id)).andDelFlagEqualTo("0");
			//获取津贴积分表中数据
			List<AllowanceSettingIntegralExchange> integralExchanges = allowanceSettingIntegralExchangeService.selectByExample(example);
			m.addObject("integralExchange1",integralExchanges.get(0));
			m.addObject("integralExchange2",integralExchanges.get(1));
		}
		m.setViewName("/activityAreaNew/addShoppingAllowance");
		return m;
	}


	@RequestMapping("/shopping/saveAllowanceSetting.shtml")
    @ResponseBody
    public Map<String, Object>  saveAllowanceSetting(HttpServletRequest  request,AllowanceSetting setting){
			StaffBean staffBean = this.getSessionStaffBean(request);
			Map<String, Object> resMap = allowanceSettingService.addOrEditAllowanceSetting(request, setting, staffBean);
			return resMap;
	}

	@ResponseBody
	@RequestMapping("/allowanceSetting/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String status = request.getParameter("status");
			String allowanceSettingId = request.getParameter("allowanceSettingId");
			if(!StringUtil.isEmpty(allowanceSettingId)) {
				AllowanceSettingExample example = new AllowanceSettingExample();
				example.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
				List<AllowanceSetting> allowanceSettings = allowanceSettingService.selectByExample(example);
				if ("1".equals(status)&& CollectionUtils.isNotEmpty(allowanceSettings)){
                    throw new ArgException("已有启用中的规则请先关闭");
				}else {
                    AllowanceSetting allowanceSetting = allowanceSettingService.selectByPrimaryKey(Integer.parseInt(allowanceSettingId));
                    allowanceSetting.setUpdateBy(Integer.parseInt(staffID));
                    allowanceSetting.setStatus(status);
                    allowanceSetting.setUpdateDate(date);
                    allowanceSettingService.updateByPrimaryKey(allowanceSetting);
				}
			}else {
				code = "9999";
				msg = "ID为空！";
			}
		}catch (ArgException arge) {
			code = "9999";
			msg=arge.getMessage();
			arge.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}


    @ResponseBody
    @RequestMapping("/allowanceSetting/allowanceReset.shtml")
    public Map<String, Object> allowanceReset(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date now = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
			ActivityAreaExample activityAreaExample = new ActivityAreaExample();
			ActivityAreaExample.Criteria criteria = activityAreaExample.createCriteria();
			criteria.andStatusEqualTo("1").andPreferentialTypeEqualTo("5").andActivityBeginTimeLessThanOrEqualTo(now).andActivityEndTimeGreaterThanOrEqualTo(now);
			List<ActivityArea> activityAreas = activityAreaService.selectByExample(activityAreaExample);
			if (CollectionUtils.isNotEmpty(activityAreas)){
				throw  new ArgException("存在未结束活动无法清零");
			}else {
				MemberAllowanceExample memberAllowanceExample = new MemberAllowanceExample();
				memberAllowanceExample.createCriteria().andDelFlagEqualTo("0");
				List<MemberAllowanceCustom> memberAllowanceCustoms=memberAllowanceService.selectByCustomExample(memberAllowanceExample);
				for (MemberAllowanceCustom memberAllowanceCustom : memberAllowanceCustoms) {
					BigDecimal money = memberAllowanceCustom.getSumAllowanceAmount().subtract(memberAllowanceCustom.getSumUsageAmount());
					if (money.compareTo(BigDecimal.ZERO) ==1){
						MemberAllowanceUsage memberAllowanceUsage = new MemberAllowanceUsage();
						memberAllowanceUsage.setMemberId(memberAllowanceCustom.getMemberId());
						memberAllowanceUsage.setType("2");
						memberAllowanceUsage.setUsageAmount(money);
						memberAllowanceUsage.setUsageDate(now);
						memberAllowanceUsage.setCreateDate(now);
						memberAllowanceUsage.setCreateBy(Integer.parseInt(staffID));
						memberAllowanceUsage.setDelFlag("0");
						memberAllowanceUsageMapper.insertSelective(memberAllowanceUsage);
					}
				}
			}

		}catch (ArgException arge) {
            code = "9999";
            msg=arge.getMessage();
            arge.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = "9999";
            msg = "系统异常请稍后再试！";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


}