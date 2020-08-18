package com.jf.entity;

import com.jf.common.constant.Const;


public class ProductPropValueCustomExample extends ProductPropValueExample{

	@Override
	public ProductPropValueCustomExample.ProductPropValueCustomCriteria createCriteria() {
		ProductPropValueCustomExample.ProductPropValueCustomCriteria criteria = new ProductPropValueCustomExample.ProductPropValueCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class ProductPropValueCustomCriteria extends ProductPropValueExample.Criteria{


	}
}