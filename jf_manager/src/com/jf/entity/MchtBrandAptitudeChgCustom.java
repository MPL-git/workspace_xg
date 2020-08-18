package com.jf.entity;

import java.util.List;
import java.util.Map;


public class MchtBrandAptitudeChgCustom extends MchtBrandAptitudeChg{
	
	private List<MchtBrandAptitudePicChg> mchtBrandAptitudePicChgs;
	
	private List<Map<String, Object>> mchtBrandAptitudePicChgList;

	public List<MchtBrandAptitudePicChg> getMchtBrandAptitudePicChgs() {
		return mchtBrandAptitudePicChgs;
	}

	public void setMchtBrandAptitudePicChgs(
			List<MchtBrandAptitudePicChg> mchtBrandAptitudePicChgs) {
		this.mchtBrandAptitudePicChgs = mchtBrandAptitudePicChgs;
	}

	public List<Map<String, Object>> getMchtBrandAptitudePicChgList() {
		return mchtBrandAptitudePicChgList;
	}

	public void setMchtBrandAptitudePicChgList(
			List<Map<String, Object>> mchtBrandAptitudePicChgList) {
		this.mchtBrandAptitudePicChgList = mchtBrandAptitudePicChgList;
	}
	
	
}
