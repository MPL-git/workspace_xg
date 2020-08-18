package com.jf.dao;

import com.jf.entity.CombineDepositOrderExt;
import com.jf.entity.CombineDepositOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombineDepositOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CombineDepositOrderExt findById(int id);

    CombineDepositOrderExt find(CombineDepositOrderExtExample example);

    List<CombineDepositOrderExt> list(CombineDepositOrderExtExample example);

    List<Integer> listId(CombineDepositOrderExtExample example);

    int count(CombineDepositOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") CombineDepositOrderExtExample example);

    int max(@Param("field") String field, @Param("example") CombineDepositOrderExtExample example);

    int min(@Param("field") String field, @Param("example") CombineDepositOrderExtExample example);

}

