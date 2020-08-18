package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
