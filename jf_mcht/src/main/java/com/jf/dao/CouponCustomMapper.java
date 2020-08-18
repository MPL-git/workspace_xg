package com.jf.dao;

import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CouponCustomMapper {
	List<CouponCustom> selectCouponCustomByExample(CouponExample example);
	int countCouponCustomByExample(CouponExample example);

    List<Map<String, Object>> queryCouponAndAreaId(@Param("mchtId") int mchtId, @Param("status") int status, @Param("limitStart") int limitStart, @Param("limitSize") int limitSize);
}
