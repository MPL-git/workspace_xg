package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PropertyRightProsecutionLog;
import com.jf.entity.PropertyRightProsecutionLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightProsecutionLogMapper extends BaseDao<PropertyRightProsecutionLog, PropertyRightProsecutionLogExample> {
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
