package com.jf.dao;

import com.jf.entity.PropertyRightProsecutionLog;
import com.jf.entity.PropertyRightProsecutionLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightProsecutionLogMapper extends BaseDao<PropertyRightProsecutionLog, PropertyRightProsecutionLogExample>{
    int countByExample(PropertyRightProsecutionLogExample example);

    int deleteByExample(PropertyRightProsecutionLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightProsecutionLog record);

    int insertSelective(PropertyRightProsecutionLog record);

    List<PropertyRightProsecutionLog> selectByExample(PropertyRightProsecutionLogExample example);

    PropertyRightProsecutionLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightProsecutionLog record, @Param("example") PropertyRightProsecutionLogExample example);

    int updateByExample(@Param("record") PropertyRightProsecutionLog record, @Param("example") PropertyRightProsecutionLogExample example);

    int updateByPrimaryKeySelective(PropertyRightProsecutionLog record);

    int updateByPrimaryKey(PropertyRightProsecutionLog record);
}