package com.jf.dao;

import com.jf.entity.MchtViewlog;
import com.jf.entity.MchtViewlogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtViewlogMapper extends BaseDao<MchtViewlog, MchtViewlogExample>{
    int countByExample(MchtViewlogExample example);

    int deleteByExample(MchtViewlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtViewlog record);

    int insertSelective(MchtViewlog record);

    List<MchtViewlog> selectByExample(MchtViewlogExample example);

    MchtViewlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtViewlog record, @Param("example") MchtViewlogExample example);

    int updateByExample(@Param("record") MchtViewlog record, @Param("example") MchtViewlogExample example);

    int updateByPrimaryKeySelective(MchtViewlog record);

    int updateByPrimaryKey(MchtViewlog record);
}