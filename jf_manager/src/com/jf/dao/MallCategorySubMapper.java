package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategorySub;
import com.jf.entity.MallCategorySubExample;
@Repository
public interface MallCategorySubMapper extends BaseDao<MallCategorySub, MallCategorySubExample>{
    int countByExample(MallCategorySubExample example);

    int deleteByExample(MallCategorySubExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCategorySub record);

    int insertSelective(MallCategorySub record);

    List<MallCategorySub> selectByExample(MallCategorySubExample example);

    MallCategorySub selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCategorySub record, @Param("example") MallCategorySubExample example);

    int updateByExample(@Param("record") MallCategorySub record, @Param("example") MallCategorySubExample example);

    int updateByPrimaryKeySelective(MallCategorySub record);

    int updateByPrimaryKey(MallCategorySub record);
}