package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBankAccountCustom;
import com.jf.entity.MchtBankAccountCustomExample;
@Repository
public interface MchtBankAccountCustomMapper{
    int countByExample(MchtBankAccountCustomExample example);


    List<MchtBankAccountCustom> selectByExample(MchtBankAccountCustomExample example);

    MchtBankAccountCustom selectByPrimaryKey(Integer id);
}