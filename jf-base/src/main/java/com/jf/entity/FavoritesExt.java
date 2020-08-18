package com.jf.entity;

import com.jf.common.constant.Constant;

public class FavoritesExt extends Favorites {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
