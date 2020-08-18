package com.jf.common.enums;

/**
 * 营销活动报名规则活动类型
 */
public enum ActivityRuleConfigurationEnum {

	YHH(1, "11"),
	CXSX(2, "12"),
	CLNZ(3, "13"),
	DMTH(4, "14"),
	YDXF(5, "15"),
	CLMZ(6, "16"),
	SPCS(7, "17"),
	MRHD(8, "18"),
	DXSCY(9,"19"),
	LJZX(10, "20"),
	JFZP(11, "21");

	private int code;
	private String value;

	ActivityRuleConfigurationEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	public static String getValueByCode(Integer code) {
		for (ActivityRuleConfigurationEnum value : ActivityRuleConfigurationEnum.values()) {
			if (code == value.getCode()) {
				return value.getValue();
			}
		}
		return null;
	}
}