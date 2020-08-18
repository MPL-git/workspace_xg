package com.jf.dao;

import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CutPriceOrderDtlMapper extends BaseDao<CutPriceOrderDtl, CutPriceOrderDtlExample> {
    int countByExample(CutPriceOrderDtlExample example);

    int deleteByExample(CutPriceOrderDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceOrderDtl record);

    int insertSelective(CutPriceOrderDtl record);

    List<CutPriceOrderDtl> selectByExample(CutPriceOrderDtlExample example);

    CutPriceOrderDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceOrderDtl record, @Param("example") CutPriceOrderDtlExample example);

    int updateByExample(@Param("record") CutPriceOrderDtl record, @Param("example") CutPriceOrderDtlExample example);

    int updateByPrimaryKeySelective(CutPriceOrderDtl record);

    int updateByPrimaryKey(CutPriceOrderDtl record);
}