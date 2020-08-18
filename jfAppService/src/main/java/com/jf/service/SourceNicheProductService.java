package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.SourceNicheProductCustomMapper;
import com.jf.dao.SourceNicheProductMapper;
import com.jf.entity.ProductCustom;
import com.jf.entity.SourceNicheProduct;
import com.jf.entity.SourceNicheProductExample;

/**
  * @author  yjc 
  * @date 创建时间：2019年8月2日09:48:48
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SourceNicheProductService extends BaseService<SourceNicheProduct, SourceNicheProductExample>{
	
	@Autowired
	private SourceNicheProductMapper sourceNicheProductMapper;
	@Autowired
	private SourceNicheProductCustomMapper sourceNicheProductCustomMapper;
	
	@Autowired
	public void setSourceNicheProductMapper(SourceNicheProductMapper sourceNicheProductMapper) {
		this.setDao(sourceNicheProductMapper);
		this.sourceNicheProductMapper = sourceNicheProductMapper;
	}

	public List<ProductCustom> getEveryDayShopProduct(Map<String, Object> map) {
		return sourceNicheProductCustomMapper.getEveryDayShopProduct(map);
	}

	public List<ProductCustom> getCollegeStudentProductList(Map<String, Object> paramMap) {
		return sourceNicheProductCustomMapper.getCollegeStudentProductList(paramMap);
	}

	
	
}
