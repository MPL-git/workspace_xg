package com.jf.dao;

import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightComplainPicMapper extends BaseDao<PropertyRightComplainPic, PropertyRightComplainPicExample>{
    int countByExample(PropertyRightComplainPicExample example);

    int deleteByExample(PropertyRightComplainPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightComplainPic record);

    int insertSelective(PropertyRightComplainPic record);

    List<PropertyRightComplainPic> selectByExample(PropertyRightComplainPicExample example);

    PropertyRightComplainPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightComplainPic record, @Param("example") PropertyRightComplainPicExample example);

    int updateByExample(@Param("record") PropertyRightComplainPic record, @Param("example") PropertyRightComplainPicExample example);

    int updateByPrimaryKeySelective(PropertyRightComplainPic record);

    int updateByPrimaryKey(PropertyRightComplainPic record);
}