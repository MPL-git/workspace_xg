package com.jf.dao;

import com.jf.entity.SalesmanExt;
import com.jf.entity.SalesmanExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesmanExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SalesmanExt findById(int id);

    SalesmanExt find(SalesmanExtExample example);

    List<SalesmanExt> list(SalesmanExtExample example);

    List<Integer> listId(SalesmanExtExample example);

    int count(SalesmanExtExample example);

    long sum(@Param("field") String field, @Param("example") SalesmanExtExample example);

    int max(@Param("field") String field, @Param("example") SalesmanExtExample example);

    int min(@Param("field") String field, @Param("example") SalesmanExtExample example);

}

