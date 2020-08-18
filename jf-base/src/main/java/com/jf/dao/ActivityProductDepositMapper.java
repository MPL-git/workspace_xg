package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityProductDeposit;
import com.jf.entity.ActivityProductDepositExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityProductDepositMapper extends BaseDao<ActivityProductDeposit, ActivityProductDepositExample> {
    int countByExample(ActivityProductDepositExample example);

    int deleteByExample(ActivityProductDepositExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityProductDeposit record);

    int insertSelective(ActivityProductDeposit record);

    List<ActivityProductDeposit> selectByExample(ActivityProductDepositExample example);

    ActivityProductDeposit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityProductDeposit record, @Param("example") ActivityProductDepositExample example);

    int updateByExample(@Param("record") ActivityProductDeposit record, @Param("example") ActivityProductDepositExample example);

    int updateByPrimaryKeySelective(ActivityProductDeposit record);

    int updateByPrimaryKey(ActivityProductDeposit record);
}
