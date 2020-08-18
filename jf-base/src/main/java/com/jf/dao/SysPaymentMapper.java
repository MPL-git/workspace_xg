package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysPayment;
import com.jf.entity.SysPaymentExample;

@Repository
public interface SysPaymentMapper extends BaseDao<SysPayment, SysPaymentExample> {
    int countByExample(SysPaymentExample example);

    int deleteByExample(SysPaymentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysPayment record);

    int insertSelective(SysPayment record);

    List<SysPayment> selectByExample(SysPaymentExample example);

    SysPayment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysPayment record, @Param("example") SysPaymentExample example);

    int updateByExample(@Param("record") SysPayment record, @Param("example") SysPaymentExample example);

    int updateByPrimaryKeySelective(SysPayment record);

    int updateByPrimaryKey(SysPayment record);
}
