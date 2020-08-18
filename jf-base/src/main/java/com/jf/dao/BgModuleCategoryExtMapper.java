package com.jf.dao;

import com.jf.entity.BgModuleCategoryExt;
import com.jf.entity.BgModuleCategoryExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BgModuleCategoryExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BgModuleCategoryExt findById(int id);

    BgModuleCategoryExt find(BgModuleCategoryExtExample example);

    List<BgModuleCategoryExt> list(BgModuleCategoryExtExample example);

    List<Integer> listId(BgModuleCategoryExtExample example);

    int count(BgModuleCategoryExtExample example);

    long sum(@Param("field") String field, @Param("example") BgModuleCategoryExtExample example);

    int max(@Param("field") String field, @Param("example") BgModuleCategoryExtExample example);

    int min(@Param("field") String field, @Param("example") BgModuleCategoryExtExample example);

}

