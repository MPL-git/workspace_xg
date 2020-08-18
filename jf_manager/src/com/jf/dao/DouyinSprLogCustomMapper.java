package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DouyinSprLog;
import com.jf.entity.DouyinSprLogCustom;
import com.jf.entity.DouyinSprLogCustomExample;

@Repository
public interface DouyinSprLogCustomMapper {
    int countByCustomExample(DouyinSprLogCustomExample example);

    List<DouyinSprLogCustom> selectByCustomExample(DouyinSprLogCustomExample example);

    DouyinSprLogCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") DouyinSprLog record, @Param("example") DouyinSprLogCustomExample example);

}