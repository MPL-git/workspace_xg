package com.jf.dao;

import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ModuleNavigationMapper extends BaseDao<ModuleNavigation, ModuleNavigationExample>{
    int countByExample(ModuleNavigationExample example);

    int deleteByExample(ModuleNavigationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ModuleNavigation record);

    int insertSelective(ModuleNavigation record);

    List<ModuleNavigation> selectByExample(ModuleNavigationExample example);

    ModuleNavigation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ModuleNavigation record, @Param("example") ModuleNavigationExample example);

    int updateByExample(@Param("record") ModuleNavigation record, @Param("example") ModuleNavigationExample example);

    int updateByPrimaryKeySelective(ModuleNavigation record);

    int updateByPrimaryKey(ModuleNavigation record);
}