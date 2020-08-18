package com.jf.dao;

import com.jf.entity.RefundRegisterDateLockExt;
import com.jf.entity.RefundRegisterDateLockExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRegisterDateLockExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    RefundRegisterDateLockExt findById(int id);

    RefundRegisterDateLockExt find(RefundRegisterDateLockExtExample example);

    List<RefundRegisterDateLockExt> list(RefundRegisterDateLockExtExample example);

    List<Integer> listId(RefundRegisterDateLockExtExample example);

    int count(RefundRegisterDateLockExtExample example);

    long sum(@Param("field") String field, @Param("example") RefundRegisterDateLockExtExample example);

    int max(@Param("field") String field, @Param("example") RefundRegisterDateLockExtExample example);

    int min(@Param("field") String field, @Param("example") RefundRegisterDateLockExtExample example);

}

