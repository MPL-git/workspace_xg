package com.jf.dao;

import com.jf.entity.WithdrawOrderStatusLogExt;
import com.jf.entity.WithdrawOrderStatusLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawOrderStatusLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WithdrawOrderStatusLogExt findById(int id);

    WithdrawOrderStatusLogExt find(WithdrawOrderStatusLogExtExample example);

    List<WithdrawOrderStatusLogExt> list(WithdrawOrderStatusLogExtExample example);

    List<Integer> listId(WithdrawOrderStatusLogExtExample example);

    int count(WithdrawOrderStatusLogExtExample example);

    long sum(@Param("field") String field, @Param("example") WithdrawOrderStatusLogExtExample example);

    int max(@Param("field") String field, @Param("example") WithdrawOrderStatusLogExtExample example);

    int min(@Param("field") String field, @Param("example") WithdrawOrderStatusLogExtExample example);

}

