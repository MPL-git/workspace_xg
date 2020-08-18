package com.jf.dao;

import com.jf.entity.ProductPropExt;
import com.jf.entity.ProductPropExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductPropExt findById(int id);

    ProductPropExt find(ProductPropExtExample example);

    List<ProductPropExt> list(ProductPropExtExample example);

    List<Integer> listId(ProductPropExtExample example);

    int count(ProductPropExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductPropExtExample example);

    int max(@Param("field") String field, @Param("example") ProductPropExtExample example);

    int min(@Param("field") String field, @Param("example") ProductPropExtExample example);

}

