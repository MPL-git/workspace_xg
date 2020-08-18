package com.jf.dao;

import com.jf.entity.MallCategoryModule;
import com.jf.entity.MallCategoryModuleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MallCategoryModuleMapper extends BaseDao<MallCategoryModule, MallCategoryModuleExample> {
    int countByExample(MallCategoryModuleExample example);

    int deleteByExample(MallCategoryModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCategoryModule record);

    int insertSelective(MallCategoryModule record);

    List<MallCategoryModule> selectByExample(MallCategoryModuleExample example);

    MallCategoryModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCategoryModule record, @Param("example") MallCategoryModuleExample example);

    int updateByExample(@Param("record") MallCategoryModule record, @Param("example") MallCategoryModuleExample example);

    int updateByPrimaryKeySelective(MallCategoryModule record);

    int updateByPrimaryKey(MallCategoryModule record);
}