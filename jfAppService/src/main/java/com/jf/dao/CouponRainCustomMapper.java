package com.jf.dao;

import com.jf.entity.Coupon;
import com.jf.entity.CouponRain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
@Repository
public interface CouponRainCustomMapper {

    Coupon randomOneProductCoupon(Map<String, Object> params);

    Coupon randomOnePlatformCoupon(Map<String, Object> params);

    Coupon randomOneAreaCoupon(Map<String, Object> params);

    int increaseCouponRecCount(@Param("couponId") int couponId);

    CouponRain getEnableFirstRain(@Param("memberId") Integer memberId);

    int countCouponRainParticipate(@Param("couponRainId") Integer couponRainId);

}
