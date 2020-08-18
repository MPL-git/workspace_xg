package com.jf.dao;

import com.jf.entity.CatalogExt;
import com.jf.entity.CatalogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CatalogExt findById(int id);

    CatalogExt find(CatalogExtExample example);

    List<CatalogExt> list(CatalogExtExample example);

    List<Integer> listId(CatalogExtExample example);

    int count(CatalogExtExample example);

    long sum(@Param("field") String field, @Param("example") CatalogExtExample example);

    int max(@Param("field") String field, @Param("example") CatalogExtExample example);

    int min(@Param("field") String field, @Param("example") CatalogExtExample example);

}

