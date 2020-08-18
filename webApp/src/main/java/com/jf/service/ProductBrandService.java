package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.CollectionUtil;
import com.jf.dao.ProductBrandMapper;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月22日 上午9:36:54 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductBrandService extends BaseService<ProductBrand, ProductBrandExample> {
	
	@Autowired
	private ProductBrandMapper productBrandMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	public void setProductBrandMapper(ProductBrandMapper productBrandMapper) {
		this.setDao(productBrandMapper);
		this.productBrandMapper = productBrandMapper;
	}

	public Map<String, Object> getShopMallProductBrandMap(Map<String, Object> paramsMap, String type) {
		List<ProductCustom> productCustoms = productService.getShopMallProductBrandList(paramsMap);
		List<Map<String,Object>> brandMapList = new ArrayList<Map<String,Object>>();
		if(CollectionUtil.isNotEmpty(productCustoms)){
			int i = 0;
			for (ProductCustom productCustom : productCustoms) {
				Map<String,Object> brandMap = new HashMap<String,Object>();
				String brandId = productCustom.getBrandId().toString();
				String brandName = productCustom.getBrandName();
				if("1".equals(type)){
					i++;
					if(i == 9){
						brandId = "";
						brandName = "全部品牌";
					}else if( i > 9){
						break;
					}
				}
				brandMap.put("value", brandId);
				brandMap.put("name", brandName);
				brandMap.put("letterIndex", productCustom.getLetterIndex());
				brandMapList.add(brandMap);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "品牌");
		map.put("productList", brandMapList);
		return map;
	}

	public List<Integer> getBrandIdListByWords(List<Integer> brandIdList, List<String> wordList) {
		ProductBrandExample brandExample = new ProductBrandExample();
		brandExample.or().andNameIn(wordList).andStatusEqualTo("1").andDelFlagEqualTo("0");
		brandExample.or().andNameEnIn(wordList).andStatusEqualTo("1").andDelFlagEqualTo("0");
		brandExample.or().andNameZhIn(wordList).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductBrand> brands = selectByExample(brandExample);
		if(CollectionUtil.isNotEmpty(brands)){
			for (ProductBrand productBrand : brands) {
				brandIdList.add(productBrand.getId());
			}
		}
		return brandIdList;
	}
	
}
