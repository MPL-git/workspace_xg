package com.jf.dao;

import com.jf.entity.MallCategoryItem;
import com.jf.entity.MallCategoryItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MallCategoryItemMapper extends BaseDao<MallCategoryItem, MallCategoryItemExample> {
    int countByExample(MallCategoryItemExample example);

    int deleteByExample(MallCategoryItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCategoryItem record);

    int insertSelective(MallCategoryItem record);

    List<MallCategoryItem> selectByExample(MallCategoryItemExample example);

    MallCategoryItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCategoryItem record, @Param("example") MallCategoryItemExample example);

    int updateByExample(@Param("record") MallCategoryItem record, @Param("example") MallCategoryItemExample example);

    int updateByPrimaryKeySelective(MallCategoryItem record);

    int updateByPrimaryKey(MallCategoryItem record);
}