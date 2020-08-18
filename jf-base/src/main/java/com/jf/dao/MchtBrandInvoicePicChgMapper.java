package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBrandInvoicePicChg;
import com.jf.entity.MchtBrandInvoicePicChgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInvoicePicChgMapper extends BaseDao<MchtBrandInvoicePicChg, MchtBrandInvoicePicChgExample> {
    int countByExample(MchtBrandInvoicePicChgExample example);

    int deleteByExample(MchtBrandInvoicePicChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandInvoicePicChg record);

    int insertSelective(MchtBrandInvoicePicChg record);

    List<MchtBrandInvoicePicChg> selectByExample(MchtBrandInvoicePicChgExample example);

    MchtBrandInvoicePicChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandInvoicePicChg record, @Param("example") MchtBrandInvoicePicChgExample example);

    int updateByExample(@Param("record") MchtBrandInvoicePicChg record, @Param("example") MchtBrandInvoicePicChgExample example);

    int updateByPrimaryKeySelective(MchtBrandInvoicePicChg record);

    int updateByPrimaryKey(MchtBrandInvoicePicChg record);
}
