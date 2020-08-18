package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.CumulativeSignInSettingCustomMapper;
import com.jf.dao.CumulativeSignInSettingMapper;
import com.jf.entity.CumulativeSignInSetting;
import com.jf.entity.CumulativeSignInSettingCustom;
import com.jf.entity.CumulativeSignInSettingExample;

@Service
@Transactional
public class CumulativeSignInSettingService extends BaseService<CumulativeSignInSetting, CumulativeSignInSettingExample> {
	@Autowired
	private CumulativeSignInSettingMapper cumulativeSignInSettingMapper;
	@Autowired
	private CumulativeSignInSettingCustomMapper cumulativeSignInSettingCustomMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private CouponService couponService;
	@Autowired
	public void setCumulativeSignInSettingMapper(CumulativeSignInSettingMapper cumulativeSignInSettingMapper) {
		this.setDao(cumulativeSignInSettingMapper);
		this.cumulativeSignInSettingMapper = cumulativeSignInSettingMapper;
	}
	public List<CumulativeSignInSetting> getMonthCumulativeAward(Integer signInManageId) {
		if(signInManageId != null){
			CumulativeSignInSettingExample cumulativeSignInSettingExample = new CumulativeSignInSettingExample();
			cumulativeSignInSettingExample.createCriteria().andSignInManageIdEqualTo(signInManageId).andStatusEqualTo("1").andDelFlagEqualTo("0");
			cumulativeSignInSettingExample.setOrderByClause("cumulative_sign_in_count");
			return selectByExample(cumulativeSignInSettingExample);
		}
		return null;
	}
	public List<Map<String, Object>> getCumulativeAwardList(Integer memberId,Integer signInManageId, Integer memberMonthSignInCount) {
		List<Map<String, Object>> cumulativeAwardList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("signInManageId", signInManageId);
		paramsMap.put("memberId", memberId);
		List<CumulativeSignInSettingCustom> customs = cumulativeSignInSettingCustomMapper.getMonthCumulativeAward(paramsMap);
		if(CollectionUtils.isNotEmpty(customs)){
			for (CumulativeSignInSettingCustom custom : customs) {
				Map<String, Object> cumulativeAwardMap = new HashMap<String, Object>();
				Integer cumulativeSignInSettingId = custom.getId();
				String rewardName = custom.getRewardName();
				Integer integral = custom.getIntegral();
				String productCode = custom.getProductCode();
				String couponIds = custom.getCouponIds();
				Integer stock = custom.getStock();
				Integer membeCumulativeSignInCount = custom.getMembeCumulativeSignInCount();//用户领取数量（用来判断改奖品是否领取过）
				Integer cumulativeSignInCount = custom.getCumulativeSignInCount();
				String isDrawAwadStatus = "0";//是否领取过奖品 0 否 1是
				String isAchieveRequire = "0";//是否达到领取规则 0 否 1是
				String integralStatus = "0"; //是否赠送积分0 否 1是
				String productStatus = "0"; //是否赠送商品0 否 1是
				String couponStatus = "0"; //是否赠送优惠券0 否 1是
				if(membeCumulativeSignInCount != null && membeCumulativeSignInCount > 0){
					isDrawAwadStatus = "1";
				}
				if(memberMonthSignInCount >= cumulativeSignInCount){
					//累签次数>= 累计签到次数规则
					isAchieveRequire = "1";
				}
				if(integral != null && integral > 0){
					integralStatus = "1";
				}
				if(!StringUtil.isBlank(productCode)){
					Map<String, Object> productMap = productService.getSignGiftProduct(productCode);
					productStatus = productMap.get("productStatus").toString();
					cumulativeAwardMap.put("productMap", productMap);
				}
				if(!StringUtil.isBlank(couponIds)){
					Map<String, Object> giftCouponMap = couponService.getSignGiftCoupon(couponIds);
					couponStatus = giftCouponMap.get("couponStatus").toString();
					cumulativeAwardMap.put("couponList", giftCouponMap.get("couponList"));
				}
				cumulativeAwardMap.put("stock", stock);
				cumulativeAwardMap.put("rewardName", rewardName);
				cumulativeAwardMap.put("isDrawAwadStatus", isDrawAwadStatus);
				cumulativeAwardMap.put("isAchieveRequire", isAchieveRequire);
				cumulativeAwardMap.put("integralStatus", integralStatus);
				cumulativeAwardMap.put("productStatus", productStatus);
				cumulativeAwardMap.put("couponStatus", couponStatus);
				cumulativeAwardMap.put("integral", integral);
				cumulativeAwardMap.put("cumulativeSignInCount", cumulativeSignInCount);
				cumulativeAwardMap.put("cumulativeSignInSettingId", cumulativeSignInSettingId);
				cumulativeAwardList.add(cumulativeAwardMap);
			}
		}
		return cumulativeAwardList;
	}
	
	
}
