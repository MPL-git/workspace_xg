package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberCumulativeSignIn;
import com.jf.entity.MemberCumulativeSignInExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCumulativeSignInMapper extends BaseDao<MemberCumulativeSignIn, MemberCumulativeSignInExample> {
    int countByExample(MemberCumulativeSignInExample example);

    int deleteByExample(MemberCumulativeSignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCumulativeSignIn record);

    int insertSelective(MemberCumulativeSignIn record);

    List<MemberCumulativeSignIn> selectByExample(MemberCumulativeSignInExample example);

    MemberCumulativeSignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCumulativeSignIn record, @Param("example") MemberCumulativeSignInExample example);

    int updateByExample(@Param("record") MemberCumulativeSignIn record, @Param("example") MemberCumulativeSignInExample example);

    int updateByPrimaryKeySelective(MemberCumulativeSignIn record);

    int updateByPrimaryKey(MemberCumulativeSignIn record);
}
