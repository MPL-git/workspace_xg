package com.jf.entity;

import com.jf.common.constant.Constant;

public class ContractRenewLogExt extends ContractRenewLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
