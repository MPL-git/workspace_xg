package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AndroidChannelGroupDiscountRate;
import com.jf.entity.AndroidChannelGroupDiscountRateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndroidChannelGroupDiscountRateMapper extends BaseDao<AndroidChannelGroupDiscountRate, AndroidChannelGroupDiscountRateExample> {
    int countByExample(AndroidChannelGroupDiscountRateExample example);

    int deleteByExample(AndroidChannelGroupDiscountRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AndroidChannelGroupDiscountRate record);

    int insertSelective(AndroidChannelGroupDiscountRate record);

    List<AndroidChannelGroupDiscountRate> selectByExample(AndroidChannelGroupDiscountRateExample example);

    AndroidChannelGroupDiscountRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AndroidChannelGroupDiscountRate record, @Param("example") AndroidChannelGroupDiscountRateExample example);

    int updateByExample(@Param("record") AndroidChannelGroupDiscountRate record, @Param("example") AndroidChannelGroupDiscountRateExample example);

    int updateByPrimaryKeySelective(AndroidChannelGroupDiscountRate record);

    int updateByPrimaryKey(AndroidChannelGroupDiscountRate record);
}