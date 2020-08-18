package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CombineOrderExtendExt;
import com.jf.entity.CombineOrderExtendExtExample;

@Repository
public interface CombineOrderExtendExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CombineOrderExtendExt findById(int id);

    CombineOrderExtendExt find(CombineOrderExtendExtExample example);

    List<CombineOrderExtendExt> list(CombineOrderExtendExtExample example);

    List<Integer> listId(CombineOrderExtendExtExample example);

    int count(CombineOrderExtendExtExample example);

    long sum(@Param("field") String field, @Param("example") CombineOrderExtendExtExample example);

    int max(@Param("field") String field, @Param("example") CombineOrderExtendExtExample example);

    int min(@Param("field") String field, @Param("example") CombineOrderExtendExtExample example);

}

