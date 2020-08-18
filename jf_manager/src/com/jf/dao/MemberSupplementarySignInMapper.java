package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInExample;
@Repository
public interface MemberSupplementarySignInMapper extends BaseDao<MemberSupplementarySignIn, MemberSupplementarySignInExample>{
    int countByExample(MemberSupplementarySignInExample example);

    int deleteByExample(MemberSupplementarySignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberSupplementarySignIn record);

    int insertSelective(MemberSupplementarySignIn record);

    List<MemberSupplementarySignIn> selectByExample(MemberSupplementarySignInExample example);

    MemberSupplementarySignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberSupplementarySignIn record, @Param("example") MemberSupplementarySignInExample example);

    int updateByExample(@Param("record") MemberSupplementarySignIn record, @Param("example") MemberSupplementarySignInExample example);

    int updateByPrimaryKeySelective(MemberSupplementarySignIn record);

    int updateByPrimaryKey(MemberSupplementarySignIn record);
}