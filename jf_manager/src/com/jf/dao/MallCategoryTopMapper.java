package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategoryTop;
import com.jf.entity.MallCategoryTopExample;
@Repository
public interface MallCategoryTopMapper extends BaseDao<MallCategoryTop, MallCategoryTopExample>{
    int countByExample(MallCategoryTopExample example);

    int deleteByExample(MallCategoryTopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCategoryTop record);

    int insertSelective(MallCategoryTop record);

    List<MallCategoryTop> selectByExample(MallCategoryTopExample example);

    MallCategoryTop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCategoryTop record, @Param("example") MallCategoryTopExample example);

    int updateByExample(@Param("record") MallCategoryTop record, @Param("example") MallCategoryTopExample example);

    int updateByPrimaryKeySelective(MallCategoryTop record);

    int updateByPrimaryKey(MallCategoryTop record);
}