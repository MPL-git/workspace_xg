package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategoryItem;
import com.jf.entity.MallCategoryItemCustom;
import com.jf.entity.MallCategoryItemCustomExample;

@Repository
public interface MallCategoryItemCustomMapper {
    int countByCustomExample(MallCategoryItemCustomExample example);

    List<MallCategoryItemCustom> selectByCustomExample(MallCategoryItemCustomExample example);

    MallCategoryItemCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") MallCategoryItem record, @Param("example") MallCategoryItemCustomExample example);

}