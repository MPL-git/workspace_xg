package com.jf.dao;

import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtTaxInvoiceInfoChgMapper extends BaseDao<MchtTaxInvoiceInfoChg, MchtTaxInvoiceInfoChgExample> {
    int countByExample(MchtTaxInvoiceInfoChgExample example);

    int deleteByExample(MchtTaxInvoiceInfoChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtTaxInvoiceInfoChg record);

    int insertSelective(MchtTaxInvoiceInfoChg record);

    List<MchtTaxInvoiceInfoChg> selectByExample(MchtTaxInvoiceInfoChgExample example);

    MchtTaxInvoiceInfoChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtTaxInvoiceInfoChg record, @Param("example") MchtTaxInvoiceInfoChgExample example);

    int updateByExample(@Param("record") MchtTaxInvoiceInfoChg record, @Param("example") MchtTaxInvoiceInfoChgExample example);

    int updateByPrimaryKeySelective(MchtTaxInvoiceInfoChg record);

    int updateByPrimaryKey(MchtTaxInvoiceInfoChg record);
}