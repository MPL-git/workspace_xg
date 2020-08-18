package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WithdrawOrderStatusLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawOrderStatusLogMapper extends BaseDao<WithdrawOrderStatusLog, WithdrawOrderStatusLogExample> {
    int countByExample(WithdrawOrderStatusLogExample example);

    int deleteByExample(WithdrawOrderStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawOrderStatusLog record);

    int insertSelective(WithdrawOrderStatusLog record);

    List<WithdrawOrderStatusLog> selectByExample(WithdrawOrderStatusLogExample example);

    WithdrawOrderStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawOrderStatusLog record, @Param("example") WithdrawOrderStatusLogExample example);

    int updateByExample(@Param("record") WithdrawOrderStatusLog record, @Param("example") WithdrawOrderStatusLogExample example);

    int updateByPrimaryKeySelective(WithdrawOrderStatusLog record);

    int updateByPrimaryKey(WithdrawOrderStatusLog record);
}
