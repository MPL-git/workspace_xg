package com.jf.entity;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;

public class ShopStoryExtExample extends ShopStoryExample{

	private QueryObject queryObject;

	public QueryObject getQueryObject() {
		if(queryObject == null)	queryObject = new QueryObject();
		return queryObject;
	}

	public ShopStoryExtExample fill(){
		if(queryObject == null)	return this;

		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		if(queryObject.getLimitSize() > 0){
			setLimitStart(0);
			setLimitSize(queryObject.getLimitSize());
		}
		return this;
	}

	public ShopStoryExtExample fillPage(){
		if(queryObject == null)	queryObject = new QueryObject();
		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		setLimitStart(queryObject.getLimitStart());
		setLimitSize(queryObject.getPageSize());
		return this;
	}

	@Override
	public ShopStoryExtCriteria createCriteria() {
		ShopStoryExtCriteria criteria = new ShopStoryExtCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class ShopStoryExtCriteria extends Criteria{

	}
}
