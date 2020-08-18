package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SignInManageCustom;
import com.jf.entity.SignInManageExample;
@Repository
public interface SignInManageCustomMapper{
    int countByExample(SignInManageExample example);
    List<SignInManageCustom> selectByExample(SignInManageExample example);
}