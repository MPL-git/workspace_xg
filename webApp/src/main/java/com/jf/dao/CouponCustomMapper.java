package com.jf.dao;

import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CouponCustomMapper {

	List<CouponCustom> getCouponListByModuleTime(Map<String, Object> paramMap);

	List<CouponCustom> getCouponListByCouponModuleTime(Map<String, Object> paramMap);

    List<Coupon> findProductRelativeCoupon(Map<String, Object> params);

    Coupon popOneLotteryCoupon(Map<String, Object> params);

}
