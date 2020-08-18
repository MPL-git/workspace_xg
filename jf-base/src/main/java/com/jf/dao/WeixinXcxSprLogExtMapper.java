package com.jf.dao;

import com.jf.entity.WeixinXcxSprLogExt;
import com.jf.entity.WeixinXcxSprLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinXcxSprLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinXcxSprLogExt findById(int id);

    WeixinXcxSprLogExt find(WeixinXcxSprLogExtExample example);

    List<WeixinXcxSprLogExt> list(WeixinXcxSprLogExtExample example);

    List<Integer> listId(WeixinXcxSprLogExtExample example);

    int count(WeixinXcxSprLogExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinXcxSprLogExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinXcxSprLogExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinXcxSprLogExtExample example);

}

