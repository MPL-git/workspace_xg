package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtTaxInvoiceInfoCustom;
import com.jf.entity.MchtTaxInvoiceInfoExample;
@Repository
public interface MchtTaxInvoiceInfoCustomMapper{
    int countByExample(MchtTaxInvoiceInfoExample example);
    List<MchtTaxInvoiceInfoCustom> selectByExample(MchtTaxInvoiceInfoExample example);
    MchtTaxInvoiceInfoCustom selectByPrimaryKey(Integer id);

}