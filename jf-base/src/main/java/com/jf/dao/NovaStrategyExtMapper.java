package com.jf.dao;

import com.jf.entity.NovaStrategyExt;
import com.jf.entity.NovaStrategyExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovaStrategyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    NovaStrategyExt findById(int id);

    NovaStrategyExt find(NovaStrategyExtExample example);

    List<NovaStrategyExt> list(NovaStrategyExtExample example);

    List<Integer> listId(NovaStrategyExtExample example);

    int count(NovaStrategyExtExample example);

    long sum(@Param("field") String field, @Param("example") NovaStrategyExtExample example);

    int max(@Param("field") String field, @Param("example") NovaStrategyExtExample example);

    int min(@Param("field") String field, @Param("example") NovaStrategyExtExample example);

}

