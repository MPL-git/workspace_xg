package com.jf.dao;

import java.util.List;
import java.util.Map;

import com.jf.entity.dto.MemberCouponCountDTO;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.dto.MemberCouponDTO;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午5:00:06 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface MemberCouponCustomMapper {

	List<MemberCouponCustom> getMemberCouponList(Map<String, Object> params);

	MemberCouponDTO getMemberCouponCount(Integer memberId);

	List<Map<String, Object>> getMemberUsebleActivityAreaCoupons(Map<String, Object> params);
	
	List<MemberCouponCustom> getMemberUseblePlatformCoupons(Map<String, Object> platParamsMap);

	void addMemberCouponBatch(String in_dataStr);

	List<Map<String, Object>> getMemberUsebleMchtShopCoupons(Map<String, Object> params);

	int getExchangeCouponCount(Integer memberId);

	List<MemberCouponDTO> getActivityMemberCouponInfo618(Map<String, Object> map);

	int updateRecCouponInfo(int couponId);

	void insertBatchMemberCoupon(List<MemberCoupon> memberCouponList);

	List<MemberCouponDTO> findProductCouponList(Map<String, Object> paramsMap);
	
	List<MemberCoupon> getMemberMchtCouponByMchtIdOrMemberId(Map<String, Object> paramsMap);

	MemberCouponCountDTO countMemberNotUsedCoupon(Integer memberId);

	int countMemberCoupon(Map<String, Object> params);

	List<MemberCouponCustom> findMemberCoupon(Map<String, Object> params);

    List<Map<String, Object>> countMemberRecQuantity(Map<String, Object> params);

	List<MemberCoupon> findMemberActiveCoupon(Map<String, Object> params);

}
