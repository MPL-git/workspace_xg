package com.jf.entity;



public class SysParamCfgCustomExample extends SysParamCfgExample{
	
	@Override
    public SysParamCfgCustomCriteria createCriteria() {
		SysParamCfgCustomCriteria criteria = new SysParamCfgCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SysParamCfgCustomCriteria extends SysParamCfgExample.Criteria{
		
		
	}
}