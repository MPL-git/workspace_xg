package com.jf.dao;

import com.jf.entity.MemberPv;
import com.jf.entity.MemberPvExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPvMapper extends BaseDao<MemberPv, MemberPvExample> {
    int countByExample(MemberPvExample example);

    int deleteByExample(MemberPvExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPv record);

    int insertSelective(MemberPv record);

    List<MemberPv> selectByExample(MemberPvExample example);

    MemberPv selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPv record, @Param("example") MemberPvExample example);

    int updateByExample(@Param("record") MemberPv record, @Param("example") MemberPvExample example);

    int updateByPrimaryKeySelective(MemberPv record);

    int updateByPrimaryKey(MemberPv record);
}