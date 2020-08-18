package com.jf.dao;

import com.jf.entity.PaymentPlatformReceivableDtlExt;
import com.jf.entity.PaymentPlatformReceivableDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentPlatformReceivableDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PaymentPlatformReceivableDtlExt findById(int id);

    PaymentPlatformReceivableDtlExt find(PaymentPlatformReceivableDtlExtExample example);

    List<PaymentPlatformReceivableDtlExt> list(PaymentPlatformReceivableDtlExtExample example);

    List<Integer> listId(PaymentPlatformReceivableDtlExtExample example);

    int count(PaymentPlatformReceivableDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") PaymentPlatformReceivableDtlExtExample example);

    int max(@Param("field") String field, @Param("example") PaymentPlatformReceivableDtlExtExample example);

    int min(@Param("field") String field, @Param("example") PaymentPlatformReceivableDtlExtExample example);

}

