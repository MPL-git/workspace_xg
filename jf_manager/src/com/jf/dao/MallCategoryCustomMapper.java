package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryCustom;
import com.jf.entity.MallCategoryCustomExample;

@Repository
public interface MallCategoryCustomMapper {
    int countByCustomExample(MallCategoryCustomExample example);

    List<MallCategoryCustom> selectByCustomExample(MallCategoryCustomExample example);

    MallCategoryCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") MallCategory record, @Param("example") MallCategoryCustomExample example);

}