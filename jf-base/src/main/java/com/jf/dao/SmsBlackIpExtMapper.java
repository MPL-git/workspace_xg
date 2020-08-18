package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SmsBlackIpExt;
import com.jf.entity.SmsBlackIpExtExample;

@Repository
public interface SmsBlackIpExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SmsBlackIpExt findById(int id);

    SmsBlackIpExt find(SmsBlackIpExtExample example);

    List<SmsBlackIpExt> list(SmsBlackIpExtExample example);

    List<Integer> listId(SmsBlackIpExtExample example);

    int count(SmsBlackIpExtExample example);

    long sum(@Param("field") String field, @Param("example") SmsBlackIpExtExample example);

    int max(@Param("field") String field, @Param("example") SmsBlackIpExtExample example);

    int min(@Param("field") String field, @Param("example") SmsBlackIpExtExample example);

}

