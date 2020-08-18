package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CustomPage;
import com.jf.entity.CustomPageExample;
@Repository
public interface CustomPageMapper extends BaseDao<CustomPage, CustomPageExample>{
    int countByExample(CustomPageExample example);

    int deleteByExample(CustomPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomPage record);

    int insertSelective(CustomPage record);

    List<CustomPage> selectByExample(CustomPageExample example);

    CustomPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomPage record, @Param("example") CustomPageExample example);

    int updateByExample(@Param("record") CustomPage record, @Param("example") CustomPageExample example);

    int updateByPrimaryKeySelective(CustomPage record);

    int updateByPrimaryKey(CustomPage record);
}