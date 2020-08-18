package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DepositOrder;
import com.jf.entity.DepositOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositOrderMapper extends BaseDao<DepositOrder, DepositOrderExample> {
    int countByExample(DepositOrderExample example);

    int deleteByExample(DepositOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepositOrder record);

    int insertSelective(DepositOrder record);

    List<DepositOrder> selectByExample(DepositOrderExample example);

    DepositOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepositOrder record, @Param("example") DepositOrderExample example);

    int updateByExample(@Param("record") DepositOrder record, @Param("example") DepositOrderExample example);

    int updateByPrimaryKeySelective(DepositOrder record);

    int updateByPrimaryKey(DepositOrder record);
}
