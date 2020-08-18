package com.jf.dao;

import com.jf.entity.MallCategoryItemExt;
import com.jf.entity.MallCategoryItemExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategoryItemExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MallCategoryItemExt findById(int id);

    MallCategoryItemExt find(MallCategoryItemExtExample example);

    List<MallCategoryItemExt> list(MallCategoryItemExtExample example);

    List<Integer> listId(MallCategoryItemExtExample example);

    int count(MallCategoryItemExtExample example);

    long sum(@Param("field") String field, @Param("example") MallCategoryItemExtExample example);

    int max(@Param("field") String field, @Param("example") MallCategoryItemExtExample example);

    int min(@Param("field") String field, @Param("example") MallCategoryItemExtExample example);

}

