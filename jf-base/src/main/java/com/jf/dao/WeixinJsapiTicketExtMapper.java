package com.jf.dao;

import com.jf.entity.WeixinJsapiTicketExt;
import com.jf.entity.WeixinJsapiTicketExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinJsapiTicketExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinJsapiTicketExt findById(int id);

    WeixinJsapiTicketExt find(WeixinJsapiTicketExtExample example);

    List<WeixinJsapiTicketExt> list(WeixinJsapiTicketExtExample example);

    List<Integer> listId(WeixinJsapiTicketExtExample example);

    int count(WeixinJsapiTicketExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinJsapiTicketExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinJsapiTicketExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinJsapiTicketExtExample example);

}

