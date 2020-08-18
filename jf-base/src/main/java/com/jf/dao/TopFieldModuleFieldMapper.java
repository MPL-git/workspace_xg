package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TopFieldModuleField;
import com.jf.entity.TopFieldModuleFieldExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopFieldModuleFieldMapper extends BaseDao<TopFieldModuleField, TopFieldModuleFieldExample> {
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
