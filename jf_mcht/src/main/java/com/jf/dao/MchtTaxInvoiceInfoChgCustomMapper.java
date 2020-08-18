package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgCustom;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;
@Repository
public interface MchtTaxInvoiceInfoChgCustomMapper {
    int countByExample(MchtTaxInvoiceInfoChgExample example);
    List<MchtTaxInvoiceInfoChgCustom> selectByExample(MchtTaxInvoiceInfoChgExample example);
    MchtTaxInvoiceInfoChgCustom selectByPrimaryKey(Integer id);
}