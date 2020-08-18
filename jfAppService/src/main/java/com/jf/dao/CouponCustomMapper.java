package com.jf.dao;

import com.jf.entity.Coupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CouponCustomMapper {

	int increaseCouponRecQuantity(@Param("couponId") Integer couponId);

    List<Coupon> findProductRelativeCoupon(Map<String, Object> params);

}
