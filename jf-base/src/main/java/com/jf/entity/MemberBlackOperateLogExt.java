package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberBlackOperateLogExt extends MemberBlackOperateLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
