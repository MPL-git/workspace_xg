package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineDepositOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombineDepositOrderMapper extends BaseDao<CombineDepositOrder, CombineDepositOrderExample> {
    int countByExample(CombineDepositOrderExample example);

    int deleteByExample(CombineDepositOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CombineDepositOrder record);

    int insertSelective(CombineDepositOrder record);

    List<CombineDepositOrder> selectByExample(CombineDepositOrderExample example);

    CombineDepositOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CombineDepositOrder record, @Param("example") CombineDepositOrderExample example);

    int updateByExample(@Param("record") CombineDepositOrder record, @Param("example") CombineDepositOrderExample example);

    int updateByPrimaryKeySelective(CombineDepositOrder record);

    int updateByPrimaryKey(CombineDepositOrder record);
}
