package com.jf.dao;

import com.jf.entity.CashTransferExt;
import com.jf.entity.CashTransferExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashTransferExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CashTransferExt findById(int id);

    CashTransferExt find(CashTransferExtExample example);

    List<CashTransferExt> list(CashTransferExtExample example);

    List<Integer> listId(CashTransferExtExample example);

    int count(CashTransferExtExample example);

    long sum(@Param("field") String field, @Param("example") CashTransferExtExample example);

    int max(@Param("field") String field, @Param("example") CashTransferExtExample example);

    int min(@Param("field") String field, @Param("example") CashTransferExtExample example);

}

