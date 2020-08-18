package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CouponCustomMapper;
import com.jf.dao.CouponMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 下午4:57:37 
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
	private MemberCouponService memberCouponService;
	@Autowired
	private ActivityAreaService activityAreaService;
	@Autowired
	private CouponCustomMapper couponCustomMapper;
	
	@Autowired
	public void setCouponMapper(CouponMapper couponMapper) {
		this.setDao(couponMapper);
		this.couponMapper = couponMapper;
	}
	public void updateByModel(Coupon coupon) {
		
		couponMapper.updateByPrimaryKeySelective(coupon);
	}

	public int increaseCouponRecQuantity(Integer couponId) {
		return couponCustomMapper.increaseCouponRecQuantity(couponId);
	}
	
	public List<Map<String, Object>> getCouponList(String recType,Integer memberId, String isSvip) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Coupon> coupons = findModelByRecType(recType);
		if(CollectionUtils.isNotEmpty(coupons)){
			for (Coupon coupon : coupons) {
				Map<String, Object> dataMap = new HashMap<>();
				Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();//优惠券发行数量
				Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();//优惠券已领数量
				BigDecimal minimum = coupon.getMinimum();
				BigDecimal money = coupon.getMoney();
				String minimumStr = "";
				boolean isCanReceive = false;
				String isCanReceiveStr = "";
				String describeContent = "抢购类型商品不可用";
				if("1".equals(isSvip)){
					if(recQuantity >= grantQuantity){
						isCanReceiveStr = "已抢光";
					}else{
						Map<String, Object> couponMap = memberCouponService.isReceiveCoupon(memberId, coupon);
						if((boolean) couponMap.get("isCanReceive")){
							isCanReceive = true;
							isCanReceiveStr = "立即领取";
						}else{
							isCanReceiveStr = "已领取";
						}
					}
				}else{
					isCanReceive = true;
					isCanReceiveStr = "立即领取";
				}
				if(minimum.subtract(money).compareTo(new BigDecimal("0.01")) == 0){
					minimumStr = money.stripTrailingZeros().toPlainString()+"元无门槛";
				}else{
					minimumStr = "满"+minimum.stripTrailingZeros().toPlainString()+"元使用";
				}
				dataMap.put("couponId", coupon.getId());
				dataMap.put("minimum", minimumStr);
				dataMap.put("money", money);
				dataMap.put("isCanReceive", isCanReceive);
				dataMap.put("isCanReceiveStr", isCanReceiveStr);
				dataMap.put("describeContent", describeContent);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	public List<Coupon> findModelByRecType(String recType) {
		Date currentDate = new Date();
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andRecTypeEqualTo(recType).andStatusEqualTo("1").andDelFlagEqualTo("0").andIsIntegralTurntableEqualTo("0")
	    .andRecLimitTypeEqualTo("4").andRecBeginDateLessThanOrEqualTo(currentDate).andRecEndDateGreaterThanOrEqualTo(currentDate);
		couponExample.setOrderByClause("(minimum-money) ASC");
		return selectByExample(couponExample);
	}
	/**
	 * 会场优惠券列表展示
	 * @param activityAreaId
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> getActivityAreaCoupons(Integer activityAreaId, String memberId) {
		List<Map<String, Object>> couponList = new ArrayList<>();
		ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
		String preferentialType = activityArea.getPreferentialType();
		String preferentialIdGroup = activityArea.getPreferentialIdGroup();
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
							if(StringUtil.isBlank(memberId)){
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
						couponList.add(couponMap);
					}
				}
			}
		}
		return couponList;
	}
	/**商品券 */
	public Map<String, Object> getProductCouponList(Integer memberId, Integer productId) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		boolean isHasProductCoupon = false;
		List<Coupon> coupons = findModelsByTypeIds(productId+"");
		if(CollectionUtils.isNotEmpty(coupons)){
			isHasProductCoupon = true;
			for (Coupon coupon : coupons) {
				Map<String, Object> dataMap = buildShopCouponInfo(coupon,memberId);
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
		dataMap.put("recLimitType", coupon.getRecLimitType());
		return dataMap;
	}
	
	
	
	public List<Coupon> findModelsByTypeIds(String productId) {
		Date currentDate = new Date();
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andTypeIdsEqualTo(productId).andCouponTypeEqualTo("4").andStatusEqualTo("1").andDelFlagEqualTo("0")
				.andIsIntegralTurntableEqualTo("0").andRecBeginDateLessThanOrEqualTo(currentDate).andRecEndDateGreaterThan(currentDate);
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
	public List<Coupon> getCouponListByRecBeginDate(List<Date> eachTimeList) {
		List<String> couponTypeList = new ArrayList<String>();
		couponTypeList.add("1");
		couponTypeList.add("2");
		CouponExample e = new CouponExample();
		e.createCriteria().andDelFlagEqualTo("0").andRangEqualTo("1").andIsIntegralTurntableEqualTo("0")
		.andBelongEqualTo("1").andCouponTypeIn(couponTypeList)
		.andStatusEqualTo("1").andRecBeginDateIn(eachTimeList)
		.andRecTypeEqualTo("1").andRecEndDateGreaterThanOrEqualTo(new Date());
		return this.selectByExample(e);
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
}
