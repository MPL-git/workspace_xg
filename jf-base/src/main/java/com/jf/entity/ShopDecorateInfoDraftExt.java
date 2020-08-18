package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopDecorateInfoDraftExt extends ShopDecorateInfoDraft {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
