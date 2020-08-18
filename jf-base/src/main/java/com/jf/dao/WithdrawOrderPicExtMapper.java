package com.jf.dao;

import com.jf.entity.WithdrawOrderPicExt;
import com.jf.entity.WithdrawOrderPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawOrderPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WithdrawOrderPicExt findById(int id);

    WithdrawOrderPicExt find(WithdrawOrderPicExtExample example);

    List<WithdrawOrderPicExt> list(WithdrawOrderPicExtExample example);

    List<Integer> listId(WithdrawOrderPicExtExample example);

    int count(WithdrawOrderPicExtExample example);

    long sum(@Param("field") String field, @Param("example") WithdrawOrderPicExtExample example);

    int max(@Param("field") String field, @Param("example") WithdrawOrderPicExtExample example);

    int min(@Param("field") String field, @Param("example") WithdrawOrderPicExtExample example);

}

