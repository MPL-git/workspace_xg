package com.jf.dao;

import com.jf.entity.WeixinAccessTokenExt;
import com.jf.entity.WeixinAccessTokenExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinAccessTokenExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinAccessTokenExt findById(int id);

    WeixinAccessTokenExt find(WeixinAccessTokenExtExample example);

    List<WeixinAccessTokenExt> list(WeixinAccessTokenExtExample example);

    List<Integer> listId(WeixinAccessTokenExtExample example);

    int count(WeixinAccessTokenExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinAccessTokenExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinAccessTokenExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinAccessTokenExtExample example);

}

