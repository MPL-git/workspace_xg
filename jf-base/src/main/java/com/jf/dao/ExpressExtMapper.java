package com.jf.dao;

import com.jf.entity.ExpressExt;
import com.jf.entity.ExpressExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpressExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ExpressExt findById(int id);

    ExpressExt find(ExpressExtExample example);

    List<ExpressExt> list(ExpressExtExample example);

    List<Integer> listId(ExpressExtExample example);

    int count(ExpressExtExample example);

    long sum(@Param("field") String field, @Param("example") ExpressExtExample example);

    int max(@Param("field") String field, @Param("example") ExpressExtExample example);

    int min(@Param("field") String field, @Param("example") ExpressExtExample example);

}

