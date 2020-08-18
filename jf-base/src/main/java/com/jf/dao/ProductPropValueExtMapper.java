package com.jf.dao;

import com.jf.entity.ProductPropValueExt;
import com.jf.entity.ProductPropValueExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropValueExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductPropValueExt findById(int id);

    ProductPropValueExt find(ProductPropValueExtExample example);

    List<ProductPropValueExt> list(ProductPropValueExtExample example);

    List<Integer> listId(ProductPropValueExtExample example);

    int count(ProductPropValueExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductPropValueExtExample example);

    int max(@Param("field") String field, @Param("example") ProductPropValueExtExample example);

    int min(@Param("field") String field, @Param("example") ProductPropValueExtExample example);

}

