package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MallCategoryMapMapper;
import com.jf.entity.MallCategoryMap;
import com.jf.entity.MallCategoryMapExample;

@Service
@Transactional
public class MallCategoryMapService extends BaseService<MallCategoryMap, MallCategoryMapExample> {
	@Autowired
	private MallCategoryMapMapper mallCategoryMapMapper;
	@Autowired
	public void setMallCategoryMapMapper(MallCategoryMapMapper mallCategoryMapMapper) {
		this.setDao(mallCategoryMapMapper);
		this.mallCategoryMapMapper = mallCategoryMapMapper;
	}
	/**
	 * 商城一级类目和特卖一级类目互相转换（通用）
	 */
	public Map<String, String> getCategoryConvert(List<Integer> productTypeList, List<Integer> mallCategoryList) {
		Map<String,String> categoryMap = new HashMap<String, String>();
		if(CollectionUtils.isNotEmpty(productTypeList) || CollectionUtils.isNotEmpty(mallCategoryList)){
			boolean b = true;
			MallCategoryMapExample categoryMapExample = new MallCategoryMapExample();
			MallCategoryMapExample.Criteria criteria = categoryMapExample.createCriteria();
			if(CollectionUtils.isNotEmpty(productTypeList)){
				//特卖一级类目转化商城一级类目
				criteria.andProductTypeIdIn(productTypeList);
			}else{
				//商城一级类目转化特卖一级类目
				b = false;
				criteria.andMallCategoryIdIn(mallCategoryList);
			}
			criteria.andDelFlagEqualTo("0");
			List<MallCategoryMap> mallCategoryMaps = selectByExample(categoryMapExample);
			if(CollectionUtils.isNotEmpty(mallCategoryMaps)){
				for (MallCategoryMap mallCategoryMap : mallCategoryMaps) {
					Integer productTypeId = mallCategoryMap.getProductTypeId();
					Integer mallCategoryId = mallCategoryMap.getMallCategoryId();
					if(productTypeId != null && mallCategoryId != null){
						if(b){
							//特卖一级类目为key，商城一级类目为value
							categoryMap.put(productTypeId.toString(), mallCategoryId.toString());
						}else{
							//商城一级类目为key，特卖一级类目为value
							categoryMap.put(mallCategoryId.toString(), productTypeId.toString());
						}
					}
				}
			}
		}
		return categoryMap;
	}
	
	
}
