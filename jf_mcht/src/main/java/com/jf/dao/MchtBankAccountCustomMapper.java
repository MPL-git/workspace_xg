package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBankAccountCustom;
import com.jf.entity.MchtBankAccountExample;
@Repository
public interface MchtBankAccountCustomMapper{
    int countByExample(MchtBankAccountExample example);


    List<MchtBankAccountCustom> selectByExample(MchtBankAccountExample example);

    MchtBankAccountCustom selectByPrimaryKey(Integer id);
}