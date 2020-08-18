package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SmsExt;
import com.jf.entity.SmsExtExample;

@Repository
public interface SmsExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SmsExt findById(int id);

    SmsExt find(SmsExtExample example);

    List<SmsExt> list(SmsExtExample example);

    List<Integer> listId(SmsExtExample example);

    int count(SmsExtExample example);

    long sum(@Param("field") String field, @Param("example") SmsExtExample example);

    int max(@Param("field") String field, @Param("example") SmsExtExample example);

    int min(@Param("field") String field, @Param("example") SmsExtExample example);

}

