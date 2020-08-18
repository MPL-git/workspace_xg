package com.jf.dao;

import com.jf.entity.ProductPriceChangeLogExt;
import com.jf.entity.ProductPriceChangeLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceChangeLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductPriceChangeLogExt findById(int id);

    ProductPriceChangeLogExt find(ProductPriceChangeLogExtExample example);

    List<ProductPriceChangeLogExt> list(ProductPriceChangeLogExtExample example);

    List<Integer> listId(ProductPriceChangeLogExtExample example);

    int count(ProductPriceChangeLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductPriceChangeLogExtExample example);

    int max(@Param("field") String field, @Param("example") ProductPriceChangeLogExtExample example);

    int min(@Param("field") String field, @Param("example") ProductPriceChangeLogExtExample example);

}

