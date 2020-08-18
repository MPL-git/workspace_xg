package com.jf.dao;

import com.jf.entity.MallCategoryMapExt;
import com.jf.entity.MallCategoryMapExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategoryMapExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MallCategoryMapExt findById(int id);

    MallCategoryMapExt find(MallCategoryMapExtExample example);

    List<MallCategoryMapExt> list(MallCategoryMapExtExample example);

    List<Integer> listId(MallCategoryMapExtExample example);

    int count(MallCategoryMapExtExample example);

    long sum(@Param("field") String field, @Param("example") MallCategoryMapExtExample example);

    int max(@Param("field") String field, @Param("example") MallCategoryMapExtExample example);

    int min(@Param("field") String field, @Param("example") MallCategoryMapExtExample example);

}

