package com.jf.dao;

import com.jf.entity.WeixinConfigExt;
import com.jf.entity.WeixinConfigExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinConfigExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinConfigExt findById(int id);

    WeixinConfigExt find(WeixinConfigExtExample example);

    List<WeixinConfigExt> list(WeixinConfigExtExample example);

    List<Integer> listId(WeixinConfigExtExample example);

    int count(WeixinConfigExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinConfigExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinConfigExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinConfigExtExample example);

}

