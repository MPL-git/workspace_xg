package com.jf.entity;

import com.jf.common.constant.Constant;

public class SignInSettingExt extends SignInSetting {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
