package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfDtlCustom;
import com.jf.entity.CutPriceCnfDtlCustomExample;

@Repository
public interface CutPriceCnfDtlCustomMapper {
    
	public int countByCustomExample(CutPriceCnfDtlCustomExample example);

	public List<CutPriceCnfDtlCustom> selectByCustomExample(CutPriceCnfDtlCustomExample example);

	public CutPriceCnfDtlCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") CutPriceCnfDtl record, @Param("example") CutPriceCnfDtlCustomExample example);

    /**
     * 
     * @Title insertCutPriceCnfDtlList   
     * @Description TODO(批量插入)   
     * @author pengl
     * @date 2018年6月7日 下午2:16:50
     */
	public void insertCutPriceCnfDtlList(List<CutPriceCnfDtl> record);
	
	/**
	 * 
	 * @Title updateCutPriceCnfDtlIdListSelective   
	 * @Description TODO(批量修改)   
	 * @author pengl
	 * @date 2018年6月7日 下午2:17:29
	 */
	public void updateCutPriceCnfDtlIdListSelective(List<CutPriceCnfDtl> record);
	
	
}