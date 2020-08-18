package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysPaymentExt;
import com.jf.entity.SysPaymentExtExample;

@Repository
public interface SysPaymentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysPaymentExt findById(int id);

    SysPaymentExt find(SysPaymentExtExample example);

    List<SysPaymentExt> list(SysPaymentExtExample example);

    List<Integer> listId(SysPaymentExtExample example);

    int count(SysPaymentExtExample example);

    long sum(@Param("field") String field, @Param("example") SysPaymentExtExample example);

    int max(@Param("field") String field, @Param("example") SysPaymentExtExample example);

    int min(@Param("field") String field, @Param("example") SysPaymentExtExample example);

}

