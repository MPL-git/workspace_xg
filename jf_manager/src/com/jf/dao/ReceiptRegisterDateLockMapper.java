package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ReceiptRegisterDateLock;
import com.jf.entity.ReceiptRegisterDateLockExample;
@Repository
public interface ReceiptRegisterDateLockMapper extends BaseDao<ReceiptRegisterDateLock, ReceiptRegisterDateLockExample>{
    int countByExample(ReceiptRegisterDateLockExample example);

    int deleteByExample(ReceiptRegisterDateLockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReceiptRegisterDateLock record);

    int insertSelective(ReceiptRegisterDateLock record);

    List<ReceiptRegisterDateLock> selectByExample(ReceiptRegisterDateLockExample example);

    ReceiptRegisterDateLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReceiptRegisterDateLock record, @Param("example") ReceiptRegisterDateLockExample example);

    int updateByExample(@Param("record") ReceiptRegisterDateLock record, @Param("example") ReceiptRegisterDateLockExample example);

    int updateByPrimaryKeySelective(ReceiptRegisterDateLock record);

    int updateByPrimaryKey(ReceiptRegisterDateLock record);
}