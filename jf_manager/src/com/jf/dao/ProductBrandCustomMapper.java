package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
@Repository
public interface ProductBrandCustomMapper{
	public String getNamesByIds(String ids);

	public List<HashMap<String, Object>> searchBrand(Map<String, Object> paramMap);
	
	public int countByCustomExample(ProductBrandCustomExample example);

	public List<ProductBrandCustom> selectByCustomExample(ProductBrandCustomExample example);

	public ProductBrandCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") ProductBrand record, @Param("example") ProductBrandCustomExample example);
	
	List<Map<String, Object>> selectMchtInfoBrandCustomList(Map<String, Object> paramMap);

	//品牌流量报表的数据
    List<ProductBrandCustom> getBrandFlowReportData(Map<String, Object> paraMap);
}