package com.jf.dao;

import com.jf.entity.CombineOrderExt;
import com.jf.entity.CombineOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombineOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CombineOrderExt findById(int id);

    CombineOrderExt find(CombineOrderExtExample example);

    List<CombineOrderExt> list(CombineOrderExtExample example);

    List<Integer> listId(CombineOrderExtExample example);

    int count(CombineOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") CombineOrderExtExample example);

    int max(@Param("field") String field, @Param("example") CombineOrderExtExample example);

    int min(@Param("field") String field, @Param("example") CombineOrderExtExample example);

}

