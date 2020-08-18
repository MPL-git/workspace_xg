package com.jf.entity;

import java.util.List;
import java.util.Map;


public class MchtBrandAptitudeCustom extends MchtBrandAptitude{
	private List<Map<String, Object>> mchtBrandAptitudePics;
	private List<MchtBrandAptitudePic> mchtBrandAptitudePicList;

	public List<Map<String, Object>> getMchtBrandAptitudePics() {
		return mchtBrandAptitudePics;
	}

	public void setMchtBrandAptitudePics(
			List<Map<String, Object>> mchtBrandAptitudePics) {
		this.mchtBrandAptitudePics = mchtBrandAptitudePics;
	}

	public List<MchtBrandAptitudePic> getMchtBrandAptitudePicList() {
		return mchtBrandAptitudePicList;
	}

	public void setMchtBrandAptitudePicList(
			List<MchtBrandAptitudePic> mchtBrandAptitudePicList) {
		this.mchtBrandAptitudePicList = mchtBrandAptitudePicList;
	}
	
	
}
