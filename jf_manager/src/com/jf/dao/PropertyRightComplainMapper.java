package com.jf.dao;

import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightComplainMapper extends BaseDao<PropertyRightComplain, PropertyRightComplainExample>{
    int countByExample(PropertyRightComplainExample example);

    int deleteByExample(PropertyRightComplainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightComplain record);

    int insertSelective(PropertyRightComplain record);

    List<PropertyRightComplain> selectByExample(PropertyRightComplainExample example);

    PropertyRightComplain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightComplain record, @Param("example") PropertyRightComplainExample example);

    int updateByExample(@Param("record") PropertyRightComplain record, @Param("example") PropertyRightComplainExample example);

    int updateByPrimaryKeySelective(PropertyRightComplain record);

    int updateByPrimaryKey(PropertyRightComplain record);
}