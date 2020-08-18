package com.jf.entity;

import com.jf.common.constant.Constant;

public class ImpeachMemberProofExt extends ImpeachMemberProof {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
