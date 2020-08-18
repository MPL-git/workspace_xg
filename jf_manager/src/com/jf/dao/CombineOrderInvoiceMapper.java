package com.jf.dao;

import com.jf.entity.CombineOrderInvoice;
import com.jf.entity.CombineOrderInvoiceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombineOrderInvoiceMapper  extends BaseDao<CombineOrderInvoice,CombineOrderInvoiceExample>{
    int countByExample(CombineOrderInvoiceExample example);

    int deleteByExample(CombineOrderInvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CombineOrderInvoice record);

    int insertSelective(CombineOrderInvoice record);

    List<CombineOrderInvoice> selectByExample(CombineOrderInvoiceExample example);

    CombineOrderInvoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CombineOrderInvoice record, @Param("example") CombineOrderInvoiceExample example);

    int updateByExample(@Param("record") CombineOrderInvoice record, @Param("example") CombineOrderInvoiceExample example);

    int updateByPrimaryKeySelective(CombineOrderInvoice record);

    int updateByPrimaryKey(CombineOrderInvoice record);
}