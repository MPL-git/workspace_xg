package com.jf.dao;

import com.jf.entity.DecorateAreaExt;
import com.jf.entity.DecorateAreaExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateAreaExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DecorateAreaExt findById(int id);

    DecorateAreaExt find(DecorateAreaExtExample example);

    List<DecorateAreaExt> list(DecorateAreaExtExample example);

    List<Integer> listId(DecorateAreaExtExample example);

    int count(DecorateAreaExtExample example);

    long sum(@Param("field") String field, @Param("example") DecorateAreaExtExample example);

    int max(@Param("field") String field, @Param("example") DecorateAreaExtExample example);

    int min(@Param("field") String field, @Param("example") DecorateAreaExtExample example);

}

