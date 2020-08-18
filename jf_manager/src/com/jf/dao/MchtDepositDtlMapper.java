package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositDtlExample;
@Repository
public interface MchtDepositDtlMapper extends BaseDao<MchtDepositDtl, MchtDepositDtlExample>{
    int countByExample(MchtDepositDtlExample example);

    int deleteByExample(MchtDepositDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtDepositDtl record);

    int insertSelective(MchtDepositDtl record);

    List<MchtDepositDtl> selectByExample(MchtDepositDtlExample example);

    MchtDepositDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtDepositDtl record, @Param("example") MchtDepositDtlExample example);

    int updateByExample(@Param("record") MchtDepositDtl record, @Param("example") MchtDepositDtlExample example);

    int updateByPrimaryKeySelective(MchtDepositDtl record);

    int updateByPrimaryKey(MchtDepositDtl record);
}