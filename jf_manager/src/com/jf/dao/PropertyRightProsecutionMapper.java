package com.jf.dao;

import com.jf.entity.PropertyRightProsecution;
import com.jf.entity.PropertyRightProsecutionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightProsecutionMapper extends BaseDao<PropertyRightProsecution, PropertyRightProsecutionExample>{
    int countByExample(PropertyRightProsecutionExample example);

    int deleteByExample(PropertyRightProsecutionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightProsecution record);

    int insertSelective(PropertyRightProsecution record);

    List<PropertyRightProsecution> selectByExample(PropertyRightProsecutionExample example);

    PropertyRightProsecution selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightProsecution record, @Param("example") PropertyRightProsecutionExample example);

    int updateByExample(@Param("record") PropertyRightProsecution record, @Param("example") PropertyRightProsecutionExample example);

    int updateByPrimaryKeySelective(PropertyRightProsecution record);

    int updateByPrimaryKey(PropertyRightProsecution record);
}