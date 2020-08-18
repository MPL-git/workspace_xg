package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductPropValueMapper;
import com.jf.dao.ProductPropValueCustomMapper;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueExample;
import com.jf.entity.ProductPropValueCustom;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月26日 下午8:22:14 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductPropValueService extends BaseService<ProductPropValue, ProductPropValueExample> {
	
	@Autowired
	private ProductPropValueMapper productPropValueMapper;
	
	@Autowired
	private ProductPropValueCustomMapper productPropValueCustomMapper;
	
	@Autowired
	public void setProductPropValueMapper(ProductPropValueMapper productPropValueMapper) {
		this.setDao(productPropValueMapper);
		this.productPropValueMapper = productPropValueMapper;
	}

	public List<ProductPropValueCustom> getProductPropdesc(Map<String, Object> propValIdsMap) {
		
		return productPropValueCustomMapper.getProductPropdesc(propValIdsMap);
	}

	public List<ProductPropValue> getProductPropValueModelByIdsNew(Map<String, Object> map) {
		
		return productPropValueCustomMapper.getProductPropValueModelByIdsNew(map);
	}

	public List<ProductPropValue> getProductPropValueModelByIdsOld(Map<String, Object> map) {
		
		return productPropValueCustomMapper.getProductPropValueModelByIdsOld(map);
	}

	public List<ProductPropValue> findModelsByAlias(String alias) {
		ProductPropValueExample productPropValueExample = new ProductPropValueExample();
		productPropValueExample.createCriteria().andDelFlagEqualTo("0").andAliasEqualTo(alias);
		return selectByExample(productPropValueExample);
	}

	public List<String> getPropValueIdList(List<String> aliasList) {
		List<String> propValIdLists = new ArrayList<>();
		List<ProductPropValue> propVaules = findModelsByAliasList(aliasList);
		if(CollectionUtils.isNotEmpty(propVaules)){
			for (ProductPropValue productPropValue : propVaules) {
				propValIdLists.add(productPropValue.getId().toString());
			}
		}
		return propValIdLists;
	}

	public List<ProductPropValue> findModelsByAliasList(List<String> aliasList) {
		ProductPropValueExample productPropValueExample = new ProductPropValueExample();
		productPropValueExample.createCriteria().andDelFlagEqualTo("0").andAliasIn(aliasList).andPropIdEqualTo(2);
		return selectByExample(productPropValueExample);
	}

}
