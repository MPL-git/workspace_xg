package com.jf.entity;

import com.jf.common.constant.Constant;

public class ViolatePunishStandardExt extends ViolatePunishStandard {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
