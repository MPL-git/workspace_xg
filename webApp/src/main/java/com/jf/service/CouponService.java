package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CouponCustomMapper;
import com.jf.dao.CouponMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.MemberInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 下午2:51:38 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CouponService extends BaseService<Coupon, CouponExample> {
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private CouponCustomMapper couponCustomMapper;
	@Resource
	private MemberCouponService memberCouponService;
	@Autowired
	private ActivityAreaService activityAreaService;
	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	public void setCouponMapper(CouponMapper couponMapper) {
		this.setDao(couponMapper);
		this.couponMapper = couponMapper;
	}
	public void updateByModel(Coupon coupon) {
		
		couponMapper.updateByPrimaryKeySelective(coupon);
	}
//	public List<Map<String,Object>> findCouponList(String preferentialIdGroup, Integer activityAreaId, String memberId) {
//		Map<String,Object> couponMap = new HashMap<String,Object>();
//		List<Map<String,Object>> couponMapList = new ArrayList<Map<String,Object>>();
//		String[] preferentialIdGroups = preferentialIdGroup.split(",");
//		List<Integer> couponIds = new ArrayList<Integer>();
//		for (String preferentialId : preferentialIdGroups) {
//			couponIds.add(Integer.valueOf(preferentialId));
//		}
//		List<Coupon> couponList = findModelList(couponIds,activityAreaId);
//		if(CollectionUtils.isNotEmpty(couponList)){
//			for (Coupon coupon : couponList) {
//				//优惠券发行数量
//				Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
//				//优惠券已领数量
//				Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
//				//优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
//				String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
//				//优惠券 限领数量
//				Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
//				//是否可以领取
//				//true 是
//				//false 否
//				boolean isCanReceive = false;
//				String recDate = "";
//				Integer memberCouponSize = 0;
//				if(grantQuantity > 0 && grantQuantity > recQuantity){
//					List<MemberCoupon> memberCoupons = new ArrayList<MemberCoupon>();
//					if(!StringUtil.isBlank(memberId)){
//						memberCoupons = memberCouponService.getMmberCouponList(Integer.valueOf(memberId),coupon.getId());
//					}
//					if(CollectionUtils.isNotEmpty(memberCoupons)){
//						memberCouponSize = memberCoupons.size();
//						recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyy,MM,dd");
//						String currentDate = DateUtil.getFormatDate(new Date(), "yyyy,MM,dd");
//						if(recLimitType.equals("1")){
//							if(!recDate.equals(currentDate)){
//								//当天未领取，可以领取
//								isCanReceive = true;
//							}
//						}else if(recLimitType.equals("2")){
//							if(memberCoupons.size() < recEach){
//								isCanReceive = true;
//							}
//						}else if(recLimitType.equals("3")){
//							//未做限制可以无限领取
//							isCanReceive = true;
//						}
//					}else{
//						//找不到用户领取过的痕迹，可以领取
//						isCanReceive = true;
//					}
//					couponMap = new HashMap<String,Object>();
//					couponMap.put("couponId", coupon.getId());
//					couponMap.put("couponName", coupon.getName());
//					couponMap.put("money", coupon.getMoney());
//					couponMap.put("minimum", coupon.getMinimum());
//					couponMap.put("expiryBeginDate", coupon.getExpiryBeginDate());
//					couponMap.put("expiryEndDate", coupon.getExpiryEndDate());
//					couponMap.put("recType", coupon.getRecType());
//					couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
//					couponMap.put("recLimitType", coupon.getRecLimitType());
//					couponMap.put("recEach", coupon.getRecEach());
//					couponMap.put("isCanReceive", isCanReceive);
//					couponMap.put("recDate", recDate);
//					couponMap.put("memberCouponSize", memberCouponSize);
//					couponMapList.add(couponMap);
//				}
//			}
//		}
//		return couponMapList;
//		
//	}

	public List<Coupon> findModelList(List<Integer> couponIds, Integer activityAreaId) {
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andActivityAreaIdEqualTo(activityAreaId)
		.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds).andRecTypeNotEqualTo("3");
		couponExample.setOrderByClause("money desc");
		couponExample.setLimitStart(0);
		couponExample.setLimitSize(3);
		return couponMapper.selectByExample(couponExample);
	}
	public Coupon findModeBy(Integer couponId, String activityAreaId) {
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria criteria = couponExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if(activityAreaId != null){
			criteria.andIdEqualTo(couponId);
		}
		if(!StringUtil.isBlank(activityAreaId)){
			criteria.andActivityAreaIdEqualTo(Integer.valueOf(activityAreaId));
		}
		List<Coupon> coupons = couponMapper.selectByExample(couponExample);
		if(CollectionUtils.isNotEmpty(coupons)){
			return coupons.get(0);
		}
		return null;
	}
	public Map<String, Object> getSignGiftCoupon(String couponIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Integer> couponIdList = new ArrayList<Integer>();
		String couponStatus = "0";
		for (String id : couponIds.split(",")) {
			couponIdList.add(Integer.parseInt(id));
		}
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andIdIn(couponIdList).andDelFlagEqualTo("0");
		List<Coupon> coupons = selectByExample(couponExample);
		if(CollectionUtils.isNotEmpty(coupons)){
			couponStatus = "1";
			for (Coupon coupon : coupons) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Integer couponId = coupon.getId();
				BigDecimal minmum = coupon.getMinimum();
				BigDecimal money = coupon.getMoney();
				String expiryType = coupon.getExpiryType();//有效期类型（1绝对时间 2相对时间）
				Integer expiryDays = coupon.getExpiryDays();
				Date expiryEndDate = coupon.getExpiryEndDate();
				Integer grantQuantity = coupon.getGrantQuantity();
				Integer recQuantity = coupon.getRecQuantity();
				String rang = coupon.getRang();
				String couponType = coupon.getCouponType();
				String name = "";
				String expiryDateStr = "";
				String couponTypeName = "";
				Integer surplusQuantity = grantQuantity - recQuantity;
				if(surplusQuantity < 0){
					surplusQuantity = 0;
				}
				if(minmum.subtract(money).compareTo(new BigDecimal("0.01")) == 0){
					name = money.stripTrailingZeros().toPlainString() + "元无门槛";
				}else{
					name = minmum.stripTrailingZeros().toPlainString() + "减" + money.stripTrailingZeros().toPlainString();
				}
				if("1".equals(expiryType)){
					expiryDateStr = DateUtil.getFormatDate(expiryEndDate, "yyyy-MM-dd") + "后过期";
				}else if("2".equals(expiryType)){
					expiryDateStr = "领取后" + expiryDays + "天过期";
				}
				if("1".equals(rang)){
					if("1".equals(couponType)){
						couponTypeName = "平台券";
					}else if("2".equals(couponType)){
						couponTypeName = "品类券";
					}else if("3".equals(couponType)){
						couponTypeName = "品牌券";
					}
				}else if("2".equals(rang)){
					couponTypeName = "会场券";
				}else if("3".equals(rang)){
					couponTypeName = "特卖券";
				}else if("4".equals(rang)){
					couponTypeName = "店铺券";
				}
				dataMap.put("couponTypeName", couponTypeName);
				dataMap.put("couponId", couponId);
				dataMap.put("name", name);
				dataMap.put("expiryDateStr", expiryDateStr);
				dataMap.put("money", money.stripTrailingZeros().toPlainString());
				dataMap.put("surplusQuantity", surplusQuantity);
				dataList.add(dataMap);
			}
		}
		map.put("couponList", dataList);
		map.put("couponStatus", couponStatus);
		return map;
	}
	public List<Map<String, Object>> addSignInCoupon(Integer memberId, String rewardGift, Date currentDate) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<MemberCoupon> memberCouponList = new ArrayList<MemberCoupon>();
		List<Integer> couponIdList = new ArrayList<Integer>();
		for (String id : rewardGift.split(",")) {
			couponIdList.add(Integer.parseInt(id));
		}
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andIdIn(couponIdList).andDelFlagEqualTo("0");
		List<Coupon> coupons = selectByExample(couponExample);
		if(CollectionUtils.isNotEmpty(coupons)){
			for (Coupon coupon : coupons) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Integer couponId = coupon.getId();
				BigDecimal minmum = coupon.getMinimum();
				BigDecimal money = coupon.getMoney();
				Integer grantQuantity = coupon.getGrantQuantity();
				Integer recQuantity = coupon.getRecQuantity();
				String expiryType = coupon.getExpiryType();
				Date expiryBeginDate = coupon.getExpiryBeginDate();
				Date expiryEndDate = coupon.getExpiryEndDate();
				String couponType = coupon.getCouponType();
				Integer surplusQuantity = grantQuantity - recQuantity;
				if("2".equals(expiryType)){
					Integer expiryDays = coupon.getExpiryDays();
					expiryBeginDate = currentDate;
					expiryEndDate = DateUtil.addDay(expiryBeginDate, expiryDays);
				}
				// 更改优惠券的领取数量
				if(surplusQuantity > 0){
					int count = memberCouponService.updateRecCouponInfo(coupon.getId());
					if(count > 0){
						dataMap.put("couponId", couponId);
						dataMap.put("minmum", minmum.stripTrailingZeros().toPlainString());
						dataMap.put("money", money.stripTrailingZeros().toPlainString());
						dataMap.put("expiryBeginDate", DateUtil.getFormatDate(expiryBeginDate, "yyyy.MM.dd HH:mm"));
						dataMap.put("expiryEndDate", DateUtil.getFormatDate(expiryEndDate, "yyyy.MM.dd HH:mm"));
						dataMap.put("couponType", couponType);
						dataList.add(dataMap);
						MemberCoupon memberCoupon = new MemberCoupon();
						memberCoupon.setMemberId(memberId);
						memberCoupon.setCouponId(couponId);
						memberCoupon.setStatus("0");
						memberCoupon.setRecDate(currentDate);
						memberCoupon.setExpiryBeginDate(expiryBeginDate);
						memberCoupon.setExpiryEndDate(expiryEndDate);
						memberCoupon.setReceiveType("2");
						memberCoupon.setDelFlag("0");
						memberCoupon.setCreateBy(Integer.valueOf(memberId));
						memberCoupon.setCreateDate(currentDate);
						memberCouponList.add(memberCoupon);
					}
				}
			}
		}
		if(CollectionUtils.isNotEmpty(memberCouponList)){
			//批量插入表数据
			memberCouponService.insertBatchMemberCoupon(memberCouponList);
		}else{
			throw new ArgException("优惠券已抢光。没关系，请联系平台客服");
		}
		return dataList;
	}
	
	/**
	 * 会场优惠券列表展示
	 * @param activityAreaId
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> getActivityAreaCoupons(Integer activityAreaId, Integer memberId) {
		List<Map<String, Object>> couponList = new ArrayList<>();
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
		String preferentialType = activityArea.getPreferentialType();
		String preferentialIdGroup = activityArea.getPreferentialIdGroup();
		String isSvip = "0";
		if(memberId!=null && memberId!=0){
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			if("1".equals(memberInfo.getIsSvip()) && memberInfo.getSvipExpireDate()!=null && memberInfo.getSvipExpireDate().after(new Date())){
				isSvip = "1";
			}
		}
		if("1".equals(preferentialType) && !StringUtil.isBlank(preferentialIdGroup)){
			//优惠券整合
			Date date = new Date();
			String[] groups = preferentialIdGroup.split(",");
			List<Integer> groupList = new ArrayList<Integer>();
			for (String str : groups) {
				groupList.add(Integer.valueOf(str));
			}
			CouponExample couponExample = new CouponExample();
			if(preferentialType.equals("1")){
				couponExample.createCriteria().andActivityAreaIdEqualTo(activityAreaId)
						.andIdIn(groupList).andDelFlagEqualTo("0").andRecBeginDateLessThanOrEqualTo(date)
						.andRecEndDateGreaterThanOrEqualTo(date).andStatusEqualTo("1").andRecTypeNotEqualTo("3"); 
				List<Coupon> coupons = selectByExample(couponExample);
				if(coupons != null && coupons.size() > 0){
					for (Coupon coupon : coupons) {
						String memberIsHasCouponType = "1";//0未领取 1已领取 2已抢光
						Integer memberCouponCount = 0;
						//优惠券发行数量
						Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
						//优惠券已领数量
						Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
						//优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
						String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
						//优惠券 限领数量
						Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
						Map<String,Object> couponMap = new HashMap<String,Object>();
						if(grantQuantity > 0 && grantQuantity > recQuantity){
							if(memberId == null){
								memberIsHasCouponType = "0";
							}else{
								MemberCouponExample memberCouponExample = new MemberCouponExample();
								memberCouponExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
								.andCouponIdEqualTo(coupon.getId()).andDelFlagEqualTo("0");
								memberCouponExample.setOrderByClause("rec_date desc");
								List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
								if(CollectionUtils.isNotEmpty(memberCoupons)){
									String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
									String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
									memberCouponCount = memberCoupons.size();
									couponMap.put("memberCouponCount", memberCoupons.size());
									if(recLimitType.equals("1")){
										if(!recDate.equals(currentDate)){
											memberIsHasCouponType = "0";
										}
									}else if(recLimitType.equals("2")){
										if(memberCoupons.size() < recEach){
											memberIsHasCouponType = "0";
										}
									}else if(recLimitType.equals("3")){
										memberIsHasCouponType = "0";
									}
								}else{
									memberIsHasCouponType = "0";
								}
							}
						}else{
							memberIsHasCouponType = "2";
						}
						couponMap.put("couponId", coupon.getId());
						couponMap.put("money", coupon.getMoney());
						couponMap.put("minimum", coupon.getMinimum());
						couponMap.put("memberIsHasCouponType", memberIsHasCouponType);
						couponMap.put("recType", coupon.getRecType());
						couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
						couponMap.put("recLimitType", recLimitType);
						couponMap.put("recEach", recEach);
						couponMap.put("memberCouponCount", memberCouponCount);
						couponMap.put("grantQuantity", grantQuantity);
						couponMap.put("recQuantity", recQuantity);
						couponMap.put("isSvip", isSvip);
						couponList.add(couponMap);
					}
				}
			}
		}
		return couponList;
	}
	/**商品券 */
	public Map<String, Object> getProductCouponList(String memberId, Integer productId) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		boolean isHasProductCoupon = false;
		List<Coupon> coupons = findModelsByTypeIds(productId+"");
		if(CollectionUtils.isNotEmpty(coupons)){
			isHasProductCoupon = true;
			for (Coupon coupon : coupons) {
				Map<String, Object> dataMap = buildShopCouponInfo(coupon,StringUtil.isBlank(memberId) ? null : Integer.parseInt(memberId));
				dataList.add(dataMap);
			}
		}
		map.put("tips", "仅限本商品");
		map.put("isHasProductCoupon", isHasProductCoupon);
		map.put("productCouponList", dataList);
		return map;
	}
	/**店铺券 */
	public List<Map<String, Object>> getShopCouponList(Integer memberId, Integer mchtId) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Coupon> coupons = findModelsByMchtId(mchtId);
		if(CollectionUtils.isNotEmpty(coupons)){
			for (Coupon coupon : coupons) {
				Map<String, Object> dataMap = buildShopCouponInfo(coupon, memberId);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	private Map<String, Object> buildShopCouponInfo(Coupon coupon, Integer memberId) {
		Map<String, Object> dataMap = new HashMap<>();
		Integer couponId = coupon.getId();
		BigDecimal money = coupon.getMoney();
		String couponPreferentialType = coupon.getPreferentialType();
		BigDecimal minimum = coupon.getMinimum();
		BigDecimal discount = coupon.getDiscount();
		Integer minicount = coupon.getMinicount();
		String conditionType = coupon.getConditionType();
		String careShopCanRec = coupon.getCareShopCanRec() == null ? "0" : coupon.getCareShopCanRec();
		String msgType = "0";
		String useDescription = "";
		if(memberId != null){
			Map<String, Object> receiveMap = memberCouponService.isReceiveCoupon(memberId, coupon);
			if(receiveMap.containsKey("msgType")){
				msgType = receiveMap.get("msgType")+"";
			}
		}
		if(couponPreferentialType.equals("1")){
			if(minimum!= null){
				useDescription = "满"+minimum.stripTrailingZeros().toPlainString()+"使用 | 活动商品除外";
			}
		}else if(couponPreferentialType.equals("2")){
			if(conditionType.equals("1")){
				useDescription = "无门槛 | 活动商品除外";
			}else if(conditionType.equals("2")){
				useDescription = "满"+minimum.stripTrailingZeros().toPlainString()+"使用 | 活动商品除外";
			}else if(conditionType.equals("3")){
				useDescription = "满"+minicount+"件使用 | 活动商品除外";
			}
		}
		dataMap.put("couponId", couponId);
		dataMap.put("money", money != null ? money.stripTrailingZeros().toPlainString() : money);
		dataMap.put("msgType", msgType);
		dataMap.put("couponPreferentialType", couponPreferentialType);
		dataMap.put("minimum", minimum);
		dataMap.put("discount", discount);
		dataMap.put("minicount", minicount);
		dataMap.put("conditionType", conditionType);
		dataMap.put("useDescription", useDescription);
		dataMap.put("careShopCanRec", careShopCanRec);
		return dataMap;
	}
	
	
	
	public List<Coupon> findModelsByTypeIds(String productId) {
		Date currentDate = new Date();
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andTypeIdsEqualTo(productId).andCouponTypeEqualTo("4").andStatusEqualTo("1").andIsIntegralTurntableEqualTo("0").andDelFlagEqualTo("0")
		.andRecBeginDateLessThanOrEqualTo(currentDate).andRecEndDateGreaterThan(currentDate);
		couponExample.setOrderByClause("preferential_type,money desc,discount");
		return selectByExample(couponExample);
	}
	
	public List<Coupon> findModelsByMchtId(Integer mchtId) {
		Date currentDate = new Date();
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andMchtIdEqualTo(mchtId).andRangEqualTo("4").andCouponTypeNotEqualTo("4").andStatusEqualTo("1").andDelFlagEqualTo("0")
				.andIsIntegralTurntableEqualTo("0").andRecBeginDateLessThanOrEqualTo(currentDate).andRecEndDateGreaterThan(currentDate);
		couponExample.setOrderByClause("preferential_type,money,discount");
		return selectByExample(couponExample);
	}
	public List<CouponCustom> getCouponListByModuleTime(Map<String, Object> paramMap) {
		return couponCustomMapper.getCouponListByModuleTime(paramMap);
	}
	public List<CouponCustom> getCouponListByCouponModuleTime(Map<String, Object> paramMap) {
		return couponCustomMapper.getCouponListByCouponModuleTime(paramMap);
	}

	/**
	 * 商品相关的商品券与店铺券查询
	 */
	public List<Coupon> findProductRelativeCoupon(Integer mchtId, Integer productId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("mchtId", mchtId);
		params.put("productId", productId);
		return couponCustomMapper.findProductRelativeCoupon(params);
	}

	/**
	 * 随机获取一个面额符合积分转盘条件的商品券
	 */
	public Coupon popOneLotteryCoupon(BigDecimal minAmount, BigDecimal maxAmount) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("minAmount", minAmount);
		params.put("maxAmount", maxAmount);
		return this.couponCustomMapper.popOneLotteryCoupon(params);
	}
}
