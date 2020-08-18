package com.jf.dao;

import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtInfoChangeLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtInfoChangeLogMapper extends BaseDao<MchtInfoChangeLog, MchtInfoChangeLogExample>{
    int countByExample(MchtInfoChangeLogExample example);

    int deleteByExample(MchtInfoChangeLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtInfoChangeLog record);

    int insertSelective(MchtInfoChangeLog record);

    List<MchtInfoChangeLog> selectByExample(MchtInfoChangeLogExample example);

    MchtInfoChangeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtInfoChangeLog record, @Param("example") MchtInfoChangeLogExample example);

    int updateByExample(@Param("record") MchtInfoChangeLog record, @Param("example") MchtInfoChangeLogExample example);

    int updateByPrimaryKeySelective(MchtInfoChangeLog record);

    int updateByPrimaryKey(MchtInfoChangeLog record);
}