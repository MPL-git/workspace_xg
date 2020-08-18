package com.jf.dao;

import com.jf.entity.ProductTypeExt;
import com.jf.entity.ProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductTypeExt findById(int id);

    ProductTypeExt find(ProductTypeExtExample example);

    List<ProductTypeExt> list(ProductTypeExtExample example);

    List<Integer> listId(ProductTypeExtExample example);

    int count(ProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") ProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") ProductTypeExtExample example);

}

