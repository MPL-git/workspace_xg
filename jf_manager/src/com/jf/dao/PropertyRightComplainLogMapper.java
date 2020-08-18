package com.jf.dao;

import com.jf.entity.PropertyRightComplainLog;
import com.jf.entity.PropertyRightComplainLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightComplainLogMapper extends BaseDao<PropertyRightComplainLog, PropertyRightComplainLogExample>{
    int countByExample(PropertyRightComplainLogExample example);

    int deleteByExample(PropertyRightComplainLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightComplainLog record);

    int insertSelective(PropertyRightComplainLog record);

    List<PropertyRightComplainLog> selectByExample(PropertyRightComplainLogExample example);

    PropertyRightComplainLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightComplainLog record, @Param("example") PropertyRightComplainLogExample example);

    int updateByExample(@Param("record") PropertyRightComplainLog record, @Param("example") PropertyRightComplainLogExample example);

    int updateByPrimaryKeySelective(PropertyRightComplainLog record);

    int updateByPrimaryKey(PropertyRightComplainLog record);
}