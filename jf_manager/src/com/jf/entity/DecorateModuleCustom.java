package com.jf.entity;

import java.util.List;

public class DecorateModuleCustom extends DecorateModule{
	private List<String> pics;
	private int count;
	private String firstLevelName;
	private String secondLevelName;
	private String brandNames;
	private List<ModuleItemCustom> moduleItemCustoms;
	private List<ProductCustom> productCustoms;
	private List<ActivityCustom> activityCustoms;
	private List<SeckillTime> seckillTimes;
	private List<BgModuleCategoryCustom> bgModuleCategoryCustoms;
	private List<TopFieldModuleField> topFieldModuleFields;
	private List<ModuleNavigation> moduleNavigationFirstLine;
	private List<ModuleNavigation> moduleNavigationSecondLine;
	private List<ModuleNavigation> moduleNavigationThirdLine;
	private List<CouponModuleTime> couponModuleTimeList;

	public List<ModuleNavigation> getModuleNavigationFirstLine() {
		return moduleNavigationFirstLine;
	}

	public void setModuleNavigationFirstLine(
			List<ModuleNavigation> moduleNavigationFirstLine) {
		this.moduleNavigationFirstLine = moduleNavigationFirstLine;
	}

	public List<ModuleNavigation> getModuleNavigationSecondLine() {
		return moduleNavigationSecondLine;
	}

	public void setModuleNavigationSecondLine(
			List<ModuleNavigation> moduleNavigationSecondLine) {
		this.moduleNavigationSecondLine = moduleNavigationSecondLine;
	}

	public List<ModuleNavigation> getModuleNavigationThirdLine() {
		return moduleNavigationThirdLine;
	}

	public void setModuleNavigationThirdLine(
			List<ModuleNavigation> moduleNavigationThirdLine) {
		this.moduleNavigationThirdLine = moduleNavigationThirdLine;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFirstLevelName() {
		return firstLevelName;
	}

	public void setFirstLevelName(String firstLevelName) {
		this.firstLevelName = firstLevelName;
	}

	public String getSecondLevelName() {
		return secondLevelName;
	}

	public void setSecondLevelName(String secondLevelName) {
		this.secondLevelName = secondLevelName;
	}

	public String getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}

	public List<ModuleItemCustom> getModuleItemCustoms() {
		return moduleItemCustoms;
	}

	public void setModuleItemCustoms(List<ModuleItemCustom> moduleItemCustoms) {
		this.moduleItemCustoms = moduleItemCustoms;
	}

	public List<ProductCustom> getProductCustoms() {
		return productCustoms;
	}

	public void setProductCustoms(List<ProductCustom> productCustoms) {
		this.productCustoms = productCustoms;
	}

	public List<ActivityCustom> getActivityCustoms() {
		return activityCustoms;
	}

	public void setActivityCustoms(List<ActivityCustom> activityCustoms) {
		this.activityCustoms = activityCustoms;
	}

	public List<SeckillTime> getSeckillTimes() {
		return seckillTimes;
	}

	public void setSeckillTimes(List<SeckillTime> seckillTimes) {
		this.seckillTimes = seckillTimes;
	}

	public List<BgModuleCategoryCustom> getBgModuleCategoryCustoms() {
		return bgModuleCategoryCustoms;
	}

	public void setBgModuleCategoryCustoms(
			List<BgModuleCategoryCustom> bgModuleCategoryCustoms) {
		this.bgModuleCategoryCustoms = bgModuleCategoryCustoms;
	}

	public List<TopFieldModuleField> getTopFieldModuleFields() {
		return topFieldModuleFields;
	}

	public void setTopFieldModuleFields(
			List<TopFieldModuleField> topFieldModuleFields) {
		this.topFieldModuleFields = topFieldModuleFields;
	}

	public List<CouponModuleTime> getCouponModuleTimeList() {
		return couponModuleTimeList;
	}

	public void setCouponModuleTimeList(List<CouponModuleTime> couponModuleTimeList) {
		this.couponModuleTimeList = couponModuleTimeList;
	}
}
