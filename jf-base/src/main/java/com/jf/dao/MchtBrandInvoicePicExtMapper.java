package com.jf.dao;

import com.jf.entity.MchtBrandInvoicePicExt;
import com.jf.entity.MchtBrandInvoicePicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInvoicePicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandInvoicePicExt findById(int id);

    MchtBrandInvoicePicExt find(MchtBrandInvoicePicExtExample example);

    List<MchtBrandInvoicePicExt> list(MchtBrandInvoicePicExtExample example);

    List<Integer> listId(MchtBrandInvoicePicExtExample example);

    int count(MchtBrandInvoicePicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandInvoicePicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandInvoicePicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandInvoicePicExtExample example);

}

