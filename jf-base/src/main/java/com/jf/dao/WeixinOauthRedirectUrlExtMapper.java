package com.jf.dao;

import com.jf.entity.WeixinOauthRedirectUrlExt;
import com.jf.entity.WeixinOauthRedirectUrlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinOauthRedirectUrlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinOauthRedirectUrlExt findById(int id);

    WeixinOauthRedirectUrlExt find(WeixinOauthRedirectUrlExtExample example);

    List<WeixinOauthRedirectUrlExt> list(WeixinOauthRedirectUrlExtExample example);

    List<Integer> listId(WeixinOauthRedirectUrlExtExample example);

    int count(WeixinOauthRedirectUrlExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinOauthRedirectUrlExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinOauthRedirectUrlExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinOauthRedirectUrlExtExample example);

}

