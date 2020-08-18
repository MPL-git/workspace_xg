package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSupplementarySignInCustom;
import com.jf.entity.MemberSupplementarySignInExample;
@Repository
public interface MemberSupplementarySignInCustomMapper{
    int countByExample(MemberSupplementarySignInExample example);
    List<MemberSupplementarySignInCustom> selectByExample(MemberSupplementarySignInExample example);
}