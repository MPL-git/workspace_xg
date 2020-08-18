package com.jf.entity;

public class IntegralDtlCustom extends IntegralDtl{
    private Integer totalIntegral;//总积分数

    private String name;//名称

    private Integer integral;//进账出账积分数

	public Integer getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(Integer totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

}