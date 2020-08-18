package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PropertyRightComplainLog;
import com.jf.entity.PropertyRightComplainLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightComplainLogMapper extends BaseDao<PropertyRightComplainLog, PropertyRightComplainLogExample> {
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
