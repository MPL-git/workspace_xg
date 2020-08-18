package com.jf.common.enums;

/**
 * 资源位管理活动类型
 */
public enum SourceNicheTypeEnum {

	YHH(1, "1"),
	CXSX(2, "4"),
	CLNZ(3, "5"),
	DMTH(4, "6"),
	YDXF(5, "7"),
	CLMZ(6, "8"),
	SPCS(7, "9"),
	MRHD(8,"2"),
	DXSCY(9,"10"),
	JFZP(11, "13");

	private int code;
	private String value;

	SourceNicheTypeEnum(int code, String value) {
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
		for (SourceNicheTypeEnum value : SourceNicheTypeEnum.values()) {
			if (code == value.getCode()) {
				return value.getValue();
			}
		}
		return null;
	}
}