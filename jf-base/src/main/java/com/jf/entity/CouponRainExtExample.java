package com.jf.entity;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;

public class CouponRainExtExample extends CouponRainExample{

	private QueryObject queryObject;

	public QueryObject getQueryObject() {
		if(queryObject == null)	queryObject = new QueryObject();
		return queryObject;
	}

	public CouponRainExtExample fill(){
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

	public CouponRainExtExample fillPage(){
		if(queryObject == null)	queryObject = new QueryObject();
		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		setLimitStart(queryObject.getLimitStart());
		setLimitSize(queryObject.getPageSize());
		return this;
	}

	@Override
	public CouponRainExtCriteria createCriteria() {
		CouponRainExtCriteria criteria = new CouponRainExtCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class CouponRainExtCriteria extends Criteria{

	}
}
