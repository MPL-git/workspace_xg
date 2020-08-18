package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-11-22 上午 9:22
 */
public class AppLoginDistinctLogCustomExample extends AppLoginDistinctLogExample {

	@Override
	public AppLoginDistinctLogCustomCriteria createCriteria() {
		AppLoginDistinctLogCustomCriteria criteria = new AppLoginDistinctLogCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class AppLoginDistinctLogCustomCriteria extends AppLoginDistinctLogExample.Criteria{

	}

}
