package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ToutiaoTokenAdvertiserInfo;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustom;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustomExample;

@Repository
public interface ToutiaoTokenAdvertiserInfoCustomMapper {
    int countByCustomExample(ToutiaoTokenAdvertiserInfoCustomExample example);

    List<ToutiaoTokenAdvertiserInfoCustom> selectByCustomExample(ToutiaoTokenAdvertiserInfoCustomExample example);

    ToutiaoTokenAdvertiserInfoCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") ToutiaoTokenAdvertiserInfo record, @Param("example") ToutiaoTokenAdvertiserInfoCustomExample example);

    ToutiaoTokenAdvertiserInfoCustom selectToutiaoTokenAdvertiserInfo(@Param("advertiserId")String advertiserId);
    
}