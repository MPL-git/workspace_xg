package com.jf.dao;

import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAccountDtlMapper extends BaseDao<MemberAccountDtl, MemberAccountDtlExample> {
    int countByExample(MemberAccountDtlExample example);

    int deleteByExample(MemberAccountDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAccountDtl record);

    int insertSelective(MemberAccountDtl record);

    List<MemberAccountDtl> selectByExample(MemberAccountDtlExample example);

    MemberAccountDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAccountDtl record, @Param("example") MemberAccountDtlExample example);

    int updateByExample(@Param("record") MemberAccountDtl record, @Param("example") MemberAccountDtlExample example);

    int updateByPrimaryKeySelective(MemberAccountDtl record);

    int updateByPrimaryKey(MemberAccountDtl record);
}