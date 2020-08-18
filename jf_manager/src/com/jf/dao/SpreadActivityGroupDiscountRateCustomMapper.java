package com.jf.dao;

import com.jf.entity.SpreadActivityGroupDiscountRate;
import com.jf.entity.SpreadActivityGroupDiscountRateCustom;
import com.jf.entity.SpreadActivityGroupDiscountRateCustomExample;
import com.jf.entity.SpreadActivityGroupDiscountRateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityGroupDiscountRateCustomMapper {
    int countByCustomExample(SpreadActivityGroupDiscountRateCustomExample example);

    List<SpreadActivityGroupDiscountRateCustom> selectByCustomExample(SpreadActivityGroupDiscountRateCustomExample example);

    SpreadActivityGroupDiscountRateCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") SpreadActivityGroupDiscountRate record, @Param("example") SpreadActivityGroupDiscountRateCustomExample example);

}