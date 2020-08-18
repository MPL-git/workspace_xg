package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtBankAccountHisExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBankAccountHisMapper extends BaseDao<MchtBankAccountHis, MchtBankAccountHisExample> {
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
