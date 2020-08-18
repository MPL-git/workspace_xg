package com.jf.dao;

import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ImpeachHandleLogMapper extends BaseDao<ImpeachHandleLog, ImpeachHandleLogExample>{
    int countByExample(ImpeachHandleLogExample example);

    int deleteByExample(ImpeachHandleLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImpeachHandleLog record);

    int insertSelective(ImpeachHandleLog record);

    List<ImpeachHandleLog> selectByExample(ImpeachHandleLogExample example);

    ImpeachHandleLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImpeachHandleLog record, @Param("example") ImpeachHandleLogExample example);

    int updateByExample(@Param("record") ImpeachHandleLog record, @Param("example") ImpeachHandleLogExample example);

    int updateByPrimaryKeySelective(ImpeachHandleLog record);

    int updateByPrimaryKey(ImpeachHandleLog record);
}