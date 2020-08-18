package com.jf.dao;

import com.jf.entity.TaobaoProductTypePageNoExt;
import com.jf.entity.TaobaoProductTypePageNoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaobaoProductTypePageNoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaobaoProductTypePageNoExt findById(int id);

    TaobaoProductTypePageNoExt find(TaobaoProductTypePageNoExtExample example);

    List<TaobaoProductTypePageNoExt> list(TaobaoProductTypePageNoExtExample example);

    List<Integer> listId(TaobaoProductTypePageNoExtExample example);

    int count(TaobaoProductTypePageNoExtExample example);

    long sum(@Param("field") String field, @Param("example") TaobaoProductTypePageNoExtExample example);

    int max(@Param("field") String field, @Param("example") TaobaoProductTypePageNoExtExample example);

    int min(@Param("field") String field, @Param("example") TaobaoProductTypePageNoExtExample example);

}

