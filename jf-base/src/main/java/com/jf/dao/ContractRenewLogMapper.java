package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ContractRenewLog;
import com.jf.entity.ContractRenewLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRenewLogMapper extends BaseDao<ContractRenewLog, ContractRenewLogExample> {
    int countByExample(ContractRenewLogExample example);

    int deleteByExample(ContractRenewLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContractRenewLog record);

    int insertSelective(ContractRenewLog record);

    List<ContractRenewLog> selectByExample(ContractRenewLogExample example);

    ContractRenewLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContractRenewLog record, @Param("example") ContractRenewLogExample example);

    int updateByExample(@Param("record") ContractRenewLog record, @Param("example") ContractRenewLogExample example);

    int updateByPrimaryKeySelective(ContractRenewLog record);

    int updateByPrimaryKey(ContractRenewLog record);
}
