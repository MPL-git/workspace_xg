package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberSignInSettingExt extends MemberSignInSetting {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
