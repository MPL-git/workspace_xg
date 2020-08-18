package com.jf.dao;

import com.jf.entity.MallCategoryTopExt;
import com.jf.entity.MallCategoryTopExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategoryTopExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MallCategoryTopExt findById(int id);

    MallCategoryTopExt find(MallCategoryTopExtExample example);

    List<MallCategoryTopExt> list(MallCategoryTopExtExample example);

    List<Integer> listId(MallCategoryTopExtExample example);

    int count(MallCategoryTopExtExample example);

    long sum(@Param("field") String field, @Param("example") MallCategoryTopExtExample example);

    int max(@Param("field") String field, @Param("example") MallCategoryTopExtExample example);

    int min(@Param("field") String field, @Param("example") MallCategoryTopExtExample example);

}

