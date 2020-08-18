package com.jf.entity;


public class SignSendMsgCnfCustomExample extends SignSendMsgCnfExample {

	@Override
    public SignSendMsgCnfCustomCriteria createCriteria() {
		SignSendMsgCnfCustomCriteria criteria = new SignSendMsgCnfCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SignSendMsgCnfCustomCriteria extends SignSendMsgCnfExample.Criteria{
	
	}
	
}
