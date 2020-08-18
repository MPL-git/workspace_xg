package com.jf.dao;

import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainSetupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRainSetupMapper extends BaseDao<CouponRainSetup, CouponRainSetupExample> {
    int countByExample(CouponRainSetupExample example);

    int deleteByExample(CouponRainSetupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponRainSetup record);

    int insertSelective(CouponRainSetup record);

    List<CouponRainSetup> selectByExample(CouponRainSetupExample example);

    CouponRainSetup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponRainSetup record, @Param("example") CouponRainSetupExample example);

    int updateByExample(@Param("record") CouponRainSetup record, @Param("example") CouponRainSetupExample example);

    int updateByPrimaryKeySelective(CouponRainSetup record);

    int updateByPrimaryKey(CouponRainSetup record);
}
