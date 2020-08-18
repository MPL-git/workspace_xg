package com.jf.entity;

import com.jf.common.constant.Constant;

public class DeliveryOvertimeCnfExt extends DeliveryOvertimeCnf {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
