package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtTaxInvoiceInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtTaxInvoiceInfoMapper extends BaseDao<MchtTaxInvoiceInfo, MchtTaxInvoiceInfoExample> {
    int countByExample(MchtTaxInvoiceInfoExample example);

    int deleteByExample(MchtTaxInvoiceInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtTaxInvoiceInfo record);

    int insertSelective(MchtTaxInvoiceInfo record);

    List<MchtTaxInvoiceInfo> selectByExample(MchtTaxInvoiceInfoExample example);

    MchtTaxInvoiceInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtTaxInvoiceInfo record, @Param("example") MchtTaxInvoiceInfoExample example);

    int updateByExample(@Param("record") MchtTaxInvoiceInfo record, @Param("example") MchtTaxInvoiceInfoExample example);

    int updateByPrimaryKeySelective(MchtTaxInvoiceInfo record);

    int updateByPrimaryKey(MchtTaxInvoiceInfo record);
}
