package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtDepositMapper extends BaseDao<MchtDeposit, MchtDepositExample> {
    int countByExample(MchtDepositExample example);

    int deleteByExample(MchtDepositExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtDeposit record);

    int insertSelective(MchtDeposit record);

    List<MchtDeposit> selectByExample(MchtDepositExample example);

    MchtDeposit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtDeposit record, @Param("example") MchtDepositExample example);

    int updateByExample(@Param("record") MchtDeposit record, @Param("example") MchtDepositExample example);

    int updateByPrimaryKeySelective(MchtDeposit record);

    int updateByPrimaryKey(MchtDeposit record);
}
