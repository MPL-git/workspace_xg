package com.jf.dao;

import com.jf.entity.DepositOrderStatusLogExt;
import com.jf.entity.DepositOrderStatusLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositOrderStatusLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DepositOrderStatusLogExt findById(int id);

    DepositOrderStatusLogExt find(DepositOrderStatusLogExtExample example);

    List<DepositOrderStatusLogExt> list(DepositOrderStatusLogExtExample example);

    List<Integer> listId(DepositOrderStatusLogExtExample example);

    int count(DepositOrderStatusLogExtExample example);

    long sum(@Param("field") String field, @Param("example") DepositOrderStatusLogExtExample example);

    int max(@Param("field") String field, @Param("example") DepositOrderStatusLogExtExample example);

    int min(@Param("field") String field, @Param("example") DepositOrderStatusLogExtExample example);

}

