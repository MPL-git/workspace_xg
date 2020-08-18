package com.jf.entity;

import com.jf.common.constant.Constant;

public class TaskSendMemberExt extends TaskSendMember {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
