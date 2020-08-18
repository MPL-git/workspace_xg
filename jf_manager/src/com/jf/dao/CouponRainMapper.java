package com.jf.dao;

import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
