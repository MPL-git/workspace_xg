package com.jf.dao;

import com.jf.entity.SubDepositOrderExt;
import com.jf.entity.SubDepositOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDepositOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SubDepositOrderExt findById(int id);

    SubDepositOrderExt find(SubDepositOrderExtExample example);

    List<SubDepositOrderExt> list(SubDepositOrderExtExample example);

    List<Integer> listId(SubDepositOrderExtExample example);

    int count(SubDepositOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") SubDepositOrderExtExample example);

    int max(@Param("field") String field, @Param("example") SubDepositOrderExtExample example);

    int min(@Param("field") String field, @Param("example") SubDepositOrderExtExample example);

}

