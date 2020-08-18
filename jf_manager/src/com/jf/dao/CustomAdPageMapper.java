package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CustomAdPage;
import com.jf.entity.CustomAdPageExample;
@Repository
public interface CustomAdPageMapper extends BaseDao<CustomAdPage, CustomAdPageExample>{
    int countByExample(CustomAdPageExample example);

    int deleteByExample(CustomAdPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomAdPage record);

    int insertSelective(CustomAdPage record);

    List<CustomAdPage> selectByExample(CustomAdPageExample example);

    CustomAdPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomAdPage record, @Param("example") CustomAdPageExample example);

    int updateByExample(@Param("record") CustomAdPage record, @Param("example") CustomAdPageExample example);

    int updateByPrimaryKeySelective(CustomAdPage record);

    int updateByPrimaryKey(CustomAdPage record);
}