package com.jf.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAuditLog;
import com.jf.entity.ActivityAuditLogCustom;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityCustomExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import com.jf.entity.FullCutCustom;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullGive;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysStatus;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityAuditLogService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import com.jf.service.CouponService;
import com.jf.service.FullCutService;
import com.jf.service.FullDiscountService;
import com.jf.service.FullGiveService;
import com.jf.service.MchtInfoService;
import com.jf.service.MsgTplService;
import com.jf.service.PlatformContactService;
import com.jf.service.PlatformMsgService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

/**
 * 活动表controller
 * @author luoyl
 * 创建时间 ：2017/4/11 9:54
 */
@Controller
public class ActivityController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4069382570128999192L;

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityAuditLogService activityAuditLogService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private FullCutService fullCutService;
	
	@Autowired
	private FullGiveService fullGiveService;
	
	@Autowired
	private FullDiscountService fullDiscountService;
	
	@Autowired
	private ActivityAreaService activityAreaService;
	
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MsgTplService msgTplService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private PlatformContactService platformContactService;

	@Resource
	private ProductTypeService productTypeService;
	
	/**
	 * 全部运营专员审核列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/allOperateAuditList.shtml")
	public String allOperateAuditList(Model model,HttpServletRequest request){
		
		//0全部运营专员1运营专员审核2运营专员驳回3设计审核4法务审核5总监审核
		Integer type=Integer.valueOf(request.getParameter("type"));
		model.addAttribute("type", type);
		
		//运营专员审核状态
		List<SysStatus> operateAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY", "OPERATE_AUDIT_STATUS");
		//设计部审核状态
		List<SysStatus> designAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY", "DESIGN_AUDIT_STATUS");
		//法务审核状态
		List<SysStatus> lawAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY", "LAW_AUDIT_STATUS");
		//运营总监审核状态
		List<SysStatus> cooAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY", "COO_AUDIT_STATUS");
		
		model.addAttribute("operateAuditStatus", operateAuditStatus);
		model.addAttribute("designAuditStatus", designAuditStatus);
		model.addAttribute("lawAuditStatus", lawAuditStatus);
		model.addAttribute("cooAuditStatus", cooAuditStatus);
		
		//运营志员列表
		if(type.intValue()==0 || type.intValue()==6 || type.intValue()==1){
			PlatformContactExample platformContactExample=new PlatformContactExample();
			platformContactExample.createCriteria().andContactTypeEqualTo("2").andStatusEqualTo("0").andDelFlagEqualTo("0");
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
			model.addAttribute("platformContacts", platformContacts);
		}
		
		//类目列表
		if(type.intValue()==6 || type.intValue()==1){
			ProductTypeExample productTypeExample=new ProductTypeExample();
			productTypeExample.createCriteria().andTLevelEqualTo(1).andDelFlagEqualTo("0");
			productTypeExample.setOrderByClause(" seq_no");
			List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
			model.addAttribute("productTypes", productTypes);
		}
		
		if(type.intValue()==0){
			return "/activity/allOperateAuditList";
		}else if(type.intValue()==1){
			return "/activity/operateAuditList";
		}else if(type.intValue()==2){
			return "/activity/operateAuditRejectList";
		}else if(type.intValue()==3||type.intValue()==4){
			//设计审核、法务审核统一页面
			return "/activity/lawAuditList";
		}else if(type.intValue()==6){
			return "/activity/throughSaleList";
		}else{
			return "/activity/cooAuditList";
		}
	}
	
	@RequestMapping(value="/activity/allOperateAuditListData.shtml")
	@ResponseBody
	public Map<String, Object> getAllOperateAuditListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityCustom> dataList = null;
		Integer totalCount =0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			ActivityCustomExample activityCustomExample=new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria=activityCustomExample.createCriteria();
			activityCustomCriteria.andDelFlagEqualTo("0");
			activityCustomCriteria.andActivityAreaBySourceEqualTo("2");//品牌特卖
			activityCustomCriteria.andStatusNotEqualTo("1");
			//0全部运营专员1运营专员审核2运营专员驳回3设计审核4法务审核5总监审核
			Integer type=Integer.valueOf(request.getParameter("type"));
			
			if(type.intValue()==1||type.intValue()==2){
				//activityCustomCriteria.andStaffIdEqualTo(staffId);
				if(type.intValue()==1){
					//activityCustomCriteria.andStaffIdEqualTo(staffId).andOperateAuditStatusIsNotNull().andOperateAuditStatusNotEqualTo("0");
					activityCustomCriteria.andOperateAuditStatusIsNotNull().andOperateAuditStatusNotEqualTo("0");
				}
			}
			if(type.intValue()==2){//特卖驳回
				activityCustomCriteria.andOperateAuditStatusEqualTo("3");
			}
			if(type.intValue()==3){//设计专员审核、法务审核
				activityCustomCriteria.andDesignAuditStatusIsNotNull().andDesignAuditStatusNotEqualTo("0");
			}
			
			if(type.intValue()==4){//设计专员审核、法务审核
				activityCustomCriteria.andLawAuditStatusIsNotNull().andLawAuditStatusNotEqualTo("0");
			}
			if(type.intValue()==5){//运营总监审核
				activityCustomCriteria.andCooAuditStatusIsNotNull().andCooAuditStatusNotEqualTo("0");
			}
			
			if(type.intValue()==6){
				activityCustomCriteria.andStatusForPass();
				
				//两者都有值的取值范围
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
					activityCustomCriteria.andActivityBeginTimeGreaterThanOrEqualTo(request.getParameter("date_begin")+" 23:59:59");
				}
					
				if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
					activityCustomCriteria.andActivityEndTimeLessThanOrEqualTo(request.getParameter("date_end")+" 00:00:00");
				}
				
				if(!StringUtil.isEmpty(request.getParameter("actStatus"))){
					Integer actStatus=Integer.valueOf(request.getParameter("actStatus"));
					activityCustomCriteria.andActivityAreaStatusEqualTo(actStatus);
				}
			}else{
				activityCustomCriteria.andStatusNotEqualTo("6");
			}
			
			
			//排序
			if(type.intValue()==0||type.intValue()==1||type.intValue()==2){
				activityCustomExample.setOrderByClause("ba.update_date");
			}
			if(type.intValue()==3){
				activityCustomExample.setOrderByClause("if(ba.design_audit_status='2', '99', ba.design_audit_status), ba.update_date desc");
			}
			if(type.intValue()==4){
				activityCustomExample.setOrderByClause("if(ba.law_audit_status='2', '99', ba.law_audit_status), ba.update_date desc");
			}
			if(type.intValue()==5){
				activityCustomExample.setOrderByClause("if(ba.coo_audit_status='2', '99', ba.coo_audit_status), ba.update_date desc");
			}
			
			if(type.intValue()==6){
				activityCustomExample.setOrderByClause("IFNULL(activity_begin_time,'') desc");
			}
			
			//特卖id
			if(!StringUtils.isEmpty(request.getParameter("activityId"))){
				activityCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("activityId")));
			}
			//活动名称
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				activityCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			//商家名称
			if(!StringUtils.isEmpty(request.getParameter("shortName"))){
				activityCustomCriteria.andMchtNameLike("%"+request.getParameter("shortName")+"%");
			}
			//商家编号
			if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
				activityCustomCriteria.andMchtCodeLike("%"+request.getParameter("mchtCode")+"%");
			}
			//运营专员审核
			if(!StringUtils.isEmpty(request.getParameter("operateAuditStatus"))){
				activityCustomCriteria.andOperateAuditStatusEqualTo(request.getParameter("operateAuditStatus").toString());
			}

			//设计部审核
			if(!StringUtils.isEmpty(request.getParameter("designAuditStatus"))){
				String designAuditStatus=request.getParameter("designAuditStatus");
				if ("0".equals(designAuditStatus)){
					activityCustomCriteria.andDesignAuditNotStart();
				}else{
					activityCustomCriteria.andDesignAuditStatusEqualTo(designAuditStatus);
				}
				
			}
			//法务审核
			if(!StringUtils.isEmpty(request.getParameter("lawAuditStatus"))){
				String lawAuditStatus=request.getParameter("lawAuditStatus");
				if ("0".equals(lawAuditStatus)){
					activityCustomCriteria.andLawAuditNotStart();
				}else{
					activityCustomCriteria.andLawAuditStatusEqualTo(lawAuditStatus);
				}
			}
			//运营总监审核
			if(!StringUtils.isEmpty(request.getParameter("cooAuditStatus"))){
				String cooAuditStatus=request.getParameter("cooAuditStatus");
				if ("0".equals(cooAuditStatus)){
					activityCustomCriteria.andCooAuditNotStart();
				}else{
					activityCustomCriteria.andCooAuditStatusEqualTo(cooAuditStatus);
				}
			}
			
			//设计部、法务部审核人搜索
			if(!StringUtils.isEmpty(request.getParameter("auditName"))){
				String auditName=request.getParameter("auditName");
				activityCustomCriteria.andAuditNameEqualTo(auditName,type);
			}
			
			//平台运营专员
			if(!StringUtils.isEmpty(request.getParameter("platformContactId"))){
				activityCustomCriteria.andPlatformContactIdEqualTo(Integer.valueOf(request.getParameter("platformContactId")));
			}
			
			//类目
			if(!StringUtils.isEmpty(request.getParameter("productTypeId"))){
				activityCustomCriteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			
			activityCustomExample.setLimitSize(page.getLimitSize());
			activityCustomExample.setLimitStart(page.getLimitStart());
			totalCount=activityService.countActivityCustomByExample(activityCustomExample);
			dataList=activityService.selectActivityCustomByExample(activityCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 全部审核页面的查看详情
	 * @param model
	 * @param request
	 * @param response
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="/activity/allSeeActivityInfo.shtml")
	public String allSeeActivityInfo(Model model,HttpServletRequest request,HttpServletResponse response,Integer activityId){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ActivityCustom activityCustom=activityService.selectActivityCustomByPrimaryKey(activityId);
		model.addAttribute("activityCustom", activityCustom);
		Integer type=Integer.valueOf(request.getParameter("type"));
		model.addAttribute("type", type);
		if(type.intValue()==6){
			try {
				if(!StringUtils.isEmpty(activityCustom.getActivityEndTime())){
					Date date_01 = dateFormat.parse(dateFormat.format(activityCustom.getActivityEndTime()));
					Date date_02 = new Date();
					if(date_01.before(date_02)==true){
						model.addAttribute("activityDate", 0);
					}else{
						model.addAttribute("activityDate", 1);
					}
				}else{
					model.addAttribute("activityDate", 1);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		//审核流水表状态
//		List<SysStatus> activityAuditLogStatus = DataDicUtil.getTableStatus("BU_ACTIVITY_AUDIT_LOG", "STATUS");
		//审核流水表
		List<ActivityAuditLogCustom> activityAuditLog=activityAuditLogService.selectActivityAuditLogList(activityId);
		model.addAttribute("activityAuditLog", activityAuditLog);
		
		//优惠券
		List<CouponCustom> copuon=couponService.selectByActivityAreaIdList(activityCustom.getActivityAreaId());
		Map<String, Object> copuonMap=new HashMap<String, Object>();
		if(copuon.size()>0){
			copuonMap.put("recLimitType", copuon.get(0).getRecLimitType());
			copuonMap.put("recEach", copuon.get(0).getRecEach());
		}
		model.addAttribute("copuon", copuon);
		model.addAttribute("copuonMap", copuonMap);
		
		
		//满减
		FullCutCustom fullCutCustom=fullCutService.selectByActivityIdList(activityCustom.getActivityAreaId());
		List<Map<String, Object>> fullCutList=new ArrayList<Map<String,Object>>();
		if(fullCutCustom!=null){
			//判断是否阶梯
			if("1".equals(fullCutCustom.getLadderFlag())){
				String [] rule=fullCutCustom.getRule().split("\\|");
				for (int i = 0; i < rule.length; i++) {
					Map<String, Object> fullCut= new HashMap<String, Object>();
					String [] rules=org.springframework.util.StringUtils.split(rule[i], ",");
					fullCut.put("full", rules[0]);
					fullCut.put("cut",rules[1]);
					fullCutList.add(fullCut);
				}
			}else{
				if(!StringUtils.isEmpty(fullCutCustom.getRule())){
					String [] rules=org.springframework.util.StringUtils.split(fullCutCustom.getRule(), ",");
					fullCutCustom.setFull(rules[0]);
					fullCutCustom.setCut(rules[1]);
				}
			}
		}
		model.addAttribute("fullCutCustom", fullCutCustom);
		model.addAttribute("fullCutList", fullCutList);
		
		//满赠
		FullGive fullGive=fullGiveService.selectByActivityId(activityCustom.getActivityAreaId());
		model.addAttribute("fullGive", fullGive);
		
		//多买多送
		FullDiscount fullDiscount=fullDiscountService.selectByActivityId(activityCustom.getActivityAreaId());
		List<Map<String, Object>> fullDiscountList=new ArrayList<Map<String,Object>>();
		Map<String, Object> fullDiscountMap=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(fullDiscount)){
			if("1".equals(fullDiscount.getType())||"2".equals(fullDiscount.getType())){
				String [] ruleDiscount=org.springframework.util.StringUtils.split(fullDiscount.getRule(), ",");
				if("1".equals(fullDiscount.getType())){
					fullDiscountMap.put("fullOfOne", ruleDiscount[0]);
					fullDiscountMap.put("fullGiftsOneName", ruleDiscount[1]);
				}else{
					fullDiscountMap.put("fullOfTwo", ruleDiscount[0]);
					fullDiscountMap.put("fullGiftsTwoName", ruleDiscount[1]);
				}
			}
			if("3".equals(fullDiscount.getType())){
				String [] ruleFullDiscount=fullDiscount.getRule().split("\\|");
				for (int i = 0; i < ruleFullDiscount.length; i++) {
					String [] fullRule=org.springframework.util.StringUtils.split(ruleFullDiscount[i], ",");
					Map<String, Object> fullGiscountThree=new HashMap<String, Object>();
					fullGiscountThree.put("fullGiscountThree", fullRule[0]);
					fullGiscountThree.put("fullGiscountThreeName", fullRule[1]);
					fullDiscountList.add(fullGiscountThree);
				}
			}
			model.addAttribute("fullDiscountType", fullDiscount.getType());
		}
		
		model.addAttribute("fullDiscountMap", fullDiscountMap);
		model.addAttribute("fullDiscountList", fullDiscountList);
		
		if(type.intValue()==3||type.intValue()==4){
			int myself=0;
			int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			if (type.intValue()==3 && activityCustom.getDesignAuditBy()!=null && activityCustom.getDesignAuditBy()==staffId){
				myself=1;
			}
			if (type.intValue()==4 && activityCustom.getLawAuditBy()!=null && activityCustom.getLawAuditBy()==staffId){
				myself=1;
			}
			model.addAttribute("myself", myself);
			return "/activity/designAndLawActivityInfo";
		}else if(type.intValue()==1||type.intValue()==2){
			return "/activity/operateSeeActivityInfo";
		}else if(type.intValue()==6){
			return "/activity/throughSaleActivityInfo";
		}else{
			return "/activity/allSeeActivityInfo";
		}
	}
	
	/**
	 * 领取按钮
	 * @param request
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="/activity/receive.shtml")
	@ResponseBody
	public Map<String, Object> receive(HttpServletRequest request,Integer activityId,Integer type){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Activity activty=activityService.selectByPrimaryKey(activityId);
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			if(type.intValue()==3){//设计部
				activty.setDesignAuditBy(staffId);
				activty.setDesignAuditStatus("1");
			}else if(type.intValue()==4){//法务
				activty.setLawAuditBy(staffId);
				activty.setLawAuditStatus("1");
			}
			activty.setUpdateBy(staffId);
			activty.setUpdateDate(new Date());
			activityService.updateByPrimaryKeySelective(activty);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 查看审核
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/seeReject.shtml")
	@ResponseBody
	public Map<String, Object> reject(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		Integer activityId=Integer.valueOf(request.getParameter("activityId"));
		String code = null;
		String msg = "";
		Integer type=Integer.valueOf(request.getParameter("type"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date preheatTime = null;
			Date activityBeginTime = null;
			Date activityEndTime = null;
			String status=request.getParameter("status");
			if(type.intValue()!=3 && type.intValue()!=4){
				if(!"3".equals(status)){
					preheatTime=dateFormat.parse(request.getParameter("preheatTime")+":00");
					activityBeginTime=dateFormat.parse(request.getParameter("activityBeginTime")+":00");
					activityEndTime=dateFormat.parse(request.getParameter("activityEndTime")+":59");
				}
			}
			
			ActivityCustom activity=activityService.selectActivityCustomByPrimaryKey(activityId);
				
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			String statusRemarks=request.getParameter("statusRemarks");
			if(!StringUtils.isEmpty(request.getParameter("feeRate"))){
				activity.setFeeRate(new BigDecimal(request.getParameter("feeRate")));
			}
			if(type.intValue()==1){
				activity.setOperateAuditBy(staffId);
				activity.setOperateAuditStatus(status);
				activity.setOperateAuditRemarks(statusRemarks);
				if("2".equals(status)){
					activity.setDesignAuditStatus("1");
					if(!StringUtils.isEmpty(activity.getDesignAuditRemarks())){
						activity.setDesignAuditRemarks("");
					}
					activity.setStatus("3");
				}else{
					activity.setStatus("4");
					//品牌特卖驳回给商家后，发送站内信, 消息类型A63
					MsgTplExample msgTplExample=new MsgTplExample();
					MsgTplExample.Criteria msgTplCriteria=msgTplExample.createCriteria();
					msgTplCriteria.andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo("A63");
					List <MsgTpl> msgTpls = msgTplService.selectByExample(msgTplExample);
					if (msgTpls.size() > 0) {
						MsgTpl msgTpl = new MsgTpl();
						msgTpl = msgTpls.get(0);
						PlatformMsg platformMsg = new PlatformMsg();
						platformMsg.setMchtId(activity.getMchtId());
						platformMsg.setMsgType("A6");
						platformMsg.setBizId(activityId);
						platformMsg.setStatus("0");
						platformMsg.setCreateBy(1);
						platformMsg.setCreateDate(new Date());
						String content=msgTpl.getContent();
						//特卖活动名称
						String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('mcht/activity')\">"+activity.getActivityAreaName()+"</a>";
						content=content.replaceAll("\\{activity_name}", msgActivityName);
						platformMsg.setTitle("关于品牌特卖审核驳回通知");
						platformMsg.setContent(content);
						platformMsgService.insertSelective(platformMsg);
					}
				}
				//不管是审核通过还是驳回，商品都直接设置为审核通过
				activityProductService.updateOperateAuditAdopt(activityId,staffId);
			}
			if(type.intValue()==3){
				activity.setDesignAuditBy(staffId);
				activity.setDesignAuditStatus(status);
				activity.setDesignAuditRemarks(statusRemarks);
				if("2".equals(status)){
					activity.setLawAuditStatus("1");
					if(!StringUtils.isEmpty(activity.getLawAuditRemarks())){
						activity.setLawAuditRemarks("");
					}
				}else{
					activity.setOperateAuditStatus("1");
					activity.setOperateAuditRemarks("");
				}
				//不管是审核通过还是驳回，商品都直接设置为审核通过
				activityProductService.updateDesignAuditAdopt(activityId,staffId);
			}
			if(type.intValue()==4){
				int num1=activity.getProductAudit1Num();
				int num3=activity.getProductAudit3Num();
				if (num1>0 && num3>0){
					resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE, "审核失败："+num1+"个商品未审核，"+num3+"个商品待驳回");
					return resMap;
				}else if (num1>0){
					resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE, "审核失败："+num1+"个商品未审核");
					return resMap;
				}else if (num3>0){
					resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE, "审核失败："+num3+"个商品待驳回");
					return resMap;
				}
				activity.setLawAuditBy(staffId);
				activity.setLawAuditStatus(status);
				activity.setLawAuditRemarks(statusRemarks);
				if("2".equals(status)){
					activity.setCooAuditStatus("1");
					if(!StringUtils.isEmpty(activity.getCooAuditRemarks())){
						activity.setCooAuditRemarks("");
					}
				}else{
					activity.setOperateAuditStatus("1");
					activity.setOperateAuditRemarks("");
				}
				//不管是审核通过还是驳回，商品都直接设置为审核通过
				activityProductService.updateLawAuditAdopt(activityId,staffId);
			}
			if(type.intValue()==5){
				activity.setCooAuditBy(staffId);
				activity.setCooAuditStatus(status);
				activity.setCooAuditRemarks(statusRemarks);
				if("2".equals(status)){
					activity.setStatus("6");
					//品牌特卖审核通过后，发送站内信, 消息类型A61
					MsgTplExample msgTplExample=new MsgTplExample();
					MsgTplExample.Criteria msgTplCriteria=msgTplExample.createCriteria();
					msgTplCriteria.andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo("A61");
					List <MsgTpl> msgTpls = msgTplService.selectByExample(msgTplExample);
					if (msgTpls.size() > 0) {
						MsgTpl msgTpl = new MsgTpl();
						msgTpl = msgTpls.get(0);
						PlatformMsg platformMsg = new PlatformMsg();
						platformMsg.setMchtId(activity.getMchtId());
						platformMsg.setMsgType("A6");
						platformMsg.setBizId(activityId);
						platformMsg.setStatus("0");
						platformMsg.setCreateBy(1);
						platformMsg.setCreateDate(new Date());
						String content=msgTpl.getContent();
						SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
						SimpleDateFormat formatDay = new SimpleDateFormat("dd");
						SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
						//特卖活动名称
						String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('mcht/activity')\">"+activity.getActivityAreaName()+"</a>";
						content=content.replaceAll("\\{activity_name}", msgActivityName);
						//活动开始时间
						content=content.replaceAll("\\{begin_month}", formatMonth.format(activityBeginTime)).replaceAll("\\{begin_day}", formatDay.format(activityBeginTime)).replaceAll("\\{begin_time}", formatTime.format(activityBeginTime));
						//活动预热时间
						content=content.replaceAll("\\{preheat_month}", formatMonth.format(preheatTime)).replaceAll("\\{preheat_day}", formatDay.format(preheatTime)).replaceAll("\\{preheat_time}", formatTime.format(preheatTime));
						platformMsg.setTitle("关于品牌特卖审核通过通知");
						platformMsg.setContent(content);
						platformMsgService.insertSelective(platformMsg);
					}
				}else{
					activity.setOperateAuditStatus("1");
					activity.setOperateAuditRemarks("");
				}
				//不管是审核通过还是驳回，商品都直接设置为审核通过
				activityProductService.updateCooAuditAdopt(activityId,staffId);
			}
			activity.setRemarks(request.getParameter("remarks"));
			if(type.intValue()==6){
				String adoptStatus=request.getParameter("adoptStatus");
				if ("1".equals(adoptStatus)){
					activity.setStatus("5");
				}else{
					activity.setStatus("6");
				}
			}
			
			//活动审核流水表添加
			if(type.intValue()!=6){
				ActivityAuditLog activityAuditLog=new ActivityAuditLog();
				activityAuditLog.setCreateBy(staffId);
				activityAuditLog.setCreateDate(new Date());
				activityAuditLog.setActivityId(activityId);
				activityAuditLog.setRemarks(statusRemarks);
				activityAuditLog.setStatus(status);
				if(type.intValue()==1){
					activityAuditLog.setType("1");
				}
				if(type.intValue()==3){
					activityAuditLog.setType("2");
				}
				if(type.intValue()==4){
					activityAuditLog.setType("3");
				}
				if(type.intValue()==5){
					activityAuditLog.setType("4");
				}
				//activityAuditLogService.insertSelective(activityAuditLog);
			}
			
			activity.setUpdateBy(staffId);
			activity.setUpdateDate(new Date());
			//活动专区
			if(type.intValue()==1||type.intValue()==5||type.intValue()==6){
				ActivityArea activityArea=activityAreaService.selectByPrimaryKey(activity.getActivityAreaId());
				activityArea.setActivityBeginTime(activityBeginTime);//活动开始时间
				activityArea.setActivityEndTime(activityEndTime);//活动结束时间
				activityArea.setPreheatTime(preheatTime);//预热活动时间
				activityArea.setUpdateBy(staffId);
				activityArea.setUpdateDate(new Date());
				
				//更新会场状态为启用
				if (type.intValue()==5){
					activityArea.setStatus("1");
					//更新优惠劵状态和时间
					if ("1".equals(activityArea.getPreferentialType())){
						CouponExample couponExample=new CouponExample();
						CouponExample.Criteria couponCriteria=couponExample.createCriteria();
						couponCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityArea.getId());
						
						Coupon coupon=new Coupon();
						coupon.setRecBeginDate(activityBeginTime);
						coupon.setRecEndDate(activityEndTime);
						coupon.setStatus("1");
						coupon.setExpiryType("1");
						coupon.setExpiryBeginDate(activityBeginTime);
						coupon.setExpiryEndDate(activityEndTime);
						coupon.setUpdateBy(staffId);
						coupon.setUpdateDate(new Date());
						couponService.updateByExampleSelective(coupon, couponExample);
					}
				}
				activityAreaService.updateByPrimaryKeySelective(activityArea);
			}
			
			activityService.updateByPrimaryKeySelective(activity);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 重置商家报名状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activity/singleActivityStatusReset.shtml")
	public String singleActivityStatusReset(Model model,HttpServletRequest request) {
		return "/activity/singleActivityStatusReset";
	}
	
	/**
	 * 重置商家报名状态提交
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activity/singleActivityStatusResetSubmit.shtml")
	public ModelAndView singleActivityStatusResetSubmit(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
			Integer activityAreaId=Integer.valueOf(request.getParameter("activityAreaId"));
			String mchtCode=request.getParameter("mchtCode");
			
			MchtInfo mchtInfo=new MchtInfo();
			MchtInfoExample mchtInfoExample=new MchtInfoExample();
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mchtCode);
			List<MchtInfo> mchtInfos=mchtInfoService.selectByExample(mchtInfoExample);
			if(mchtInfos==null||mchtInfos.size()==0){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "商家不存在！");
				return new ModelAndView(rtPage, resMap);
			}else{
				mchtInfo=mchtInfos.get(0);
			}
			
			ActivityExample activityExample=new ActivityExample();
			activityExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId).andMchtIdEqualTo(mchtInfo.getId());
			Activity activity=new Activity();
			activity.setStatus("1");
			activity.setOperateAuditStatus("0");
			activity.setDesignAuditStatus("0");
			activity.setLawAuditStatus("0");
			activity.setCooAuditStatus("0");
			activity.setUpdateBy(staffId);
			activity.setUpdateDate(new Date());
			activityService.updateByExampleSelective(activity, activityExample);
			
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
	 * 重置商家报名状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activity/singleActivityProductQuit.shtml")
	public String singleActivityProductQuit(Model model,HttpServletRequest request) {
		return "/activity/singleActivityProductQuit";
	}
	
	/**
	 * 重置商家报名状态提交
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activity/singleActivityProductQuitSubmit.shtml")
	public ModelAndView singleActivityProductQuitSubmit(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
			Integer productId=Integer.valueOf(request.getParameter("productId"));
			
			ActivityProductExample activityProductExample=new ActivityProductExample();
			activityProductExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
			ActivityProduct activityProduct=new ActivityProduct();
			activityProduct.setDelFlag("1");
			activityProduct.setUpdateBy(staffId);
			activityProduct.setUpdateDate(new Date());
			activityProductService.updateByExampleSelective(activityProduct, activityProductExample);
			
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
}
