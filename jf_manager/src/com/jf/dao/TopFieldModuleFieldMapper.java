package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TopFieldModuleField;
import com.jf.entity.TopFieldModuleFieldExample;
@Repository
public interface TopFieldModuleFieldMapper extends BaseDao<TopFieldModuleField, TopFieldModuleFieldExample>{
    int countByExample(TopFieldModuleFieldExample example);

    int deleteByExample(TopFieldModuleFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopFieldModuleField record);

    int insertSelective(TopFieldModuleField record);

    List<TopFieldModuleField> selectByExample(TopFieldModuleFieldExample example);

    TopFieldModuleField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopFieldModuleField record, @Param("example") TopFieldModuleFieldExample example);

    int updateByExample(@Param("record") TopFieldModuleField record, @Param("example") TopFieldModuleFieldExample example);

    int updateByPrimaryKeySelective(TopFieldModuleField record);

    int updateByPrimaryKey(TopFieldModuleField record);
}