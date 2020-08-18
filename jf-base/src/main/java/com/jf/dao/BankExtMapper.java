package com.jf.dao;

import com.jf.entity.BankExt;
import com.jf.entity.BankExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BankExt findById(int id);

    BankExt find(BankExtExample example);

    List<BankExt> list(BankExtExample example);

    List<Integer> listId(BankExtExample example);

    int count(BankExtExample example);

    long sum(@Param("field") String field, @Param("example") BankExtExample example);

    int max(@Param("field") String field, @Param("example") BankExtExample example);

    int min(@Param("field") String field, @Param("example") BankExtExample example);

}

