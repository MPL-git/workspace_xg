package com.jf.dao;

import com.jf.entity.WithdrawOrderExt;
import com.jf.entity.WithdrawOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WithdrawOrderExt findById(int id);

    WithdrawOrderExt find(WithdrawOrderExtExample example);

    List<WithdrawOrderExt> list(WithdrawOrderExtExample example);

    List<Integer> listId(WithdrawOrderExtExample example);

    int count(WithdrawOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") WithdrawOrderExtExample example);

    int max(@Param("field") String field, @Param("example") WithdrawOrderExtExample example);

    int min(@Param("field") String field, @Param("example") WithdrawOrderExtExample example);

}

