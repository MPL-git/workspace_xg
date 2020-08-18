package com.jf.entity;

import com.jf.common.constant.Constant;

public class SeckillTimeExt extends SeckillTime {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
