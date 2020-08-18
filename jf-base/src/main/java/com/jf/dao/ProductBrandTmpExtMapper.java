package com.jf.dao;

import com.jf.entity.ProductBrandTmpExt;
import com.jf.entity.ProductBrandTmpExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandTmpExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductBrandTmpExt findById(int id);

    ProductBrandTmpExt find(ProductBrandTmpExtExample example);

    List<ProductBrandTmpExt> list(ProductBrandTmpExtExample example);

    List<Integer> listId(ProductBrandTmpExtExample example);

    int count(ProductBrandTmpExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductBrandTmpExtExample example);

    int max(@Param("field") String field, @Param("example") ProductBrandTmpExtExample example);

    int min(@Param("field") String field, @Param("example") ProductBrandTmpExtExample example);

}

