package com.jf.entity;

import java.util.List;




public class DecorateAreaCustom extends DecorateArea{
	private List<DecorateModuleCustom> decorateModuleCustoms;

	public List<DecorateModuleCustom> getDecorateModuleCustoms() {
		return decorateModuleCustoms;
	}

	public void setDecorateModuleCustoms(
			List<DecorateModuleCustom> decorateModuleCustoms) {
		this.decorateModuleCustoms = decorateModuleCustoms;
	}

}
