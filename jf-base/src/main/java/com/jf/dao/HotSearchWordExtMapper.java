package com.jf.dao;

import com.jf.entity.HotSearchWordExt;
import com.jf.entity.HotSearchWordExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotSearchWordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    HotSearchWordExt findById(int id);

    HotSearchWordExt find(HotSearchWordExtExample example);

    List<HotSearchWordExt> list(HotSearchWordExtExample example);

    List<Integer> listId(HotSearchWordExtExample example);

    int count(HotSearchWordExtExample example);

    long sum(@Param("field") String field, @Param("example") HotSearchWordExtExample example);

    int max(@Param("field") String field, @Param("example") HotSearchWordExtExample example);

    int min(@Param("field") String field, @Param("example") HotSearchWordExtExample example);

}

