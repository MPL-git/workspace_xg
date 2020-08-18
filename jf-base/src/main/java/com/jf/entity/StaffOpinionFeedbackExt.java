package com.jf.entity;

import com.jf.common.constant.Constant;

public class StaffOpinionFeedbackExt extends StaffOpinionFeedback {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
