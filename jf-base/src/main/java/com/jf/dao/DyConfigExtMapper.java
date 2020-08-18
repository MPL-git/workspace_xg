package com.jf.dao;

import com.jf.entity.DyConfigExt;
import com.jf.entity.DyConfigExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DyConfigExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DyConfigExt findById(int id);

    DyConfigExt find(DyConfigExtExample example);

    List<DyConfigExt> list(DyConfigExtExample example);

    List<Integer> listId(DyConfigExtExample example);

    int count(DyConfigExtExample example);

    long sum(@Param("field") String field, @Param("example") DyConfigExtExample example);

    int max(@Param("field") String field, @Param("example") DyConfigExtExample example);

    int min(@Param("field") String field, @Param("example") DyConfigExtExample example);

}

