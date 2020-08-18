package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PropertyRightProsecutionPic;
import com.jf.entity.PropertyRightProsecutionPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightProsecutionPicMapper extends BaseDao<PropertyRightProsecutionPic, PropertyRightProsecutionPicExample> {
    int countByExample(PropertyRightProsecutionPicExample example);

    int deleteByExample(PropertyRightProsecutionPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyRightProsecutionPic record);

    int insertSelective(PropertyRightProsecutionPic record);

    List<PropertyRightProsecutionPic> selectByExample(PropertyRightProsecutionPicExample example);

    PropertyRightProsecutionPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyRightProsecutionPic record, @Param("example") PropertyRightProsecutionPicExample example);

    int updateByExample(@Param("record") PropertyRightProsecutionPic record, @Param("example") PropertyRightProsecutionPicExample example);

    int updateByPrimaryKeySelective(PropertyRightProsecutionPic record);

    int updateByPrimaryKey(PropertyRightProsecutionPic record);
}
