package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.Coupon;
import com.jf.entity.MchtInfoCustom;

@Repository
public interface MchtInfoCustomMapper {

	List<MchtInfoCustom> getEveryDayShopList(Map<String, Object> paramsMap);

	List<Coupon> getCouponListByIds(Map<String, Object> paramsMap);

}
