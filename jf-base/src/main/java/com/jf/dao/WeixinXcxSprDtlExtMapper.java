package com.jf.dao;

import com.jf.entity.WeixinXcxSprDtlExt;
import com.jf.entity.WeixinXcxSprDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinXcxSprDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinXcxSprDtlExt findById(int id);

    WeixinXcxSprDtlExt find(WeixinXcxSprDtlExtExample example);

    List<WeixinXcxSprDtlExt> list(WeixinXcxSprDtlExtExample example);

    List<Integer> listId(WeixinXcxSprDtlExtExample example);

    int count(WeixinXcxSprDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinXcxSprDtlExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinXcxSprDtlExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinXcxSprDtlExtExample example);

}

