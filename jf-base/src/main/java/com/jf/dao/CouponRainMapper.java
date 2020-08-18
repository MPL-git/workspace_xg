package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainExample;

@Repository
public interface CouponRainMapper extends BaseDao<CouponRain, CouponRainExample> {
    int countByExample(CouponRainExample example);

    int deleteByExample(CouponRainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponRain record);

    int insertSelective(CouponRain record);

    List<CouponRain> selectByExample(CouponRainExample example);

    CouponRain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponRain record, @Param("example") CouponRainExample example);

    int updateByExample(@Param("record") CouponRain record, @Param("example") CouponRainExample example);

    int updateByPrimaryKeySelective(CouponRain record);

    int updateByPrimaryKey(CouponRain record);
}
