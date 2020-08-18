package com.jf.entity;

import com.jf.common.constant.Constant;

public class NovaStrategyExt extends NovaStrategy {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
