package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBankAccount;
import com.jf.entity.MchtBankAccountExample;
@Repository
public interface MchtBankAccountMapper extends BaseDao<MchtBankAccount, MchtBankAccountExample>{
    int countByExample(MchtBankAccountExample example);

    int deleteByExample(MchtBankAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBankAccount record);

    int insertSelective(MchtBankAccount record);

    List<MchtBankAccount> selectByExample(MchtBankAccountExample example);

    MchtBankAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBankAccount record, @Param("example") MchtBankAccountExample example);

    int updateByExample(@Param("record") MchtBankAccount record, @Param("example") MchtBankAccountExample example);

    int updateByPrimaryKeySelective(MchtBankAccount record);

    int updateByPrimaryKey(MchtBankAccount record);
}