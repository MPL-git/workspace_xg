package com.jf.dao;

import com.jf.entity.DecorateModuleExt;
import com.jf.entity.DecorateModuleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateModuleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DecorateModuleExt findById(int id);

    DecorateModuleExt find(DecorateModuleExtExample example);

    List<DecorateModuleExt> list(DecorateModuleExtExample example);

    List<Integer> listId(DecorateModuleExtExample example);

    int count(DecorateModuleExtExample example);

    long sum(@Param("field") String field, @Param("example") DecorateModuleExtExample example);

    int max(@Param("field") String field, @Param("example") DecorateModuleExtExample example);

    int min(@Param("field") String field, @Param("example") DecorateModuleExtExample example);

}

