package com.jf.entity;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;

public class CombineOrderExtendExtExample extends CombineOrderExtendExample{

	private QueryObject queryObject;

	public QueryObject getQueryObject() {
		if(queryObject == null)	queryObject = new QueryObject();
		return queryObject;
	}

	public CombineOrderExtendExtExample fill(){
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

	public CombineOrderExtendExtExample fillPage(){
		if(queryObject == null)	queryObject = new QueryObject();
		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		setLimitStart(queryObject.getLimitStart());
		setLimitSize(queryObject.getPageSize());
		return this;
	}

	@Override
	public CombineOrderExtendExtCriteria createCriteria() {
		CombineOrderExtendExtCriteria criteria = new CombineOrderExtendExtCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class CombineOrderExtendExtCriteria extends Criteria{

		public CombineOrderExtendExtCriteria andCombineOrderStatusEquals(String status) {
			addCriterion("EXISTS(select b.id FROM bu_combine_order b where b.id = a.combine_order_id and b.status = '" + status + "')");
			return this;
		}

	}
}
