package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ModuleItem;
import com.jf.entity.ModuleItemExample;
@Repository
public interface ModuleItemMapper extends BaseDao<ModuleItem, ModuleItemExample>{
    int countByExample(ModuleItemExample example);

    int deleteByExample(ModuleItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ModuleItem record);

    int insertSelective(ModuleItem record);

    List<ModuleItem> selectByExample(ModuleItemExample example);

    ModuleItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ModuleItem record, @Param("example") ModuleItemExample example);

    int updateByExample(@Param("record") ModuleItem record, @Param("example") ModuleItemExample example);

    int updateByPrimaryKeySelective(ModuleItem record);

    int updateByPrimaryKey(ModuleItem record);
}