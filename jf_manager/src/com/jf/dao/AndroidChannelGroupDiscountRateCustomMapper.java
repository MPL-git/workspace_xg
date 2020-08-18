package com.jf.dao;

import com.jf.entity.AndroidChannelGroupDiscountRate;
import com.jf.entity.AndroidChannelGroupDiscountRateCustom;
import com.jf.entity.AndroidChannelGroupDiscountRateCustomExample;
import com.jf.entity.AndroidChannelGroupDiscountRateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndroidChannelGroupDiscountRateCustomMapper {
    int countByCustomExample(AndroidChannelGroupDiscountRateCustomExample example);

    List<AndroidChannelGroupDiscountRateCustom> selectByCustomExample(AndroidChannelGroupDiscountRateCustomExample example);

    AndroidChannelGroupDiscountRateCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") AndroidChannelGroupDiscountRate record, @Param("example") AndroidChannelGroupDiscountRateCustomExample example);

}