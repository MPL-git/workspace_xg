package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberFeedbackPicExt extends MemberFeedbackPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
