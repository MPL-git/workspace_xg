package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtMonthlyCollectionsExt extends MchtMonthlyCollections {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
