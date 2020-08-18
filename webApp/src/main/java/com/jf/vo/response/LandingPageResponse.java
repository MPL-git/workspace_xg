package com.jf.vo.response;

import java.util.List;

/**
 * @author Pengl
 * @create 2020-06-28 上午 10:26
 */
public class LandingPageResponse {

	private String editorRecommend;
	private String applicationInformation;
	private List<String> landingPagePicList;

	public String getEditorRecommend() {
		return editorRecommend;
	}

	public void setEditorRecommend(String editorRecommend) {
		this.editorRecommend = editorRecommend;
	}

	public String getApplicationInformation() {
		return applicationInformation;
	}

	public void setApplicationInformation(String applicationInformation) {
		this.applicationInformation = applicationInformation;
	}

	public List<String> getLandingPagePicList() {
		return landingPagePicList;
	}

	public void setLandingPagePicList(List<String> landingPagePicList) {
		this.landingPagePicList = landingPagePicList;
	}
}
