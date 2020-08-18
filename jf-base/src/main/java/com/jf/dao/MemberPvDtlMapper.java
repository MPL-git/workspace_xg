package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberPvDtl;
import com.jf.entity.MemberPvDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPvDtlMapper extends BaseDao<MemberPvDtl, MemberPvDtlExample> {
    int countByExample(MemberPvDtlExample example);

    int deleteByExample(MemberPvDtlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberPvDtl record);

    int insertSelective(MemberPvDtl record);

    List<MemberPvDtl> selectByExample(MemberPvDtlExample example);

    MemberPvDtl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberPvDtl record, @Param("example") MemberPvDtlExample example);

    int updateByExample(@Param("record") MemberPvDtl record, @Param("example") MemberPvDtlExample example);

    int updateByPrimaryKeySelective(MemberPvDtl record);

    int updateByPrimaryKey(MemberPvDtl record);
}