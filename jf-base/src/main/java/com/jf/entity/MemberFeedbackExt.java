package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberFeedbackExt extends MemberFeedback {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
