package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.vo.ResponseMsg;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ActivityService extends BaseService<Activity, ActivityExample>{
	@Autowired
	private ActivityMapper activityMapper;
	@Resource
	private ActivityCustomMapper activityCustomMapper;
	
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	
	@Autowired
	private MsgTplMapper msgTplMapper;
	
	@Autowired
	private ActivityAuditLogMapper activityAuditLogMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private ActivityGroupMapper activityGroupMapper;
	
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	
	@Autowired
	private ActivityAreaCustomMapper activityAreaCustomMapper;

	@Autowired
	private ActivityAreaService activityAreaService;

	@Autowired
	private ActivityGroupSerivce activityGroupSerivce;
	@Autowired
	public void setActivityMapper(ActivityMapper activityMapper) {
		super.setDao(activityMapper);
		this.activityMapper = activityMapper;
	}

	/**
	 * 总条数
	 */
	public int countActivityCustomByExample(ActivityExample example){
		return activityCustomMapper.countByExample(example);
	}

	/**
	 * 列表
	 */
    public List<ActivityCustom> selectActivityCustomByExample(ActivityExample example){
    	return activityCustomMapper.selectByExample(example);
    }
    /**
     * 详情
     */
    public ActivityCustom selectActivityCustomByPrimaryKey(Integer id){
    	return activityCustomMapper.selectByPrimaryKey(id);
    }
    /**
     * 根据活动id统计商品库存
     * @param activityId
     * @return
     */
    public Integer selectByProductIdList(Integer activityId){
    	return activityCustomMapper.selectByProductIdList(activityId);
    }
    
    public List<ActivityCustom> selectMchtActivityList(Map<String, Object> map){
    	return activityCustomMapper.selectMchtActivityList(map);
    }
    
    /**
     * 通过专区id过去活动id集合
     * @param activityAreaId
     * @return
     */
    public List<Integer> getActivityByIdList(Integer activityAreaId){
    	return activityCustomMapper.getActivityByIdList(activityAreaId);
    }

	public void updateActivityList(List<Activity> activitys) {
		for(Activity activity:activitys){
			activityMapper.updateByPrimaryKey(activity);
		}
	}
	
	/**
	 * 
	 * @Title getAuditByList   
	 * @Description TODO(获取30天内的已审核人)   
	 * @author pengl
	 * @date 2018年1月10日 下午6:16:02
	 */
	public List<Map<String, Object>> getAuditByList(Map<String, Object> map) {
		return activityCustomMapper.getAuditByList(map);
	}
	
	/**
	 * 
	 * @Title selectByCustomExample   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月11日 下午2:15:13
	 */
	public List<ActivityNewCustom> selectByCustomExample(ActivityCustomExample activityCustomExample) {
		return activityCustomMapper.selectByCustomExample(activityCustomExample);
	}
	
	/**
	 * 
	 * @Title selectByCustomPrimaryKey   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月12日 上午10:19:09
	 */
	public ActivityNewCustom selectByCustomPrimaryKey(Integer id) {
		return activityCustomMapper.selectByCustomPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Title auditActivity   
	 * @Description TODO(专员审核与排期审核)
	 * @author pengl
	 * @date 2018年1月15日 上午10:11:43
	 */
	public void auditActivity(HttpServletRequest request, StaffBean staffBean) throws IOException {
		String statusAudit = request.getParameter("statusAudit"); // 1.专员审核	2.排期审核	3.设计审核
		Integer activityId = Integer.parseInt(request.getParameter("id")); // 活动Id
		String entryPic = request.getParameter("entryPic"); //入口图
		String posterPic = request.getParameter("posterPic"); //头部海报
		String brandteamPic = request.getParameter("brandteamPic"); //品牌团入口图
		String name = request.getParameter("name"); //活动名称
		Integer productTypeId = null;
		if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) { //1级类目ID
			productTypeId = Integer.parseInt(request.getParameter("productTypeId")); 
		}
		Integer productTypeSecondId = null;
		if(!StringUtil.isEmpty(request.getParameter("productTypeSecondId"))) { //2级类目ID
			productTypeSecondId = Integer.parseInt(request.getParameter("productTypeSecondId")); 
		}
		String benefitPoint = request.getParameter("benefitPoint"); //利益点
		String operateAuditStatus = request.getParameter("operateAuditStatus"); //专员审核结果	2.通过	3.驳回
		String preheatTime = request.getParameter("preheatTime"); //预热时间
		String activityBeginTime = request.getParameter("activityBeginTime"); //活动开始时间
		String activityEndTime = request.getParameter("activityEndTime"); //活动结束时间
		String cooAuditStatus = request.getParameter("cooAuditStatus"); //排期审核结果	2.通过	3.驳回
		String designAuditStatus = request.getParameter("designAuditStatus"); //设计审核结果	2.通过	3.驳回
		String auditRemarks = request.getParameter("auditRemarks"); //审核驳回理由
		String preSellAuditStatus = request.getParameter("preSellAuditStatus"); //预售审核状态
		String preSellAuditRemarks = request.getParameter("preSellAuditRemarks"); //预售审核备注
		Integer activityGroupId = null;
		if(!StringUtil.isEmpty(request.getParameter("activityGroupId"))) { //特卖分组
			activityGroupId = Integer.parseInt(request.getParameter("activityGroupId"));
		}
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Activity activity = activityMapper.selectByPrimaryKey(activityId);
			ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activity.getActivityAreaId());
			activity.setPreSellAuditStatus(preSellAuditStatus);
			activity.setPreSellAuditRemarks(preSellAuditRemarks);
			ActivityGroup activityGroup = new ActivityGroup();

			//入口图更新或者分组更新时,重新加标
			if(activityGroupId == null){
				if(org.apache.commons.lang.StringUtils.equals(entryPic,activity.getEntryPic()) && "1".equals(activity.getIsSign())){
					activity.setIsSign("0");
					activityArea.setIsSign("0");
					String entryPicOld = activity.getEntryPic();
					String[] str = entryPicOld.split("_");
					String[] split = str[2].split("\\.");
					String del = "_"+split[0];
					String entryPicNew = entryPic.replace(del, "");
					activity.setEntryPic(entryPicNew);
					activityArea.setEntryPic(entryPicNew);
				}else {
					activity.setIsSign("0");
					activityArea.setIsSign("0");
					activity.setEntryPic(entryPic);
					activityArea.setEntryPic(entryPic);
				}
			}else {
				if ((!StringUtil.isEmpty(entryPic) && !org.apache.commons.lang.StringUtils.equals(entryPic,activity.getEntryPic()))
						|| !activityGroupId.equals(activity.getActivityGroupId())) {
					activityGroup = activityGroupSerivce.selectByPrimaryKey(activityGroupId);
					entryPic = addTag(StringUtil.isEmpty(entryPic) ? activity.getEntryPic() : entryPic, activityGroup.getSignPic());
					activity.setIsSign("1");
					activity.setEntryPic(entryPic);
					activityArea.setEntryPic(entryPic);
					activityArea.setIsSign("1");
				}
			}

			if(!StringUtil.isEmpty(posterPic))
				activity.setPosterPic(posterPic);
			if(!StringUtil.isEmpty(brandteamPic))
				activity.setBrandteamPic(brandteamPic);
			if(!StringUtil.isEmpty(name))
				activity.setName(name);
			activity.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
			activity.setUpdateDate(date);
			if(productTypeId != null)
				activity.setProductTypeId(productTypeId); //1级类目ID
			activity.setProductTypeSecondId(productTypeSecondId); //2级类目ID
			if(!StringUtil.isEmpty(statusAudit) && "1".equals(statusAudit)) { // 1.专员审核
				if(!StringUtils.isEmpty(preheatTime)){
					activityArea.setPreheatTime(sdf.parse(preheatTime));
				}
				if(!StringUtils.isEmpty(activityBeginTime)){
					activityArea.setActivityBeginTime(sdf.parse(activityBeginTime));
				}
				if(!StringUtils.isEmpty(activityEndTime)){
					activityArea.setActivityEndTime(sdf.parse(activityEndTime));
				}
				activityArea.setActivityGroupId(activityGroupId); //特卖分组
				activity.setActivityGroupId(activityGroupId); //特卖分组
				activity.setOperateAuditBy(Integer.parseInt(staffBean.getStaffID()));
				activity.setOperateAuditStatus(operateAuditStatus);
				if(!StringUtil.isEmpty(auditRemarks))
					activity.setOperateAuditRemarks(auditRemarks);
				if("3".equals(operateAuditStatus)) { 
					activity.setStatus("4"); //活动状态驳回
					//品牌特卖驳回给商家后，发送站内信, 消息类型A63
					addPlatformMsg(activity.getMchtId(), Const.MSG_TPL_MSG_TYPE_A63, activityId, Integer.parseInt(staffBean.getStaffID()),
							date, activityArea.getName(), null, null, activity.getStatus());
				}else{ //通过
					activity.setStatus("3"); //审核中
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
				activityArea.setActivityGroupId(activityGroupId); //特卖分组
				activity.setActivityGroupId(activityGroupId); //特卖分组
				activity.setCooAuditBy(Integer.parseInt(staffBean.getStaffID()));
				activity.setCooAuditStatus(cooAuditStatus);
				if(!StringUtil.isEmpty(auditRemarks))
					activity.setCooAuditRemarks(auditRemarks);
				if("3".equals(cooAuditStatus)) {
					activity.setStatus("4"); //活动状态驳回
					//品牌特卖驳回给商家后，发送站内信, 消息类型A63
					addPlatformMsg(activity.getMchtId(), Const.MSG_TPL_MSG_TYPE_A63, activityId, Integer.parseInt(staffBean.getStaffID()), 
							date, activityArea.getName(), null, null, activity.getStatus());
				}
				if("2".equals(cooAuditStatus)) {
					activity.setStatus("6"); //活动状态通过
					//品牌特卖审核通过后，发送站内信, 消息类型A61
					addPlatformMsg(activity.getMchtId(), Const.MSG_TPL_MSG_TYPE_A61, activityId, Integer.parseInt(staffBean.getStaffID()), 
							date, activityArea.getName(), sdf.parse(activityBeginTime), sdf.parse(preheatTime), activity.getStatus());
					//更新优惠劵状态和时间
					updateCoupon(activityArea.getId(), Integer.parseInt(staffBean.getStaffID()), sdf.parse(activityBeginTime), sdf.parse(activityEndTime), date);
				}
				//活动审核流水表添加
				addActivityAuditLog(activity.getId(), "4", cooAuditStatus, Integer.parseInt(staffBean.getStaffID()), auditRemarks, date);
			}
			if(!StringUtil.isEmpty(statusAudit) && "3".equals(statusAudit)) { // 3.设计审核
				activity.setDesignAuditBy(Integer.parseInt(staffBean.getStaffID()));
				activity.setDesignAuditStatus(designAuditStatus);
				if(!StringUtil.isEmpty(auditRemarks))
					activity.setDesignAuditRemarks(auditRemarks);
				//活动审核流水表添加
				addActivityAuditLog(activity.getId(), "2", designAuditStatus, Integer.parseInt(staffBean.getStaffID()), auditRemarks, date);
			}
			activityMapper.updateByPrimaryKey(activity); //修改活动
			
			if(!StringUtil.isEmpty(name))
				activityArea.setName(name);
			if(!StringUtil.isEmpty(benefitPoint))
				activityArea.setBenefitPoint(benefitPoint);
			activityArea.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
			activityArea.setUpdateDate(date);
			if("2".equals(statusAudit) && "2".equals(cooAuditStatus)) {
				activityArea.setStatus("1"); // 1：启用
				if(!StringUtil.isEmpty(preheatTime))
					activityArea.setPreheatTime(sdf.parse(preheatTime));
				if(!StringUtil.isEmpty(activityBeginTime))
					activityArea.setActivityBeginTime(sdf.parse(activityBeginTime));
				if(!StringUtil.isEmpty(activityEndTime))
					activityArea.setActivityEndTime(sdf.parse(activityEndTime));
			}
			activityAreaMapper.updateByPrimaryKey(activityArea); //修改活动会场

		}  catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//批量排期
	public ResponseMsg saveBatchSchedule(HttpServletRequest request, String staffID) {
		String preheatTime = request.getParameter("preheatTime"); //预热时间
		String activityBeginTime = request.getParameter("activityBeginTime"); //活动开始时间
		String activityEndTime = request.getParameter("activityEndTime"); //活动结束时间
		Integer activityGroupId = null;
		if(!StringUtil.isEmpty(request.getParameter("activityGroupId"))) { //特卖分组
			activityGroupId = Integer.parseInt(request.getParameter("activityGroupId"));
		}
		String[] activityIds=request.getParameter("activityIds").split(",");
		if(activityIds.length <=0){
			throw new ArgException("批量排期错误");
		}
		List<Integer> idList=new ArrayList<Integer>();
		for (int i = 0; i < activityIds.length; i++) {
			if(!StringUtil.isEmpty(activityIds[i])){
				idList.add(Integer.valueOf(activityIds[i]));
			}
		}
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ActivityExample activityExample = new ActivityExample();
			activityExample.createCriteria().andIdIn(idList);
			//查询特卖活动信息
			List<Activity> activities = activityMapper.selectByExample(activityExample);
			ArrayList<Integer> activityAreaIds = new ArrayList<>(); //活动会场id集合
			for (Activity activity : activities) {
				ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activity.getActivityAreaId());
				//特卖会场修改
				if(!StringUtils.isEmpty(activityBeginTime)){
					if (sdf.parse(activityBeginTime).before(activityArea.getActivityEndTime()) && sdf.parse(activityBeginTime).after(activityArea.getPreheatTime())){
						activityArea.setActivityBeginTime(sdf.parse(activityBeginTime));
					}else {
						throw  new ArgException("活动开始时间必须大于预热时间且小于活动结束时间");
					}
					if (activityArea.getPreferentialType().equals("1")){
						updateCouponBegin(activityArea.getId(), Integer.parseInt(staffID), sdf.parse(activityBeginTime), date);
					}
				}
				if(!StringUtils.isEmpty(activityEndTime)){
					if (activityArea.getActivityBeginTime().before(sdf.parse(activityEndTime))){
						activityArea.setActivityEndTime(sdf.parse(activityEndTime));
					}else {
						throw  new ArgException("活动结束时间必须大于活动开始时间");
					}
					if (activityArea.getPreferentialType().equals("1")){
						updateCouponEnd(activityArea.getId(), Integer.parseInt(staffID), sdf.parse(activityEndTime), date);
					}
				}
				if(!StringUtils.isEmpty(preheatTime)){
					if (sdf.parse(preheatTime).before(activityArea.getActivityBeginTime())){
						activityArea.setPreheatTime(sdf.parse(preheatTime));
					}else {
						throw new ArgException("预热时间必须小于活动开始时间");
					}
				}
				activityArea.setUpdateDate(date);
				activityArea.setUpdateBy(Integer.parseInt(staffID));
				if (activityGroupId!=null){
					if (activityGroupId.equals(1) && activity.getIsSign().equals("1")){
						restoreSign(activity.getId(),"0",Integer.parseInt(staffID), date,activityGroupId);
					}else {
						activity.setUpdateBy(Integer.parseInt(staffID));
						activity.setUpdateDate(date);
						activity.setActivityGroupId(activityGroupId);
						activityArea.setActivityGroupId(activityGroupId); //特卖分组id
						activityMapper.updateByPrimaryKeySelective(activity);
						activityAreaMapper.updateByPrimaryKeySelective(activityArea); //修改活动会场
					}
				}else {
					activityAreaMapper.updateByPrimaryKeySelective(activityArea); //修改活动会场
				}
			}
		}catch (ArgException arge){
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}


	public void updateCouponBegin(Integer activityAreaId, Integer staffId, Date activityBeginTime, Date date) {
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria couponCriteria = couponExample.createCriteria();
		couponCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId);
		List<Coupon> couponList = couponMapper.selectByExample(couponExample);
		for(Coupon cou : couponList) {
			Coupon coupon = new Coupon();
			coupon.setId(cou.getId());
			coupon.setRecBeginDate(activityBeginTime);
			coupon.setExpiryBeginDate(activityBeginTime);
			coupon.setStatus("1");
			coupon.setExpiryType("1");
			coupon.setUpdateDate(date);
			coupon.setUpdateBy(staffId);
			couponMapper.updateByPrimaryKeySelective(coupon);

			MemberCouponExample memberCouponExample = new MemberCouponExample();
			memberCouponExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(cou.getId());
			List<MemberCoupon> memberCouponList = memberCouponMapper.selectByExample(memberCouponExample);
			for(MemberCoupon mc : memberCouponList) {
				MemberCoupon memberCoupon = new MemberCoupon();
				memberCoupon.setId(mc.getId());
				memberCoupon.setExpiryBeginDate(activityBeginTime);
				memberCoupon.setUpdateBy(staffId);
				memberCoupon.setUpdateDate(date);
				memberCouponMapper.updateByPrimaryKeySelective(memberCoupon);
			}
		}
	}

	public void updateCouponEnd(Integer activityAreaId, Integer staffId,  Date activityEndTime, Date date) {
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria couponCriteria = couponExample.createCriteria();
		couponCriteria.andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId);
		List<Coupon> couponList = couponMapper.selectByExample(couponExample);
		for(Coupon cou : couponList) {
			Coupon coupon = new Coupon();
			coupon.setId(cou.getId());
			coupon.setRecEndDate(activityEndTime);
			coupon.setExpiryEndDate(activityEndTime);
			coupon.setStatus("1");
			coupon.setExpiryType("1");
			coupon.setUpdateDate(date);
			coupon.setUpdateBy(staffId);
			couponMapper.updateByPrimaryKeySelective(coupon);

			MemberCouponExample memberCouponExample = new MemberCouponExample();
			memberCouponExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(cou.getId());
			List<MemberCoupon> memberCouponList = memberCouponMapper.selectByExample(memberCouponExample);
			for(MemberCoupon mc : memberCouponList) {
				MemberCoupon memberCoupon = new MemberCoupon();
				memberCoupon.setId(mc.getId());
				memberCoupon.setExpiryEndDate(activityEndTime);
				memberCoupon.setUpdateBy(staffId);
				memberCoupon.setUpdateDate(date);
				memberCouponMapper.updateByPrimaryKeySelective(memberCoupon);
			}
		}
	}

	//还原加标
	public  void  restoreSign(Integer activityId, String isSign, Integer staffID, Date date,Integer activityGroupId){
		String entryPicStr = "";
		String isSignStr = "";
		Activity activity = activityMapper.selectByPrimaryKey(activityId);
		ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activity.getActivityAreaId());
		if ("0".equals(isSign)) { //还原
			activity.setIsSign(isSign);
			activityArea.setIsSign(isSign);
			String entryPic = activity.getEntryPic();
			String[] str = entryPic.split("_");
			String[] split = str[2].split("\\.");
			String del = "_"+split[0];
			String entryPicNew = entryPic.replace(del, "");
			activity.setEntryPic(entryPicNew);
			activityArea.setEntryPic(entryPicNew);
			entryPicStr = activity.getEntryPic();
			isSignStr = activityArea.getIsSign();
            //特卖活动修改
            activity.setUpdateDate(date);
            activity.setOperateAuditBy(staffID);
			activity.setActivityGroupId(activityGroupId);
			//特卖会场修改
			activityArea.setUpdateDate(date);
			activityArea.setUpdateBy(staffID);
			activityArea.setActivityGroupId(activityGroupId);
            activityMapper.updateByPrimaryKeySelective(activity); //修改活动
			activityAreaMapper.updateByPrimaryKeySelective(activityArea); //修改活动会场
		}
	}

	//图标加标
	public String addTag(String entryPic,String watermark) throws IOException {
		if(entryPic.indexOf("wtmk") != -1){
			String[] str = entryPic.split("_");
			String[] split = str[2].split("\\.");
			String del = "_"+split[0];
			entryPic = entryPic.replace(del, "");
		}
		String[] str1 = entryPic.split("\\.");
		Integer random = (int)Math.random()*1024;
		random = (int)new Date().getTime()+random;
		String output = str1[0] + "_wtmk"+random+"."+ str1[1];
		Integer width = 800;
		Integer height = 400;
		Position position = Positions.TOP_RIGHT;
		Float transparency = 1f;
		Float quality = 1f;
		FileUtil.ImgWatermark(entryPic, output, width, height, position, watermark, transparency, quality); //合成水印
		return output;
	}

	/**
	 * 
	 * @Title addPlatformMsg   
	 * @Description TODO(品牌特卖发送站内信)   
	 * @author pengl
	 * @date 2018年1月13日 下午5:19:55
	 */
	public void addPlatformMsg(Integer mchtId, String msgType, Integer activityId, Integer staffId, 
			Date date, String activityAreaName, Date activityBeginTime, 
			Date preheatTime, String status ) {
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
			if("6".equals(status)) { //品牌特卖审核通过
				SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
				SimpleDateFormat formatDay = new SimpleDateFormat("dd");
				SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
				//特卖活动名称
				String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('mcht/activity')\">"+activityAreaName+"</a>";
				content = content.replaceAll("\\{activity_name}", msgActivityName);
				//活动开始时间
				content = content.replaceAll("\\{begin_month}", formatMonth.format(activityBeginTime)).replaceAll("\\{begin_day}", formatDay.format(activityBeginTime)).replaceAll("\\{begin_time}", formatTime.format(activityBeginTime));
				//活动预热时间
				content = content.replaceAll("\\{preheat_month}", formatMonth.format(preheatTime)).replaceAll("\\{preheat_day}", formatDay.format(preheatTime)).replaceAll("\\{preheat_time}", formatTime.format(preheatTime));
				platformMsg.setTitle("关于品牌特卖审核通过通知");
				platformMsg.setContent(content);
			}
			if("4".equals(status)) {
				//特卖活动名称
				String msgActivityName="<a href=\"javascript:void(0)\" onclick=\"getContent('mcht/activity')\">"+activityAreaName+"</a>";
				content=content.replaceAll("\\{activity_name}", msgActivityName);
				platformMsg.setTitle("关于品牌特卖审核驳回通知");
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
	 * @date 2018年1月15日 上午9:51:19
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
	 * @date 2018年1月15日 上午10:05:32
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
			
			MemberCouponExample memberCouponExample = new MemberCouponExample();
			memberCouponExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(cou.getId());
			List<MemberCoupon> memberCouponList = memberCouponMapper.selectByExample(memberCouponExample);
			for(MemberCoupon mc : memberCouponList) {
				MemberCoupon memberCoupon = new MemberCoupon();
				memberCoupon.setId(mc.getId());
				memberCoupon.setExpiryBeginDate(activityBeginTime);
				memberCoupon.setExpiryEndDate(activityEndTime);
				memberCoupon.setUpdateBy(staffId);
				memberCoupon.setUpdateDate(date);
				memberCouponMapper.updateByPrimaryKeySelective(memberCoupon);
			}
		}
	}

	
	public Map<String, Object> updateActivityGroupId(Integer activityId, String isSign) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "";
		String entryPicStr = "";
		String isSignStr = "";
		try {
			if (activityId != null && !StringUtil.isEmpty(isSign)) {
				Activity activity = activityMapper.selectByPrimaryKey(activityId);
				ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activity.getActivityAreaId());
				if ("0".equals(isSign)) { //还原
					activity.setIsSign(isSign);
					activityArea.setIsSign(isSign);
					String entryPic = activity.getEntryPic();
					String[] str = entryPic.split("_");
					String[] split = str[2].split("\\.");
					String del = "_"+split[0];
					String entryPicNew = entryPic.replace(del, "");
					activity.setEntryPic(entryPicNew);
					activityArea.setEntryPic(entryPicNew);
					entryPicStr = activity.getEntryPic();
					isSignStr = activityArea.getIsSign();
					activityMapper.updateByPrimaryKeySelective(activity); //修改活动
					activityAreaMapper.updateByPrimaryKeySelective(activityArea); //修改活动会场
				} else if ("1".equals(isSign)) { //加标
					ActivityGroupExample activityGroupExample = new ActivityGroupExample();
					activityGroupExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(activityArea.getActivityGroupId());
					List<ActivityGroup> activityGroupList = activityGroupMapper.selectByExample(activityGroupExample);
					if (activityGroupList != null && activityGroupList.size() > 0) {
						ActivityGroup activityGroup = activityGroupList.get(0);
						if (!StringUtil.isEmpty(activityGroup.getSignPic())) {
							String source = activity.getEntryPic();
							String pic = addTag(source,activityGroup.getSignPic());
							activity.setIsSign(isSign);
							activityArea.setIsSign(isSign);
							activity.setEntryPic(pic);
							activityArea.setEntryPic(pic);
							entryPicStr = activity.getEntryPic();
							isSignStr = activityArea.getIsSign();
							activityMapper.updateByPrimaryKeySelective(activity); //修改活动
							activityAreaMapper.updateByPrimaryKeySelective(activityArea); //修改活动会场
						} else {
							code = "999";
							msg = "入口图彩标为空！";
						}
					} else {
						code = "999";
						msg = "特卖分组为空或不存在！";
					}
				}
			}
		} catch (IOException e){
			code = "999";
			msg = "图片上传失败!";
		} catch (Exception e) {
			code = "999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("entryPicStr", entryPicStr);
		map.put("isSignStr", isSignStr);
		return map;
	}
	
	/**
	 * 
	 * @Title updateActivityGroupId   
	 * @Description TODO(合成图片 水印 后期扩展)   
	 * @author pengl
	 * @date 2018年6月13日 下午6:55:58
	 */
	public Map<String, Object> updateActivityGroupIdCustom(Integer activityId, String isSign) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "";
		String entryPicStr = "";
		String isSignStr = "";
		try {
			if (activityId != null && !StringUtil.isEmpty(isSign)) {
				Activity activity = activityMapper.selectByPrimaryKey(activityId);
				ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(activity.getActivityAreaId());
				if ("0".equals(isSign)) { //还原

					String entryPic = activity.getEntryPic();
					String[] str = entryPic.split("_");
					String[] split = str[2].split("\\.");
					String del = "_"+split[0];
					String entryPicNew = entryPic.replace(del, "");
					
					Activity activityNew = new Activity();
					activityNew.setId(activityId);
					activityNew.setIsSign(isSign);
					activityNew.setEntryPic(entryPicNew);
					
					isSignStr = activityNew.getIsSign();
					entryPicStr = activityNew.getEntryPic();
					
					activityMapper.updateByPrimaryKeySelective(activityNew); //修改活动
				} else if ("1".equals(isSign)) { //加标
					ActivityGroupExample activityGroupExample = new ActivityGroupExample();
					activityGroupExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(activityArea.getActivityGroupId());
					List<ActivityGroup> activityGroupList = activityGroupMapper.selectByExample(activityGroupExample);
					if (activityGroupList != null && activityGroupList.size() > 0) {
						ActivityGroup activityGroup = activityGroupList.get(0);
						if (!StringUtil.isEmpty(activityGroup.getSignPic())) {
							String source = activity.getEntryPic();
                            String pic = addTag(source, activityGroup.getSignPic());
                            Activity activityNew = new Activity();
							activityNew.setId(activity.getId());
							activityNew.setIsSign(isSign);
							activityNew.setEntryPic(pic);
							entryPicStr = activityNew.getEntryPic();
							isSignStr = activityNew.getIsSign();
							activityMapper.updateByPrimaryKeySelective(activityNew); //修改活动
						} else {
							code = "999";
							msg = "入口图彩标为空！";
						}
					} else {
						code = "999";
						msg = "特卖分组为空或不存在！";
					}
				}
			}
		} catch (IOException e){
			code = "999";
			msg = "图片上传失败!";
		} catch (Exception e) {
			code = "999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("entryPicStr", entryPicStr);
		map.put("isSignStr", isSignStr);
		return map;
	}

	public List<Integer> getProductIdsByActivityIds(String activityIds) {
		return activityCustomMapper.getProductIdsByActivityIds(activityIds);
	}
	/**
	 * 
	 * @Title auditDesign   
	 * @Description TODO(设计快速审核   通过   驳回)   
	 * @author pengl
	 * @date 2018年3月5日 下午6:09:23
	 */
	public void auditDesign(StaffBean staffBean, Integer activityId, String designAuditStatus, String designRejectReason, String designAuditRemarks) {
		Date date = new Date();
		Activity activity  = new Activity();
		activity.setId(activityId);
		activity.setDesignAuditStatus(designAuditStatus);
		activity.setDesignAuditBy(Integer.parseInt(staffBean.getStaffID()));
		if(!StringUtil.isEmpty(designRejectReason)) {
			activity.setDesignRejectReason(designRejectReason);
		}
		if(!StringUtil.isEmpty(designAuditRemarks)) {
			activity.setDesignAuditRemarks(designAuditRemarks);
		}
		activity.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		activity.setUpdateDate(date);
		activityMapper.updateByPrimaryKeySelective(activity);
		//活动审核流水表添加
		addActivityAuditLog(activityId, "2", designAuditStatus, Integer.parseInt(staffBean.getStaffID()), designAuditRemarks, date);
	}
	
	public List<String> getEntryPics(HashMap<String, Object> paramMap){
		return activityCustomMapper.getEntryPics(paramMap);
	}
	
	public Integer updateByCustomExampleSelective(@Param("record") Activity record, @Param("example") ActivityCustomExample example) {
		return activityCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	public void activityClose(Map<String, String> paramMap) {
		Integer mchtId = Integer.parseInt(paramMap.get("mchtId"));
		Integer productBrandId = Integer.parseInt(paramMap.get("productBrandId"));
		Integer staffId = Integer.parseInt(paramMap.get("staffId"));
		Date date = new Date();
		
		//总监审核状态=通过 且 未结束的品牌特卖 的 要处理：结束时间设置成当前时间
		ActivityAreaCustomExample activityAreaCustomExample = new ActivityAreaCustomExample();
		ActivityAreaCustomExample.ActivityAreaCustomCriteria activityAreaCustomCriteria = activityAreaCustomExample.createCriteria();
		activityAreaCustomCriteria.andDelFlagEqualTo("0")
			.andActivityEndTimeGreaterThan(date);
		activityAreaCustomCriteria.andActivityStatus("2", mchtId, productBrandId);
		ActivityArea activityArea = new ActivityArea();
		activityArea.setActivityEndTime(date);
		activityArea.setUpdateBy(staffId);
		activityArea.setUpdateDate(date);
		activityAreaCustomMapper.updateByExampleSelective(activityArea, activityAreaCustomExample);
		//总监审核状态！=通过的  要处理：将活动状态更改为 驳回
		ActivityCustomExample activityCustomExample = new ActivityCustomExample();
		ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
		activityCustomCriteria.andDelFlagEqualTo("0")
			.andMchtIdEqualTo(mchtId);
		if(productBrandId != 0) {
			activityCustomCriteria.andProductBrandIdEqualTo(productBrandId);
		}
		activityCustomCriteria.andCooAuditStatusIsNullOrNotEqualTo("2");
		Activity activity = new Activity();
		activity.setStatus("4");
		activity.setUpdateBy(staffId);
		activity.setUpdateDate(date);
		activityCustomMapper.updateByCustomExampleSelective(activity, activityCustomExample);
	}


    public Integer countActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap) {
		return activityCustomMapper.countActivityTrafficStatisticsRealTime(paraMap);
    }

	public List<HashMap<String,Object>> selectActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap) {
		return activityCustomMapper.selectActivityTrafficStatisticsRealTime(paraMap);
	}
}
