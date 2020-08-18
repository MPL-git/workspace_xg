package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductPicService extends BaseService<ProductPic, ProductPicExample> {
	@Autowired
	private ProductPicMapper dao;

	@Autowired
	public void setProductPicMapper(ProductPicMapper productPicMapper) {
		super.setDao(productPicMapper);
		this.dao = productPicMapper;
	}

	public ProductPic findMainByProductId(Integer productId) {
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("productId", productId);
		queryObject.addSort("is_default", QueryObject.SORT_DESC);
		queryObject.addSort("seq_no", QueryObject.SORT_ASC);
		List<ProductPic> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<ProductPic> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private ProductPicExample builderQuery(QueryObject queryObject) {
		ProductPicExample example = new ProductPicExample();
		ProductPicExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("productId") != null){
			criteria.andProductIdEqualTo(queryObject.getQueryToInt("productId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
