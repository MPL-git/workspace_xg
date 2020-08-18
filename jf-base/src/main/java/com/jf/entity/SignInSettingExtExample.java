package com.jf.entity;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;

public class SignInSettingExtExample extends SignInSettingExample{

	private QueryObject queryObject;

	public QueryObject getQueryObject() {
		if(queryObject == null)	queryObject = new QueryObject();
		return queryObject;
	}

	public SignInSettingExtExample fill(){
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

	public SignInSettingExtExample fillPage(){
		if(queryObject == null)	queryObject = new QueryObject();
		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		setLimitStart(queryObject.getLimitStart());
		setLimitSize(queryObject.getPageSize());
		return this;
	}

	@Override
	public SignInSettingExtCriteria createCriteria() {
		SignInSettingExtCriteria criteria = new SignInSettingExtCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class SignInSettingExtCriteria extends Criteria{

	}
}
