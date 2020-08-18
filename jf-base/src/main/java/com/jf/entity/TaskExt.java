package com.jf.entity;

import com.jf.common.constant.Constant;

public class TaskExt extends Task {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}

	public boolean isNormal(){
		return "3".equals(getStatus()) || "4".equals(getStatus());
	}
}
