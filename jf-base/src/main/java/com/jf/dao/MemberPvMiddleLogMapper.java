package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberPvMiddleLog;
import com.jf.entity.MemberPvMiddleLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPvMiddleLogMapper extends BaseDao<MemberPvMiddleLog, MemberPvMiddleLogExample> {
    int countByExample(MemberPvMiddleLogExample example);

    int deleteByExample(MemberPvMiddleLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPvMiddleLog record);

    int insertSelective(MemberPvMiddleLog record);

    List<MemberPvMiddleLog> selectByExample(MemberPvMiddleLogExample example);

    MemberPvMiddleLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPvMiddleLog record, @Param("example") MemberPvMiddleLogExample example);

    int updateByExample(@Param("record") MemberPvMiddleLog record, @Param("example") MemberPvMiddleLogExample example);

    int updateByPrimaryKeySelective(MemberPvMiddleLog record);

    int updateByPrimaryKey(MemberPvMiddleLog record);
}