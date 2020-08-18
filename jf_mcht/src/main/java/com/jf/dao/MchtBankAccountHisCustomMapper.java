package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBankAccountHisCustom;
import com.jf.entity.MchtBankAccountHisExample;
@Repository
public interface MchtBankAccountHisCustomMapper {
    int countByExample(MchtBankAccountHisExample example);
    List<MchtBankAccountHisCustom> selectByExample(MchtBankAccountHisExample example);
    MchtBankAccountHisCustom selectByPrimaryKey(Integer id);
}