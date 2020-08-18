package com.jf.entity;

public class SysStaffRoleCustomExample extends SysStaffRoleExample {

	@Override
	public SysStaffRoleCustomCriteria createCriteria() {
		SysStaffRoleCustomCriteria criteria = new SysStaffRoleCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class SysStaffRoleCustomCriteria extends SysStaffRoleExample.Criteria {
		
	}
	
}
