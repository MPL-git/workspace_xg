package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DouyinSprDtl;
import com.jf.entity.DouyinSprDtlCustom;
import com.jf.entity.DouyinSprDtlCustomExample;

@Repository
public interface DouyinSprDtlCustomMapper {
    int countByCustomExample(DouyinSprDtlCustomExample example);

    List<DouyinSprDtlCustom> selectByCustomExample(DouyinSprDtlCustomExample example);

    DouyinSprDtlCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") DouyinSprDtl record, @Param("example") DouyinSprDtlCustomExample example);

}