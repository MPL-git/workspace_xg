package com.jf.dao;

import com.jf.entity.WeixinXcxSprPlanExt;
import com.jf.entity.WeixinXcxSprPlanExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinXcxSprPlanExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinXcxSprPlanExt findById(int id);

    WeixinXcxSprPlanExt find(WeixinXcxSprPlanExtExample example);

    List<WeixinXcxSprPlanExt> list(WeixinXcxSprPlanExtExample example);

    List<Integer> listId(WeixinXcxSprPlanExtExample example);

    int count(WeixinXcxSprPlanExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinXcxSprPlanExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinXcxSprPlanExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinXcxSprPlanExtExample example);

}

