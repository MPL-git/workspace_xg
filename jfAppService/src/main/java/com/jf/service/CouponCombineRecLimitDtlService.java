package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CouponCombineRecLimitDtlMapper;
import com.jf.entity.CouponCombineRecLimit;
import com.jf.entity.CouponCombineRecLimitDtl;
import com.jf.entity.CouponCombineRecLimitDtlExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CouponCombineRecLimitDtlService extends BaseService<CouponCombineRecLimitDtl, CouponCombineRecLimitDtlExample> {
	@Autowired
	private CouponCombineRecLimitDtlMapper couponCombineRecLimitDtlMapper;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private CouponCombineRecLimitService couponCombineRecLimitService;
	
	@Autowired
	public void setCouponCombineRecLimitDtlMapper(CouponCombineRecLimitDtlMapper couponCombineRecLimitDtlMapper) {
		this.setDao(couponCombineRecLimitDtlMapper);
		this.couponCombineRecLimitDtlMapper = couponCombineRecLimitDtlMapper;
	}
	
	public Map<String, Object> isCombinationLimit(Integer couponId, Integer limitNum,Integer memberId, String recLimitType) {
		boolean success = true;
		String msg = "";
		List<CouponCombineRecLimitDtl> recLimitDtls = findModesByCouponId(couponId);
		if(CollectionUtils.isNotEmpty(recLimitDtls)){
			List<Integer> couponCombineRecLimitIdList = new ArrayList<>();
			List<Integer> couponIdList = new ArrayList<>();
			for (CouponCombineRecLimitDtl couponCombineRecLimitDtl : recLimitDtls) {
				couponCombineRecLimitIdList.add(couponCombineRecLimitDtl.getCouponCombineRecLimitId());
			}
			recLimitDtls = findModeListByCouponCombineRecLimitId(couponCombineRecLimitIdList);
			CouponCombineRecLimitDtl combineRecLimitDtl = recLimitDtls.get(0);
			for (CouponCombineRecLimitDtl couponCombineRecLimitDtl : recLimitDtls) {
				couponIdList.add(couponCombineRecLimitDtl.getCouponId());
			}
			Integer memberCouponNum = memberCouponService.findMemberCouponNumByCouponId(memberId, couponIdList,recLimitType);
			if(memberCouponNum >= limitNum){
				CouponCombineRecLimit couponCombineRecLimit = couponCombineRecLimitService.selectByPrimaryKey(combineRecLimitDtl.getCouponCombineRecLimitId());
				success = false;
				msg = couponCombineRecLimit.getName()+"限领"+limitNum+"张";
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("success", success);
		map.put("msg", msg);
		return map;
	}

	public List<CouponCombineRecLimitDtl> findModesByCouponId(Integer couponId) {
		CouponCombineRecLimitDtlExample couponCombineRecLimitDtlExample = new CouponCombineRecLimitDtlExample();
		couponCombineRecLimitDtlExample.createCriteria().andCouponIdEqualTo(couponId).andDelFlagEqualTo("0");
		return selectByExample(couponCombineRecLimitDtlExample);
	}
	
	public List<CouponCombineRecLimitDtl> findModeListByCouponCombineRecLimitId(List<Integer> couponCombineRecLimitIdList) {
		CouponCombineRecLimitDtlExample couponCombineRecLimitDtlExample = new CouponCombineRecLimitDtlExample();
		couponCombineRecLimitDtlExample.createCriteria().andCouponCombineRecLimitIdIn(couponCombineRecLimitIdList).andDelFlagEqualTo("0");
		couponCombineRecLimitDtlExample.setOrderByClause("id desc");
		return selectByExample(couponCombineRecLimitDtlExample);
	}
	
}
