package com.jf.dao;

import com.jf.entity.WeixinXcxSprChnlExt;
import com.jf.entity.WeixinXcxSprChnlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinXcxSprChnlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinXcxSprChnlExt findById(int id);

    WeixinXcxSprChnlExt find(WeixinXcxSprChnlExtExample example);

    List<WeixinXcxSprChnlExt> list(WeixinXcxSprChnlExtExample example);

    List<Integer> listId(WeixinXcxSprChnlExtExample example);

    int count(WeixinXcxSprChnlExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinXcxSprChnlExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinXcxSprChnlExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinXcxSprChnlExtExample example);

}

