package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityStatusEnum;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.dao.*;
import com.jf.entity.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author huangdl
 */
@Service
@Transactional
public class ActivityAreaService {

	@Autowired
	private ActivityAreaMapper dao;

	//@Autowired
	//private ActivityAreaCustomMapper areaCustomMapper;

	@Autowired
	private CouponCustomMapper couponCustomMapper;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private FullCutMapper fullCutMapper;

	@Autowired
	private FullGiveMapper fullGiveMapper;

	@Autowired
	private CouponMapper couponMapper;

	@Autowired
	private FullDiscountMapper fullDiscountMapper;

	@Autowired
	private AllowanceInfoMapper allowanceInfoMapper;


	/**
	 * 添加品牌特卖
	 *
	 * @param mchtId       the mcht id
	 * @param paramActivityArea the activity area
	 * @return activity area
	 */
	public ActivityArea save(int mchtId, ActivityArea paramActivityArea){
		boolean isUpdate = paramActivityArea.getId() != null && paramActivityArea.getId() > 0;

		ActivityArea activityArea;
		if(isUpdate){
			activityArea = findById(paramActivityArea.getId());
		}else{
			activityArea = new ActivityArea();
		}

		activityArea.setName(paramActivityArea.getName());
		activityArea.setBenefitPoint(paramActivityArea.getBenefitPoint());
		activityArea.setPreferentialType(paramActivityArea.getPreferentialType());

		activityArea.setMchtId(mchtId);
		activityArea.setSource(Const.ACTIVITY_SOURCE_BRAND);
		//activityArea.setType(Const.ACTIVITY_TYPE_BRAND);
		activityArea.setStatus(Const.ACTIVITY_AREA_STATUS_DISABLE);
		if(isUpdate){
			update(activityArea);
		}else{
			activityArea.setDelFlag("0");
			save(activityArea);
		}
		return activityArea;
	}

	public boolean isLimitMchtNumFull(ActivityArea model){
		boolean b = false;
		if(model.getLimitMchtNum() != null && model.getLimitMchtNum() == 0){
			b = true;
		}
		if(model.getMchtId() == 0 && model.getLimitMchtNum() != null && model.getLimitMchtNum() > 0){
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("activityAreaId", model.getId());
			queryObject.addQuery("status", Const.ACTIVITY_STATUS_PASS);
			if(activityService.count(queryObject) >= model.getLimitMchtNum()){
				b = true;
			}
		}
		return b;
	}

	public ActivityArea findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public ActivityArea save(ActivityArea model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public ActivityArea update(ActivityArea model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int mchtId, int id){
		ActivityArea model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE) || model.getMchtId() != mchtId) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);

		activityService.deleteByAreaId(mchtId, id);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public int countPlat(QueryObject queryObject) {
		Integer mchtId = queryObject.getQueryToInt("mchtId");
		String mchtType = queryObject.getQueryToStr("mchtType");
		Integer pageStatus = queryObject.getQueryToInt("pageStatus");
		
		queryObject.addQuery("mchtId", 0);
		queryObject.addQuery("pageStatus", pageStatus);
		queryObject.addQuery("status", Const.ACTIVITY_AREA_STATUS_AVAILABLE);
		//queryObject.addQuery("typeNotEquals", Const.ACTIVITY_TYPE_BRAND);
		
		Map<String, Object> platActivityParam = new HashMap<>();
		platActivityParam.put("mchtId", mchtId);
		platActivityParam.put("mchtType", mchtType);
		queryObject.addQuery("platActivity", platActivityParam);
		
		addQueryPageStatus(mchtId, queryObject);
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<ActivityArea> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<ActivityArea> paginate(QueryObject queryObject) {
		ActivityAreaExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<ActivityArea> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	/**
	 * 商家活动分页列表
	 *
	 * @param mchtId      the mcht id
	 * @param queryObject the query object
	 * @return page
	 */
	public Page<ActivityArea> paginateMcht(int mchtId, QueryObject queryObject) {

		if(queryObject.getQuery("productBrandId") != null || queryObject.getQuery("activityStatus") != null){
			QueryObject activityQuery = new QueryObject();
			activityQuery.addQuery("mchtId", mchtId);
			// 查询品牌
			if(queryObject.getQuery("productBrandId") != null){
				activityQuery.addQuery("productBrandId", queryObject.getQuery("productBrandId"));
			}
			// 查询状态
			if(queryObject.getQuery("activityStatus") != null){
				activityQuery.addQuery("status", queryObject.getQuery("activityStatus"));
			}
			List<Activity> activityList = activityService.list(activityQuery);
			List<Integer> ids = new ArrayList<>();
			ids.add(0);
			for(Activity activity : activityList){
				ids.add(activity.getActivityAreaId());
			}
			queryObject.addQuery("ids", ids);
		}

		queryObject.addQuery("mchtId", mchtId);
		queryObject.removeQuery("status");
		Page<ActivityArea> page = paginate(queryObject);
		for(ActivityArea activityArea : page.getList()){
			fillInfo(mchtId, activityArea);
		}
		return page;
	}

	/**
	 * 平台活动分页列表
	 *
	 * @param queryObject the query object
	 * @return page
	 */
	public Page<ActivityArea> paginatePlat(QueryObject queryObject) {
		Integer mchtId = queryObject.getQueryToInt("mchtId");
		String mchtType = queryObject.getQueryToStr("mchtType");
		
		queryObject.addQuery("mchtId", 0);
		queryObject.addQuery("status", Const.ACTIVITY_AREA_STATUS_AVAILABLE);
		//queryObject.addQuery("typeNotEquals", Const.ACTIVITY_TYPE_BRAND);
		
		Map<String, Object> platActivityParam = new HashMap<>();
		platActivityParam.put("mchtId", mchtId);
		platActivityParam.put("mchtType", mchtType);
		queryObject.addQuery("platActivity", platActivityParam);
		addQueryPageStatus(mchtId, queryObject);
		Page<ActivityArea> page = paginate(queryObject);
		for(ActivityArea activityArea : page.getList()){
			fillInfo(mchtId, activityArea);
		}
		return page;
	}

	private ActivityAreaExample builderQuery(QueryObject queryObject) {
		ActivityAreaCustomExample example = new ActivityAreaCustomExample();
		ActivityAreaCustomExample.ActivityAreaCustomCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("type") != null){
			criteria.andTypeEqualTo(queryObject.getQueryToStr("type"));
		}
		if(queryObject.getQuery("typeNotEquals") != null){
			criteria.andTypeNotEqualTo(queryObject.getQueryToStr("typeNotEquals"));
		}
		if(queryObject.getQuery("name") != null){
			criteria.andNameLike("%" + queryObject.getQueryToStr("name") + "%");
		}
		if(queryObject.getQuery("ids") != null){
			List<Integer> ids = queryObject.getQuery("ids");
			criteria.andIdIn(ids);
		}
		if(queryObject.getQuery("idNotIn") != null){
			List<Integer> idNotIn = queryObject.getQuery("idNotIn");
			criteria.andIdNotIn(idNotIn);
		}
		if(queryObject.getQuery("activityBeginTimeAfter") != null){
			criteria.andActivityBeginTimeGreaterThan(queryObject.getQueryToDate("activityBeginTimeAfter"));
		}
		if(queryObject.getQuery("activityBeginTimeBefore") != null){
			criteria.andActivityBeginTimeLessThan(queryObject.getQueryToDate("activityBeginTimeBefore"));
		}
		if(queryObject.getQuery("activityEndTimeAfter") != null){
			criteria.andActivityEndTimeGreaterThan(queryObject.getQueryToDate("activityEndTimeAfter"));
		}
		if(queryObject.getQuery("activityEndTimeBefore") != null){
			criteria.andActivityEndTimeLessThan(queryObject.getQueryToDate("activityEndTimeBefore"));
		}
		if(queryObject.getQuery("platActivity") != null){
			Map<String, Object> param = (Map<String, Object>)queryObject.getQuery("platActivity");
			Integer mchtId = (Integer)param.get("mchtId");
			String mchtType = (String)param.get("mchtType");
			criteria.andPlatActivity(mchtId, mchtType);
		}
		if(queryObject.getQuery("source")==null || !queryObject.getQuery("source").equals("2")){//官方会场，非特卖
			criteria.andActivityEndTimeGreaterThanOrEqualTo(new Date());
		}
		if(queryObject.getQuery("typeIn") != null){
			List<String> types = queryObject.getQuery("typeIn");
			criteria.andTypeIn(types);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	private ActivityArea fillInfo(int mchtId, ActivityArea info){
		if(StrKit.notBlank(info.getType())){
			String typeStr = "";
			if(info.getType().equals(Const.ACTIVITY_TYPE_BRAND)){
				typeStr = "品牌";
			}else if(info.getType().equals(Const.ACTIVITY_TYPE_DANPIN)){
				typeStr = "单品";
			}else if(info.getType().equals(Const.ACTIVITY_TYPE_BAOKUAN)){
				typeStr = "单品爆款";
			}else if(info.getType().equals(Const.ACTIVITY_TYPE_NEWGUY)){
				typeStr = "新用户专享";
			}else if(info.getType().equals(Const.ACTIVITY_TYPE_EXTENSION)){
				typeStr = "推广";
			}
			info.put("typeStr", typeStr);
		}

		Activity activity = activityService.findByAreaId(mchtId, info.getId());
		if(activity == null){
			info.put("statusStr", "未报名");
			// 如果活动有限制商家名额，则查看该活动满额了没
			if(isLimitMchtNumFull(info)){
				info.put("statusStr", "报名名额已满");
			}

			if(info.getEnrollBeginTime() != null && info.getEnrollEndTime() != null){
				DateTime now = DateTime.now();
				if(now.isBefore(info.getEnrollBeginTime().getTime())){
					info.put("statusStr", "未到报名时间");
				}else if(now.isAfter(info.getEnrollEndTime().getTime())){
					info.put("statusStr", "报名已结束");
				}
			}
			return info;
		}

		info.put("activity", activity);

		String statusStr = "";
		if(info.getActivityEndTime() != null && DateTime.now().isAfter(info.getActivityEndTime().getTime())){
			statusStr = "活动已结束";
		}else if(activity.getStatus().equals(Const.ACTIVITY_STATUS_WAIT_COMMIT)){
			statusStr = ActivityStatusEnum.WAIT_COMMIT.getName();
		}else if(activity.getStatus().equals(Const.ACTIVITY_STATUS_WAIT_AUDIT)){
			statusStr = ActivityStatusEnum.WAIT_AUDIT.getName();
		}else if(activity.getStatus().equals(Const.ACTIVITY_STATUS_AUDITING)){
			statusStr = ActivityStatusEnum.AUDITING.getName();
		}else if(activity.getStatus().equals(Const.ACTIVITY_STATUS_AUDIT_REJECT)){
			statusStr = ActivityStatusEnum.AUDIT_REJECT.getName();
		}else if(activity.getStatus().equals(Const.ACTIVITY_STATUS_STOP)){
			statusStr = "暂停";
		}else if(activity.getStatus().equals(Const.ACTIVITY_STATUS_PASS)){
			DateTime now = DateTime.now();
			long startTime = info.getActivityBeginTime().getTime();
			long endTime = info.getActivityEndTime().getTime();
			if(now.plusDays(Const.ACTIVITY_PREHEAT_DAY).isBefore(startTime)){
				statusStr = ActivityStatusEnum.PREPARING.getName();
			}else if(now.plusDays(Const.ACTIVITY_PREHEAT_DAY).isAfter(startTime) && now.isBefore(startTime)){
				statusStr = ActivityStatusEnum.PREHEAT.getName();
			}else if(now.isAfter(startTime) && now.isBefore(endTime)){
				statusStr = ActivityStatusEnum.PROCESSING.getName();
			}else if(now.isAfter(endTime)){
				statusStr = ActivityStatusEnum.FINISHED.getName();
			}
		}
		info.put("statusStr", statusStr);

		if(info.getMchtId() > 0){
			// 商家活动
			activityService.fillInfo(activity);
		}
		return info;
	}

	/**
	 * 查询已报名、未报名等活动
	 * 参数吗：pageStatus
     */
	private void addQueryPageStatus(int mchtId, QueryObject queryObject){
		if(queryObject.getQuery("pageStatus") != null){
			Integer pageStatus = queryObject.getQuery("pageStatus");
			if(pageStatus == 1){
				// 未报名活动
				QueryObject activityQueryObject = new QueryObject();
				activityQueryObject.addQuery("mchtId", mchtId);
				List<Activity> activityList = activityService.list(activityQueryObject);
				List<Integer> activityIds = new ArrayList<>();
				activityIds.add(0);
				for(Activity activity : activityList){
					activityIds.add(activity.getActivityAreaId());
				}
				queryObject.addQuery("idNotIn", activityIds);
			}else if(pageStatus ==2 ){
				// 已报名活动
				QueryObject activityQueryObject = new QueryObject();
				activityQueryObject.addQuery("mchtId", mchtId);
				activityQueryObject.addQuery("statusNotEquals", Const.ACTIVITY_STATUS_AUDIT_REJECT);
				List<Activity> activityList = activityService.list(activityQueryObject);
				List<Integer> activityIds = new ArrayList<>();
				activityIds.add(0);
				for(Activity activity : activityList){
					activityIds.add(activity.getActivityAreaId());
				}
				queryObject.addQuery("ids", activityIds);
			}else if(pageStatus ==3 ){
				// 已驳回活动
				QueryObject activityQueryObject = new QueryObject();
				activityQueryObject.addQuery("mchtId", mchtId);
				activityQueryObject.addQuery("status", Const.ACTIVITY_STATUS_AUDIT_REJECT);
				List<Activity> activityList = activityService.list(activityQueryObject);
				List<Integer> activityIds = new ArrayList<>();
				activityIds.add(0);
				for(Activity activity : activityList){
					activityIds.add(activity.getActivityAreaId());
				}
				queryObject.addQuery("ids", activityIds);
			}
		}
	}

	public List<Map<String,Object>> queryCouponAndAreaId(int mchtId,int status,Integer limitStart,Integer limitSize) {
		return couponCustomMapper.queryCouponAndAreaId(mchtId,status,limitStart,limitSize);
	}

	/**
	 *  可报名商品页面,展示会场优惠方式
	 */
	public String getPromotionMethod(Integer activityAreaId) {
		ActivityArea activityArea = dao.selectByPrimaryKey(activityAreaId);
		String res = "";
		if ("1".equals(activityArea.getPreferentialType())){
			CouponExample where = new CouponExample();
			CouponExample.Criteria criteria = where.createCriteria();
			criteria.andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
			List<Coupon> coupons = couponMapper.selectByExample(where);
			for (Coupon coupon : coupons) {
				if ("1".equals(coupon.getPreferentialType())){
					if (res.length()>0){
						res += "," ;
					}else {
						res += "优惠券: ";
					}
					res += "满" + coupon.getMinimum() + "元 抵" + coupon.getMoney() + "元";
				}
			}
		}
		if ("2".equals(activityArea.getPreferentialType())){
			FullCutExample where = new FullCutExample();
			FullCutExample.Criteria criteria = where.createCriteria();
			criteria.andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
			List<FullCut> fullCuts = fullCutMapper.selectByExample(where);
			for (FullCut fullCut : fullCuts) {
				if (res.length()>0){
					res += "," ;
				}else {
					res += "满减: ";
				}
				String[] split = fullCut.getRule().split(",");
				res += "满" + split[0] + "元 减" + split[1] + "元";
			}
		}
		if ("3".equals(activityArea.getPreferentialType())){
			FullGiveExample where = new FullGiveExample();
			FullGiveExample.Criteria criteria = where.createCriteria();
			criteria.andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
			List<FullGive> fullGives = fullGiveMapper.selectByExample(where);
			for (FullGive fullGive : fullGives) {
				if (res.length()>0){
					res += "," ;
				}else {
					res += "满赠: ";
				}
				if ("1".equals(fullGive.getType())){
					res += "满" + fullGive.getMinimum() + "元 赠";
				}
				if ("2".equals(fullGive.getType())){
					res += "买即赠 ";
				}
				if ("1".equals(fullGive.getCouponFlag())){
					String[] split = fullGive.getCouponIdGroup().split(",");
					for (String s : split) {
						Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(s));
						res += " 优惠券--满" + coupon.getMinimum() + "元 抵" + coupon.getMoney() + "元";
					}
				}
				if ("1".equals(fullGive.getIntegralFlag())){
					res += " " + fullGive.getIntegral() + "金币";
				}
			}
		}
		if ("4".equals(activityArea.getPreferentialType())){
			FullDiscountExample where = new FullDiscountExample();
			FullDiscountExample.Criteria criteria = where.createCriteria();
			criteria.andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
			List<FullDiscount> fullDiscounts = fullDiscountMapper.selectByExample(where);
			for (FullDiscount fullDiscount : fullDiscounts) {
				if (res.length()>0){
					res += "," ;
				}else {
					res += "多买优惠: ";
				}
				String type = fullDiscount.getType();
				String[] split = fullDiscount.getRule().split(",");
				if ("1".equals(type)){
					res += "满" + split[0] + "件 减" + split[1] + "件";
				}
				if ("2".equals(type)){
					res += "任选" + split[0] + "件" + split[1] + "元";
				}
				if ("1".equals(type)){
					res += split[0] + "件" + split[1] + "折";
				}
				if ("1".equals(type)){
					res += "第" + split[0] + "件" + split[1] + "折";
				}
			}
		}
		if ("5".equals(activityArea.getPreferentialType())){
			AllowanceInfoExample where = new AllowanceInfoExample();
			AllowanceInfoExample.Criteria criteria = where.createCriteria();
			criteria.andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
			List<AllowanceInfo> allowanceInfos = allowanceInfoMapper.selectByExample(where);
			for (AllowanceInfo allowanceInfo : allowanceInfos) {
				if (res.length()>0){
					res += "," ;
				}else {
					res += "购物津贴: ";
				}
				String[] split = allowanceInfo.getRule().split(",");
				res += "每满" + split[0] + "元 减" + split[1] + "元,上不封顶";
			}
		}
		return res;
	}


}
