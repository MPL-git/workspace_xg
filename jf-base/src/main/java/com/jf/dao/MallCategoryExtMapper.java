package com.jf.dao;

import com.jf.entity.MallCategoryExt;
import com.jf.entity.MallCategoryExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategoryExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MallCategoryExt findById(int id);

    MallCategoryExt find(MallCategoryExtExample example);

    List<MallCategoryExt> list(MallCategoryExtExample example);

    List<Integer> listId(MallCategoryExtExample example);

    int count(MallCategoryExtExample example);

    long sum(@Param("field") String field, @Param("example") MallCategoryExtExample example);

    int max(@Param("field") String field, @Param("example") MallCategoryExtExample example);

    int min(@Param("field") String field, @Param("example") MallCategoryExtExample example);

}

