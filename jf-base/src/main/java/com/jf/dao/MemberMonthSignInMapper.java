package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMonthSignInMapper extends BaseDao<MemberMonthSignIn, MemberMonthSignInExample> {
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
