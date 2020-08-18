package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SmsBlackMobileExt;
import com.jf.entity.SmsBlackMobileExtExample;

@Repository
public interface SmsBlackMobileExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SmsBlackMobileExt findById(int id);

    SmsBlackMobileExt find(SmsBlackMobileExtExample example);

    List<SmsBlackMobileExt> list(SmsBlackMobileExtExample example);

    List<Integer> listId(SmsBlackMobileExtExample example);

    int count(SmsBlackMobileExtExample example);

    long sum(@Param("field") String field, @Param("example") SmsBlackMobileExtExample example);

    int max(@Param("field") String field, @Param("example") SmsBlackMobileExtExample example);

    int min(@Param("field") String field, @Param("example") SmsBlackMobileExtExample example);

}

