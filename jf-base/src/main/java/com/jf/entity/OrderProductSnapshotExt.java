package com.jf.entity;

import com.jf.common.constant.Constant;

public class OrderProductSnapshotExt extends OrderProductSnapshot {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
