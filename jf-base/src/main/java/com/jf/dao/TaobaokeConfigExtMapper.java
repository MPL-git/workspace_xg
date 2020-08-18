package com.jf.dao;

import com.jf.entity.TaobaokeConfigExt;
import com.jf.entity.TaobaokeConfigExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaobaokeConfigExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaobaokeConfigExt findById(int id);

    TaobaokeConfigExt find(TaobaokeConfigExtExample example);

    List<TaobaokeConfigExt> list(TaobaokeConfigExtExample example);

    List<Integer> listId(TaobaokeConfigExtExample example);

    int count(TaobaokeConfigExtExample example);

    long sum(@Param("field") String field, @Param("example") TaobaokeConfigExtExample example);

    int max(@Param("field") String field, @Param("example") TaobaokeConfigExtExample example);

    int min(@Param("field") String field, @Param("example") TaobaokeConfigExtExample example);

}

