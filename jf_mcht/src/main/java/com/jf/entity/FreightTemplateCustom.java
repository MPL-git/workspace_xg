package com.jf.entity;

import java.util.List;


public class FreightTemplateCustom extends FreightTemplate{
	
	private List<ProvinceFreightCustom> provinceFreightCustoms;

	public List<ProvinceFreightCustom> getProvinceFreightCustoms() {
		return provinceFreightCustoms;
	}

	public void setProvinceFreightCustoms(
			List<ProvinceFreightCustom> provinceFreightCustoms) {
		this.provinceFreightCustoms = provinceFreightCustoms;
	}
	
	
}
