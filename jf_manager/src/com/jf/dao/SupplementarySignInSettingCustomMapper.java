package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SupplementarySignInSettingCustom;
import com.jf.entity.SupplementarySignInSettingExample;
@Repository
public interface SupplementarySignInSettingCustomMapper{
    int countByExample(SupplementarySignInSettingExample example);
    List<SupplementarySignInSettingCustom> selectByExample(SupplementarySignInSettingExample example);
}