package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightAppealPicMapper extends BaseDao<PropertyRightAppealPic, PropertyRightAppealPicExample> {
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
