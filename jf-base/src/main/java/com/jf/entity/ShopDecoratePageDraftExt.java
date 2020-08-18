package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopDecoratePageDraftExt extends ShopDecoratePageDraft {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
