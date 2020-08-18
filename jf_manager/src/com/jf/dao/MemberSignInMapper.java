package com.jf.dao;

import com.jf.entity.MemberSignIn;
import com.jf.entity.MemberSignInExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSignInMapper extends BaseDao<MemberSignIn, MemberSignInExample> {
    int countByExample(MemberSignInExample example);

    int deleteByExample(MemberSignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberSignIn record);

    int insertSelective(MemberSignIn record);

    List<MemberSignIn> selectByExample(MemberSignInExample example);

    MemberSignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberSignIn record, @Param("example") MemberSignInExample example);

    int updateByExample(@Param("record") MemberSignIn record, @Param("example") MemberSignInExample example);

    int updateByPrimaryKeySelective(MemberSignIn record);

    int updateByPrimaryKey(MemberSignIn record);
}