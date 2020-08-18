package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformMsgEdit;
import com.jf.entity.PlatformMsgEditExample;
@Repository
public interface PlatformMsgEditMapper extends BaseDao<PlatformMsgEdit, PlatformMsgEditExample>{
    int countByExample(PlatformMsgEditExample example);

    int deleteByExample(PlatformMsgEditExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformMsgEdit record);

    int insertSelective(PlatformMsgEdit record);

    List<PlatformMsgEdit> selectByExample(PlatformMsgEditExample example);

    PlatformMsgEdit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformMsgEdit record, @Param("example") PlatformMsgEditExample example);

    int updateByExample(@Param("record") PlatformMsgEdit record, @Param("example") PlatformMsgEditExample example);

    int updateByPrimaryKeySelective(PlatformMsgEdit record);

    int updateByPrimaryKey(PlatformMsgEdit record);
}