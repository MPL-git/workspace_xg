package com.jf.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaCustom;
import com.jf.entity.ActivityAreaCustomExample;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityAuditLog;
import com.jf.entity.ActivityAuditLogCustom;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityCustomExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityProductCustom;
import com.jf.entity.ActivityProductCustomExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutCustom;
import com.jf.entity.FullCutExample;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MemberGroup;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
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
import com.jf.service.MemberGroupService;
import com.jf.service.MsgTplService;
import com.jf.service.PlatformContactService;
import com.jf.service.PlatformMsgService;
import com.jf.service.SysParamCfgService;
import com.jf.vo.ActivityDateSchedule;
import com.jf.vo.Page;
/**
 * 活动、单品专区
 * @author Administrator
 *
 */
@Controller
public class ActivityAreaController extends BaseController {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5460995450026401847L;

	@Autowired
	private MemberGroupService memberGroupService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private ActivityAreaService activityAreaService;
	
	@Autowired
	private FullCutService fullCutService;
	
	@Autowired
	private FullGiveService fullGiveService;
	
	@Autowired
	private FullDiscountService fullDiscountService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityAuditLogService activityAuditLogService;
	
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MsgTplService msgTplService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Resource
	private PlatformContactService platformContactService;
	/**
	 * 创建会场
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/activityArea/createActivity.shtml")
	public String createActivity(Model model,HttpServletRequest request,HttpServletResponse response,Integer id){
		ActivityAreaCustom activityArea=null;
		FullGive fullGive=null;
		FullDiscount fullDiscount=null;
		List<Map<String, Object>> copuon=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> fullCutList=new ArrayList<Map<String,Object>>(); 
		List<Map<String, Object>> fullDiscountList=new ArrayList<Map<String,Object>>();
		if(id!=null){
			activityArea=activityAreaService.selectByCustomPrimaryKey(id);
			if(!StringUtils.isEmpty(activityArea.getMchtIdGroup())){
				String mchtInfoGroup="";
				String [] mchtGroup=activityArea.getMchtIdGroup().split(",");
				for (int i = 0; i < mchtGroup.length; i++) {
					MchtInfo mchtInfo=mchtInfoService.selectByPrimaryKey(Integer.valueOf(mchtGroup[i]));
					if (mchtInfo!=null && mchtInfo.getShortName()!=null){
						if(mchtInfoGroup!=""){
							mchtInfoGroup+=",";
						}
						mchtInfoGroup+=mchtInfo.getShortName();
					}
				}
				model.addAttribute("mchtInfoGroup", mchtInfoGroup);
			}
//			优惠券
			List<CouponCustom> couponList=couponService.selectByActivityAreaIdList(id);
			Map<String, Object> copuonMap=new HashMap<String, Object>();
			if(couponList.size()>0){
				copuonMap.put("recLimitType", couponList.get(0).getRecLimitType());
				copuonMap.put("recEach", couponList.get(0).getRecEach());
				for (int i = 0; i < couponList.size(); i++) {
					Map<String, Object> copuonListMap=new HashMap<String, Object>();
					copuonListMap.put("couponId", couponList.get(i).getId());
					copuonListMap.put("money", couponList.get(i).getMoney());
					copuonListMap.put("minimum", couponList.get(i).getMinimum());
					copuonListMap.put("recType", couponList.get(i).getRecType());
					copuonListMap.put("grantQuantity", couponList.get(i).getGrantQuantity());
					copuonListMap.put("needIntegral", couponList.get(i).getNeedIntegral());
					copuon.add(copuonListMap);
				}
			}
			model.addAttribute("copuonMap", copuonMap);

			//满减
			FullCutCustom fullCut=fullCutService.selectByActivityId(id);
			if(!StringUtils.isEmpty(fullCut)){
				if("1".equals(fullCut.getLadderFlag())){
					String [] rule = fullCut.getRule().split("\\|");
					for (int i = 0; i < rule.length; i++) {
						String [] rules=org.springframework.util.StringUtils.split(rule[i], ",");
						Map<String, Object> fullCutMap=new HashMap<String, Object>();
						fullCutMap.put("fullName", rules[0]);
						fullCutMap.put("reduceName", rules[1]);
						fullCutList.add(fullCutMap);
					}
				}else{
					if(!StringUtils.isEmpty(fullCut.getRule())){
						String [] rules=org.springframework.util.StringUtils.split(fullCut.getRule(), ",");
						model.addAttribute("fullName", rules[0]);
						model.addAttribute("reduceName", rules[1]);
					}
				}
			}
			model.addAttribute("fullCut", fullCut);
			
			//满赠
			fullGive=fullGiveService.selectByActivityId(id);
			if(!StringUtils.isEmpty(fullGive)){
				if(!StringUtil.isEmpty(fullGive.getCouponIdGroup())){
					String fullGiv=fullGive.getCouponIdGroup();
					String [] fullGives=fullGiv.split(",");
					String couponName="";
					for (int i = 0; i < fullGives.length; i++) {
						Coupon cou=couponService.selectCouponCustomByPrimaryKey(Integer.valueOf(fullGives[i]));
						if(couponName!=""){
							couponName+=couponName+","+cou.getName();
						}
						couponName+=cou.getName();
					}
					model.addAttribute("couponName", couponName);
				}
			}
			
			//多买多送
			fullDiscount=fullDiscountService.selectByActivityId(id);
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
			
			//会员组
			MemberGroup memberGroup=null;
			if(!StringUtils.isEmpty(activityArea.getMinMemberGroup())){
				memberGroup=memberGroupService.selectByPrimaryKey(activityArea.getMinMemberGroup());
			}
			model.addAttribute("memberGroup", memberGroup);
		}
		model.addAttribute("activityArea", activityArea);
		//面向对象商家
		List<MemberGroup> memberGroupList=memberGroupService.selectMemberGroupList();
		model.addAttribute("memberGroupList", memberGroupList);
		if(copuon.size()>0){
			model.addAttribute("couponList", JSONArray.fromObject(copuon).toString());
		}
		if(fullCutList.size()>0){
			model.addAttribute("fullCutList", JSONArray.fromObject(fullCutList).toString());
		}
		if(!StringUtils.isEmpty(fullGive)){
			model.addAttribute("fullGiveInfo", fullGive);
		}
		model.addAttribute("fullDiscount", fullDiscount);
		model.addAttribute("fullDiscountList", JSONArray.fromObject(fullDiscountList).toString());
		return "/activity/createActivity";
	}
	
	@RequestMapping(value="/activityArea/addActivityArea.shtml")
	@ResponseBody
	public ModelAndView addActivityArea(HttpServletRequest request,HttpServletResponse response,ActivityArea activityArea){
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
//		优惠券
		String jsonCoupon=request.getParameter("jsonCoupon");
//		满减
		String jsonFullCut=request.getParameter("jsonFullCut");
//		多买多送 
		String jsonFullDiscount=request.getParameter("jsonFullDiscount");
		
		//0：无 、1：优惠劵、2：满减、3：满赠、4：多买优惠
		Integer preferentialType=Integer.valueOf(request.getParameter("preferentialType"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			int activityAreaId;
			String activityBeginTime=request.getParameter("activityBeginTime");
			String activityEndTime=request.getParameter("activityEndTime");
			String status="0";
//			创建
			if(activityArea.getId()==null){
				activityArea.setCreateBy(staffId);
				activityArea.setCreateDate(new Date());
				activityArea.setEnrollBeginTime(dateFormat.parse(request.getParameter("enrollBeginTime")));
				activityArea.setEnrollEndTime(dateFormat.parse(request.getParameter("enrollEndTime")));
				activityArea.setActivityBeginTime(dateFormat.parse(activityBeginTime));
				activityArea.setActivityEndTime(dateFormat.parse(activityEndTime));
				activityArea.setType(request.getParameter("areaType"));
				activityArea.setMchtIdGroup(request.getParameter("thisAppoint"));
				activityArea.setSource("1");
				activityArea.setMchtId(0);
				activityArea.setDelFlag("0");
				activityArea.setStatus("0");
				activityArea.setPreheatFlag("2");
				activityArea.setUrlSuffix(request.getParameter("urlSuffix"));
				String pushMchtType=request.getParameter("pushMchtType");
				activityArea.setPushMchtType(pushMchtType);
				activityAreaService.insertSelective(activityArea);
				activityAreaId=activityArea.getId();
//				0：无 、1：优惠劵、2：满减、3：满赠、4：多买优惠
//				Integer preferentialType=Integer.valueOf(request.getParameter("preferentialType"));
			}else{
				//修改
				ActivityArea activityAreaModify=activityAreaService.selectByPrimaryKey(activityArea.getId());
				activityAreaModify.setUpdateBy(staffId);
				activityAreaModify.setUpdateDate(new Date());
				activityAreaModify.setEnrollBeginTime(dateFormat.parse(request.getParameter("enrollBeginTime")));
				activityAreaModify.setEnrollEndTime(dateFormat.parse(request.getParameter("enrollEndTime")));
				activityAreaModify.setActivityBeginTime(dateFormat.parse(activityBeginTime));
				activityAreaModify.setActivityEndTime(dateFormat.parse(activityEndTime));
				activityAreaModify.setType(request.getParameter("areaType"));
				activityAreaModify.setPushMchtType(request.getParameter("pushMchtType"));
				activityAreaModify.setName(activityArea.getName());
				activityAreaModify.setEntryPic(activityArea.getEntryPic());
				activityAreaModify.setPic(activityArea.getPic());
				activityAreaModify.setDescription(activityArea.getDescription());
				activityAreaModify.setLimitMchtNum(activityArea.getLimitMchtNum());
				activityAreaModify.setBenefitPoint(activityArea.getBenefitPoint());
				activityAreaModify.setMinMemberGroup(activityArea.getMinMemberGroup());
				activityAreaModify.setMchtIdGroup(request.getParameter("mchtIdGroup"));
				activityAreaModify.setStatus(activityArea.getStatus());
				activityAreaModify.setUrlSuffix(activityArea.getUrlSuffix());
				activityAreaModify.setPreferentialType(request.getParameter("preferentialType"));
				activityAreaService.updateByPrimaryKeySelective(activityAreaModify);
				activityAreaId=activityAreaModify.getId();
				status=activityArea.getStatus();
			}
			
			String preferentialIdGroup="";
			
			if(preferentialType.intValue()==1){
				Coupon coupon=null;
				JSONArray couponList = JSONArray.fromObject(jsonCoupon);
				String recLimitType=request.getParameter("recLimitType");
				List <Integer> couponIds=new ArrayList<Integer>();
				List <String> couponIdStr=new ArrayList<String>();
				for (int i = 0; i < couponList.size(); i++) {
//					1：免费、2：金币兑换
					String recType=couponList.getJSONObject(i).getString("recType");
					if(!"0".equals(couponList.getJSONObject(i).getString("couponId"))&&!StringUtils.isEmpty(couponList.getJSONObject(i).getString("couponId"))){
						coupon=couponService.selectByPrimaryKey(Integer.valueOf(couponList.getJSONObject(i).getString("couponId")));
					}else{
						coupon=new Coupon();
						coupon.setActivityAreaId(activityArea.getId());
						coupon.setCreateBy(staffId);
						coupon.setCreateDate(new Date());
						coupon.setRang("2");
						coupon.setBelong("1");
						coupon.setStatus("0");
						coupon.setDelFlag("0");
						coupon.setMinMemberGroup(1);
						coupon.setExpiryType("1");
					}
					coupon.setMoney(new BigDecimal(couponList.getJSONObject(i).getString("money")));
					coupon.setMinimum(new BigDecimal(couponList.getJSONObject(i).getString("minimum")));
					coupon.setName("满"+couponList.getJSONObject(i).getString("money")+"-"+couponList.getJSONObject(i).getString("minimum")+"元优惠券");
					coupon.setGrantQuantity(Integer.valueOf(couponList.getJSONObject(i).getString("grantQuantity")));
					coupon.setRecType(recType);
					if(Integer.valueOf(recType)==2){
						coupon.setNeedIntegral(Integer.valueOf(couponList.getJSONObject(i).getString("needIntegral")));
					}
					coupon.setRecLimitType(recLimitType);
					if(Integer.valueOf(recLimitType)==2){
						coupon.setRecEach(Integer.valueOf(request.getParameter("recEach")));
					}
					if(!"0".equals(couponList.getJSONObject(i).getString("couponId"))&&!StringUtils.isEmpty(couponList.getJSONObject(i).getString("couponId"))){
						if ("1".equals(status)){
							coupon.setStatus("1");
							coupon.setRecBeginDate(dateFormat.parse(activityBeginTime));
							coupon.setRecEndDate(dateFormat.parse(activityEndTime));
							coupon.setExpiryBeginDate(dateFormat.parse(activityBeginTime));
							coupon.setExpiryEndDate(dateFormat.parse(activityEndTime));
						}
						couponService.updateByPrimaryKeySelective(coupon);
					}else{
						couponService.insertSelective(coupon);
					}
					couponIds.add(coupon.getId());
					couponIdStr.add(coupon.getId().toString());
				}
				preferentialIdGroup=org.apache.commons.lang.StringUtils.join(couponIdStr, ",");
				
				CouponExample couponExample=new CouponExample();
				CouponExample.Criteria couponCriteria=couponExample.createCriteria();
				couponCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId).andIdNotIn(couponIds);
				Coupon couponDel= new Coupon();
				couponDel.setDelFlag("1");
				couponService.updateByExampleSelective(couponDel, couponExample);
			}
			if(preferentialType.intValue()==2){
				String rule="";
				FullCut fullCut=null;
				if(!StringUtils.isEmpty(request.getParameter("fullCutId"))){
					fullCut=fullCutService.selectByPrimaryKey(Integer.valueOf(request.getParameter("fullCutId")));
					fullCut.setUpdateBy(staffId);
					fullCut.setUpdateDate(new Date());
				}else{
					fullCut=new FullCut();
					fullCut.setCreateBy(staffId);
					fullCut.setCreateDate(new Date());
					fullCut.setRang("2");
					fullCut.setBelong("2");
					fullCut.setDelFlag("0");
				}
				String ladderFlag=request.getParameter("ladderFlag");
				JSONArray fullCutList = JSONArray.fromObject(jsonFullCut);
				String sumFlag=null;
				for (int i = 0; i < fullCutList.size(); i++) {
					String fullName=fullCutList.getJSONObject(i).getString("fullName");
					String reduceName=fullCutList.getJSONObject(i).getString("reduceName");
					sumFlag=fullCutList.getJSONObject(i).getString("sumFlag");
					if(rule!=""){
						rule=rule+"|";
					}
					rule+=fullName+","+reduceName;
				}
				fullCut.setRule(rule);
				fullCut.setActivityAreaId(activityArea.getId());
				fullCut.setLadderFlag(ladderFlag);
				fullCut.setSumFlag(sumFlag);
				if(!StringUtils.isEmpty(request.getParameter("fullCutId"))){
					fullCutService.updateByPrimaryKeySelective(fullCut);
				}else{
					fullCutService.insertSelective(fullCut);
				}
				preferentialIdGroup=fullCut.getId().toString();
				
				FullCutExample fullCutExample=new FullCutExample();
				FullCutExample.Criteria fullCutCriteria=fullCutExample.createCriteria();
				fullCutCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId).andIdNotEqualTo(fullCut.getId());
				FullCut fullCutDel= new FullCut();
				fullCutDel.setDelFlag("1");
				fullCutService.updateByExampleSelective(fullCutDel, fullCutExample);
			}
			
			if(preferentialType.intValue()==3){
				FullGive fullGive=null;
				if(!StringUtils.isEmpty(request.getParameter("fullGiveId"))){
					fullGive=fullGiveService.selectByPrimaryKey(Integer.valueOf(request.getParameter("fullGiveId")));
					fullGive.setUpdateBy(staffId);
					fullGive.setUpdateDate(new Date());
				}else{
					fullGive=new FullGive();
					fullGive.setBelong("2");
					fullGive.setRang("2");
					fullGive.setDelFlag("0");
					fullGive.setCreateBy(staffId);
					fullGive.setCreateDate(new Date());
				}
				fullGive.setActivityAreaId(activityArea.getId());
				fullGive.setType(request.getParameter("type"));
				if(Integer.valueOf(request.getParameter("type"))==1){
					fullGive.setMinimum(new BigDecimal(request.getParameter("fullGiveMinimum")));
				}

				if(!StringUtils.isEmpty(request.getParameter("sumFlag"))){
					fullGive.setSumFlag(request.getParameter("sumFlag"));
				}
				
				String couponFlag=request.getParameter("couponFlag");
				if(StringUtil.isEmpty(couponFlag)){
					couponFlag="0";
				}
				fullGive.setCouponFlag(couponFlag);
				if(Integer.valueOf(couponFlag)==1){
					fullGive.setCouponIdGroup(request.getParameter("preferentialIdGroup"));
				}else{
					fullGive.setCouponIdGroup("");
				}
				
				if(!StringUtils.isEmpty(request.getParameter("integralFlag"))){
					fullGive.setIntegralFlag(request.getParameter("integralFlag"));
					if("1".equals(request.getParameter("integralFlag"))){
						fullGive.setIntegral(Integer.valueOf(request.getParameter("integral")));
					}
				}
				
				if(!StringUtils.isEmpty(request.getParameter("fullGiveId"))){
					fullGiveService.updateByPrimaryKeySelective(fullGive);
				}else{
					fullGiveService.insertSelective(fullGive);
				}
				preferentialIdGroup=fullGive.getId().toString();
				
				FullGiveExample fullGiveExample=new FullGiveExample();
				FullGiveExample.Criteria fullGiveCriteria=fullGiveExample.createCriteria();
				fullGiveCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId).andIdNotEqualTo(fullGive.getId());
				FullGive fullGiveDel= new FullGive();
				fullGiveDel.setDelFlag("1");
				fullGiveService.updateByExampleSelective(fullGiveDel, fullGiveExample);
			}
			if(preferentialType.intValue()==4){
				Integer type=Integer.valueOf(request.getParameter("fullDiscount"));
				FullDiscount fullDiscount=null;
				if(!StringUtils.isEmpty(request.getParameter("fullDiscountId"))){
					fullDiscount=fullDiscountService.selectByPrimaryKey(Integer.valueOf(request.getParameter("fullDiscountId")));
					fullDiscount.setUpdateBy(staffId);
					fullDiscount.setUpdateDate(new Date());
				}else{
					fullDiscount=new FullDiscount();
					fullDiscount.setBelong("2");
					fullDiscount.setRang("2");
					fullDiscount.setDelFlag("0");
					fullDiscount.setCreateBy(staffId);
					fullDiscount.setCreateDate(new Date());
				}
				fullDiscount.setActivityAreaId(activityArea.getId());
				fullDiscount.setType(type.toString());
				String rule="";
				if(type.intValue()==1||type.intValue()==2){
					fullDiscount.setType(request.getParameter("fullDiscount"));
					if(type.intValue()==1){
						fullDiscount.setRule(request.getParameter("fullOfOne")+","+request.getParameter("fullGiftsOneName"));
					}else{
						fullDiscount.setRule(request.getParameter("fullOfTwo")+","+request.getParameter("fullGiftsTwoName"));
					}
				}
				if(type.intValue()==3){
					JSONArray fullDiscountList = JSONArray.fromObject(jsonFullDiscount);
					for (int i = 0; i < fullDiscountList.size(); i++) {
						String fullPieces=fullDiscountList.getJSONObject(i).getString("fullPieces");
						String discountName=fullDiscountList.getJSONObject(i).getString("discountName");
						if(rule!=""){
							rule=rule+"|";
						}
						rule+=fullPieces+","+discountName;
						fullDiscount.setRule(rule);
					}
				}
				if(!StringUtils.isEmpty(request.getParameter("fullDiscountId"))){
					fullDiscountService.updateByPrimaryKeySelective(fullDiscount);
				}else{
					fullDiscountService.insertSelective(fullDiscount);
				}
				preferentialIdGroup=fullDiscount.getId().toString();
				
				FullDiscountExample fullDiscountExample=new FullDiscountExample();
				FullDiscountExample.Criteria fullDiscountCriteria=fullDiscountExample.createCriteria();
				fullDiscountCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId).andIdNotEqualTo(fullDiscount.getId());
				FullDiscount fullDiscountDel= new FullDiscount();
				fullDiscountDel.setDelFlag("1");
				fullDiscountService.updateByExampleSelective(fullDiscountDel, fullDiscountExample);
			}
				
			ActivityArea activityAreaPre=new ActivityArea();
			activityAreaPre.setId(activityAreaId);
			activityAreaPre.setPreferentialIdGroup(preferentialIdGroup);
			if ("1".equals(status)){
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
				sysParamCfgCriteria.andParamCodeEqualTo("ACTIVITY_TEMPLET_PREFIX");
				sysParamCfgExample.setLimitSize(1);
				List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
				SysParamCfg sysParamCfg=sysParamCfgs.get(0);
				String defaultName=""; 
				String areaType=request.getParameter("areaType");
				if ("1".equals(areaType)){
					defaultName="brand_def.html";
				}
				if ("2".equals(areaType)){
					defaultName="single_def.html";
				}
				activityAreaPre.setTempletSuffix(sysParamCfg.getParamValue()+defaultName);
			}
			activityAreaService.updateByPrimaryKeySelective(activityAreaPre);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap); 
		} catch (ParseException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap); 
		}
	}
	
	/**
	 * 会场管理、单品管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/activityArea/activityAreaManager.shtml")
	public String activityAreaManager(HttpServletRequest request,HttpServletResponse response,Model model,Integer type){
		model.addAttribute("type", type);
		if(type.intValue()==1){
			return "/activity/activityAreaManagerList";
		}else{
			return "/activity/singleProductManagerList";
		}
	}
	
	@RequestMapping(value="/activityArea/activityAreaManagerData.shtml")
	@ResponseBody
	public Map<String, Object> activityAreaManagerData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityAreaCustom> dataList = null;
		Integer totalCount =0;
		Integer type=Integer.valueOf(request.getParameter("type"));
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			ActivityAreaCustomExample activityAreaCustomExample =new ActivityAreaCustomExample();
			ActivityAreaCustomExample.ActivityAreaCustomCriteria activityAreaCustomCriteria=activityAreaCustomExample.createCriteria();
			activityAreaCustomCriteria.andDelFlagEqualTo("0");
			activityAreaCustomCriteria.andSourceEqualTo("1");//会场
			activityAreaCustomExample.setOrderByClause("ba.create_date desc");
			if(type.intValue()==1){
				activityAreaCustomCriteria.andTypeBetween("1", "2");//type:1品牌活动
			}else{
//				activityAreaCustomCriteria.andTypeNotEqualTo("1");//type:不等于1剩下的都是属于单品活动
				activityAreaCustomCriteria.andTypeNotBetween("1", "2");
			}
			
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				activityAreaCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("activityAreaId"))){
				activityAreaCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("activityAreaId")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("activityBeginTime")) && !StringUtil.isEmpty(request.getParameter("activityEndTime"))){
				activityAreaCustomCriteria.andActivityBeginTimeAndActivityEndTimeIsNotNull(dateFormat.parse(request.getParameter("activityBeginTime")), dateFormat.parse(request.getParameter("activityEndTime")));
			}else{
				if(!StringUtil.isEmpty(request.getParameter("activityBeginTime"))){
					activityAreaCustomCriteria.andActivityBeginTimeByEqualTo(dateFormat.parse(request.getParameter("activityBeginTime")));
				}
				
				if(!StringUtil.isEmpty(request.getParameter("activityEndTime"))){
					activityAreaCustomCriteria.andActivityEndTimeByEqualTo(dateFormat.parse(request.getParameter("activityEndTime")));
				}
			}
			activityAreaCustomExample.setLimitSize(page.getLimitSize());
			activityAreaCustomExample.setLimitStart(page.getLimitStart());
			dataList=activityAreaService.selectByCustomExample(activityAreaCustomExample);
			totalCount=activityAreaService.countByCustomExample(activityAreaCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	/**
	 * 选择商家列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activityArea/mchtInfoList.shtml")
	public String activityMemberInfoList(HttpServletRequest request,HttpServletResponse response){
		return "/activity/mchtInfoList";
	}
	
	@RequestMapping(value="/activityArea/mchtInfoListData.shtml")
	@ResponseBody
	public Map<String, Object> memberInfoListData(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<MchtInfoCustom> dataList = null;
		Integer totalCount =0;
		if(!StringUtil.isEmpty(request.getParameter("shortName"))){
			paramMap.put("shortName", "%"+request.getParameter("shortName")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
			paramMap.put("mchtCode", request.getParameter("mchtCode"));
		}
		paramMap=this.setPageParametersLiger(request,paramMap);
		dataList=mchtInfoService.selectMchtInfoAllList(paramMap);
		totalCount=mchtInfoService.selectMchtInfoListCount(paramMap);
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	@RequestMapping(value="/activityArea/choiceCouponList.shtml")
	public String choiceCouponList(HttpServletRequest request,HttpServletResponse response){
		return "/activity/choiceCouponList";
	}
	
	@RequestMapping(value="/activityArea/choiceCouponListData.shtml")
	@ResponseBody
	public Map<String, Object> choiceCouponListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<Coupon> dataList = null;
		Integer totalCount =0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CouponExample couponExample=new CouponExample();
		CouponExample.Criteria criteria=couponExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
		try {
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
	 * 查看活动商家
	 * @param model
	 * @param request
	 * @param activityAreaId
	 * @return
	 */
	@RequestMapping(value="/activityArea/seeActivityMchtList.shtml")
	private String seeActivityMchtList(Model model,HttpServletRequest request,Integer activityAreaId,Integer type){
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
		model.addAttribute("activityAreaId", activityAreaId);
		model.addAttribute("type", type);
		//type:1:运营专员审核/2:所有报名商家活动/3:设计专员审核/4:法务专员审核/5:运营总监审核
		if(type.intValue()==1){
			return "/activity/activityOperateAuditList";
		}else if(type.intValue()==2||type.intValue()==0){
			return "/activity/seeActivityMchtList";
		}else if(type.intValue()==3||type.intValue()==4){
			return "/activity/activityDesignOrLawList";
		}else{
			return "/activity/activityCooAuditList";
		}
	}
	
	
	@RequestMapping(value="/activityArea/seeActivityMchtListData.shtml")
	@ResponseBody
	public Map<String, Object> seeActivityMchtListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityCustom> dataList = null;
		Integer totalCount =0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			ActivityCustomExample activityCustomExample=new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria=activityCustomExample.createCriteria();
			
			activityCustomCriteria.andDelFlagEqualTo("0");
			activityCustomCriteria.andActivityAreaBySourceEqualTo("1");//会场
			activityCustomCriteria.andActivityAreaByTypeEqualOneAndTwo(1,2);//1:品牌活动、2:单品活动
			Integer type=Integer.valueOf(request.getParameter("type"));
			if(type.intValue()==0){
				activityCustomCriteria.andActivityAreaIdEqualTo(Integer.valueOf(request.getParameter("activityAreaId")));
				String[] strs={"5","6"};
				List<String> sList = Arrays.asList(strs);
				activityCustomCriteria.andStatusIn(sList);
			}
			
			if(type.intValue()==1){//运营专员审核
				//activityCustomCriteria.andStaffIdEqualTo(staffId).andOperateAuditStatusIsNotNull().andOperateAuditStatusNotEqualTo("0");
				activityCustomCriteria.andOperateAuditStatusIsNotNull().andOperateAuditStatusNotEqualTo("0");
			}
			
			if(type.intValue()==2){
				if(!StringUtil.isEmpty(request.getParameter("activityBeginTime")) ){
					activityCustomCriteria.andActivityBeginTimeGreaterThanOrEqualTo(request.getParameter("activityBeginTime")+" 23:59:59");
				}
					
				if(!StringUtil.isEmpty(request.getParameter("activityEndTime")) ){
					activityCustomCriteria.andActivityEndTimeLessThanOrEqualTo(request.getParameter("activityEndTime")+" 00:00:00");
				}
			}
			
			if(type.intValue()==3){//设计专员审核
				activityCustomCriteria.andDesignAuditStatusIsNotNull().andDesignAuditStatusNotEqualTo("0");
			}
			
			if(type.intValue()==4){//法务部审核
				activityCustomCriteria.andLawAuditStatusIsNotNull().andLawAuditStatusNotEqualTo("0");
			}
			
			if(type.intValue()==5){//运营总监审核
				activityCustomCriteria.andCooAuditStatusIsNotNull().andCooAuditStatusNotEqualTo("0");
			}
			
			//排序
			if(type.intValue()==0||type.intValue()==1){
				activityCustomExample.setOrderByClause("ba.update_date ASC");
			}
			if(type.intValue()==2){
				activityCustomExample.setOrderByClause("ba.id desc");
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
					
			//设计审核专员、法务审核专员名称搜索
			if(!StringUtils.isEmpty(request.getParameter("auditName"))){
				activityCustomCriteria.andActivityAuditNameLike("%"+request.getParameter("auditName")+"%",type);
			}
			
			//商家名称
			if(!StringUtils.isEmpty(request.getParameter("shortName"))){
				activityCustomCriteria.andMchtNameLike(request.getParameter("shortName"));
				//活动状态搜索
				if(!StringUtil.isEmpty(request.getParameter("actStatus"))){
					Integer actStatus=Integer.valueOf(request.getParameter("actStatus"));
					activityCustomCriteria.andActivityAreaStatusEqualTo(actStatus);
				}
			}
			//商家编号
			if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
				activityCustomCriteria.andMchtCodeLike(request.getParameter("mchtCode"));
			}
			//设计审核状态、法务审核状态
			if(!StringUtils.isEmpty(request.getParameter("designOrlawAuditStatus"))){
				if(type==3){
					activityCustomCriteria.andDesignAuditStatusEqualTo(request.getParameter("designOrlawAuditStatus"));
				}else{
					activityCustomCriteria.andLawAuditStatusEqualTo(request.getParameter("designOrlawAuditStatus"));
				}
			}
			
			//运营专员审核状态
			if(!StringUtils.isEmpty(request.getParameter("operateAuditStatus"))){
				activityCustomCriteria.andOperateAuditStatusEqualTo(request.getParameter("operateAuditStatus"));
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
			
			//会场ID
			if(!StringUtil.isEmpty(request.getParameter("activityAreaId"))) {
				activityCustomCriteria.andActivityAreaIdEqualTo(Integer.valueOf(request.getParameter("activityAreaId")));
			}
			
			activityCustomExample.setLimitSize(page.getLimitSize());
			activityCustomExample.setLimitStart(page.getLimitStart());
			dataList=activityService.selectActivityCustomByExample(activityCustomExample);
			totalCount=activityService.countActivityCustomByExample(activityCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 会场装修,商品排序（单品活动）
	 * @param model
	 * @param request
	 * @param activityAreaId
	 * @return
	 */
	@RequestMapping(value="/activityArea/activityAreaDesign.shtml")
	private String activityAreaDesign(Model model,HttpServletRequest request,Integer activityAreaId,Integer type){
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
		List<Map<String, Object>> pics = new ArrayList<Map<String, Object>>();
		Map<String, Object> pic=new HashMap<String, Object>();
		pic.put("PICTURE_PATH", activityArea.getTopPic());
		pics.add(pic);
		model.addAttribute("topPic", pics);
		model.addAttribute("activityAreaId", activityAreaId);
		model.addAttribute("type", type);
		model.addAttribute("templetSuffix", activityArea.getTempletSuffix());
		ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
		String mUrl = resource.getString("mUrl");
		model.addAttribute("mUrl", mUrl);
		//type 1.品牌 2.单品
		if(type.intValue()==1){//品牌
			ActivityExample example = new ActivityExample();
			example.setOrderByClause("seq_no asc");
			ActivityExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andStatusEqualTo("6");
			criteria.andCooAuditStatusEqualTo("2");
			criteria.andActivityAreaIdEqualTo(activityAreaId);
			List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(example);
			model.addAttribute("activityCustoms", activityCustoms);
			if(!StringUtils.isEmpty(activityArea.getTempletSuffix())){
				String templetName = activityArea.getTempletSuffix().substring(activityArea.getTempletSuffix().lastIndexOf("/"), activityArea.getTempletSuffix().length());
				if(templetName.equals("/brand_def.html")){
					model.addAttribute("isDefault", 1);
				}else{
					model.addAttribute("isDefault", 0);
				}
			}
			return "/activity/brandDesignList";
		}else if(type.intValue()==2){//单品
			List<ActivityProductCustom> activityProductCustoms = activityProductService.getProductsByActivityAreaId(activityAreaId);
			for (ActivityProductCustom activityProductCustom:activityProductCustoms) {
				activityProductCustom.setProductPic(FileUtil.getMiddleImageName(activityProductCustom.getProductPic()));
			}
			model.addAttribute("activityProductCustoms", activityProductCustoms);
			if(!StringUtils.isEmpty(activityArea.getTempletSuffix())){
				String templetName = activityArea.getTempletSuffix().substring(activityArea.getTempletSuffix().lastIndexOf("/"), activityArea.getTempletSuffix().length());
				if(templetName.equals("/single_def.html")){
					model.addAttribute("isDefault", 1);
				}else{
					model.addAttribute("isDefault", 0);
				}
			}
			return "/activity/singleProductDesignList";
		}else{//商品排序（单品活动）
			ActivityProductCustomExample activityProductCustomExample = new ActivityProductCustomExample();
			activityProductCustomExample.setOrderByClause("ap.seq_no asc");
			ActivityProductCustomExample.ActivityProductCustomCriteria activityProductCustomCriteria = activityProductCustomExample.createCriteria();
			activityProductCustomCriteria.andDelFlagEqualTo("0");
			activityProductCustomCriteria.andRefuseFlagEqualTo("0");
			activityProductCustomCriteria.andCooAuditStatusEqualTo("2");
			activityProductCustomCriteria.andActivityAreaIdEqualTo(activityAreaId);
			List<ActivityProductCustom> activityProductCustoms = activityProductService.getProductsByActivityAreaId(activityAreaId);
			model.addAttribute("activityProductCustoms", activityProductCustoms);
			model.addAttribute("activityAreaType", activityArea.getType());
			return "/activity/singleProductSortList";
		}
		
	}
	
	/**
	 * 保存装修页头部图
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activityArea/saveTopPic.shtml")
	@ResponseBody
	public Map<String, Object> saveTopPic(HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String activityAreaId = request.getParameter("activityAreaId");
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
		String topPic = request.getParameter("topPic");
		activityArea.setTopPic(topPic);
		activityAreaService.updateByPrimaryKeySelective(activityArea);
		resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_SUCCESS.getStateCode());
		return resMap;
	}
	
	/**
	 * 保存排序值（品牌）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activityArea/saveSort.shtml")
	@ResponseBody
	public Map<String, Object> saveSort(HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String sortIds = request.getParameter("sortIds");
		List<Activity> activitys = new ArrayList<Activity>();
		if(!StringUtils.isEmpty(sortIds)){
			String[] sortIdsArray = sortIds.split("\\|");
			for(int i=0;i<sortIdsArray.length;i++){
				String sortId = sortIdsArray[i];
				String[] sortIdArray = sortId.split(",");
				String activityId = sortIdArray[0];
				String sort = sortIdArray[1];
				Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(activityId));
				if(!sort.equals("-1")){
					activity.setSeqNo(Integer.parseInt(sort));
				}else{
					activity.setSeqNo(null);
				}
				activity.setUpdateDate(new Date());
				activity.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				activitys.add(activity);
			}
		}
		if(activitys!=null && activitys.size()>0){
			activityService.updateActivityList(activitys);
			resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_SUCCESS.getStateCode());
		}
		return resMap;
	}
	
	/**
	 * 保存排序值(单品)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activityArea/saveActivityProductSort.shtml")
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
	 * 官方会场
	 * 查看活动
	 * @param model
	 * @param request
	 * @param activityId
	 * @param type：1.运营专员审核，2.所有报名商家活动，3.设计审核，4.法务审核，5.运营总监
	 * @return
	 */
	@RequestMapping(value="/activityArea/venueActivityInfo.shtml")
	public String venueActivityInfo(Model model,HttpServletRequest request,Integer activityId,Integer type){
		ActivityCustom activityCustom=activityService.selectActivityCustomByPrimaryKey(activityId);
		model.addAttribute("activityCustom", activityCustom);
		
		//审核流水表
		List<ActivityAuditLogCustom> activityAuditLog=activityAuditLogService.selectActivityAuditLogList(activityId);
		model.addAttribute("activityAuditLog", activityAuditLog);
		
		model.addAttribute("type", type);
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
			return "/activity/seeDesignOrLawVenueActivityInfo";
		}else{
			return "/activity/seeVenueActivityInfo";
		}
	}
	
	
	/**
	 * 官方会场
	 * 活动审核
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activityArea/venueActivityAudit.shtml")
	@ResponseBody
	public Map<String, Object> venueActivityAudit(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		String statusRemarks=null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			Integer activityId=Integer.valueOf(request.getParameter("activityId"));
			//活动商品列表
//			List<ActivityProduct> ActivityProduct=activityProductService.getActivityProductList(activityId);
			Integer type=Integer.valueOf(request.getParameter("type"));
			if(!StringUtils.isEmpty(request.getParameter("statusRemarks"))){
				statusRemarks=request.getParameter("statusRemarks");
			}
			String status=request.getParameter("status");
			//活动表
			ActivityCustom activity=activityService.selectActivityCustomByPrimaryKey(activityId);
			activity.setUpdateBy(staffId);
			activity.setUpdateDate(new Date());
			//活动审核流水表
			ActivityAuditLog activityAuditLog=new ActivityAuditLog(); 
			activityAuditLog.setCreateBy(staffId);
			activityAuditLog.setCreateDate(new Date());
			activityAuditLog.setActivityId(activityId);
			activityAuditLog.setStatus(status);
			activityAuditLog.setRemarks(statusRemarks);
			if(type.intValue()==1){//运营专员审核
				activityAuditLog.setType(type.toString());
				activity.setOperateAuditBy(staffId);
				activity.setOperateAuditStatus(status);
				activity.setOperateAuditRemarks(statusRemarks);
				if(!StringUtils.isEmpty(request.getParameter("feeRate"))){
					activity.setFeeRate(new BigDecimal(request.getParameter("feeRate")));
					activity.setRemarks(request.getParameter("remarks"));
				}
				if("2".equals(status)){
					activity.setDesignAuditStatus("1");
					if(!StringUtils.isEmpty(activity.getDesignAuditRemarks())){
						activity.setDesignAuditRemarks("");
					}
					activity.setStatus("3");
				}else{
					activity.setStatus("4");
					//官方会场活动驳回给商家后，发送站内信, 消息类型A64
					MsgTplExample msgTplExample=new MsgTplExample();
					MsgTplExample.Criteria msgTplCriteria=msgTplExample.createCriteria();
					msgTplCriteria.andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo("A64");
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
						String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('plat/activity?pageStatus=3')\">"+activity.getActivityAreaName()+"</a>";
						content=content.replaceAll("\\{activity_name}", msgActivityName);
						platformMsg.setTitle("关于会场活动审核驳回通知");
						platformMsg.setContent(content);
						platformMsgService.insertSelective(platformMsg);
					}
				}
				//不管是审核通过还是驳回，商品都直接设置为审核通过
				activityProductService.updateOperateAuditAdopt(activityId,staffId);
			}
			if(type.intValue()==3){//设计审核
				activityAuditLog.setType("2");
				activity.setDesignAuditBy(staffId);
				activity.setDesignAuditStatus(status);
				activity.setDesignAuditRemarks(statusRemarks);
				if("2".equals(status)){//如果是审核通过，那商品只要不是被驳回的商品都设置为审核通过
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
			if(type.intValue()==4){//法务审核
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
				activityAuditLog.setType("3");
				activity.setLawAuditBy(staffId);
				activity.setLawAuditRemarks(statusRemarks);
				activity.setLawAuditStatus(status);
				if("2".equals(status)){//如果是审核通过，那商品只要不是被驳回的商品都设置为审核通过
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
			if(type.intValue()==5){//运营总监审核
				activityAuditLog.setType("4");
				activity.setCooAuditBy(staffId);
				activity.setCooAuditRemarks(statusRemarks);
				activity.setCooAuditStatus(status);
				if("2".equals(status)){
					activity.setStatus("6");
					//官方活动报名审核通过后，发送站内信, 消息类型A62
					MsgTplExample msgTplExample=new MsgTplExample();
					MsgTplExample.Criteria msgTplCriteria=msgTplExample.createCriteria();
					msgTplCriteria.andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo("A62");
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
						String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('plat/activity?pageStatus=2')\">"+activity.getActivityAreaName()+"</a>";
						content=content.replaceAll("\\{activity_name}", msgActivityName);
						//活动开始时间
						content=content.replaceAll("\\{begin_month}", formatMonth.format(activity.getActivityBeginTime())).replaceAll("\\{begin_day}", formatDay.format(activity.getActivityBeginTime())).replaceAll("\\{begin_time}", formatTime.format(activity.getActivityBeginTime()));
						platformMsg.setTitle("关于会场活动审核通过通知");
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
			activityAuditLogService.insertSelective(activityAuditLog);
			activityService.updateByPrimaryKeySelective(activity);
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
	 * 单品会场创建
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/activityArea/createSingleProductActivity.shtml")
	public String createSingleProductActivity(Model model,HttpServletRequest request,Integer id){
		ActivityArea activityArea=null;
		if(id!=null){
			activityArea=activityAreaService.selectByPrimaryKey(id);
		}
		model.addAttribute("activityArea", activityArea);
		return "/activity/createSingleProductActivity";
	}
	
	@RequestMapping(value="/activityArea/addSingleProductActivityArea.shtml")
	@ResponseBody
	public ModelAndView addSingleProductActivityArea(HttpServletRequest request,ActivityArea activityArea){
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			String status=request.getParameter("status");
			if(StringUtils.isEmpty(status)){
				activityArea.setStatus("0");
			}
			if(activityArea.getId()==null){
				activityArea.setCreateBy(staffId);
				activityArea.setCreateDate(new Date());
				activityArea.setEnrollBeginTime(dateFormat.parse(request.getParameter("enrollBeginTime")));
				activityArea.setEnrollEndTime(dateFormat.parse(request.getParameter("enrollEndTime")));
				activityArea.setActivityBeginTime(dateFormat.parse(request.getParameter("activityBeginTime")));
				activityArea.setActivityEndTime(dateFormat.parse(request.getParameter("activityEndTime")));
				activityArea.setSource("1");
				activityArea.setMchtId(0);
				activityArea.setStatus("0");
				activityArea.setPreheatFlag("2");
				activityArea.setPushMchtType("1");
				activityAreaService.insertSelective(activityArea);
			}else{
				ActivityArea a=activityAreaService.selectByPrimaryKey(activityArea.getId());
				a.setUpdateBy(staffId);
				a.setUpdateDate(new Date());
				a.setEnrollBeginTime(dateFormat.parse(request.getParameter("enrollBeginTime")));
				a.setEnrollEndTime(dateFormat.parse(request.getParameter("enrollEndTime")));
				a.setActivityBeginTime(dateFormat.parse(request.getParameter("activityBeginTime")));
				a.setActivityEndTime(dateFormat.parse(request.getParameter("activityEndTime")));
				a.setType(activityArea.getType());
				a.setPic(activityArea.getPic());
				a.setDescription(activityArea.getDescription());
				a.setStatus(activityArea.getStatus());
				a.setName(activityArea.getName());
				a.setLimitMchtNum(activityArea.getLimitMchtNum());
				activityAreaService.updateByPrimaryKeySelective(a);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage,resMap);
		}catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage,resMap);
		}
	}
	
	
	/**
	 * 品牌特卖排期列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activityArea/activityAreDateSchedule.shtml")
	public String activityAreDateSchedule(HttpServletRequest request){
		return "/activity/activityAreaDateSchedule";
	}
	
	
	@RequestMapping(value="/activityArea/activityAreDateScheduleData.shtml")
	@ResponseBody
	public Map<String, Object> activityAreDateScheduleData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityDateSchedule> dataList = null;
		Integer totalCount =0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();//取时间 
		String dateTime=null;
		Date date1=new Date();
		try {
			if(!StringUtils.isEmpty(request.getParameter("dataTime"))){
				dateTime=request.getParameter("dataTime");
				
					date=dateFormat.parse(dateTime);
			}
		    Calendar  calendar = new GregorianCalendar(); 
		    calendar.setTime(date); 
		    calendar.add(calendar.DATE,-14);//把日期往后增加一天.整数往后推,负数往前移动 
		    date=calendar.getTime();   //这个时间就是日期往后推一天的结果
			dataList=activityAreaService.getActivityDateSchedule(date);
				for (int i = 0; i < dataList.size(); i++) {
					if(!StringUtils.isEmpty(request.getParameter("dataTime"))){
						dataList.get(i).setDateTime(dateFormat.parse(dateTime));
					}else{
						dateTime=dateFormat.format(date1);
						dataList.get(i).setDateTime(dateFormat.parse(dateTime));
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		totalCount=activityService.countActivityCustomByExample(activityCustomExample);
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	@RequestMapping(value="/activityArea/singleProductActivityAreaTypeName.shtml")
	@ResponseBody
	public Map<String, Object> singleProductActivityAreaTypeName(HttpServletRequest request,ActivityArea activityArea){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		List<Map<String, Object>> activity=null;
		try {
			if(!StringUtils.isEmpty(request.getParameter("activityType"))){
				Integer type=Integer.valueOf(request.getParameter("activityType"));
				activity=activityAreaService.getTypeEqualToSixOrSeven(type);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
			resMap.put("activity", JSONArray.fromObject(activity));
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return resMap;
		}catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return resMap;
		}
	}
	
	//判断是否存在地址后缀
	@RequestMapping(value = "/activityArea/urlSuffix/checkSuffix.shtml")
	@ResponseBody
	public Map<String, Object> checkSuffix(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String name=request.getParameter("name");
		ActivityAreaExample example = new ActivityAreaExample();
		example.createCriteria().andDelFlagEqualTo("0").andUrlSuffixEqualTo(name);
		
		int count=activityAreaService.countByExample(example);
		resMap.put("count", count);
		if(count>0){
			resMap.put("hased", "1");
		}else{
			resMap.put("hased", "0");
		}
		return resMap;
	}
	
	/**
	 * 预告排序
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/activityArea/previewSortList.shtml")
	public ModelAndView activityAreaPreviewSortList(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/activity/previewSortList";
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
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact);
        
		resMap.put("isContact", isContact);
		return new ModelAndView(rtPage,resMap);
		/*return "/activity/previewSortList";*/
	}
	
	@RequestMapping(value="/activityArea/previewSortListData.shtml")
	@ResponseBody
	public Map<String, Object> activityAreaPreviewSortListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityAreaCustom> dataList = null;
		Integer totalCount =0;
		String source=request.getParameter("source");
		ActivityAreaCustomExample activityAreaCustomExample=new ActivityAreaCustomExample();
		ActivityAreaCustomExample.ActivityAreaCustomCriteria activityAreaCustomCriteria=activityAreaCustomExample.createCriteria();
		activityAreaCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andSourceEqualTo(source).andActivityBeginTimeGreaterThanOrEqualTo(new Date());
		activityAreaCustomExample.setOrderByClause("IFNULL(ba.preheat_seq_no, 999999999),IFNULL(activity_begin_time,''),ba.id,activity_id");
		
		if (!StringUtils.isEmpty(request.getParameter("listType"))){
			activityAreaCustomCriteria.andPreheatFlagEqualTo("2");
		}else{
			activityAreaCustomCriteria.andPreheatFlagEqualTo("1");
		}
		
		if ("1".equals(source)){
			String[] strs={"1","2"};
			List<String> sList = Arrays.asList(strs);
			activityAreaCustomCriteria.andTypeIn(sList);
		}

		//特卖id
		if(!StringUtils.isEmpty(request.getParameter("activityOrAreaId"))){
			Integer activityOrAreaId=Integer.valueOf(request.getParameter("activityOrAreaId"));
			if ("1".equals(source)){
				activityAreaCustomCriteria.andIdEqualTo(activityOrAreaId);
			}else if ("2".equals(source)){
				activityAreaCustomCriteria.andActivityIdEqualTo(activityOrAreaId);
			}
		}
		
		//活动专区名称
		if(!StringUtils.isEmpty(request.getParameter("name"))){
			activityAreaCustomCriteria.andNameEqualTo(request.getParameter("name"));
		}
		
		//商家名称
		if(!StringUtils.isEmpty(request.getParameter("shortName"))){
			activityAreaCustomCriteria.andMchtNameLike(request.getParameter("shortName"));
		}
		//商家编号
		if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
			activityAreaCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
		}
		if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
			Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
			//我对接的商家/我协助的商家
			activityAreaCustomCriteria.andPlatformContactEqualTo(platformContactId);
		}
		totalCount=activityAreaService.countByCustomExample(activityAreaCustomExample);
		activityAreaCustomExample.setLimitSize(page.getLimitSize());
		activityAreaCustomExample.setLimitStart(page.getLimitStart());
		dataList=activityAreaService.selectByCustomExample(activityAreaCustomExample);
		
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 活动预告排期剔除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/activityArea/previewActivityDel.shtml")
	@ResponseBody
	public Map<String, Object> activityAreaPreviewActivityDel(HttpServletRequest request,HttpServletResponse response,Integer id){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			ActivityArea activityArea=new ActivityArea();
			activityArea.setId(id);
			activityArea.setPreheatFlag("2");
			activityArea.setUpdateBy(staffId);
			activityArea.setUpdateDate(new Date());
			activityAreaService.updateByPrimaryKeySelective(activityArea);
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
	 * 选择被剔除的活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activityArea/previewActivityList.shtml")
	public String activityAreaPreviewActivityList(HttpServletRequest request){
		return "/activity/previewActivityList";
	}
	
	/**
	 * 活动预告排期添加
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/activityArea/previewActivityAdd.shtml")
	@ResponseBody
	public Map<String, Object> activityAreaPreviewActivityAdd(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());		
			List <String> idsStr=Arrays.asList(request.getParameter("ids").split(","));
			List<Integer> ids =new ArrayList<Integer>(idsStr.size());
			for(int i=0;i<idsStr.size();i++){
				ids.add(Integer.parseInt(idsStr.get(i)));
			}
			ActivityAreaExample activityAreaExample=new ActivityAreaExample();
			ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
			activityAreaCriteria.andDelFlagEqualTo("0").andIdIn(ids);
			
			ActivityArea activityArea=new ActivityArea();
			activityArea.setPreheatFlag("1");
			activityArea.setUpdateBy(staffId);
			activityArea.setUpdateDate(new Date());
			activityAreaService.updateByExampleSelective(activityArea, activityAreaExample);
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
	
	@RequestMapping(value = "/activityArea/seqEdit/Submit.shtml")
	@ResponseBody
	public Map<String, Object> activityAreaSeqEditSubmit(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			String seqData=request.getParameter("seqData");
			paramMap.put("seqData", seqData);
			paramMap.put("staffId", staffId);
			activityAreaService.updateActivityAreaPreheatSeqNo(paramMap);
			
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 修改会场类型页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activityArea/toEdit.shtml")
	public String toEdit(Model model,HttpServletRequest request){
		String activityAreaId = request.getParameter("id");
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
		if(!StringUtils.isEmpty(activityArea.getTempletSuffix())){
			String templetName = activityArea.getTempletSuffix().substring(activityArea.getTempletSuffix().lastIndexOf("/"), activityArea.getTempletSuffix().length());
			if(templetName.equals("/brand_def.html")||templetName.equals("/single_def.html")){
				model.addAttribute("isDefault", 1);
			}else{
				model.addAttribute("isDefault", 0);
			}
		}
		model.addAttribute("activityAreaId", activityAreaId);
		model.addAttribute("templetSuffix", activityArea.getTempletSuffix());
		return "/activity/toEdit";
	}
	
	/**
	 * 保存修改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activityArea/edit.shtml")
	@ResponseBody
	public Map<String, Object> edit(Model model,HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "修改成功");
		String activityAreaId = request.getParameter("id");
		String isDefault = request.getParameter("isDefault");
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaId));
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
		sysParamCfgCriteria.andParamCodeEqualTo("ACTIVITY_TEMPLET_PREFIX");
		sysParamCfgExample.setLimitSize(1);
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		SysParamCfg sysParamCfg = sysParamCfgs.get(0);
		if(isDefault.equals("1")){//默认模板
			if(activityArea.getType().equals("1")){//1.品牌 2.单品
				activityArea.setTempletSuffix(sysParamCfg.getParamValue()+"brand_def.html");
			}else if(activityArea.getType().equals("2")){
				activityArea.setTempletSuffix(sysParamCfg.getParamValue()+"single_def.html");
			}
		}else{//自定义
			activityArea.setTempletSuffix(request.getParameter("templetSuffix"));
		}
		activityAreaService.updateByPrimaryKeySelective(activityArea);
		return resMap;
	}
}
