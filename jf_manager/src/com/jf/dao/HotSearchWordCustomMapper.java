package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordCustom;
import com.jf.entity.HotSearchWordCustomExample;

@Repository
public interface HotSearchWordCustomMapper {
    int countByCustomExample(HotSearchWordCustomExample example);

    List<HotSearchWordCustom> selectByCustomExample(HotSearchWordCustomExample example);

    HotSearchWordCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") HotSearchWord record, @Param("example") HotSearchWordCustomExample example);

    List<HotSearchWordCustom> selectByCustomExampleNew(Map<String, Object> map);
}