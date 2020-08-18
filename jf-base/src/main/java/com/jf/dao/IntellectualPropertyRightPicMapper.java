package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.IntellectualPropertyRightPic;
import com.jf.entity.IntellectualPropertyRightPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntellectualPropertyRightPicMapper extends BaseDao<IntellectualPropertyRightPic, IntellectualPropertyRightPicExample> {
    int countByExample(IntellectualPropertyRightPicExample example);

    int deleteByExample(IntellectualPropertyRightPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntellectualPropertyRightPic record);

    int insertSelective(IntellectualPropertyRightPic record);

    List<IntellectualPropertyRightPic> selectByExample(IntellectualPropertyRightPicExample example);

    IntellectualPropertyRightPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntellectualPropertyRightPic record, @Param("example") IntellectualPropertyRightPicExample example);

    int updateByExample(@Param("record") IntellectualPropertyRightPic record, @Param("example") IntellectualPropertyRightPicExample example);

    int updateByPrimaryKeySelective(IntellectualPropertyRightPic record);

    int updateByPrimaryKey(IntellectualPropertyRightPic record);
}
