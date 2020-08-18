package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;
@Repository
public interface MemberMonthSignInMapper extends BaseDao<MemberMonthSignIn, MemberMonthSignInExample>{
    int countByExample(MemberMonthSignInExample example);

    int deleteByExample(MemberMonthSignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberMonthSignIn record);

    int insertSelective(MemberMonthSignIn record);

    List<MemberMonthSignIn> selectByExample(MemberMonthSignInExample example);

    MemberMonthSignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberMonthSignIn record, @Param("example") MemberMonthSignInExample example);

    int updateByExample(@Param("record") MemberMonthSignIn record, @Param("example") MemberMonthSignInExample example);

    int updateByPrimaryKeySelective(MemberMonthSignIn record);

    int updateByPrimaryKey(MemberMonthSignIn record);
}