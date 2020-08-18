package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtSettleOrderExample;
@Repository
public interface MchtSettleOrderMapper extends BaseDao<MchtSettleOrder, MchtSettleOrderExample>{
    int countByExample(MchtSettleOrderExample example);

    int deleteByExample(MchtSettleOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSettleOrder record);

    int insertSelective(MchtSettleOrder record);

    List<MchtSettleOrder> selectByExample(MchtSettleOrderExample example);

    MchtSettleOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSettleOrder record, @Param("example") MchtSettleOrderExample example);

    int updateByExample(@Param("record") MchtSettleOrder record, @Param("example") MchtSettleOrderExample example);

    int updateByPrimaryKeySelective(MchtSettleOrder record);

    int updateByPrimaryKey(MchtSettleOrder record);
}