package com.jf.entity;

import com.jf.common.constant.Constant;

public class SmsTemplateExt extends SmsTemplate {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
