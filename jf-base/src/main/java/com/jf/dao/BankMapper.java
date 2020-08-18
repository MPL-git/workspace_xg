package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Bank;
import com.jf.entity.BankExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankMapper extends BaseDao<Bank, BankExample> {
    int countByExample(BankExample example);

    int deleteByExample(BankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bank record);

    int insertSelective(Bank record);

    List<Bank> selectByExample(BankExample example);

    Bank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bank record, @Param("example") BankExample example);

    int updateByExample(@Param("record") Bank record, @Param("example") BankExample example);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
}
