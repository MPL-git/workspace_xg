package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlCustomExample;
import com.jf.entity.CutPriceOrderDtlExample;

@Repository
public interface CutPriceOrderDtlCustomMapper {
    
	public int countByCustomExample(CutPriceOrderDtlCustomExample example);

	public List<CutPriceOrderDtlCustom> selectByCustomExample(CutPriceOrderDtlCustomExample example);

	public CutPriceOrderDtlCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") CutPriceOrderDtl record, @Param("example") CutPriceOrderDtlCustomExample example);

}