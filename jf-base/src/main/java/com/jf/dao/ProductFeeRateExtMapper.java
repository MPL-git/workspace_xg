package com.jf.dao;

import com.jf.entity.ProductFeeRateExt;
import com.jf.entity.ProductFeeRateExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeeRateExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductFeeRateExt findById(int id);

    ProductFeeRateExt find(ProductFeeRateExtExample example);

    List<ProductFeeRateExt> list(ProductFeeRateExtExample example);

    List<Integer> listId(ProductFeeRateExtExample example);

    int count(ProductFeeRateExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductFeeRateExtExample example);

    int max(@Param("field") String field, @Param("example") ProductFeeRateExtExample example);

    int min(@Param("field") String field, @Param("example") ProductFeeRateExtExample example);

}

