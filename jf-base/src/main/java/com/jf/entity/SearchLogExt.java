package com.jf.entity;

import com.jf.common.constant.Constant;

public class SearchLogExt extends SearchLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
