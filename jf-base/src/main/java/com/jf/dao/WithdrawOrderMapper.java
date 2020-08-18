package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawOrderMapper extends BaseDao<WithdrawOrder, WithdrawOrderExample> {
    int countByExample(WithdrawOrderExample example);

    int deleteByExample(WithdrawOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawOrder record);

    int insertSelective(WithdrawOrder record);

    List<WithdrawOrder> selectByExample(WithdrawOrderExample example);

    WithdrawOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawOrder record, @Param("example") WithdrawOrderExample example);

    int updateByExample(@Param("record") WithdrawOrder record, @Param("example") WithdrawOrderExample example);

    int updateByPrimaryKeySelective(WithdrawOrder record);

    int updateByPrimaryKey(WithdrawOrder record);
}
