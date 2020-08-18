package com.jf.dao;

import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberPlatformCoupon;
import com.jf.entity.dto.MemberCouponCountDTO;
import com.jf.entity.dto.MemberCouponCountV2DTO;
import com.jf.entity.dto.MemberCouponDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午5:00:06 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface MemberCouponCustomMapper {
	
	List<MemberCouponCustom> getMemberCouponLists(Map<String,Object> params);
	/**
	 * 废弃20190709
	 * @param params
	 * @return
	 */
	List<MemberCouponDTO> getMemberCouponList(Map<String, Object> params);

	MemberCouponDTO getMemberCouponCount(Integer memberId);
	
	List<Map<String, Object>> getMemberUsebleActivityAreaCoupons(Map<String, Object> params);
	
	List<MemberCouponCustom> getMemberUseblePlatformCoupons(Map<String, Object> platParamsMap);

	void addMemberCouponBatch(String in_dataStr);

	List<Map<String, Object>> getMemberUsebleMchtShopCoupons(Map<String, Object> params);

	int getExchangeCouponCount(Integer memberId);

	List<MemberCouponDTO> findProductCouponList(Map<String, Object> paramsMap);
	/**Android<=57,ios<=58(废弃)*/
	List<MemberPlatformCoupon> getMemberUseblePlatformCouponsOld(Map<String, Object> platParamsMap);
	
	List<MemberCoupon> getMemberMchtCouponByMchtIdOrMemberId(Map<String, Object> paramsMap);

    MemberCouponCountDTO countMemberNotUsedCoupon(Integer memberId);

	MemberCouponCountV2DTO countMemberNotUsedCouponV2(Integer memberId);

	int countMemberCoupon(Map<String, Object> params);

    List<MemberCouponCustom> findMemberCoupon(Map<String, Object> params);

	int countMemberCouponV2(Map<String, Object> params);

    List<MemberCouponCustom> findMemberCouponV2(Map<String, Object> params);

    List<Map<String,Object>> countMemberRecQuantity(Map<String, Object> params);

    List<MemberCoupon> findMemberActiveCoupon(Map<String, Object> params);

}
