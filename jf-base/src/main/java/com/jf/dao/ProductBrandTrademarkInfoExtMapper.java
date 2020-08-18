package com.jf.dao;

import com.jf.entity.ProductBrandTrademarkInfoExt;
import com.jf.entity.ProductBrandTrademarkInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandTrademarkInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductBrandTrademarkInfoExt findById(int id);

    ProductBrandTrademarkInfoExt find(ProductBrandTrademarkInfoExtExample example);

    List<ProductBrandTrademarkInfoExt> list(ProductBrandTrademarkInfoExtExample example);

    List<Integer> listId(ProductBrandTrademarkInfoExtExample example);

    int count(ProductBrandTrademarkInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductBrandTrademarkInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ProductBrandTrademarkInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ProductBrandTrademarkInfoExtExample example);

}

