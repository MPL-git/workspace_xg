package com.jf.dao;

import com.jf.entity.FullCutExt;
import com.jf.entity.FullCutExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullCutExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    FullCutExt findById(int id);

    FullCutExt find(FullCutExtExample example);

    List<FullCutExt> list(FullCutExtExample example);

    List<Integer> listId(FullCutExtExample example);

    int count(FullCutExtExample example);

    long sum(@Param("field") String field, @Param("example") FullCutExtExample example);

    int max(@Param("field") String field, @Param("example") FullCutExtExample example);

    int min(@Param("field") String field, @Param("example") FullCutExtExample example);

}

