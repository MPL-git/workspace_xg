package com.jf.entity;

import java.util.List;


public class InterventionProcessCustom extends InterventionProcess{

	private List<InterventionProcessPic> interventionProcessPics;

	public List<InterventionProcessPic> getInterventionProcessPics() {
		return interventionProcessPics;
	}

	public void setInterventionProcessPics(
			List<InterventionProcessPic> interventionProcessPics) {
		this.interventionProcessPics = interventionProcessPics;
	}
	
}

