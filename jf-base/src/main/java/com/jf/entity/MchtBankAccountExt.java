package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBankAccountExt extends MchtBankAccount {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
