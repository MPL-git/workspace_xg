package com.jf.dao;

import com.jf.entity.MchtBrandInvoicePic;
import com.jf.entity.MchtBrandInvoicePicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBrandInvoicePicMapper extends BaseDao<MchtBrandInvoicePic, MchtBrandInvoicePicExample>{
    int countByExample(MchtBrandInvoicePicExample example);

    int deleteByExample(MchtBrandInvoicePicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandInvoicePic record);

    int insertSelective(MchtBrandInvoicePic record);

    List<MchtBrandInvoicePic> selectByExample(MchtBrandInvoicePicExample example);

    MchtBrandInvoicePic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandInvoicePic record, @Param("example") MchtBrandInvoicePicExample example);

    int updateByExample(@Param("record") MchtBrandInvoicePic record, @Param("example") MchtBrandInvoicePicExample example);

    int updateByPrimaryKeySelective(MchtBrandInvoicePic record);

    int updateByPrimaryKey(MchtBrandInvoicePic record);
}