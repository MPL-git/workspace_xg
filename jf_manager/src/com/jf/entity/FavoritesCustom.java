package com.jf.entity;


public class FavoritesCustom extends Favorites{
	private Integer firstProductTypeId;
	private String secondProductTypeIdGroup;
	private String secondProductTypeNameGroup;

    public Integer getFirstProductTypeId() {
        return firstProductTypeId;
    }

    public void setFirstProductTypeId(Integer firstProductTypeId) {
        this.firstProductTypeId = firstProductTypeId;
    }
	public String getSecondProductTypeIdGroup() {
		return secondProductTypeIdGroup;
	}
	public void setSecondProductTypeIdGroup(String secondProductTypeIdGroup) {
		this.secondProductTypeIdGroup = secondProductTypeIdGroup;
	}
	public String getSecondProductTypeNameGroup() {
		return secondProductTypeNameGroup;
	}
	public void setSecondProductTypeNameGroup(String secondProductTypeNameGroup) {
		this.secondProductTypeNameGroup = secondProductTypeNameGroup;
	}
}
