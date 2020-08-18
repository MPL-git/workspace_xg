package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtFeedbackPicExt extends MchtFeedbackPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
