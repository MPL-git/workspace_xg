package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberSignInDtl;
import com.jf.entity.MemberSignInDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSignInDtlMapper extends BaseDao<MemberSignInDtl, MemberSignInDtlExample> {
    int countByExample(MemberSignInDtlExample example);

    int deleteByExample(MemberSignInDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberSignInDtl record);

    int insertSelective(MemberSignInDtl record);

    List<MemberSignInDtl> selectByExample(MemberSignInDtlExample example);

    MemberSignInDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberSignInDtl record, @Param("example") MemberSignInDtlExample example);

    int updateByExample(@Param("record") MemberSignInDtl record, @Param("example") MemberSignInDtlExample example);

    int updateByPrimaryKeySelective(MemberSignInDtl record);

    int updateByPrimaryKey(MemberSignInDtl record);
}
