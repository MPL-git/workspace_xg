package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SpreadActivityGroupDiscountRate;
import com.jf.entity.SpreadActivityGroupDiscountRateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityGroupDiscountRateMapper extends BaseDao<SpreadActivityGroupDiscountRate, SpreadActivityGroupDiscountRateExample> {
    int countByExample(SpreadActivityGroupDiscountRateExample example);

    int deleteByExample(SpreadActivityGroupDiscountRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadActivityGroupDiscountRate record);

    int insertSelective(SpreadActivityGroupDiscountRate record);

    List<SpreadActivityGroupDiscountRate> selectByExample(SpreadActivityGroupDiscountRateExample example);

    SpreadActivityGroupDiscountRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadActivityGroupDiscountRate record, @Param("example") SpreadActivityGroupDiscountRateExample example);

    int updateByExample(@Param("record") SpreadActivityGroupDiscountRate record, @Param("example") SpreadActivityGroupDiscountRateExample example);

    int updateByPrimaryKeySelective(SpreadActivityGroupDiscountRate record);

    int updateByPrimaryKey(SpreadActivityGroupDiscountRate record);
}