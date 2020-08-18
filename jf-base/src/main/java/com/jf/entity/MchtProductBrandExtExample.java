package com.jf.entity;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;

public class MchtProductBrandExtExample extends MchtProductBrandExample{

	private QueryObject queryObject;

	public QueryObject getQueryObject() {
		if(queryObject == null)	queryObject = new QueryObject();
		return queryObject;
	}

	public MchtProductBrandExtExample fill(){
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

	public MchtProductBrandExtExample fillPage(){
		if(queryObject == null)	queryObject = new QueryObject();
		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		setLimitStart(queryObject.getLimitStart());
		setLimitSize(queryObject.getPageSize());
		return this;
	}

	@Override
	public MchtProductBrandExtCriteria createCriteria() {
		MchtProductBrandExtCriteria criteria = new MchtProductBrandExtCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class MchtProductBrandExtCriteria extends Criteria{
		public Criteria andMchtStatusEquals(String mchtStatus) {
			addCriterion(" EXISTS(select 1 from bu_mcht_info b where a.mcht_id=b.id and b.del_flag='0' and b.status=" + mchtStatus + ")");
			return this;
		}
	}
}
