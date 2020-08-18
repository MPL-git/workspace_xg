package com.jf.entity;

import com.jf.common.constant.Constant;

public class CustomerServiceRecordsExt extends CustomerServiceRecords {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
