package com.jf.entity;

import com.jf.common.constant.Constant;

public class CashTransferExt extends CashTransfer {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
