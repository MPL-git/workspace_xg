package com.jf.dao;

import com.jf.entity.MemberPvMiddle;
import com.jf.entity.MemberPvMiddleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPvMiddleMapper extends BaseDao<MemberPvMiddle, MemberPvMiddleExample> {
    int countByExample(MemberPvMiddleExample example);

    int deleteByExample(MemberPvMiddleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPvMiddle record);

    int insertSelective(MemberPvMiddle record);

    List<MemberPvMiddle> selectByExampleWithBLOBs(MemberPvMiddleExample example);

    List<MemberPvMiddle> selectByExample(MemberPvMiddleExample example);

    MemberPvMiddle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPvMiddle record, @Param("example") MemberPvMiddleExample example);

    int updateByExampleWithBLOBs(@Param("record") MemberPvMiddle record, @Param("example") MemberPvMiddleExample example);

    int updateByExample(@Param("record") MemberPvMiddle record, @Param("example") MemberPvMiddleExample example);

    int updateByPrimaryKeySelective(MemberPvMiddle record);

    int updateByPrimaryKeyWithBLOBs(MemberPvMiddle record);

    int updateByPrimaryKey(MemberPvMiddle record);
}