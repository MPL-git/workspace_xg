package com.jf.dao;

import com.jf.entity.FullGiveExt;
import com.jf.entity.FullGiveExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullGiveExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    FullGiveExt findById(int id);

    FullGiveExt find(FullGiveExtExample example);

    List<FullGiveExt> list(FullGiveExtExample example);

    List<Integer> listId(FullGiveExtExample example);

    int count(FullGiveExtExample example);

    long sum(@Param("field") String field, @Param("example") FullGiveExtExample example);

    int max(@Param("field") String field, @Param("example") FullGiveExtExample example);

    int min(@Param("field") String field, @Param("example") FullGiveExtExample example);

}

