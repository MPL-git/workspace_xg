package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.FullGive;
import com.jf.entity.FullGiveExample;
@Repository
public interface FullGiveMapper extends BaseDao<FullGive, FullGiveExample>{
    int countByExample(FullGiveExample example);

    int deleteByExample(FullGiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FullGive record);

    int insertSelective(FullGive record);

    List<FullGive> selectByExample(FullGiveExample example);

    FullGive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FullGive record, @Param("example") FullGiveExample example);

    int updateByExample(@Param("record") FullGive record, @Param("example") FullGiveExample example);

    int updateByPrimaryKeySelective(FullGive record);

    int updateByPrimaryKey(FullGive record);
}