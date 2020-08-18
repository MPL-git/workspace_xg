package com.jf.dao;

import com.jf.entity.ProductItemExt;
import com.jf.entity.ProductItemExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductItemExt findById(int id);

    ProductItemExt find(ProductItemExtExample example);

    List<ProductItemExt> list(ProductItemExtExample example);

    List<Integer> listId(ProductItemExtExample example);

    int count(ProductItemExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductItemExtExample example);

    int max(@Param("field") String field, @Param("example") ProductItemExtExample example);

    int min(@Param("field") String field, @Param("example") ProductItemExtExample example);

}

