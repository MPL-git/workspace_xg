package com.jf.entity;

import com.jf.common.constant.Constant;

public class OrderViewLogExt extends OrderViewLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
