package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainSetupExample;

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
