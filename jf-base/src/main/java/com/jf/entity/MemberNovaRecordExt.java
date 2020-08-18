package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberNovaRecordExt extends MemberNovaRecord {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
