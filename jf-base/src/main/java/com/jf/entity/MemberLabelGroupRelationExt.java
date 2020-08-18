package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberLabelGroupRelationExt extends MemberLabelGroupRelation {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
