package com.jf.dao;

import com.jf.entity.Coupon;
import com.jf.entity.dto.CouponRainDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
@Repository
public interface CouponRainCustomMapper {

    Coupon randomOneProductCoupon(Map<String, Object> params);

    Coupon randomOnePlatformCoupon(Map<String, Object> params);

    Coupon randomOneAreaCoupon(Map<String, Object> params);

    int increaseCouponRecCount(@Param("couponId") int couponId);

    CouponRainDTO getEnableFirstRain(Map<String, Object> params);

    int countCouponRainParticipate(@Param("couponRainId") Integer couponRainId);

}
