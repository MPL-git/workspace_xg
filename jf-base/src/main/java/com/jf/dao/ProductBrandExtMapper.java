package com.jf.dao;

import com.jf.entity.ProductBrandExt;
import com.jf.entity.ProductBrandExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductBrandExt findById(int id);

    ProductBrandExt find(ProductBrandExtExample example);

    List<ProductBrandExt> list(ProductBrandExtExample example);

    List<Integer> listId(ProductBrandExtExample example);

    int count(ProductBrandExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductBrandExtExample example);

    int max(@Param("field") String field, @Param("example") ProductBrandExtExample example);

    int min(@Param("field") String field, @Param("example") ProductBrandExtExample example);

}

