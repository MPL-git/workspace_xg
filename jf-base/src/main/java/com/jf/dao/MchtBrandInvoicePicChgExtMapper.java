package com.jf.dao;

import com.jf.entity.MchtBrandInvoicePicChgExt;
import com.jf.entity.MchtBrandInvoicePicChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInvoicePicChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandInvoicePicChgExt findById(int id);

    MchtBrandInvoicePicChgExt find(MchtBrandInvoicePicChgExtExample example);

    List<MchtBrandInvoicePicChgExt> list(MchtBrandInvoicePicChgExtExample example);

    List<Integer> listId(MchtBrandInvoicePicChgExtExample example);

    int count(MchtBrandInvoicePicChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandInvoicePicChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandInvoicePicChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandInvoicePicChgExtExample example);

}

