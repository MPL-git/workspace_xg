package com.jf.dao;

import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtBankAccountHisExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBankAccountHisMapper extends BaseDao<MchtBankAccountHis, MchtBankAccountHisExample>{
    int countByExample(MchtBankAccountHisExample example);

    int deleteByExample(MchtBankAccountHisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBankAccountHis record);

    int insertSelective(MchtBankAccountHis record);

    List<MchtBankAccountHis> selectByExample(MchtBankAccountHisExample example);

    MchtBankAccountHis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBankAccountHis record, @Param("example") MchtBankAccountHisExample example);

    int updateByExample(@Param("record") MchtBankAccountHis record, @Param("example") MchtBankAccountHisExample example);

    int updateByPrimaryKeySelective(MchtBankAccountHis record);

    int updateByPrimaryKey(MchtBankAccountHis record);
}