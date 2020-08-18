package com.jf.dao;

import com.jf.entity.MchtTaxInvoiceInfoExt;
import com.jf.entity.MchtTaxInvoiceInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtTaxInvoiceInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtTaxInvoiceInfoExt findById(int id);

    MchtTaxInvoiceInfoExt find(MchtTaxInvoiceInfoExtExample example);

    List<MchtTaxInvoiceInfoExt> list(MchtTaxInvoiceInfoExtExample example);

    List<Integer> listId(MchtTaxInvoiceInfoExtExample example);

    int count(MchtTaxInvoiceInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtTaxInvoiceInfoExtExample example);

    int max(@Param("field") String field, @Param("example") MchtTaxInvoiceInfoExtExample example);

    int min(@Param("field") String field, @Param("example") MchtTaxInvoiceInfoExtExample example);

}

