package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberLabelRuleExt extends MemberLabelRule {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
