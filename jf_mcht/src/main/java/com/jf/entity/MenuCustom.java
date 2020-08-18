package com.jf.entity;

import java.util.List;


public class MenuCustom extends Menu{
	private List<Menu> subMenus;

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

}