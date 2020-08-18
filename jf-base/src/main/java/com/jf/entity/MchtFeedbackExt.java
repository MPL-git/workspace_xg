package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtFeedbackExt extends MchtFeedback {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
