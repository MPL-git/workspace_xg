package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberCumulativeSignInCustom;
import com.jf.entity.MemberCumulativeSignInExample;
@Repository
public interface MemberCumulativeSignInCustomMapper{
    int countByExample(MemberCumulativeSignInExample example);
    List<MemberCumulativeSignInCustom> selectByExample(MemberCumulativeSignInExample example);
}