package com.jf.dao;

import com.jf.entity.ReceiptRegisterDateLockExt;
import com.jf.entity.ReceiptRegisterDateLockExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRegisterDateLockExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ReceiptRegisterDateLockExt findById(int id);

    ReceiptRegisterDateLockExt find(ReceiptRegisterDateLockExtExample example);

    List<ReceiptRegisterDateLockExt> list(ReceiptRegisterDateLockExtExample example);

    List<Integer> listId(ReceiptRegisterDateLockExtExample example);

    int count(ReceiptRegisterDateLockExtExample example);

    long sum(@Param("field") String field, @Param("example") ReceiptRegisterDateLockExtExample example);

    int max(@Param("field") String field, @Param("example") ReceiptRegisterDateLockExtExample example);

    int min(@Param("field") String field, @Param("example") ReceiptRegisterDateLockExtExample example);

}

