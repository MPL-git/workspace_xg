package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SmsTemplateExt;
import com.jf.entity.SmsTemplateExtExample;

@Repository
public interface SmsTemplateExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SmsTemplateExt findById(int id);

    SmsTemplateExt find(SmsTemplateExtExample example);

    List<SmsTemplateExt> list(SmsTemplateExtExample example);

    List<Integer> listId(SmsTemplateExtExample example);

    int count(SmsTemplateExtExample example);

    long sum(@Param("field") String field, @Param("example") SmsTemplateExtExample example);

    int max(@Param("field") String field, @Param("example") SmsTemplateExtExample example);

    int min(@Param("field") String field, @Param("example") SmsTemplateExtExample example);

}

