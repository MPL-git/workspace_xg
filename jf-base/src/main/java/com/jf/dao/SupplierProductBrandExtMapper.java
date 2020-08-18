package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SupplierProductBrandExt;
import com.jf.entity.SupplierProductBrandExtExample;

@Repository
public interface SupplierProductBrandExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SupplierProductBrandExt findById(int id);

    SupplierProductBrandExt find(SupplierProductBrandExtExample example);

    List<SupplierProductBrandExt> list(SupplierProductBrandExtExample example);

    List<Integer> listId(SupplierProductBrandExtExample example);

    int count(SupplierProductBrandExtExample example);

    long sum(@Param("field") String field, @Param("example") SupplierProductBrandExtExample example);

    int max(@Param("field") String field, @Param("example") SupplierProductBrandExtExample example);

    int min(@Param("field") String field, @Param("example") SupplierProductBrandExtExample example);

}

