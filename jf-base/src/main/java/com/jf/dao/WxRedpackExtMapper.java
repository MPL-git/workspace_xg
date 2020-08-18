package com.jf.dao;

import com.jf.entity.WxRedpackExt;
import com.jf.entity.WxRedpackExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxRedpackExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WxRedpackExt findById(int id);

    WxRedpackExt find(WxRedpackExtExample example);

    List<WxRedpackExt> list(WxRedpackExtExample example);

    List<Integer> listId(WxRedpackExtExample example);

    int count(WxRedpackExtExample example);

    long sum(@Param("field") String field, @Param("example") WxRedpackExtExample example);

    int max(@Param("field") String field, @Param("example") WxRedpackExtExample example);

    int min(@Param("field") String field, @Param("example") WxRedpackExtExample example);

}

