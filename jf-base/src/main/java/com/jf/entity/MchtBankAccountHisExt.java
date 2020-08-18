package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBankAccountHisExt extends MchtBankAccountHis {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
