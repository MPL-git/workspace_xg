package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberStatisticalInfoExt extends MemberStatisticalInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
