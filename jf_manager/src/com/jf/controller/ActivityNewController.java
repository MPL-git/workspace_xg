package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.enums.ActivityStatusEnum;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import com.jf.vo.ResponseMsg;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName ActivityNewController
 * @Description TODO(新版活动会场)
 * @author pengl
 * @date 2018年1月10日 下午6:13:57
 */
@SuppressWarnings("serial")
@Controller
public class ActivityNewController extends BaseController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
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
	private ProductService productService;
	
	@Autowired
	private ActivityGroupSerivce activityGroupSerivce;
	
	@Autowired
	private ActivityAreaService activityAreaService;
	
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private AdInfoService adInfoService;
	
	@Autowired
	private ActivityBrandGroupService  activitybrandGroupService;
	
	@Autowired
	private ActivityBrandGroupActivityService activitybrandGroupActivityService;
	
	@Resource
	private PlatformContactService platformContactService;

	@Resource
	private TypeColumnPvStatisticalService typeColumnPvStatisticalService;

	/**
	 * 
	 * @Title getProductBrandList   
	 * @Description TODO(品牌)   
	 * @author pengl
	 * @date 2018年1月11日 下午5:18:05
	 */
	@ResponseBody
	@RequestMapping("/activityNew/getProductBrandList.shtml")
	public Map<String, Object> getProductBrandList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ProductBrand> dataList = null;
		Integer totalCount = 0;
		try {
			ProductBrandExample productBrandExample = new ProductBrandExample();
			ProductBrandExample.Criteria productBrandCriteria = productBrandExample.createCriteria();
			productBrandCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("condition"))) {
				JSONArray jsonArray = JSONArray.fromObject(request.getParameter("condition"));
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String searchvalue = jsonObject.getString("value");
					String searchitem = jsonObject.getString("field");
					if (!StringUtil.isEmpty(searchvalue)) {
						if (searchitem.equals("name")) {
							productBrandCriteria.andNameLike("%"+searchvalue+"%");
						}
					}
				}
			}
			productBrandExample.setLimitSize(page.getLimitSize());
			productBrandExample.setLimitStart(page.getLimitStart());
			dataList = productBrandService.selectByExample(productBrandExample);
			totalCount = productBrandService.countByExample(productBrandExample);
			
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
	 * @Description TODO(新版品牌特卖)   
	 * @author pengl
	 * @date 2018年1月11日 上午9:26:57
	 */
	@RequestMapping("/activityNew/activityManager.shtml")
	public ModelAndView activityManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		m.addObject("preferentialTypeList", DataDicUtil.getTableStatus("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE")); //特惠类型
		m.addObject("statusList", ActivityStatusEnum.list);	// 活动状态
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		m.addObject("isCwOrgStatus", isCwOrgStatus);
		
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList); //1级类目
		
		ActivityGroupExample activityGroupExample = new ActivityGroupExample();
		activityGroupExample.createCriteria().andDelFlagEqualTo("0");
		activityGroupExample.setOrderByClause(" id desc");
		activityGroupExample.setLimitStart(0);
		activityGroupExample.setLimitSize(50);
		List<ActivityGroup> activityGroupList = activityGroupSerivce.selectByExample(activityGroupExample); //调用特卖分组ID最大50条
		m.addObject("activityGroupList", activityGroupList); //调用特卖分组ID最大50条
		
		List<SysStaffInfo> sysStaffInfoList = new ArrayList<SysStaffInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(statusPage != null && statusPage.equals("1")) { //品牌特卖审核
			map.put("operate_audit_by", "operate_audit_by"); //运营审核人员
			List<Map<String, Object>> operateAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> operateAuditByMap : operateAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) operateAuditByMap.get("operate_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //专员审核人
			m.addObject("operateAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "OPERATE_AUDIT_STATUS")); //运营专员审核状态
			m.addObject("designAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "DESIGN_AUDIT_STATUS")); //设计部审核状态
			
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Integer isContact = 0; //默认不是对接人
			
			PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
			if (platformContacts !=null && platformContacts.size()>0) {
				isContact=1;
				Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
				PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
				List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
				m.addObject("myContactId", myContactId);
				m.addObject("platformAssistantContacts", platformAssistantContact);
			}			
			
			PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
			platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
			m.addObject("platformMchtContacts", platformMchtContact);	        
			m.addObject("isContact", isContact);		
			m.setViewName("/activityNew/activityBrandSaleAuditList");
		}else if(statusPage != null && statusPage.equals("2")) { //品牌特卖排期
			map.put("coo_audit_by", "coo_audit_by"); //运营总监
			List<Map<String, Object>> cooAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> cooAuditByMap : cooAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) cooAuditByMap.get("coo_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //排期审核人
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "COO_AUDIT_STATUS")); //运营总监审核状态
			m.addObject("designAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "DESIGN_AUDIT_STATUS")); //设计部审核状态

			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Integer isContact = 0; //默认不是对接人
			
			PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
			if (platformContacts !=null && platformContacts.size()>0) {
				isContact=1;
				Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
				PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
				List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
				m.addObject("myContactId", myContactId);
				m.addObject("platformAssistantContacts", platformAssistantContact);
			}			
			
			PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
			platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
			m.addObject("platformMchtContacts", platformMchtContact);	        
			m.addObject("isContact", isContact);
			m.setViewName("/activityNew/activityBrandSaleScheduleList");
		}else if(statusPage != null && statusPage.equals("3")) { //品牌特卖设计
			map.put("design_audit_by", "design_audit_by"); //设计部
			List<Map<String, Object>> designAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> designAuditByMap : designAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) designAuditByMap.get("design_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //设计部审核人
			m.addObject("designAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "DESIGN_AUDIT_STATUS")); //设计部审核状态
			m.setViewName("/activityNew/activityBrandSaleBesignList");
		}else if(statusPage != null && statusPage.equals("4")) { //已通过品牌特卖
			map.put("coo_audit_by", "coo_audit_by"); //运营总监
			List<Map<String, Object>> cooAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> cooAuditByMap : cooAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) cooAuditByMap.get("coo_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //排期审核人
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "COO_AUDIT_STATUS")); //运营总监审核状态
			
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Integer isContact = 0; //默认不是对接人
			
			PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
			if (platformContacts !=null && platformContacts.size()>0) {
				isContact=1;
				Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
				PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
				List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
				m.addObject("myContactId", myContactId);
				m.addObject("platformAssistantContacts", platformAssistantContact);
			}			
			
			PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
			platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
			m.addObject("platformMchtContacts", platformMchtContact);	        
			m.addObject("isContact", isContact);	
			m.setViewName("/activityNew/activityBrandSalePassList");
		}else if(statusPage != null && statusPage.equals("5")) { //品牌特卖排序
			map.put("coo_audit_by", "coo_audit_by"); //运营总监
			List<Map<String, Object>> cooAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> cooAuditByMap : cooAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) cooAuditByMap.get("coo_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //排期审核人
			
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Integer isContact = 0; //默认不是对接人
			
			PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
			if (platformContacts !=null && platformContacts.size()>0) {
				isContact=1;
				Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
				PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
				List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
				m.addObject("myContactId", myContactId);
				m.addObject("platformAssistantContacts", platformAssistantContact);
			}			
			
			PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
			platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);
			//特卖品牌团
			ActivityBrandGroupExample activityBrandGroupExample = new ActivityBrandGroupExample();
			activityBrandGroupExample.createCriteria().andDelFlagEqualTo("0");
			List<ActivityBrandGroup> activityBrandGroupList = activitybrandGroupService.selectByExample(activityBrandGroupExample);
			m.addObject("activityBrandGroupList", activityBrandGroupList);	        
			m.addObject("platformMchtContacts", platformMchtContact);	        
			m.addObject("isContact", isContact);		
			m.setViewName("/activityNew/activityBrandSaleSeqNoList");
		}else if(statusPage != null && statusPage.equals("6")) { //设计快速审核
			map.put("design_audit_by", "design_audit_by"); //设计部
			List<Map<String, Object>> designAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> designAuditByMap : designAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) designAuditByMap.get("design_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //设计部审核人
			m.addObject("designAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "DESIGN_AUDIT_STATUS")); //设计部审核状态
			
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Integer isContact = 0; //默认不是对接人
			
			PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
			if (platformContacts !=null && platformContacts.size()>0) {
				isContact=1;
				Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
				PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
				List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
				m.addObject("myContactId", myContactId);
				m.addObject("platformAssistantContacts", platformAssistantContact);
			}			
			
			PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
			platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
			m.addObject("platformMchtContacts", platformMchtContact);	        
			m.addObject("isContact", isContact);		
			m.setViewName("/activityNew/activityDesignAuditList");
		}else{ //全部品牌特卖
			map.put("coo_audit_by", "coo_audit_by"); //运营总监
			List<Map<String, Object>> cooAuditByList = activityService.getAuditByList(map);
			for(Map<String, Object> cooAuditByMap : cooAuditByList) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey((Integer) cooAuditByMap.get("coo_audit_by"));
				sysStaffInfoList.add(sysStaffInfo);
			}
			m.addObject("sysStaffInfoList", sysStaffInfoList); //排期审核人
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus("BU_ACTIVITY", "COO_AUDIT_STATUS")); //运营总监审核状态
			m.addObject("activityGroupId", request.getParameter("activityGroupId")); //分组ID
			
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Integer isContact = 0; //默认不是对接人
			
			PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
			if (platformContacts !=null && platformContacts.size()>0) {
				isContact=1;
				Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
				PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
				List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
				m.addObject("myContactId", myContactId);
				m.addObject("platformAssistantContacts", platformAssistantContact);
			}			
			
			PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
			platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
			m.addObject("platformMchtContacts", platformMchtContact);	        
			m.addObject("isContact", isContact);
			m.setViewName("/activityNew/activityBrandSaleList");
		}
		
		return m;
	}
	
	/**
	 * 
	 * @Title activityBrandSaleAuditList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月11日 下午2:17:58
	 */
	@ResponseBody
	@RequestMapping("/activityNew/activityBrandSaleAuditList.shtml")
	public Map<String, Object> activityBrandSaleAuditList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityNewCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActivityCustomExample activityCustomExample = new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
			activityCustomCriteria.andDelFlagEqualTo("0");
			activityCustomCriteria.andActivityAreaBySourceEqualTo("2"); //品牌特卖
			if(!StringUtil.isEmpty(request.getParameter("statusAudit")) && request.getParameter("statusAudit").equals("1")) { //排期 专员审核状态未通过不显示，活动结束了不显示
				activityCustomCriteria.andOperateAuditStatusEqualTo("2");
				activityCustomCriteria.andActivityEndTimeGreaterThanNow();
			}
			if(!StringUtil.isEmpty(request.getParameter("statusAudit")) && request.getParameter("statusAudit").equals("2")) { //专员审核状态=通过  且  活动状态！=驳回   且  活动未结束  
				activityCustomCriteria.andOperateAuditStatusEqualTo("2");
				activityCustomCriteria.andActivityEndTimeGreaterThanNow();
				activityCustomCriteria.andStatusNotEqualTo("4");
			}
			if(!StringUtil.isEmpty(request.getParameter("statusAudit")) && request.getParameter("statusAudit").equals("3")) { //品牌特卖排序 	 活动状态=通过  且 活动未结束
				activityCustomCriteria.andStatusEqualTo("6");
				activityCustomCriteria.andActivityEndTimeGreaterThanNow();
			}
			//排序
			if(!StringUtil.isEmpty(request.getParameter("statusAudit")) && request.getParameter("statusAudit").equals("3")) { //品牌特卖排序 	 活动状态=通过  且 活动未结束
				activityCustomExample.setOrderByClause(" IFNULL(ba.seq_no, 999999) asc, (select baa.activity_begin_time from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0') desc, ba.id desc");
			}else {
				activityCustomExample.setOrderByClause(" ba.submit_time desc");
			}
			//特卖id
			if(!StringUtil.isEmpty(request.getParameter("activityId"))) { 
				activityCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("activityId").trim()));
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
			//合作类型
			if (!StringUtil.isEmpty(request.getParameter("mchtType"))){
				activityCustomCriteria.andMchtTypeEqualTo(request.getParameter("mchtType"));
			}

			//品牌特卖排期是否自营
			if (!StringUtil.isEmpty(request.getParameter("isManageSelf"))){
				activityCustomCriteria.andIsManageSelfEqualTo(request.getParameter("isManageSelf"));
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
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					activityCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				//一级类目
				if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
					activityCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
				}
			}
			
			//二级类目
			if(!StringUtil.isEmpty(request.getParameter("productTypeSecondId"))) {
				activityCustomCriteria.andProductTypeSecondIdEqualTo(Integer.parseInt(request.getParameter("productTypeSecondId")));
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
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				activityCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}
			//特卖品牌团
			if(!StringUtil.isEmpty(request.getParameter("activityBrandGroupId")) ) {
				if("A".equals(request.getParameter("activityBrandGroupId"))) { //全部品牌团
					activityCustomCriteria.andActivityBrandGroupNotNull();
				}else if("B".equals(request.getParameter("activityBrandGroupId"))) { //未加入
					activityCustomCriteria.andActivityBrandGroupNull();
				}else { //特卖品牌团ID
					activityCustomCriteria.andActivityBrandGroupEqualTo(request.getParameter("activityBrandGroupId"));
				}
			}
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
	 * @Title getActivity   
	 * @Description TODO(领取)   
	 * @author pengl
	 * @date 2018年1月11日 下午6:45:23
	 */
	@ResponseBody
	@RequestMapping("/activityNew/getActivity.shtml")
	public Map<String, Object> getActivity(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if (!StringUtil.isEmpty(request.getParameter("activityId"))) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				ActivityExample activityExample = new ActivityExample();
				activityExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("activityId")));
				List<Activity> activityList =  activityService.selectByExample(activityExample);
				if(activityList != null && activityList.size() > 0) {
					Activity activity = activityList.get(0);
					if(!StringUtil.isEmpty(request.getParameter("status"))) {
						if("1".equals(request.getParameter("status"))) { //专员审核人 领取
							if(activity.getOperateAuditBy() != null) {
								statusCode = "999";
								message = "对不起，已被其他人员领取";
							}else {
								activity.setOperateAuditBy(staffId);
								activity.setStatus("3"); //活动状态变为 审核中
								activity.setUpdateBy(staffId);
								activity.setUpdateDate(new Date());
								activityService.updateByPrimaryKeySelective(activity);
							}
						}else if("2".equals(request.getParameter("status"))) { //排期审核人 领取
							if(activity.getCooAuditBy() != null) {
								statusCode = "999";
								message = "对不起，已被其他人员领取";
							}else {
								activity.setCooAuditBy(staffId);
								activity.setUpdateBy(staffId);
								activity.setUpdateDate(new Date());
								activityService.updateByPrimaryKeySelective(activity);
							}
						}else if("3".equals(request.getParameter("status"))) { //设计审核人 领取
							if(activity.getDesignAuditBy() != null) {
								statusCode = "999";
								message = "对不起，已被其他人员领取";
							}else {
								activity.setDesignAuditBy(staffId);
								activity.setUpdateBy(staffId);
								activity.setUpdateDate(new Date());
								activityService.updateByPrimaryKeySelective(activity);
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
	 * @Title showOrAuditActivity   
	 * @Description TODO(查看或审核页面)   
	 * @author pengl
	 * @date 2018年1月12日 上午11:59:34
	 */
	@RequestMapping("/activityNew/showOrAuditActivity.shtml")
	public ModelAndView showOrAuditActivity(HttpServletRequest request, Integer activityId, String statusPage, String statusAudit) {
		ModelAndView m = new ModelAndView();
		ActivityNewCustom activityNewCustom = activityService.selectByCustomPrimaryKey(activityId);
		m.addObject("activityNewCustom", activityNewCustom);
		m.addObject("statusAudit", statusAudit); //审核活动  1：专员审核   2：排期审核	3：设计审核
		
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
		
		if(!StringUtil.isEmpty(activityNewCustom.getPreferentialType())) {
			//优惠券
			if(activityNewCustom.getPreferentialType().equals("1") ) {
				CouponExample couponExample = new CouponExample();
				CouponExample.Criteria couponCriteria = couponExample.createCriteria(); 
				couponCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityNewCustom.getActivityAreaId());
				List<Coupon> cuponList = couponService.selectByExample(couponExample);
				m.addObject("cuponList", cuponList);
				m.addObject("recBeginDate",cuponList.get(0).getRecBeginDate());
				m.addObject("recEndDate",cuponList.get(0).getRecEndDate());
				String couponIds = "";
				for(int i=0;i<cuponList.size();i++){
					if(StringUtils.isEmpty(couponIds)){
						couponIds = cuponList.get(i).getId().toString();
					}else{
						couponIds+=","+cuponList.get(i).getId().toString();
					}
				}
				m.addObject("couponIds",couponIds);
			}
			//满减
			if(activityNewCustom.getPreferentialType().equals("2") ) {
				FullCutCustom fullCutCustom = fullCutService.selectByActivityIdList(activityNewCustom.getActivityAreaId());
				List<Map<String, Object>> fullCutList=new ArrayList<Map<String,Object>>();
				if(fullCutCustom != null) {
					//判断是否阶梯
					if("1".equals(fullCutCustom.getLadderFlag())){
						String[] rule = fullCutCustom.getRule().split("\\|");
						for (int i = 0; i < rule.length; i++) {
							Map<String, Object> fullCut = new HashMap<String, Object>();
							String[] rules=org.springframework.util.StringUtils.split(rule[i], ",");
							fullCut.put("full", rules[0]);
							fullCut.put("cut",rules[1]);
							fullCutList.add(fullCut);
						}
					}else{
						if(!StringUtils.isEmpty(fullCutCustom.getRule())){
							String[] rules = org.springframework.util.StringUtils.split(fullCutCustom.getRule(), ",");
							fullCutCustom.setFull(rules[0]);
							fullCutCustom.setCut(rules[1]);
						}
					}
				}
				m.addObject("fullCutCustom", fullCutCustom);
				m.addObject("fullCutList", fullCutList);
			}
			//满赠
			if(activityNewCustom.getPreferentialType().equals("3") ) {
				FullGive fullGive = fullGiveService.selectByActivityId(activityNewCustom.getActivityAreaId());
				m.addObject("fullGive", fullGive);
				Product product = productService.selectByPrimaryKey(fullGive.getProductId());
				m.addObject("product", product);
			}
			//多买优惠
			if(activityNewCustom.getPreferentialType().equals("4") ) {
				FullDiscount fullDiscount = fullDiscountService.selectByActivityId(activityNewCustom.getActivityAreaId());
				List<Map<String, Object>> fullDiscountList = new ArrayList<Map<String,Object>>();
				Map<String, Object> fullDiscountMap = new HashMap<String, Object>();
				if(fullDiscount != null && fullDiscount.getType() != null) {
					if("1".equals(fullDiscount.getType()) || "2".equals(fullDiscount.getType())) {
						String[] ruleDiscount = org.springframework.util.StringUtils.split(fullDiscount.getRule(), ",");
						if("1".equals(fullDiscount.getType())) {
							fullDiscountMap.put("fullOfOne", ruleDiscount[0]);
							fullDiscountMap.put("fullGiftsOneName", ruleDiscount[1]);
						}else{
							fullDiscountMap.put("fullOfTwo", ruleDiscount[0]);
							fullDiscountMap.put("fullGiftsTwoName", ruleDiscount[1]);
						}
					}
					if("3".equals(fullDiscount.getType())) {
						String[] ruleFullDiscount = fullDiscount.getRule().split("\\|");
						for (int i = 0; i < ruleFullDiscount.length; i++) {
							String[] fullRule = org.springframework.util.StringUtils.split(ruleFullDiscount[i], ",");
							Map<String, Object> fullGiscountThree = new HashMap<String, Object>();
							fullGiscountThree.put("fullGiscountThree", fullRule[0]);
							fullGiscountThree.put("fullGiscountThreeName", fullRule[1]);
							fullDiscountList.add(fullGiscountThree);
						}
					}
					m.addObject("fullDiscountType", fullDiscount.getType());
				}
				m.addObject("fullDiscountMap", fullDiscountMap);
				m.addObject("fullDiscountList", fullDiscountList);
			}
		}
		if(!StringUtil.isEmpty(statusPage)) {
			if("1".equals(statusPage)) { //查看
				m.setViewName("/activityNew/showActivityBrandSale");
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
					
					ActivityGroupExample activityGroupExample = new ActivityGroupExample();
					activityGroupExample.createCriteria().andDelFlagEqualTo("0");
					activityGroupExample.setOrderByClause(" id desc");
					activityGroupExample.setLimitStart(0);
					activityGroupExample.setLimitSize(50);
					List<ActivityGroup> activityGroupList = activityGroupSerivce.selectByExample(activityGroupExample); //调用特卖分组ID最大50条
					m.addObject("activityGroupList", activityGroupList); //调用特卖分组ID最大50条
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m.setViewName("/activityNew/auditActivityBrandSale");
			}else if("3".equals(statusPage)) { //设计快速审核-->查看活动
				m.setViewName("/activityNew/showActivityDesignAudit");
			}
		}
		return m;
	}


	/**
	 * 批量排期操作
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/activityNew/batchSchedule.shtml")
	public ModelAndView batchChangePrice(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/activityNew/batchSchedule");
		m.addObject("ids", request.getParameter("ids"));
		ActivityGroupExample activityGroupExample = new ActivityGroupExample();
		activityGroupExample.createCriteria().andDelFlagEqualTo("0");
		activityGroupExample.setOrderByClause(" id desc");
		activityGroupExample.setLimitStart(0);
		activityGroupExample.setLimitSize(50);
		List<ActivityGroup> activityGroupList = activityGroupSerivce.selectByExample(activityGroupExample); //调用特卖分组ID最大50条
		m.addObject("activityGroupList", activityGroupList); //调用特卖分组ID最大50条
		return m;
	}

	/**
	 * 批量排期修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activityNew/saveBatchSchedule.shtml")
	@ResponseBody
	public ResponseMsg saveBatchSchedule(HttpServletRequest request) {
		String staffID = this.getSessionStaffBean(request).getStaffID();
		ResponseMsg responseMsg = activityService.saveBatchSchedule(request, staffID);
		return  responseMsg;
	}


	
	/**
	 * 
	 * @Title auditActivity   
	 * @Description TODO(审核活动)   
	 * @author pengl
	 * @date 2018年1月13日 下午1:59:48
	 */
	@ResponseBody
	@RequestMapping("/activityNew/auditActivity.shtml")
	public Map<String, Object> auditActivity(HttpServletRequest request) {
		String returnCode = "0000";
		String returnMsg = "操作成功！";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			activityService.auditActivity(request, staffBean);
		}catch (IOException e){
			returnCode = "9999";
			returnMsg = "系统错误";
			e.printStackTrace();
		} catch (Exception e) {
			returnCode = "999";
			returnMsg = "系统错误";
			e.printStackTrace();
		}
		resMap.put("operateAuditStatus", request.getParameter("operateAuditStatus"));
		resMap.put("cooAuditStatus", request.getParameter("cooAuditStatus"));
		resMap.put("id", request.getParameter("id"));
		resMap.put("returnCode", returnCode);
		resMap.put("returnMsg", returnMsg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title validateActivity   
	 * @Description TODO(验证品牌活动下是否有商品)   
	 * @author pengl
	 * @date 2018年1月25日 下午4:09:01
	 */
	@ResponseBody
	@RequestMapping("/activityNew/validateActivity.shtml")
	public Map<String, Object> validateActivity(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "";
		try {
			String statusAudit = request.getParameter("statusAudit"); // 1.专员审核	2.排期审核	3.设计审核
			if (!StringUtil.isEmpty(statusAudit) && !"3".equals(statusAudit)) {
				String activityId = request.getParameter("activityId");
				if (!StringUtil.isEmpty(activityId)) {
					
					ActivityProductExample activityProductExample = new ActivityProductExample();
					ActivityProductExample.Criteria activityProductCriteria = activityProductExample.createCriteria();
					activityProductCriteria.andDelFlagEqualTo("0");
					activityProductCriteria.andActivityIdEqualTo(Integer.parseInt(activityId));
					if("1".equals(statusAudit)) {
						activityProductCriteria.andOperateAuditStatusEqualTo("2");
					}else if("2".equals(statusAudit)) {
						activityProductCriteria.andCooAuditStatusEqualTo("2");
					}
					List<ActivityProduct> activityProductList = activityProductService.selectByExample(activityProductExample);
					if(activityProductList != null && activityProductList.size() > 0) {
						msg = "操作成功";
					}else {
						code = "999";
						if("1".equals(statusAudit)) {
							msg = "该品牌特卖活动下，无专员审核通过活动商品。请先添加商品！";
						}else if("2".equals(statusAudit)) {
							msg = "该品牌特卖活动下，无排期审核通过活动商品。请先添加商品！";
						}
					}
				}
			}
		} catch (Exception e) {
			code = "999";
			msg = "系统错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title updateProductTypeId   
	 * @Description TODO(2级类目修改)   
	 * @author pengl
	 * @date 2018年1月27日 下午3:23:31
	 */
	@ResponseBody
	@RequestMapping("/activityNew/updateProductTypeId.shtml")
	public Map<String, Object> updateProductTypeId(Integer productTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "";
		try {
			if(productTypeId != null) {
				ProductTypeExample productTypeSecondExample = new ProductTypeExample();
				productTypeSecondExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andParentIdEqualTo(productTypeId);
				productTypeSecondExample.setOrderByClause(" seq_no");
				List<ProductType> productTypeSecondList = productTypeService.selectByExample(productTypeSecondExample);
				msg = "操作成功！";
				map.put("productTypeSecondList", productTypeSecondList); //2级类目
			}
		} catch (Exception e) {
			code = "999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title updateActivityGroupId   
	 * @Description TODO(合成图片 水印)   
	 * @author pengl
	 * @date 2018年1月27日 下午7:54:20
	 */
	@ResponseBody
	@RequestMapping("/activityNew/updateActivityGroupId.shtml")
	public Map<String, Object> updateActivityGroupId(Integer activityId, String isSign) {
		Map<String, Object> map = activityService.updateActivityGroupId(activityId, isSign);
		return map;
	}
	
	/**
	 * 
	 * @Title updateActivityGroupIdCustom   
	 * @Description TODO(合成图片 水印 后期扩展)   
	 * @author pengl
	 * @date 2018年6月13日 下午6:55:12
	 */
	@ResponseBody
	@RequestMapping("/activityNew/updateActivityGroupIdCustom.shtml")
	public Map<String, Object> updateActivityGroupIdCustom(Integer activityId, String isSign) {
		Map<String, Object> map = activityService.updateActivityGroupIdCustom(activityId, isSign);
		return map;
	}
	
	
	
	//特卖分组列表数据
	@RequestMapping(value = "/activityNew/SaleGroupingList.shtml")
	public ModelAndView brandExpireCount(HttpServletRequest request) {
		String rtPage = "/activityNew/SaleGroupingList";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}

	@RequestMapping(value = "/activityNew/SaleGroupingListdata.shtml")
	@ResponseBody
	public Map<String, Object> initProductBrandData(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		List<ActivityGroupCustom> salelist = null;

		try {
			ActivityGroupExample activityGroupExample = new ActivityGroupExample();
			activityGroupExample.setOrderByClause("id desc");
			ActivityGroupExample.Criteria activityGroupExampleCriteria = activityGroupExample.createCriteria();
			activityGroupExampleCriteria.andDelFlagEqualTo("0");

			totalCount=activityGroupSerivce.countByCustomExample(activityGroupExample);

			activityGroupExample.setLimitStart(page.getLimitStart());
			activityGroupExample.setLimitSize(page.getLimitSize());

			salelist=activityGroupSerivce.selectByCustomExample(activityGroupExample);

		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", salelist);
		resMap.put("Total", totalCount);
		return resMap;	
	}

	//根据id获取特卖数据信息
	@RequestMapping(value = "/activityNew/editsale.shtml")
	public ModelAndView edit(HttpServletRequest request) {
		String rtPage = "/activityNew/editsale";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if(!StringUtil.isEmpty(request.getParameter("ID"))){
				ActivityGroup activityGroup = activityGroupSerivce.selectByPrimaryKey(Integer.valueOf(request.getParameter("ID"))); 
				resMap.put("activityGroup", activityGroup);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	//特卖添加数据,更新数据
	@ResponseBody
	@RequestMapping(value = "/activityNew/editsales.shtml")
	public ModelAndView editbrandsave(HttpServletRequest request){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Date date = new Date();
			if(StringUtil.isEmpty(request.getParameter("activityGroupId"))){
				//添加数据
				ActivityGroup activityGroup = new ActivityGroup();
				activityGroup.setGroupName(request.getParameter("groupName"));
				activityGroup.setSignPic(request.getParameter("signPic"));
				if(!StringUtil.isEmpty(request.getParameter("productWkPic"))) {
					activityGroup.setProductWkPic(request.getParameter("productWkPic"));
					if(!StringUtil.isEmpty(request.getParameter("productWkLinkId"))) {
						activityGroup.setProductWkLinkId(Integer.parseInt(request.getParameter("productWkLinkId")));
					}
					activityGroup.setProductWkPosition(request.getParameter("productWkPosition"));
				}
				if(!StringUtil.isEmpty(request.getParameter("productWkPicM"))) {
					activityGroup.setProductWkPicM(request.getParameter("productWkPicM"));
				}
				if(!StringUtil.isEmpty(request.getParameter("priceWkPic"))) {
					activityGroup.setPriceWkPic(request.getParameter("priceWkPic"));
					activityGroup.setPriceFontColor(request.getParameter("priceFontColor"));
				}
				activityGroup.setCreateBy(staffId);
				activityGroup.setCreateDate(date);
				activityGroup.setDelFlag("0");
				activityGroupSerivce.insertsale(activityGroup);
			}else{
				//更新数据
				ActivityGroup activityGroup = activityGroupSerivce.selectByPrimaryKey(Integer.parseInt(request.getParameter("activityGroupId")));
				String signPic = activityGroup.getSignPic();
				activityGroup.setGroupName(request.getParameter("groupName"));
				activityGroup.setSignPic(request.getParameter("signPic"));
				if(!StringUtil.isEmpty(request.getParameter("productWkPic"))) {
					activityGroup.setProductWkPic(request.getParameter("productWkPic"));
					if(!StringUtil.isEmpty(request.getParameter("productWkLinkId"))) {
							activityGroup.setProductWkLinkId(Integer.parseInt(request.getParameter("productWkLinkId")));
					}else {
						activityGroup.setProductWkLinkId(null);
					}
					activityGroup.setProductWkPosition(request.getParameter("productWkPosition"));
				}
				if(!StringUtil.isEmpty(request.getParameter("productWkPicM"))) {
					activityGroup.setProductWkPicM(request.getParameter("productWkPicM"));
				}
				if(!StringUtil.isEmpty(request.getParameter("priceWkPic"))) {
					activityGroup.setPriceWkPic(request.getParameter("priceWkPic"));
					activityGroup.setPriceFontColor(request.getParameter("priceFontColor"));
				}
				activityGroup.setUpdateBy(staffId);
				activityGroup.setUpdateDate(date);
				activityGroupSerivce.updateByPrimaryKey(activityGroup);

				//当修改分组入口图彩标的时候，分别对活动表跟会场表赋值新的彩标
				if(!org.apache.commons.lang.StringUtils.equals(activityGroup.getSignPic(),signPic)){
					ActivityAreaExample activityAreaExample = new ActivityAreaExample();
					activityAreaExample.createCriteria().andActivityGroupIdEqualTo(activityGroup.getId()).andDelFlagEqualTo("0").andIsSignEqualTo("1");
					List<ActivityArea> activityAreaList = activityAreaService.selectByExample(activityAreaExample);
					for (ActivityArea activityArea:activityAreaList) {
						ActivityExample activityExample = new ActivityExample();
						activityExample.createCriteria().andActivityAreaIdEqualTo(activityArea.getId()).andDelFlagEqualTo("0").andIsSignEqualTo("1");
						List<Activity> activitieList = activityService.selectByExample(activityExample);
						for (Activity activity:activitieList) {
								String pic = activityService.addTag(activity.getEntryPic(),activityGroup.getSignPic());
								activity.setIsSign("1");
								activity.setEntryPic(pic);
								activityService.updateByPrimaryKeySelective(activity); //修改活动
						}
							String pic = activityService.addTag(activityArea.getEntryPic(),activityGroup.getSignPic());
							activityArea.setEntryPic(pic);
							activityArea.setIsSign("1");
							activityAreaService.updateByPrimaryKeySelective(activityArea); //修改活动
					}
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		}catch (IOException e){
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_FILE_UPLOAD.getStateMessage();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}


	/**
	 * 校验特卖分组中增加/修改时的主会场ID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activityNew/isActivityAreaId.shtml")
	public ResponseMsg isActivityAreaId(HttpServletRequest request) {
		if(!StringUtil.isEmpty(request.getParameter("productWkLinkId"))) {
			ActivityAreaExample activityAreaExample = new ActivityAreaExample();
			activityAreaExample.createCriteria().andIdEqualTo(Integer.parseInt(request.getParameter("productWkLinkId"))).andDelFlagEqualTo("0");
			Integer totalCount=activityAreaService.countByExample(activityAreaExample);
		//	ActivityArea activityarea = activityAreaService.selectByPrimaryKey(Integer.parseInt(request.getParameter("productWkLinkId")));
			if (totalCount==0){
				return  new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}else {
				return  new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
	}

	//删除特卖数据
	@ResponseBody
	@RequestMapping(value = "/activityNew/editsaledel.shtml")
	public Map<String, Object> saledel(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));

			ActivityGroup agp=new ActivityGroup();
			agp.setId(id);
			agp.setUpdateBy(staffId);
			agp.setUpdateDate(new Date());
			agp.setDelFlag("1");
			activityGroupSerivce.updateByPrimaryKeySelective(agp);

		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateSeqNo   
	 * @Description TODO(修改排序值)   
	 * @author pengl
	 * @date 2018年2月24日 上午11:49:20
	 */
	@ResponseBody
	@RequestMapping("/activityNew/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "200");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer activityId = Integer.valueOf(request.getParameter("activityId"));
			Integer seqNo = Integer.valueOf(request.getParameter("seqNo"));
			Activity activity = new Activity();
			activity.setId(activityId);
			activity.setSeqNo(seqNo);
			activity.setUpdateBy(staffId);
			activity.setUpdateDate(new Date());
			activityService.updateByPrimaryKeySelective(activity);
		} catch (NumberFormatException e) {
			resMap.put("returnCode", "9999");
			resMap.put("returnMsg", "系统错误");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title addAdInfo   
	 * @Description TODO(推荐到首页)   
	 * @author pengl
	 * @date 2018年2月24日 下午3:35:41
	 */
	@ResponseBody
	@RequestMapping("/activityNew/addAdInfo.shtml")
	public Map<String, Object> addAdInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "200");
		resMap.put("returnMsg", "成功");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer activityId = Integer.valueOf(request.getParameter("activityId"));
			ActivityNewCustom activityNewCustom = activityService.selectByCustomPrimaryKey(activityId);
			Date autoUpDate = null; //自动上架时间
			Date autoDownDate = activityNewCustom.getActivityEndTime(); //自动下架时间
			if(activityNewCustom.getActivityBeginTime() != null && activityNewCustom.getActivityBeginTime().getTime() > new Date().getTime() ) {
				autoUpDate = activityNewCustom.getActivityBeginTime();
			}else {
				autoUpDate = new Date();
			}
//			Integer seqNo = activityNewCustom.getSeqNo();
			Integer productTypeId = activityNewCustom.getProductTypeId(); //一级类目ID
			if(productTypeId != null) {
				AdInfoCustomExample adInfoCustomExample = new AdInfoCustomExample();
				AdInfoCustomExample.AdInfoCustomCriteria adInfoCustomCriteria = adInfoCustomExample.createCriteria();
				adInfoCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("2").andPositionEqualTo("9")
					.andCatalogEqualTo("1").andLinkTypeEqualTo("2").andStatusEqualTo("1");
				adInfoCustomCriteria.andActivityStatus(productTypeId); //一级类目，活动状态=通过
//				adInfoCustomCriteria.andAutoUpDateLessThanOrEqualTo(autoUpDate); //上架时间小于此时间
//				adInfoCustomCriteria.andAutoDownDateGreaterThanOrEqualTo(autoUpDate); //下架时间大于此时间
				adInfoCustomCriteria.andActivityTime(sdf.format(autoUpDate));
				Integer totalCount = adInfoService.countAdInfoCustomByExample(adInfoCustomExample);
				if(totalCount >= 100) {
					resMap.put("returnCode", "9999");
					resMap.put("returnMsg", "对不起，"+sdf.format(autoUpDate)+"推荐数量已达到100个。");
				}else {
					AdInfo adInfo = new AdInfo();
					adInfo.setType("2"); //2 推荐活动
					adInfo.setCatalog("1"); //1 首页
					adInfo.setPosition("9"); //9 推荐区域
					adInfo.setLinkType("2"); //2 活动
					adInfo.setLinkId(activityId); 
					adInfo.setStatus("1"); //1 上架
					adInfo.setAutoUpDate(autoUpDate); 
					adInfo.setAutoDownDate(autoDownDate);
//					adInfo.setSeqNo(seqNo);
					adInfo.setCreateBy(staffId);
					adInfo.setCreateDate(new Date());
					adInfoService.insertSelective(adInfo);
				}
			}else {
				resMap.put("returnCode", "9999");
				resMap.put("returnMsg", "一级类目ID为空");
			}
		} catch (NumberFormatException e) {
			resMap.put("returnCode", "9999");
			resMap.put("returnMsg", "系统错误");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title activityAuditList   
	 * @Description TODO(设计快速审核)   
	 * @author pengl
	 * @date 2018年3月2日 下午2:36:04
	 */
	@ResponseBody
	@RequestMapping("/activityNew/activityAuditList.shtml")
	public Map<String, Object> activityAuditList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityNewCustom> dataList = null;
		Integer totalCount = 0;
		try {
			String statusAudit = request.getParameter("statusAudit");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActivityCustomExample activityCustomExample = new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
			if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("1")) { //设计快速审核
				activityCustomCriteria.andDelFlagEqualTo("0");
				activityCustomCriteria.andDataSource(); //品牌特卖   和   品牌会场
				activityCustomCriteria.andOperateAuditStatusEqualTo("2"); //专员审核状态=通过
				List<String> activityStatusList = new ArrayList<String>();
				activityStatusList.add("4");
				activityStatusList.add("1");
				activityCustomCriteria.andStatusNotIn(activityStatusList); //活动状态！=驳回 且  活动状态！=待提报
				activityCustomCriteria.andActivityEndTimeGreaterThanNow(); //活动未结束
				activityCustomExample.setOrderByClause(" ba.submit_time desc"); //按首次提报时间 倒序
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
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					activityCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				//一级类目
				if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
					activityCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
				}
			}
			
			//二级类目
			if(!StringUtil.isEmpty(request.getParameter("productTypeSecondId"))) {
				activityCustomCriteria.andProductTypeSecondIdEqualTo(Integer.parseInt(request.getParameter("productTypeSecondId")));
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
			//是否标志
			if(!StringUtil.isEmpty(request.getParameter("isSign"))) {
				activityCustomCriteria.andActivityAreaIsSignEqualTo(request.getParameter("isSign"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				activityCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}
			activityCustomExample.setLimitSize(page.getLimitSize());
			activityCustomExample.setLimitStart(page.getLimitStart());
			totalCount = activityService.countActivityCustomByExample(activityCustomExample);
			if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("1")) { //设计快速审核
				dataList = preferentialIdGroupManager(activityService.selectByCustomExample(activityCustomExample));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title preferentialIdGroupManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年3月5日 下午5:21:14
	 */
	public List<ActivityNewCustom> preferentialIdGroupManager(List<ActivityNewCustom> objectList) {
		for(ActivityNewCustom activityNewCustom : objectList) {
			if(!StringUtil.isEmpty(activityNewCustom.getPreferentialIdGroup())) {
				List<Integer> idList = new ArrayList<Integer>();
				for(String id : activityNewCustom.getPreferentialIdGroup().split(",")) {
					idList.add(Integer.parseInt(id));
				}
				if(!StringUtil.isEmpty(activityNewCustom.getPreferentialType()) && "1".equals(activityNewCustom.getPreferentialType())) { //优惠券
					CouponExample couponExample = new CouponExample();
					CouponExample.Criteria couponCriteria = couponExample.createCriteria();
					couponCriteria.andDelFlagEqualTo("0").andIdIn(idList);
					List<Coupon> couponList = couponService.selectByExample(couponExample);
					StringBuffer couponName = new StringBuffer();
					for (int i = 0; i < couponList.size(); i++) {
						couponName.append("面额"+couponList.get(i).getMoney()+"元"+" ，使用条件"+couponList.get(i).getMinimum()+"元");
						if(i < couponList.size()-1) {
							couponName.append("；");
						}
					}
					activityNewCustom.setRemarks(couponName==null?"":couponName.toString());
				}else if(!StringUtil.isEmpty(activityNewCustom.getPreferentialType()) && "2".equals(activityNewCustom.getPreferentialType())) { //满减
					FullCutExample fullCutExample = new FullCutExample();
					FullCutExample.Criteria fullCutCriteria = fullCutExample.createCriteria();
					fullCutCriteria.andDelFlagEqualTo("0").andIdIn(idList);
					List<FullCut> fullCutList = fullCutService.selectByExample(fullCutExample);
					StringBuffer fullCutName = new StringBuffer();
					for (int i = 0; i < fullCutList.size(); i++) {
						if(!StringUtil.isEmpty(fullCutList.get(i).getRule())) {
							String[] ruleStrs = fullCutList.get(i).getRule().split("\\|");
							for (int j = 0; j < ruleStrs.length; j++) {
								String[] rules = ruleStrs[i].split(",");
								fullCutName.append("满"+rules[0]+"元减"+rules[1]+"元");
								if(j < ruleStrs.length-1) {
									fullCutName.append("；");
								}
							}
						}
					}
					activityNewCustom.setRemarks(fullCutName==null?"":fullCutName.toString());
				}else if(!StringUtil.isEmpty(activityNewCustom.getPreferentialType()) && "3".equals(activityNewCustom.getPreferentialType())) { //满赠
					FullGiveExample fullGiveExample = new FullGiveExample();
					FullGiveExample.Criteria fullGiveCriteria = fullGiveExample.createCriteria();
					fullGiveCriteria.andDelFlagEqualTo("0").andIdIn(idList);
					List<FullGive> fullGiveList = fullGiveService.selectByExample(fullGiveExample);
					StringBuffer fullGiveName = new StringBuffer();
					for (int i = 0; i < fullGiveList.size(); i++) {
						if(!StringUtil.isEmpty(fullGiveList.get(i).getType()) && "1".equals(fullGiveList.get(i).getType())) { //满额赠
							Integer minimum = fullGiveList.get(i).getMinimum().intValue();
							fullGiveName.append("满"+minimum+"元赠：");
						}
						StringBuffer couponName = new StringBuffer();
						if(!StringUtil.isEmpty(fullGiveList.get(i).getCouponFlag()) && "1".equals(fullGiveList.get(i).getCouponFlag())) { //是否赠送优惠劵
							if(!StringUtil.isEmpty(fullGiveList.get(i).getCouponIdGroup())) {
								List<Integer> couponIdList = new ArrayList<Integer>();
								for(String id : fullGiveList.get(i).getCouponIdGroup().split(",")) {
									couponIdList.add(Integer.parseInt(id));
								}
								CouponExample couponExample = new CouponExample();
								CouponExample.Criteria couponCriteria = couponExample.createCriteria();
								couponCriteria.andDelFlagEqualTo("0").andIdIn(couponIdList);
								List<Coupon> couponList = couponService.selectByExample(couponExample);
								for (int j = 0; j < couponList.size(); j++) {
									couponName.append(couponList.get(j).getName());
									if(i < couponList.size()-1) {
										couponName.append("；");
									}
								}
								fullGiveName.append(couponName);
							}
						}
						if(!StringUtil.isEmpty(fullGiveList.get(i).getIntegralFlag()) && "1".equals(fullGiveList.get(i).getIntegralFlag())) { //是否赠送金币
							Integer integral = fullGiveList.get(i).getIntegral().intValue();
							if(couponName != null) {
								fullGiveName.append("；");
							}
							fullGiveName.append("金币"+integral);
						}
					}
					activityNewCustom.setRemarks(fullGiveName==null?"":fullGiveName.toString());
				}else if(!StringUtil.isEmpty(activityNewCustom.getPreferentialType()) && "4".equals(activityNewCustom.getPreferentialType())) { //多买优惠
					FullDiscountExample fullDiscountExample = new FullDiscountExample();
					FullDiscountExample.Criteria fullDiscountCriteria = fullDiscountExample.createCriteria();
					fullDiscountCriteria.andDelFlagEqualTo("0").andIdIn(idList);
					List<FullDiscount> fullDiscountList = fullDiscountService.selectByExample(fullDiscountExample);
					StringBuffer fullDiscountName = new StringBuffer();
					for (int i = 0; i < fullDiscountList.size(); i++) {
						if(!StringUtil.isEmpty(fullDiscountList.get(i).getType()) && "1".equals(fullDiscountList.get(i).getType())) { //满M件减N件
							if(!StringUtil.isEmpty(fullDiscountList.get(i).getRule())) {
								String[] rules = fullDiscountList.get(i).getRule().split(",");
								fullDiscountName.append("满"+rules[0]+"件减"+rules[1]+"件");
								if(i < fullDiscountList.size()-1) {
									fullDiscountName.append("；");
								}
							}
						}else if(!StringUtil.isEmpty(fullDiscountList.get(i).getType()) && "2".equals(fullDiscountList.get(i).getType())) { //M元任选N件
							if(!StringUtil.isEmpty(fullDiscountList.get(i).getRule())) {
								String[] rules = fullDiscountList.get(i).getRule().split(",");
								fullDiscountName.append(rules[0]+"元任选"+rules[1]+"件");
								if(i < fullDiscountList.size()-1) {
									fullDiscountName.append("；");
								}
							}
						}else if(!StringUtil.isEmpty(fullDiscountList.get(i).getType()) && "3".equals(fullDiscountList.get(i).getType())) { //M件N折
							if(!StringUtil.isEmpty(fullDiscountList.get(i).getRule())) {
								String[] ruleStrs = fullDiscountList.get(i).getRule().split("\\|");
								for (int j = 0; j < ruleStrs.length; j++) {
									String[] rules = ruleStrs[j].split(",");
									fullDiscountName.append(rules[0]+"件"+rules[1]+"折");
									if(j < ruleStrs.length-1) {
										fullDiscountName.append("；");
									}
								}
								if(i < fullDiscountList.size()-1) {
									fullDiscountName.append("；");
								}
							}
						}
					}
					activityNewCustom.setRemarks(fullDiscountName==null?"":fullDiscountName.toString());
				}
			}else {
				activityNewCustom.setRemarks("");
			}
		}
		return objectList;
	}
	
	/**
	 * 
	 * @Title getActivity   
	 * @Description TODO(查看驳回详情)   
	 * @author pengl
	 * @date 2018年3月5日 下午5:21:47
	 */
	@RequestMapping("/activityNew/seeDesignReject.shtml")
	public ModelAndView seeDesignReject(Integer activityId) {
		ModelAndView m = new ModelAndView("/activityNew/seeDesignReject");
		Activity activity = activityService.selectByPrimaryKey(activityId);
		m.addObject("activity", activity);
		return m;
	}
	
	/**
	 * 
	 * @Title auditDesignPass   
	 * @Description TODO(设计快速审核   通过   驳回)   
	 * @author pengl
	 * @date 2018年3月5日 下午6:09:29
	 */
	@ResponseBody
	@RequestMapping("/activityNew/auditDesignPass.shtml")
	public Map<String, Object> auditDesign(HttpServletRequest request, Integer activityId, String designAuditStatus, String designRejectReason, String designAuditRemarks){
		Map<String, Object> map = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			activityService.auditDesign(staffBean, activityId, designAuditStatus, designRejectReason, designAuditRemarks);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		map.put(this.JSON_RESULT_CODE, code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return map;
	}
	
	/**
	 * 
	 * @Title auditDesignReject   
	 * @Description TODO(设计快速审核   驳回)   
	 * @author pengl
	 * @date 2018年3月5日 下午6:21:43
	 */
	@RequestMapping("/activityNew/auditDesignReject.shtml")
	public ModelAndView auditDesignReject(HttpServletRequest request, Integer activityId) {
		ModelAndView m = new ModelAndView("/activityNew/auditDesignReject");
		Activity activity = activityService.selectByPrimaryKey(activityId);
		m.addObject("activity", activity);
		return m;
	}
	
	/**
	 * 
	 * @Title saveAuditDesignReject   
	 * @Description TODO(保存设计快速审核   驳回)   
	 * @author pengl
	 * @date 2018年3月6日 上午10:28:26
	 */
	@RequestMapping("/activityNew/saveAuditDesignReject.shtml")
	public ModelAndView saveAuditDesignReject(HttpServletRequest request, Integer activityId, String designAuditStatus, String designRejectReason, String designAuditRemarks) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			activityService.auditDesign(staffBean, activityId, designAuditStatus, designRejectReason, designAuditRemarks);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title productTypelist   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月11日 下午4:29:20
	 */
	@ResponseBody
	@RequestMapping("/activityNew/productTypelist.shtml")
	public Map<String, Object> productTypelist(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String productTypeId = request.getParameter("productTypeId");
			if(!StringUtil.isEmpty(productTypeId)) {
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andParentIdEqualTo(Integer.parseInt(productTypeId)).andStatusEqualTo("1").andDelFlagEqualTo("0");
				productTypeExample.setOrderByClause(" seq_no");
				List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
				map.put("productTypeList", productTypeList);
			}else {
				code = "9999";
				msg = "一级类目ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
	//特卖活动品牌团首页
	@RequestMapping(value = "/activityNew/activityBranding.shtml")
	public ModelAndView activityBrandGroup(HttpServletRequest request) {
		String rtPage = "/activityNew/ActivityBrandGroupList";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList);
		return new ModelAndView(rtPage, resMap);
	}
	
	//特卖活动品牌团列表数据
	@RequestMapping(value = "/activityNew/activityBrandingdata.shtml")
	@ResponseBody
	public Map<String, Object> activityBrandGrouplist(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		List<ActivityBrandGroupCustom> activityBrandGrouplist = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ActivityBrandGroupCustomExample activityBrandGroupExample = new ActivityBrandGroupCustomExample();
			ActivityBrandGroupCustomExample.Criteria activitybrandGroupExampleCriteria = activityBrandGroupExample.createCriteria();
			activitybrandGroupExampleCriteria.andDelFlagEqualTo("0");
			activityBrandGroupExample.setOrderByClause("a.create_date desc");
			if (!StringUtil.isEmpty(request.getParameter("name"))) {
				activitybrandGroupExampleCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				activitybrandGroupExampleCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createDateBegin")) && !StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
				activitybrandGroupExampleCriteria.andCreateDateBetween(dateFormat.parse(request.getParameter("createDateBegin")+" 00:00:00"),dateFormat.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				activitybrandGroupExampleCriteria.andAppCatalogEqualTo(request.getParameter("productTypeId"));
			}
			totalCount=activitybrandGroupService.countByCustomExample(activityBrandGroupExample);
			activityBrandGroupExample.setLimitStart(page.getLimitStart());
			activityBrandGroupExample.setLimitSize(page.getLimitSize());
			activityBrandGrouplist=activitybrandGroupService.selectByCustomExample(activityBrandGroupExample);

		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", activityBrandGrouplist);
		resMap.put("Total", totalCount);
		return resMap;	
	}
	
	//特卖活动品牌团添加
	@RequestMapping(value = "/activityBrandGroupnew/addActivityBrandGroup.shtml")
	public ModelAndView addActivityBrandGroup(HttpServletRequest request) {
		String rtPage = "/activityNew/addactivityBrandGroup";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if(!StringUtil.isEmpty(request.getParameter("activitybrandgroupid"))){//ID不为空获取数据
				ActivityBrandGroup activitybrandGroup = activitybrandGroupService.selectByPrimaryKey(Integer.valueOf(request.getParameter("activitybrandgroupid")));
				resMap.put("activitybrandGroup", activitybrandGroup);
			}
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
			List<ProductType> producttypeList = productTypeService.selectByExample(productTypeExample);
			resMap.put("producttypeList", producttypeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping("/activitybrandGroupadd/activitybrandGroupadds.shtml")
	public ModelAndView saveSeckillBrandGroup(HttpServletRequest request, ActivityBrandGroup activityBrandGroup) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(activityBrandGroup.getId() == null) {//添加数据
				String productTypeId = request.getParameter("productTypeId");
				activityBrandGroup.setAppCatalog(productTypeId);
				activityBrandGroup.setStatus("0");
				activityBrandGroup.setCreateDate(date);
				activityBrandGroup.setCreateBy(staffID);
				activityBrandGroup.setUpdateDate(date);
				activityBrandGroup.setDelFlag("0");
				activitybrandGroupService.insert(activityBrandGroup);
			}else {//更新数据
				String productTypeId = request.getParameter("productTypeId");
				activityBrandGroup.setAppCatalog(productTypeId);
				activityBrandGroup.setUpdateDate(date);
				activityBrandGroup.setUpdateBy(staffID);
				activitybrandGroupService.updateByPrimaryKeySelective(activityBrandGroup);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	//删除特卖活动品牌团
	@ResponseBody
	@RequestMapping(value = "/activityNew/delBrandGroup.shtml")
	public Map<String, Object> delBrandGroup(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			ActivityBrandGroup delBrandGroup=new ActivityBrandGroup();
			delBrandGroup.setId(id);
			delBrandGroup.setUpdateBy(staffId);
			delBrandGroup.setUpdateDate(new Date());
			delBrandGroup.setDelFlag("1");
			activitybrandGroupService.updateByPrimaryKeySelective(delBrandGroup);
			ActivityBrandGroupActivity activitybrandGroupActivity=new ActivityBrandGroupActivity();
			activitybrandGroupActivity.setUpdateBy(staffId);
			activitybrandGroupActivity.setUpdateDate(new Date());
			activitybrandGroupActivity.setDelFlag("1");
			ActivityBrandGroupActivityCustomExample activityBrandGroupActivityCustomExample = new ActivityBrandGroupActivityCustomExample();
			activityBrandGroupActivityCustomExample.createCriteria().andDelFlagEqualTo("0").andActivityBrandGroupIdEqualTo(id);
			activitybrandGroupActivityService.updateByExampleSelective(activitybrandGroupActivity, activityBrandGroupActivityCustomExample);
			
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//停用或启用特卖活动品牌团
	@ResponseBody
	@RequestMapping(value = "/activityNew/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			String status=request.getParameter("status");
			ActivityBrandGroup updateBrandGroup=new ActivityBrandGroup();
			updateBrandGroup.setId(id);
			updateBrandGroup.setStatus(status);
			updateBrandGroup.setUpdateBy(staffId);
			updateBrandGroup.setUpdateDate(new Date());
			activitybrandGroupService.updateByPrimaryKeySelective(updateBrandGroup);

		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//特卖品牌团-特卖关联表
	@RequestMapping("/activityNew/activityBrandGroupActivity.shtml")
	public ModelAndView activityBrandGroupActivity(HttpServletRequest request, Integer activitybrandgroupid) {	
		String rtPage = "/activityNew/activityBrandgroupactivityList";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> producttypeList = productTypeService.selectByExample(productTypeExample);	
		resMap.put("producttypeList", producttypeList);
		resMap.put("activitybrandgroupid", activitybrandgroupid);//特卖品牌团ID
		return new ModelAndView(rtPage, resMap);
	}
	
	//特卖品牌团-特卖关联表列表数据
	@ResponseBody
	@RequestMapping("/activityNew/activitybrandgroupactivitylist.shtml")
	public Map<String, Object> activitybrandgroupactivitylist(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<ActivityBrandGroupActivityCustom> dataList = null;
		try {
			ActivityBrandGroupActivityCustomExample activityBrandGroupActivityCustomExample = new ActivityBrandGroupActivityCustomExample();
			ActivityBrandGroupActivityCustomExample.ActivityBrandGroupActivityCustomCriteria activityBrandGroupActivityCustomCriteria = activityBrandGroupActivityCustomExample.createCriteria();
			activityBrandGroupActivityCustomCriteria.andDelFlagEqualTo("0");
			activityBrandGroupActivityCustomCriteria.andactivityAreaBysourceEqualTo("2");
			activityBrandGroupActivityCustomExample.setOrderByClause("(select b.id from bu_activity_area ba,bu_activity b where ba.id=a.activity_area_id and b.activity_area_id=ba.id and ba.del_flag='0' and b.del_flag='0') desc");
			
			if(!StringUtil.isEmpty(request.getParameter("activitybrandgroupid"))) {
				activityBrandGroupActivityCustomCriteria.andActivityBrandGroupIdEqualTo(Integer.valueOf(request.getParameter("activitybrandgroupid")));
			 }
			if (!StringUtil.isEmpty(request.getParameter("activityId"))) {
				activityBrandGroupActivityCustomCriteria.andactivityIdEqualTo(Integer.valueOf(request.getParameter("activityId")));
			 }
			if (!StringUtil.isEmpty(request.getParameter("activityName"))) {
				activityBrandGroupActivityCustomCriteria.andactivityNameEqualTo(request.getParameter("activityName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				activityBrandGroupActivityCustomCriteria.andShopNameOrCompanyNameLike(request.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("activityProductType"))) {
				activityBrandGroupActivityCustomCriteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("activityProductType")));
			}
			if (!StringUtil.isEmpty(request.getParameter("activityProductType"))) {
				activityBrandGroupActivityCustomCriteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("activityProductType")));
			}
			if (!StringUtil.isEmpty(request.getParameter("activityStatusDesc"))) {
				activityBrandGroupActivityCustomCriteria.andactivityStatusCustom(Integer.valueOf(request.getParameter("activityStatusDesc")));
			}
			if(!StringUtil.isEmpty(request.getParameter("activityareaBeginTtime")) && !StringUtil.isEmpty(request.getParameter("activityareaBeginTtimes"))) {
				String activityBeginTimeA= request.getParameter("activityareaBeginTtime")+" 00:00:00";
				String activityBeginTimeB= request.getParameter("activityareaBeginTtimes")+" 23:59:59";
				activityBrandGroupActivityCustomCriteria.andActivityBeginTimeBetweens(activityBeginTimeA,activityBeginTimeB);
			}
			if(!StringUtil.isEmpty(request.getParameter("activityareaEndTime")) && !StringUtil.isEmpty(request.getParameter("activityareaEndTimes"))) {
				String activityEndTimeA = request.getParameter("activityareaEndTime") + " 00:00:00";
				String activityEndTimeB = request.getParameter("activityareaEndTimes") + " 23:59:59";
				activityBrandGroupActivityCustomCriteria.andActivityEndTimeBetweens(activityEndTimeA, activityEndTimeB);
			} 
			activityBrandGroupActivityCustomExample.setLimitStart(page.getLimitStart());
			activityBrandGroupActivityCustomExample.setLimitSize(page.getLimitSize());
			
			totalCount = activitybrandGroupActivityService.countByCustomExample(activityBrandGroupActivityCustomExample);
			dataList = activitybrandGroupActivityService.selectByCustomExample(activityBrandGroupActivityCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//活动
	@RequestMapping("/activityNew/addactivityarealist.shtml")
	public ModelAndView addactivityarealist(HttpServletRequest request, Integer activitybrandgroupid) {
		String rtPage="/activityNew/addactivityBrandgroupactivityList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypeList = productTypeService.selectByExample(example);
		resMap.put("productTypeList", productTypeList);
		resMap.put("activitybrandgroupid", activitybrandgroupid);//特卖品牌团ID
		return new ModelAndView(rtPage, resMap);
	}
	//活动数据列表
	@ResponseBody
	@RequestMapping("/activityNew/activityareatdata.shtml")
	public Map<String, Object> activityarealist(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<ActivityNewCustom> dataList = null;
		try {
			ActivityCustomExample activityCustomExample = new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
			activityCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6");
			activityCustomExample.setOrderByClause("ba.id desc");
			activityCustomCriteria.andActivityAreaBysourceEqualTo("2");
			activityCustomCriteria.andActivityAreaBysourceNotEqualTo();
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				activityCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			if (!StringUtil.isEmpty(request.getParameter("name"))) {
				activityCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				activityCustomCriteria.andShopNameOrCompanyNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				activityCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
			}
		
			if(!StringUtil.isEmpty(request.getParameter("status"))) { 		
			   activityCustomCriteria.andStatusCustom(Integer.parseInt(request.getParameter("status")));			
			}
			if(!StringUtil.isEmpty(request.getParameter("activityBeginTime")) && !StringUtil.isEmpty(request.getParameter("activityBeginTimes"))) {
				String activityBeginTimeA= request.getParameter("activityBeginTime")+" 00:00:00";
				String activityBeginTimeB= request.getParameter("activityBeginTimes")+" 23:59:59";
				activityCustomCriteria.andActivityBeginTimeBetweens(activityBeginTimeA,activityBeginTimeB);
			}
			if(!StringUtil.isEmpty(request.getParameter("activityEndTime")) && !StringUtil.isEmpty(request.getParameter("activityEndTimes"))) {
				String activityEndTimeA = request.getParameter("activityEndTime") + " 00:00:00";
				String activityEndTimeB = request.getParameter("activityEndTimes") + " 23:59:59";
				activityCustomCriteria.andActivityEndTimeBetweens(activityEndTimeA, activityEndTimeB);
			} 
			activityCustomExample.setLimitSize(page.getLimitSize());
			activityCustomExample.setLimitStart(page.getLimitStart());
			totalCount = activityService.countActivityCustomByExample(activityCustomExample);
			dataList = activityService.selectByCustomExample(activityCustomExample);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//添加至特卖品牌团-特卖关联表
	@ResponseBody
	@RequestMapping("/activityNew/addactivityBrandgroupactivitylist.shtml")
	public Map<String, Object> addSeckillBrandGroupProduct(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String activityareaid = request.getParameter("activityareaid");//活动会场ID
			String activitybrandgroupid = request.getParameter("activitybrandgroupid");//特卖品牌团ID
			if(!StringUtil.isEmpty(activityareaid) && !StringUtil.isEmpty(activitybrandgroupid)) {
				activitybrandGroupService.addActivityBrandGroupactivity(staffID, activityareaid, activitybrandgroupid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	//删除
	@ResponseBody
	@RequestMapping("/activityNew/delactivityBrandGroupActivity.shtml")
	public Map<String, Object> delSeckillBrandGroupProduct(HttpServletRequest request, String activitybrandgroupactivityids) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(!StringUtil.isEmpty(activitybrandgroupactivityids)) {
				String[] strs = activitybrandgroupactivityids.split(",");
				List<Integer> activitybrandgroupactivityidList = new ArrayList<Integer>();
				for(String activitybrandgroupactivityid : strs) {
					activitybrandgroupactivityidList.add(Integer.parseInt(activitybrandgroupactivityid));
				}
				ActivityBrandGroupActivityCustomExample activitybrandGroupactivityCustomExample = new ActivityBrandGroupActivityCustomExample();
				activitybrandGroupactivityCustomExample.createCriteria().andIdIn(activitybrandgroupactivityidList);
				ActivityBrandGroupActivity activitybrandGroupactivity = new ActivityBrandGroupActivity();
				activitybrandGroupactivity.setUpdateBy(staffID);
				activitybrandGroupactivity.setUpdateDate(new Date());
				activitybrandGroupactivity.setDelFlag("1");
				activitybrandGroupActivityService.updateByExampleSelective(activitybrandGroupactivity, activitybrandGroupactivityCustomExample);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("/activityNew/toEditRecDate.shtml")
	public ModelAndView toEditRecDate(HttpServletRequest request) throws ParseException {
		String rtPage="/activityNew/toEditRecDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("couponIds", request.getParameter("couponIds"));//优惠券ids
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtils.isEmpty(request.getParameter("recBeginDate"))){
			resMap.put("recBeginDate", sdf.parse(request.getParameter("recBeginDate")));
		}
		if(!StringUtils.isEmpty(request.getParameter("recEndDate"))){
			resMap.put("recEndDate", sdf.parse(request.getParameter("recEndDate")));
		}
		ActivityNewCustom activityNewCustom = activityService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("activityBeginTime", activityNewCustom.getActivityBeginTime());
		resMap.put("activityEndTime", activityNewCustom.getActivityEndTime());
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存优惠券领取时间
	@ResponseBody
	@RequestMapping("/activityNew/saveRecDate.shtml")
	public Map<String, Object> saveRecDate(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "0000";
		String msg = "操作成功！";
		try {
			String couponIds = request.getParameter("couponIds");
			String recBeginDate = request.getParameter("recBeginDate");
			String recEndDate = request.getParameter("recEndDate");
			couponService.updateByCouponIds(couponIds,recBeginDate,recEndDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "4004";
			msg = "操作失败！";
		}
		map.put("returnCode", code);
		map.put("msg", msg);
		return map;
	}


	@RequestMapping("/activityNew/activityTrafficStatistics.shtml")
	public ModelAndView activityTrafficStatistics(HttpServletRequest request) throws ParseException {
		String rtPage="/activityNew/activityTrafficStatistics";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date d=cal.getTime();
		String date = simpleDateFormat.format(d);
		resMap.put("dateBegin", date);
		resMap.put("dateEnd", date);
		String dateNow = simpleDateFormat.format(new Date());
		resMap.put("dateNow", dateNow);
		//获取一级目录
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 活动流量统计数据
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activityNew/activityTrafficStatisticsData.shtml")
	public Map<String, Object> activityTrafficStatisticsData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<HashMap<String,Object>> dataList = null;
		try {
			HashMap<String, Object> paraMap = new HashMap<>();
			paraMap.put("limitSize",page.getLimitSize());
			paraMap.put("limitStart",page.getLimitStart());
			if(!StringUtils.isEmpty(request.getParameter("activityId"))){
				paraMap.put("activityId",request.getParameter("activityId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				paraMap.put("name","%"+request.getParameter("name")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
				paraMap.put("mchtCode",request.getParameter("mchtCode"));
			}
			if(!StringUtils.isEmpty(request.getParameter("mchtName"))){
				paraMap.put("mchtName","%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("productTypeId"))){
				paraMap.put("productTypeId",request.getParameter("productTypeId"));
			}
			String selectType = request.getParameter("selectType");
			if("1".equals(selectType)){
				String dateNow =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				paraMap.put("beginTime",dateNow+" 00:00:00");
				paraMap.put("endTime",dateNow+" 23:59:59");
				totalCount = activityService.countActivityTrafficStatisticsRealTime(paraMap);
				dataList = activityService.selectActivityTrafficStatisticsRealTime(paraMap);
			}else {
				if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
					paraMap.put("beginTime",request.getParameter("beginTime")+" 00:00:00");
				}
				if(!StringUtils.isEmpty(request.getParameter("endTime"))){
					paraMap.put("endTime",request.getParameter("endTime")+" 23:59:59");
				}
				totalCount = typeColumnPvStatisticalService.countActivityTrafficStatistics(paraMap);
				dataList = typeColumnPvStatisticalService.selectActivityTrafficStatistics(paraMap);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}


	    //品牌特卖排期更新优惠券数据
		@ResponseBody
		@RequestMapping(value = "/activityNew/updateCuPon.shtml")
		public Map<String, Object> updateCuPon(HttpServletRequest request, Integer id, String Name, String Value) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			try {
				 Coupon coupon=couponService.selectByPrimaryKey(id);
				 if (Name.equals("money")) {
					 coupon.setMoney(new BigDecimal(Value));
				 }else if (Name.equals("minimum")) {
					 coupon.setMinimum(new BigDecimal(Value));
				 }else if (Name.equals("grantQuantity")) {
					 coupon.setGrantQuantity(Integer.valueOf(Value));
				 }
				 coupon.setBelong("1");
				 coupon.setUpdateBy(staffID);
				 coupon.setUpdateDate(new Date());
				 couponService.updateByPrimaryKey(coupon);

				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {

				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return resMap;
		}


		         //品牌特卖排期更新满减数据
				@ResponseBody
				@RequestMapping(value = "/activityNew/updateFullCut.shtml")
				public Map<String, Object> updateFullCut(HttpServletRequest request, Integer id) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						String jsonFullCut = request.getParameter("jsonFullCut");

						JSONArray fullCutList = JSONArray.fromObject(jsonFullCut);
						String sumFlag = null;
						FullCut fullCut=fullCutService.selectByPrimaryKey(id);
						String rule = "";
						for (int i = 0; i < fullCutList.size(); i++) {
							String fullName = fullCutList.getJSONObject(i).getString("fullName");
							String reduceName = fullCutList.getJSONObject(i).getString("reduceName");
							sumFlag = fullCutList.getJSONObject(i).getString("sumFlag");
							if(!"".equals(rule)){
								rule = rule + "|";
							}
							rule += fullName +"," + reduceName;
						}
						fullCut.setRule(rule);
						fullCut.setSumFlag(sumFlag);
						fullCut.setBelong("1");//平台承担方
						fullCut.setUpdateBy(staffID);
						fullCut.setUpdateDate(new Date());
						fullCutService.updateByPrimaryKeySelective(fullCut);

						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {

						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return resMap;
				}
}
