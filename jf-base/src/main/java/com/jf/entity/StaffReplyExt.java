package com.jf.entity;

import com.jf.common.constant.Constant;

public class StaffReplyExt extends StaffReply {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
