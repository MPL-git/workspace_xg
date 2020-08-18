package com.jf.dao;

import com.jf.entity.PropertyRightAppealLog;
import com.jf.entity.PropertyRightAppealLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightAppealLogMapper extends BaseDao<PropertyRightAppealLog, PropertyRightAppealLogExample>{
    int countByExample(PropertyRightAppealLogExample example);

    int deleteByExample(PropertyRightAppealLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightAppealLog record);

    int insertSelective(PropertyRightAppealLog record);

    List<PropertyRightAppealLog> selectByExample(PropertyRightAppealLogExample example);

    PropertyRightAppealLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightAppealLog record, @Param("example") PropertyRightAppealLogExample example);

    int updateByExample(@Param("record") PropertyRightAppealLog record, @Param("example") PropertyRightAppealLogExample example);

    int updateByPrimaryKeySelective(PropertyRightAppealLog record);

    int updateByPrimaryKey(PropertyRightAppealLog record);
}