package com.jf.dao;

import com.jf.entity.MallCategoryModuleExt;
import com.jf.entity.MallCategoryModuleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategoryModuleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MallCategoryModuleExt findById(int id);

    MallCategoryModuleExt find(MallCategoryModuleExtExample example);

    List<MallCategoryModuleExt> list(MallCategoryModuleExtExample example);

    List<Integer> listId(MallCategoryModuleExtExample example);

    int count(MallCategoryModuleExtExample example);

    long sum(@Param("field") String field, @Param("example") MallCategoryModuleExtExample example);

    int max(@Param("field") String field, @Param("example") MallCategoryModuleExtExample example);

    int min(@Param("field") String field, @Param("example") MallCategoryModuleExtExample example);

}

