package com.jf.dao;

import com.jf.entity.MchtTaxInvoiceInfoChgExt;
import com.jf.entity.MchtTaxInvoiceInfoChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtTaxInvoiceInfoChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtTaxInvoiceInfoChgExt findById(int id);

    MchtTaxInvoiceInfoChgExt find(MchtTaxInvoiceInfoChgExtExample example);

    List<MchtTaxInvoiceInfoChgExt> list(MchtTaxInvoiceInfoChgExtExample example);

    List<Integer> listId(MchtTaxInvoiceInfoChgExtExample example);

    int count(MchtTaxInvoiceInfoChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtTaxInvoiceInfoChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtTaxInvoiceInfoChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtTaxInvoiceInfoChgExtExample example);

}

