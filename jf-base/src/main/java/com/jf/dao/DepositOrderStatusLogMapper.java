package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DepositOrderStatusLog;
import com.jf.entity.DepositOrderStatusLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositOrderStatusLogMapper extends BaseDao<DepositOrderStatusLog, DepositOrderStatusLogExample> {
    int countByExample(DepositOrderStatusLogExample example);

    int deleteByExample(DepositOrderStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepositOrderStatusLog record);

    int insertSelective(DepositOrderStatusLog record);

    List<DepositOrderStatusLog> selectByExample(DepositOrderStatusLogExample example);

    DepositOrderStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepositOrderStatusLog record, @Param("example") DepositOrderStatusLogExample example);

    int updateByExample(@Param("record") DepositOrderStatusLog record, @Param("example") DepositOrderStatusLogExample example);

    int updateByPrimaryKeySelective(DepositOrderStatusLog record);

    int updateByPrimaryKey(DepositOrderStatusLog record);
}
