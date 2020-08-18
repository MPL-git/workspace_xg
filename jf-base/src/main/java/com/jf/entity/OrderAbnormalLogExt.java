package com.jf.entity;

import com.jf.common.constant.Constant;

public class OrderAbnormalLogExt extends OrderAbnormalLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
