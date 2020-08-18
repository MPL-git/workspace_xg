package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PaymentPlatformReceivableDtl;
import com.jf.entity.PaymentPlatformReceivableDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentPlatformReceivableDtlMapper extends BaseDao<PaymentPlatformReceivableDtl, PaymentPlatformReceivableDtlExample> {
    int countByExample(PaymentPlatformReceivableDtlExample example);

    int deleteByExample(PaymentPlatformReceivableDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PaymentPlatformReceivableDtl record);

    int insertSelective(PaymentPlatformReceivableDtl record);

    List<PaymentPlatformReceivableDtl> selectByExample(PaymentPlatformReceivableDtlExample example);

    PaymentPlatformReceivableDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PaymentPlatformReceivableDtl record, @Param("example") PaymentPlatformReceivableDtlExample example);

    int updateByExample(@Param("record") PaymentPlatformReceivableDtl record, @Param("example") PaymentPlatformReceivableDtlExample example);

    int updateByPrimaryKeySelective(PaymentPlatformReceivableDtl record);

    int updateByPrimaryKey(PaymentPlatformReceivableDtl record);
}
