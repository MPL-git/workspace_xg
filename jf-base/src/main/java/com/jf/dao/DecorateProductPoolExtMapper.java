package com.jf.dao;

import com.jf.entity.DecorateProductPoolExt;
import com.jf.entity.DecorateProductPoolExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateProductPoolExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DecorateProductPoolExt findById(int id);

    DecorateProductPoolExt find(DecorateProductPoolExtExample example);

    List<DecorateProductPoolExt> list(DecorateProductPoolExtExample example);

    List<Integer> listId(DecorateProductPoolExtExample example);

    int count(DecorateProductPoolExtExample example);

    long sum(@Param("field") String field, @Param("example") DecorateProductPoolExtExample example);

    int max(@Param("field") String field, @Param("example") DecorateProductPoolExtExample example);

    int min(@Param("field") String field, @Param("example") DecorateProductPoolExtExample example);

}

