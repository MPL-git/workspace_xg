package com.jf.dao;

import com.jf.entity.MallCategorySubExt;
import com.jf.entity.MallCategorySubExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategorySubExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MallCategorySubExt findById(int id);

    MallCategorySubExt find(MallCategorySubExtExample example);

    List<MallCategorySubExt> list(MallCategorySubExtExample example);

    List<Integer> listId(MallCategorySubExtExample example);

    int count(MallCategorySubExtExample example);

    long sum(@Param("field") String field, @Param("example") MallCategorySubExtExample example);

    int max(@Param("field") String field, @Param("example") MallCategorySubExtExample example);

    int min(@Param("field") String field, @Param("example") MallCategorySubExtExample example);

}

