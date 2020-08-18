package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDepositOrderMapper extends BaseDao<SubDepositOrder, SubDepositOrderExample> {
    int countByExample(SubDepositOrderExample example);

    int deleteByExample(SubDepositOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubDepositOrder record);

    int insertSelective(SubDepositOrder record);

    List<SubDepositOrder> selectByExample(SubDepositOrderExample example);

    SubDepositOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubDepositOrder record, @Param("example") SubDepositOrderExample example);

    int updateByExample(@Param("record") SubDepositOrder record, @Param("example") SubDepositOrderExample example);

    int updateByPrimaryKeySelective(SubDepositOrder record);

    int updateByPrimaryKey(SubDepositOrder record);
}
