package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductTypeCustomMapper;
import com.jf.dao.ProductTypeMapper;
import com.jf.entity.MchtProductType;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeCustom;
import com.jf.entity.ProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductTypeService extends BaseService<ProductType,ProductTypeExample> {
	@Autowired
	private ProductTypeMapper dao;
	@Autowired
	private MchtProductTypeService mchtProductTypeService;
	
	@Autowired
	private ProductTypeCustomMapper productTypeCustomMapper;

	@Autowired
	public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
		super.setDao(productTypeMapper);
		this.dao = productTypeMapper;
	}

	public ProductType findById(int id){
		return dao.selectByPrimaryKey(id);
	}


	public List<ProductType> findListOfTopLevelByMchtId(int mchtId, QueryObject queryObject){
		queryObject.addQuery("mchtId", mchtId);
		List<MchtProductType> mchtProductTypeList = mchtProductTypeService.findList(queryObject);
		if(mchtProductTypeList.size() == 0)	return new ArrayList<>();

		List<Integer> productTypeIds = new ArrayList<>();
		for(MchtProductType mchtProductType : mchtProductTypeList){
			productTypeIds.add(mchtProductType.getProductTypeId());
		}

		queryObject = new QueryObject();
		queryObject.addQuery("productTypeIds", productTypeIds);
		List<ProductType> list = list(queryObject);
		List<ProductType> topLevelList = new ArrayList<>();
		for(ProductType productType : list){
			topLevelList.add(findTop(productType));
		}
		return topLevelList;
	}




	public List<ProductType> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}


	public   List<ProductTypeCustom> getMchtUserbleProductType(int mchtId){
		return productTypeCustomMapper.getMchtUserbleProductType(mchtId);
	}
	
	public   List<ProductTypeCustom> getMchtUserbleSmallwareProductType(int mchtId){
		return productTypeCustomMapper.getMchtUserbleSmallwareProductType(mchtId);
	}
	
	
	private ProductType findTop(ProductType productType){
		if(productType.gettLevel() == 0)	return null;
		if(productType.gettLevel() == 1)	return productType;
		return findTop(findById(productType.getParentId()));
	}

	private ProductTypeExample builderQuery(QueryObject queryObject) {
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("1");
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("productTypeIds") != null){
			List<Integer> ids = queryObject.getQuery("productTypeIds");
			criteria.andIdIn(ids);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
