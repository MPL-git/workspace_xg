package com.jf.dao;

import com.jf.entity.AndroidChannelGroupDiscountRate;
import com.jf.entity.AndroidChannelGroupDiscountRateCustom;
import com.jf.entity.AndroidChannelGroupDiscountRateCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AndroidChannelGroupDiscountRateCustomMapper {
    int countByCustomExample(AndroidChannelGroupDiscountRateCustomExample example);

    List<AndroidChannelGroupDiscountRateCustom> selectByCustomExample(AndroidChannelGroupDiscountRateCustomExample example);

    AndroidChannelGroupDiscountRateCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") AndroidChannelGroupDiscountRate record, @Param("example") AndroidChannelGroupDiscountRateCustomExample example);

    void insertAndroidChannelGroupDiscountRateList(String dateStr);

}