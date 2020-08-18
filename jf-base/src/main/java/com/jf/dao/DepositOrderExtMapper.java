package com.jf.dao;

import com.jf.entity.DepositOrderExt;
import com.jf.entity.DepositOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DepositOrderExt findById(int id);

    DepositOrderExt find(DepositOrderExtExample example);

    List<DepositOrderExt> list(DepositOrderExtExample example);

    List<Integer> listId(DepositOrderExtExample example);

    int count(DepositOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") DepositOrderExtExample example);

    int max(@Param("field") String field, @Param("example") DepositOrderExtExample example);

    int min(@Param("field") String field, @Param("example") DepositOrderExtExample example);

}

