package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.vo.ActivityDateSchedule;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityAreaService extends BaseService<ActivityArea, ActivityAreaExample>{
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	
	@Autowired
	private ActivityAreaCustomMapper activityAreaCustomMapper;
	
	@Autowired
	private ActivityDateScheduleMapper activityDateScheduleMapper;
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private MsgTplMapper msgTplMapper;
	
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
	@Autowired
	private ActivityAuditLogMapper activityAuditLogMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private FullCutMapper fullCutMapper;
	
	@Autowired
	private FullGiveMapper fullGiveMapper;
	
	@Autowired
	private FullDiscountMapper fullDiscountMapper;
	
	@Autowired
	private ActivityAreaTempletPparamMapper activityAreaTempletPparamMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	private CouponExchangeCodeMapper couponExchangeCodeMapper;
	
	@Autowired
	private ActivityAreaDecorateMapper activityAreaDecorateMapper;

	@Autowired
	private ActivityAreaPreSellRuleMapper activityAreaPreSellRuleMapper;

	@Autowired
	private AllowanceInfoMapper allowanceInfoMapper;
	
	@Autowired
	public void setactivityAreaMapper(ActivityAreaMapper activityAreaMapper) {
		super.setDao(activityAreaMapper);
		this.activityAreaMapper = activityAreaMapper;
	}
	
	public List<ActivityAreaCustom> selectByCustomExample(ActivityAreaCustomExample example){
		return activityAreaCustomMapper.selectByCustomExample(example);
	}
	   
   public Integer countByCustomExample(ActivityAreaCustomExample example){
	   return activityAreaCustomMapper.countByCustomExample(example);
   }
   
   public ActivityAreaCustom selectByCustomPrimaryKey(Integer id){
   	return activityAreaCustomMapper.selectByCustomPrimaryKey(id);
   }
   
   public List<ActivityDateSchedule> getActivityDateSchedule(Date activityDate){
	   return activityDateScheduleMapper.getActivityDateSchedule(activityDate);
   }
   
   public List<Map<String, Object>> getTypeEqualToSixOrSeven(Integer type){
	   return activityAreaCustomMapper.getTypeEqualToSixOrSeven(type);
   }
   
	public int updateActivityAreaPreheatSeqNo(HashMap<String,Object> map){
		return activityAreaCustomMapper.updateActivityAreaPreheatSeqNo(map);
	}
	
	/**
	 * 
	 * @Title getCreateByList   
	 * @Description TODO(获取30天内已创建人)   
	 * @author pengl
	 * @date 2018年1月22日 上午10:44:43
	 */
	public List<Map<String, Object>> getCreateByList() {
		return activityAreaCustomMapper.getCreateByList();
	}
	
	public void auditActivity(HttpServletRequest request, StaffBean staffBean) {
		String statusAudit = request.getParameter("statusAudit"); // 1.专员审核	2.排期审核
		Integer activityId = Integer.parseInt(request.getParameter("id")); // 活动Id
		String entryPic = request.getParameter("entryPic"); //入口图
		String posterPic = request.getParameter("posterPic"); //头部海报
		String name = request.getParameter("name"); //活动名称
		String operateAuditStatus = request.getParameter("operateAuditStatus"); //专员审核结果	2.通过	3.驳回
		String cooAuditStatus = request.getParameter("cooAuditStatus"); //排期审核结果	2.通过	3.驳回
		String auditRemarks = request.getParameter("auditRemarks"); //审核驳回理由
		Date date = new Date();
		Activity activity = activityMapper.selectByPrimaryKey(activityId);
		ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activity.getActivityAreaId());
		if(!StringUtil.isEmpty(entryPic))
			activity.setEntryPic(entryPic);
		if(!StringUtil.isEmpty(posterPic))
			activity.setPosterPic(posterPic);
		if(!StringUtil.isEmpty(name))
			activity.setName(name);
		activity.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		activity.setUpdateDate(date);
		if(!StringUtil.isEmpty(statusAudit) && "1".equals(statusAudit)) { // 1.专员审核
			activity.setOperateAuditBy(Integer.parseInt(staffBean.getStaffID()));
			activity.setOperateAuditStatus(operateAuditStatus);
			if(!StringUtil.isEmpty(auditRemarks))
				activity.setOperateAuditRemarks(auditRemarks);
			if("3".equals(operateAuditStatus)) { 
				activity.setStatus("4"); //活动状态驳回
				//官方会场活动驳回给商家后，发送站内信, 消息类型A64
				addPlatformMsg(activity.getMchtId(), Const.MSG_TPL_MSG_TYPE_A64, activityId, Integer.parseInt(staffBean.getStaffID()), 
						date, activityArea.getName(), null, activity.getStatus());
			}else{ //通过
				activity.setDesignAuditStatus("1");
				activity.setCooAuditStatus("1");
				if(!StringUtils.isEmpty(activity.getCooAuditRemarks())){
					activity.setCooAuditRemarks("");
				}
			}
			//活动审核流水表添加
			addActivityAuditLog(activity.getId(), "1", operateAuditStatus, Integer.parseInt(staffBean.getStaffID()), auditRemarks, date);
		}
		if(!StringUtil.isEmpty(statusAudit) && "2".equals(statusAudit)) { // 2.排期审核
			activity.setCooAuditBy(Integer.parseInt(staffBean.getStaffID()));
			activity.setCooAuditStatus(cooAuditStatus);
			if(!StringUtil.isEmpty(auditRemarks))
				activity.setCooAuditRemarks(auditRemarks);
			if("3".equals(cooAuditStatus)) {
				activity.setStatus("4"); //活动状态驳回
				//官方会场活动驳回给商家后，发送站内信, 消息类型A64
				addPlatformMsg(activity.getMchtId(), Const.MSG_TPL_MSG_TYPE_A64, activityId, Integer.parseInt(staffBean.getStaffID()), 
						date, activityArea.getName(), null, activity.getStatus());
			}
			if("2".equals(cooAuditStatus)) {
				activity.setStatus("6"); //活动状态通过
				//官方活动报名审核通过后，发送站内信, 消息类型A62
				addPlatformMsg(activity.getMchtId(), Const.MSG_TPL_MSG_TYPE_A62, activityId, Integer.parseInt(staffBean.getStaffID()), 
						date, activityArea.getName(), activityArea.getActivityBeginTime(), activity.getStatus());
			}
			//活动审核流水表添加
			addActivityAuditLog(activity.getId(), "4", cooAuditStatus, Integer.parseInt(staffBean.getStaffID()), auditRemarks, date);
		}
		activityMapper.updateByPrimaryKeySelective(activity); //修改活动
		
	}
	
	/**
	 * 
	 * @Title addPlatformMsg   
	 * @Description TODO(官方会场发送站内信)   
	 * @author pengl
	 * @date 2018年1月31日 下午12:32:14
	 */
	public void addPlatformMsg(Integer mchtId, String msgType, Integer activityId, Integer staffId, 
			Date date, String activityAreaName, Date activityBeginTime, String status ) {
		MsgTplExample msgTplExample = new MsgTplExample();
		MsgTplExample.Criteria msgTplCriteria = msgTplExample.createCriteria();
		msgTplCriteria.andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo(msgType);
		List <MsgTpl> msgTplList = msgTplMapper.selectByExample(msgTplExample);
		if(msgTplList.size() > 0) {
			MsgTpl msgTpl = new MsgTpl();
			msgTpl = msgTplList.get(0);
			PlatformMsg platformMsg = new PlatformMsg();
			platformMsg.setMchtId(mchtId);
			platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A6);
			platformMsg.setBizId(activityId);
			platformMsg.setStatus("0");
			platformMsg.setCreateBy(staffId);
			platformMsg.setCreateDate(date);
			platformMsg.setUpdateDate(date);
			String content = msgTpl.getContent();
			if("6".equals(status)) { //审核通过
				SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
				SimpleDateFormat formatDay = new SimpleDateFormat("dd");
				SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
				//特卖活动名称
				String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('plat/activity?pageStatus=2')\">"+activityAreaName+"</a>";
				content=content.replaceAll("\\{activity_name}", msgActivityName);
				//活动开始时间
				content=content.replaceAll("\\{begin_month}", formatMonth.format(activityBeginTime)).replaceAll("\\{begin_day}", formatDay.format(activityBeginTime)).replaceAll("\\{begin_time}", formatTime.format(activityBeginTime));
				platformMsg.setTitle("关于会场活动审核通过通知");
				platformMsg.setContent(content);
			}
			if("4".equals(status)) {
				//会场活动名称
				String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('plat/activity?pageStatus=3')\">"+activityAreaName+"</a>";
				content=content.replaceAll("\\{activity_name}", msgActivityName);
				platformMsg.setTitle("关于会场活动审核驳回通知");
				platformMsg.setContent(content);
			}
			platformMsgMapper.insertSelective(platformMsg);
		}
	}
	
	/**
	 * 
	 * @Title addActivityAuditLog   
	 * @Description TODO(活动审核流水表添加)   
	 * @author pengl
	 * @date 2018年1月31日 上午11:36:19
	 */
	public void addActivityAuditLog(Integer activityId, String type, String status, Integer staffId, String remarks, Date date) {
		ActivityAuditLog activityAuditLog = new ActivityAuditLog();
		activityAuditLog.setActivityId(activityId);
		activityAuditLog.setType(type);
		activityAuditLog.setStatus(status);
		activityAuditLog.setCreateBy(staffId);
		activityAuditLog.setCreateDate(date);
		activityAuditLog.setUpdateDate(date);
		if(!StringUtil.isEmpty(remarks))
			activityAuditLog.setRemarks(remarks);
		activityAuditLog.setDelFlag("0");
		activityAuditLogMapper.insertSelective(activityAuditLog);
	}
	
	/**
	 * 
	 * @Title updateCoupon   
	 * @Description TODO(更新优惠劵状态和时间)   
	 * @author pengl
	 * @date 2018年1月31日 下午12:35:10
	 */
	public void updateCoupon(Integer activityAreaId, Integer staffId, Date activityBeginTime, Date activityEndTime, Date date) {
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria couponCriteria = couponExample.createCriteria();
		couponCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId);
		List<Coupon> couponList = couponMapper.selectByExample(couponExample);
		for(Coupon cou : couponList) {
			Coupon coupon = new Coupon();
			coupon.setId(cou.getId());
			coupon.setRecBeginDate(activityBeginTime);
			coupon.setRecEndDate(activityEndTime);
			coupon.setStatus("1");
			coupon.setExpiryType("1");
			coupon.setExpiryBeginDate(activityBeginTime);
			coupon.setExpiryEndDate(activityEndTime);
			coupon.setUpdateBy(staffId);
			coupon.setUpdateDate(date);
			couponMapper.updateByPrimaryKeySelective(coupon);
		}
	}
	
	/**
	 * 
	 * @Title updateActivityAreaPreferential   
	 * @Description TODO(修改活动会场优惠与时间)   
	 * @author pengl
	 * @date 2018年2月2日 下午1:39:18
	 */
	public Map<String, Object> updateActivityAreaPreferential(HttpServletRequest request, StaffBean staffBean) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = null;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//活动会场开始时间
			String activityBeginTime = request.getParameter("activityBeginTime");
			//活动会场结束时间
			String activityEndTime = request.getParameter("activityEndTime");
			//优惠券
			String jsonCoupon = request.getParameter("jsonCoupon");
			//满减
			String jsonFullCut = request.getParameter("jsonFullCut");
			//多买多送 
			String jsonFullDiscount = request.getParameter("jsonFullDiscount");
			//购物津贴 rule
			String jsonAllowance = request.getParameter("jsonAllowance");

			//0：无 、1：优惠劵、2：满减、3：满赠、4：多买优惠、5：购物津贴
			String preferentialType = request.getParameter("preferentialType");
			//活动会场ID
			String preheatTime = request.getParameter("preheatTime");
			//定金开始时间
			Integer activityAreaId = Integer.valueOf(request.getParameter("activityAreaId"));
			ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activityAreaId);
			if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "1".equals(activityArea.getPreferentialType())) { //1：优惠劵
				if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup()) && !StringUtil.isEmpty(activityArea.getType()) && !"3".equals(activityArea.getType())) {
					String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
					for (int i = 0; i < preferentialIdGroups.length; i++) {
						Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
						if(coupon.getRecQuantity() > 0) {
							code = StateCode.JSON_AJAX_ERROR.getStateCode();
							msg = "优惠券已被领取，不能修改优惠与时间！";
							break;
						}
					}
				}else if(!StringUtil.isEmpty(activityArea.getType()) && "3".equals(activityArea.getType())){
					String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
					for (int i = 0; i < preferentialIdGroups.length; i++) {
						Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
						CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
						couponExchangeCodeExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(coupon.getId());
						List<CouponExchangeCode> couponExchangeCodeList = couponExchangeCodeMapper.selectByExample(couponExchangeCodeExample);
						if(couponExchangeCodeList != null && couponExchangeCodeList.size() > 0) {
							code = StateCode.JSON_AJAX_ERROR.getStateCode();
							msg = "优惠券已生成游离码，不能修改优惠与时间！";
							break;
						}
					}
				}
			}
			if(StringUtil.isEmpty(code)) {
				ActivityArea activityAreaNew = new ActivityArea();
				activityAreaNew.setId(activityArea.getId());
				activityAreaNew.setActivityBeginTime(sdf.parse(activityBeginTime));
				activityAreaNew.setActivityEndTime(sdf.parse(activityEndTime));
				activityAreaNew.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
				activityAreaNew.setUpdateDate(date);
				if(!StringUtil.isEmpty(activityArea.getStatus()) && "1".equals(activityArea.getStatus()) 
						&& !StringUtil.isEmpty(activityArea.getPreferentialType()) && !"0".equals(activityArea.getPreferentialType())) { //优惠内容，在这种情况下不可编辑：  活动启用 且  优惠方式！=无
					if("1".equals(activityArea.getPreferentialType())) { //优惠券
						if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
							String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
							for (int i = 0; i < preferentialIdGroups.length; i++) {
								Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
								coupon.setRecBeginDate(sdf.parse(activityBeginTime));
								coupon.setRecEndDate(sdf.parse(activityEndTime));
								coupon.setExpiryBeginDate(sdf.parse(activityBeginTime));
								coupon.setExpiryEndDate(sdf.parse(activityEndTime));
								coupon.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
								coupon.setUpdateDate(date);
								couponMapper.updateByPrimaryKeySelective(coupon); //修改优惠券
							}
						}
					}
					activityAreaMapper.updateByPrimaryKeySelective(activityAreaNew); //修改活动会场
				}else {
					//删除
					if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "1".equals(activityArea.getPreferentialType())) { //优惠券
						if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
							String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
							for (int i = 0; i < preferentialIdGroups.length; i++) {
								Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
								coupon.setDelFlag("1"); //删除
								coupon.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
								coupon.setUpdateDate(date);
								/*couponMapper.updateByPrimaryKeySelective(coupon);*/
								couponMapper.updateByPrimaryKey(coupon);
							}
						}
					}else if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "2".equals(activityArea.getPreferentialType())) { //满减
						if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
							String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
							for (int i = 0; i < preferentialIdGroups.length; i++) {
								FullCut fullCut = fullCutMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
								fullCut.setDelFlag("1"); //删除
								fullCut.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
								fullCut.setUpdateDate(date);
								fullCutMapper.updateByPrimaryKeySelective(fullCut);
							}
						}
					}else if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "3".equals(activityArea.getPreferentialType())) { //满赠
						if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
							String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
							for (int i = 0; i < preferentialIdGroups.length; i++) {
								FullGive fullGive = fullGiveMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
								fullGive.setDelFlag("1"); //删除
								fullGive.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
								fullGive.setUpdateDate(date);
								fullGiveMapper.updateByPrimaryKeySelective(fullGive);
							}
						}
					}else if(!StringUtil.isEmpty(activityArea.getPreferentialType()) && "4".equals(activityArea.getPreferentialType())) { //多买优惠
						if(!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())) {
							String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
							for (int i = 0; i < preferentialIdGroups.length; i++) {
								FullDiscount fullDiscount = fullDiscountMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
								fullDiscount.setDelFlag("1"); //删除
								fullDiscount.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
								fullDiscount.setUpdateDate(date);
								fullDiscountMapper.updateByPrimaryKeySelective(fullDiscount);
							}
						}
					}else if (!StringUtil.isEmpty(activityArea.getPreferentialType())&& "5".equals(activityArea.getPreferentialType())){ //购物津贴
						if (!StringUtil.isEmpty(activityArea.getPreferentialIdGroup())){
							String[] preferentialIdGroups = activityArea.getPreferentialIdGroup().split(",");
							for (int i = 0; i < preferentialIdGroups.length; i++) {
								AllowanceInfo allowanceInfo = allowanceInfoMapper.selectByPrimaryKey(Integer.parseInt(preferentialIdGroups[i]));
								allowanceInfo.setDelFlag("1");//删除
								allowanceInfo.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
								allowanceInfo.setUpdateDate(date);
								allowanceInfoMapper.updateByPrimaryKeySelective(allowanceInfo);
							}
						}
					}
					String preferentialIdGroup = "";
					//新增
					if(!StringUtil.isEmpty(preferentialType) && "1".equals(preferentialType)) { //优惠券
						Coupon coupon = null;
						JSONArray couponList = JSONArray.fromObject(jsonCoupon);
						String recLimitType = request.getParameter("recLimitType"); //限领类型
						List <String> couponIdStr = new ArrayList<String>();
						
						for (int i = 0; i < couponList.size(); i++) {
							String recType = couponList.getJSONObject(i).getString("recType"); //领取方式  1：免费  2：金币兑换		
							if(!"0".equals(couponList.getJSONObject(i).getString("couponId")) && !StringUtils.isEmpty(couponList.getJSONObject(i).getString("couponId"))) {
								coupon = couponMapper.selectByPrimaryKey(Integer.valueOf(couponList.getJSONObject(i).getString("couponId")));
							}else{
								coupon = new Coupon();
								coupon.setActivityAreaId(activityArea.getId());
								coupon.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
								coupon.setCreateDate(date);
								coupon.setUpdateDate(date);
								coupon.setRang("2");
								if(activityArea.getType().equals("3")){//推广会场
									coupon.setBelong("2");//2.费用归属 商家
								}else{
									coupon.setBelong(request.getParameter("belong"));//  1.费用归属 平台   2.费用归属 商家
								}
								coupon.setMinMemberGroup(1);
								coupon.setExpiryType("1");
							}
							if(!StringUtil.isEmpty(request.getParameter("belong"))) {
								coupon.setBelong(request.getParameter("belong"));//  1.费用归属 平台   2.费用归属 商家
							}
							if (!StringUtil.isEmpty(activityArea.getStatus()) && "1".equals(activityArea.getStatus())){ //会场活动启用，优惠券启用
								coupon.setStatus("1");
							}else { //会场活动未启用，优惠券未启用
								coupon.setStatus("0");
							}
							coupon.setDelFlag("0");
							coupon.setRecBeginDate(sdf.parse(activityBeginTime));
							coupon.setRecEndDate(sdf.parse(activityEndTime));
							coupon.setExpiryBeginDate(sdf.parse(activityBeginTime));
							coupon.setExpiryEndDate(sdf.parse(activityEndTime));
							coupon.setMoney(new BigDecimal(couponList.getJSONObject(i).getString("money")));
							coupon.setMinimum(new BigDecimal(couponList.getJSONObject(i).getString("minimum")));
							coupon.setName("满"+couponList.getJSONObject(i).getString("minimum")+"-"+couponList.getJSONObject(i).getString("money")+"元优惠券");
							coupon.setGrantQuantity(Integer.valueOf(couponList.getJSONObject(i).getString("grantQuantity")));
							coupon.setPreferentialType(preferentialType);//优惠类型：1;
							
							if (Integer.valueOf(couponList.getJSONObject(i).getString("needIntegral"))>0 && Integer.valueOf(recType)!=4){
								coupon.setRecType("2");//限领方式;
								coupon.setNeedIntegral(Integer.valueOf(couponList.getJSONObject(i).getString("needIntegral")));
							}else if (Integer.valueOf(couponList.getJSONObject(i).getString("needIntegral"))<=0 && Integer.valueOf(recType)!=4) {
								coupon.setRecType(recType);//限领方式;
							}else {
								coupon.setRecType("4");//限领方式;
								coupon.setNeedIntegral(Integer.valueOf(couponList.getJSONObject(i).getString("needIntegral")));
							}
							/*if(Integer.valueOf(recType)==2) {
								coupon.setNeedIntegral(Integer.valueOf(couponList.getJSONObject(i).getString("needIntegral")));
							}*/
							if(StringUtil.isEmpty(recLimitType)) {
								if(StringUtil.isEmpty(coupon.getRecLimitType())) {
									coupon.setRecLimitType("3"); //3：不限 
								}
							}else {
								coupon.setRecLimitType(recLimitType);
								if(Integer.valueOf(recLimitType)==2) {
									coupon.setRecEach(Integer.valueOf(request.getParameter("recEach")));
								}
							}
							if(!"0".equals(couponList.getJSONObject(i).getString("couponId")) && !StringUtils.isEmpty(couponList.getJSONObject(i).getString("couponId"))){
								couponMapper.updateByPrimaryKeySelective(coupon);
							}else{
								couponMapper.insertSelective(coupon);
							}
							couponIdStr.add(coupon.getId().toString());
						}
						preferentialIdGroup = org.apache.commons.lang.StringUtils.join(couponIdStr, ",");
					}else if(!StringUtil.isEmpty(preferentialType) && "2".equals(preferentialType)) { //满减
						String rule = "";
						FullCut fullCut = null;
						if(!StringUtils.isEmpty(request.getParameter("fullCutId"))) {
							fullCut = fullCutMapper.selectByPrimaryKey(Integer.valueOf(request.getParameter("fullCutId")));
							fullCut.setDelFlag("0");
							fullCut.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
							fullCut.setUpdateDate(date);
						}else{
							fullCut = new FullCut();
							fullCut.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
							fullCut.setCreateDate(date);
							fullCut.setUpdateDate(date);
							fullCut.setRang("2");
							fullCut.setBelong("2");
							fullCut.setDelFlag("0");
						}
						String ladderFlag = request.getParameter("ladderFlag");
						JSONArray fullCutList = JSONArray.fromObject(jsonFullCut);
						String sumFlag = null;
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
						fullCut.setActivityAreaId(activityArea.getId());
						fullCut.setLadderFlag(ladderFlag);
						fullCut.setSumFlag(sumFlag);
						fullCut.setBelong(request.getParameter("belong"));//承担方
						if(!StringUtils.isEmpty(request.getParameter("fullCutId"))) {
							fullCutMapper.updateByPrimaryKeySelective(fullCut);
						}else {
							fullCutMapper.insertSelective(fullCut);
						}
						preferentialIdGroup = fullCut.getId().toString();
					}else if(!StringUtil.isEmpty(preferentialType) && "3".equals(preferentialType)) { //满赠
						FullGive fullGive = null;
						if(!StringUtils.isEmpty(request.getParameter("fullGiveId"))) {
							fullGive = fullGiveMapper.selectByPrimaryKey(Integer.valueOf(request.getParameter("fullGiveId")));
							fullGive.setDelFlag("0");
							fullGive.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
							fullGive.setUpdateDate(date);
						}else{
							fullGive = new FullGive();
							fullGive.setBelong("1");
							fullGive.setRang("2");
							fullGive.setDelFlag("0");
							fullGive.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
							fullGive.setCreateDate(date);
							fullGive.setUpdateDate(date);
						}
						fullGive.setActivityAreaId(activityArea.getId());
						fullGive.setType(request.getParameter("type"));
						if(Integer.valueOf(request.getParameter("type"))==1) {
							fullGive.setMinimum(new BigDecimal(request.getParameter("fullGiveMinimum")));
						}
						if(!StringUtils.isEmpty(request.getParameter("sumFlag"))) {
							fullGive.setSumFlag(request.getParameter("sumFlag"));
						}
						String couponFlag = request.getParameter("couponFlag");
						if(StringUtil.isEmpty(couponFlag)) {
							couponFlag = "0";
						}
						fullGive.setCouponFlag(couponFlag);
						if(Integer.valueOf(couponFlag)==1) {
							fullGive.setCouponIdGroup(request.getParameter("preferentialIdGroup"));
						}else{
							fullGive.setCouponIdGroup("");
						}
						if(!StringUtils.isEmpty(request.getParameter("integralFlag"))) {
							fullGive.setIntegralFlag(request.getParameter("integralFlag"));
							if("1".equals(request.getParameter("integralFlag"))) {
								fullGive.setIntegral(Integer.valueOf(request.getParameter("integral")));
							}
						}
						fullGive.setBelong(request.getParameter("belong"));//承担方
						if(!StringUtils.isEmpty(request.getParameter("fullGiveId"))) {
							fullGiveMapper.updateByPrimaryKeySelective(fullGive);
						}else {
							fullGiveMapper.insertSelective(fullGive);
						}
						preferentialIdGroup = fullGive.getId().toString();
					}else if(!StringUtil.isEmpty(preferentialType) && "4".equals(preferentialType)) { //多买优惠
						Integer type=Integer.valueOf(request.getParameter("fullDiscount"));
						FullDiscount fullDiscount = null;
						if(!StringUtils.isEmpty(request.getParameter("fullDiscountId"))){
							fullDiscount = fullDiscountMapper.selectByPrimaryKey(Integer.valueOf(request.getParameter("fullDiscountId")));
							fullDiscount.setDelFlag("0");
							fullDiscount.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
							fullDiscount.setUpdateDate(date);
						}else{
							fullDiscount=new FullDiscount();
							fullDiscount.setBelong("2");
							fullDiscount.setRang("2");
							fullDiscount.setDelFlag("0");
							fullDiscount.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
							fullDiscount.setCreateDate(date);
							fullDiscount.setUpdateDate(date);
						}
						fullDiscount.setActivityAreaId(activityArea.getId());
						fullDiscount.setType(type.toString());
						String rule = "";
						if(type.intValue()==1 || type.intValue()==2 || type.intValue()==4) {
							fullDiscount.setType(request.getParameter("fullDiscount"));
							if(type.intValue()==1) {
								fullDiscount.setRule(request.getParameter("fullOfOne")+","+request.getParameter("fullGiftsOneName"));
							}else if(type.intValue()==2){
								fullDiscount.setRule(request.getParameter("fullOfTwo")+","+request.getParameter("fullGiftsTwoName"));
							}else {
								fullDiscount.setRule(request.getParameter("fullOfFour")+","+request.getParameter("fullGiftsFourName"));
							}
						}
						if(type.intValue()==3) {
							JSONArray fullDiscountList = JSONArray.fromObject(jsonFullDiscount);
							for (int i = 0; i < fullDiscountList.size(); i++) {
								String fullPieces=fullDiscountList.getJSONObject(i).getString("fullPieces");
								String discountName=fullDiscountList.getJSONObject(i).getString("discountName");
								if(rule!=""){
									rule=rule+"|";
								}
								rule += fullPieces + "," + discountName;
								fullDiscount.setRule(rule);
							}
						}
						fullDiscount.setBelong(request.getParameter("belong"));//承担方
						if(!StringUtils.isEmpty(request.getParameter("fullDiscountId"))){
							fullDiscountMapper.updateByPrimaryKeySelective(fullDiscount);
						}else{
							fullDiscountMapper.insertSelective(fullDiscount);
						}
						preferentialIdGroup = fullDiscount.getId().toString();
					}else if (!StringUtil.isEmpty(preferentialType) && "5".equals(preferentialType)){ //购物津贴
						String rule = "";
						AllowanceInfo allowanceInfo = null;
						if(!StringUtils.isEmpty(request.getParameter("allowanceInfoId"))) {
							allowanceInfo = allowanceInfoMapper.selectByPrimaryKey(Integer.valueOf(request.getParameter("allowanceInfoId")));
							allowanceInfo.setDelFlag("0");
							allowanceInfo.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
							allowanceInfo.setUpdateDate(date);
						}else{
							allowanceInfo = new AllowanceInfo();
							allowanceInfo.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
							allowanceInfo.setCreateDate(date);
							allowanceInfo.setUpdateDate(date);
							allowanceInfo.setRang("2");
							allowanceInfo.setBelong("2");
							allowanceInfo.setDelFlag("0");
						}
						JSONArray jsonAllowancelist = JSONArray.fromObject(jsonAllowance);
						for (int i = 0; i < jsonAllowancelist.size(); i++) {
							String fullName = jsonAllowancelist.getJSONObject(i).getString("fullMoney");
							String reduceName = jsonAllowancelist.getJSONObject(i).getString("reduceMoney");
							if(!"".equals(rule)){
								rule = rule + "|";
							}
							rule += fullName +"," + reduceName;
						}
						allowanceInfo.setRule(rule);
						allowanceInfo.setActivityAreaId(activityArea.getId());
						allowanceInfo.setLadderFlag("0");
						allowanceInfo.setSumFlag("1");
						allowanceInfo.setBelong("2");//承担方
						allowanceInfo.setUsageBeginTime(sdf.parse(request.getParameter("usageBeginTime")));
						allowanceInfo.setUsageEndTime(sdf.parse(request.getParameter("usageEndTime")));
						allowanceInfo.setAllowanceDescription(request.getParameter("allowanceDescription"));
						if(!StringUtils.isEmpty(request.getParameter("allowanceInfoId"))) {
							allowanceInfoMapper.updateByPrimaryKeySelective(allowanceInfo);
						}else {
							allowanceInfoMapper.insertSelective(allowanceInfo);
						}
						preferentialIdGroup = allowanceInfo.getId().toString();

					}
					activityAreaNew.setPreferentialIdGroup(preferentialIdGroup);
					activityAreaNew.setPreferentialType(preferentialType);
					if(!StringUtils.isEmpty(preheatTime)){
						activityAreaNew.setPreheatTime(sdf.parse(preheatTime));
					}
					activityAreaMapper.updateByPrimaryKeySelective(activityAreaNew); //修改活动会场
				}
			}
			if(StringUtil.isEmpty(msg)) {
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put("statusCode", code);
		resMap.put("message", msg);
		return resMap;
	}
	
	public void updateTempletType(HttpServletRequest request, StaffBean staffBean, Integer activityAreaId, String type, String templetType) {
		Date date = new Date();
		ActivityAreaTempletPparamExample activityAreaTempletPparamExample = new ActivityAreaTempletPparamExample();
		ActivityArea activityAreaSele = activityAreaMapper.selectByPrimaryKey(activityAreaId);
		ActivityArea activityArea = new ActivityArea();
		activityArea.setId(activityAreaId);
		activityArea.setTempletType(templetType);
		activityArea.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		activityArea.setUpdateDate(date);
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
		sysParamCfgCriteria.andParamCodeEqualTo("ACTIVITY_TEMPLET_PREFIX");
		sysParamCfgExample.setLimitSize(1);
		List<SysParamCfg> sysParamCfgs = sysParamCfgMapper.selectByExample(sysParamCfgExample);
		SysParamCfg sysParamCfg = sysParamCfgs.get(0);
		if("1".equals(templetType)) { //默认模板
			String defaultName = ""; 
			if("1".equals(type)) { //会场类型(1品牌活动 2单品活动)
				defaultName = "brand_def.html";
			}else if("2".equals(type)) {
				defaultName = "single_def.html";
			}
			activityArea.setTempletSuffix(sysParamCfg.getParamValue()+defaultName);
		}else if("2".equals(templetType)) { //代码模板
			activityArea.setTempletSuffix(sysParamCfg.getParamValue()+"single_day.html");
			activityAreaTempletPparamExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId);
			List<ActivityAreaTempletPparam> activityAreaTempletPparamList = activityAreaTempletPparamMapper.selectByExample(activityAreaTempletPparamExample);
			if(activityAreaTempletPparamList == null || activityAreaTempletPparamList.size() == 0) {
				ActivityAreaTempletPparam activityAreaTempletPparam = new ActivityAreaTempletPparam();
				activityAreaTempletPparam.setName(activityAreaSele.getName());
				activityAreaTempletPparam.setActivityAreaId(activityAreaId);
				activityAreaTempletPparam.setSuffix("single_day.html");
				activityAreaTempletPparam.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
				activityAreaTempletPparam.setCreateDate(date);
				activityAreaTempletPparam.setUpdateDate(date);
				activityAreaTempletPparam.setDelFlag("0");
				activityAreaTempletPparamMapper.insertSelective(activityAreaTempletPparam); //新增会场模板参数
			}else {
				for(ActivityAreaTempletPparam activityAreaTempletPparam : activityAreaTempletPparamList) {
					activityAreaTempletPparam.setSuffix("single_day.html");
					activityAreaTempletPparam.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
					activityAreaTempletPparam.setUpdateDate(date);
					activityAreaTempletPparamMapper.updateByPrimaryKeySelective(activityAreaTempletPparam); //修改会场模板参数
				}
			}
		}else if(templetType.equals("3")){//自定义模板
			activityArea.setTempletSuffix(sysParamCfg.getParamValue()+"brand_decorate.html");
			ActivityAreaDecorateExample aade = new ActivityAreaDecorateExample();
			ActivityAreaDecorateExample.Criteria aadec = aade.createCriteria();
			aadec.andDelFlagEqualTo("0");
			aadec.andActivityAreaIdEqualTo(activityAreaId);
			List<ActivityAreaDecorate> activityAreaDecorates = activityAreaDecorateMapper.selectByExample(aade);
			if(activityAreaDecorates!=null && activityAreaDecorates.size()>0){
				ActivityAreaDecorate activityAreaDecorate = activityAreaDecorates.get(0);
				activityAreaDecorate.setIsEffect("1");//启用
				activityAreaDecorate.setUpdateDate(new Date());
				activityAreaDecorateMapper.updateByPrimaryKeySelective(activityAreaDecorate);
			}
		}
		activityAreaMapper.updateByPrimaryKeySelective(activityArea); //修改活动会场信息
	}
	
	public List<String> getEntryPics(HashMap<String, Object> paramMap){
		return activityAreaCustomMapper.getEntryPics(paramMap);
	}
	
	public List<Integer> getProductIdsByActivityAreaIds(String activityAreaIds){
		return activityAreaCustomMapper.getProductIdsByActivityAreaIds(activityAreaIds);
	}

	public int countActivityByMchtId(Integer mchtId) {
		return activityAreaCustomMapper.countActivityByMchtId(mchtId);
	}

    public void addPresellactivityArea(ActivityArea activityAreaNew, ActivityAreaPreSellRule activityAreaPreSellRule) {
		this.insertSelective(activityAreaNew);
		activityAreaPreSellRule.setActivityAreaId(activityAreaNew.getId());
        activityAreaPreSellRuleMapper.insertSelective(activityAreaPreSellRule);
    }

    public void updatePresellactivityArea(ActivityArea activityAreaNew, ActivityAreaPreSellRule activityAreaPreSellRule, Integer staffId, Date date) {
        this.updateByPrimaryKeySelective(activityAreaNew);
        ActivityAreaPreSellRuleExample activityAreaPreSellRuleExample = new ActivityAreaPreSellRuleExample();
        activityAreaPreSellRuleExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaNew.getId());
		List<ActivityAreaPreSellRule> activityAreaPreSellRules = activityAreaPreSellRuleMapper.selectByExample(activityAreaPreSellRuleExample);
		if(activityAreaPreSellRules != null && activityAreaPreSellRules.size() > 0){
			activityAreaPreSellRule.setId(activityAreaPreSellRules.get(0).getId());
			activityAreaPreSellRule.setUpdateBy(staffId);
			activityAreaPreSellRule.setUpdateDate(date);
			activityAreaPreSellRuleMapper.updateByPrimaryKeySelective(activityAreaPreSellRule);
		}else{
			activityAreaPreSellRule.setId(null);
			activityAreaPreSellRule.setActivityAreaId(activityAreaNew.getId());
			activityAreaPreSellRule.setCreateBy(staffId);
			activityAreaPreSellRule.setCreateDate(date);
			activityAreaPreSellRule.setDelFlag("0");
			activityAreaPreSellRuleMapper.insertSelective(activityAreaPreSellRule);
		}

    }

	public void updateShareIntegralIsNull(Integer id) {
		activityAreaCustomMapper.updateShareIntegralIsNull(id);
	}
}
