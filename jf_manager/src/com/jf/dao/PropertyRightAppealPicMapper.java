package com.jf.dao;

import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRightAppealPicMapper extends BaseDao<PropertyRightAppealPic, PropertyRightAppealPicExample>{
    int countByExample(PropertyRightAppealPicExample example);

    int deleteByExample(PropertyRightAppealPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightAppealPic record);

    int insertSelective(PropertyRightAppealPic record);

    List<PropertyRightAppealPic> selectByExample(PropertyRightAppealPicExample example);

    PropertyRightAppealPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightAppealPic record, @Param("example") PropertyRightAppealPicExample example);

    int updateByExample(@Param("record") PropertyRightAppealPic record, @Param("example") PropertyRightAppealPicExample example);

    int updateByPrimaryKeySelective(PropertyRightAppealPic record);

    int updateByPrimaryKey(PropertyRightAppealPic record);
}