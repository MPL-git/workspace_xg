package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceCnfTplDtl;
import com.jf.entity.CutPriceCnfTplDtlCustom;
import com.jf.entity.CutPriceCnfTplDtlCustomExample;
import com.jf.entity.CutPriceCnfTplDtlExample;

@Repository
public interface CutPriceCnfTplDtlCustomMapper extends BaseDao<CutPriceCnfTplDtl, CutPriceCnfTplDtlExample> {
    
	public int countByCustomExample(CutPriceCnfTplDtlCustomExample example);

	public List<CutPriceCnfTplDtlCustom> selectByCustomExample(CutPriceCnfTplDtlCustomExample example);

	public CutPriceCnfTplDtlCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") CutPriceCnfTplDtl record, @Param("example") CutPriceCnfTplDtlCustomExample example);

	/**
	 * 
	 * @Title insertCutPriceCnfTplDtlList   
	 * @Description TODO(批量新增)   
	 * @author pengl
	 * @date 2018年6月8日 下午12:32:31
	 */
	public void insertCutPriceCnfTplDtlList(List<CutPriceCnfTplDtl> cutPriceCnfTplDtlList);
	
	/**
	 * 
	 * @Title updateCutPriceCnfTplDtlIdListSelective   
	 * @Description TODO(批量修改)   
	 * @author pengl
	 * @date 2018年6月8日 下午12:32:36
	 */
	public void updateCutPriceCnfTplDtlIdListSelective(List<CutPriceCnfTplDtl> cutPriceCnfTplDtlList);
	
}