package com.jf.entity;

import java.util.ArrayList;
import java.util.List;



public class AppealLogCustom extends AppealLog{
	
	private List<String> pics = new ArrayList<String>();

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	
	
}
