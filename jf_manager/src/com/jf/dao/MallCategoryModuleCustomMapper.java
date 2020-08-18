package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategoryModule;
import com.jf.entity.MallCategoryModuleCustom;
import com.jf.entity.MallCategoryModuleCustomExample;

@Repository
public interface MallCategoryModuleCustomMapper {
    int countByCustomExample(MallCategoryModuleCustomExample example);

    List<MallCategoryModuleCustom> selectByCustomExample(MallCategoryModuleCustomExample example);

    MallCategoryModuleCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") MallCategoryModule record, @Param("example") MallCategoryModuleCustomExample example);

}