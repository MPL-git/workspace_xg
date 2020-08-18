package com.jf.entity;

public class MemberNovaRecordCustomExample extends MemberNovaRecordExample{
	@Override
    public MemberNovaRecordCustomCriteria createCriteria() {
		MemberNovaRecordCustomCriteria criteria = new MemberNovaRecordCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	public class MemberNovaRecordCustomCriteria extends MemberNovaRecordExample.Criteria{
	}
}