package com.jf.common.ext.exception;


import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 断言
 * 
 * @author hdl
 *
 */
public class Assert {

	/**
	 * 不为空声明
	 * 
	 * @param argument
	 * @param errorMessage
	 */
	public static void notBlank(final String argument, final String errorMessage) {
		if (StringUtils.isBlank(argument)) {
			fail(errorMessage);
		}
	}

	/**
	 * 不为null声明
	 * 
	 * @param argument
	 * @param errorMessage
	 */
	public static void notNull(final Object argument, final String errorMessage) {
		if (argument == null) {
			fail(errorMessage);
		}
	}

	/**
	 * 列表不为空
	 * 
	 * @param list
	 * @param errorMessage
	 */
	public static <T> void notEmpty(List<T> list, String errorMessage) {
		if (list == null || list.isEmpty()) {
			fail(errorMessage);
		}
	}

	// //////////////////////////////////声明为不等

	public static void notZero(Long value, String message) {
		if (value == null || value == 0l) {
			fail(message);
		}
	}

	public static void notZero(Integer value, String message) {
		if (value == null || value == 0) {
			fail(message);
		}
	}

	public static void notZero(Float value, String message) {
		if (value == null || Float.compare(value, 0f) == 0) {
			fail(message);
		}
	}

	public static void notZero(Double value, String message) {
		if (value == null || Double.compare(value, 0d) == 0) {
			fail(message);
		}
	}

	public static void notEquals(Long actual, Long unexpected, String message) {
		if (actual == null || unexpected == null || actual == unexpected) {
			fail(message);
		}
	}

	public static void notEquals(Integer actual, Integer unexpected, String message) {
		if (actual == null || unexpected == null || actual == unexpected) {
			fail(message);
		}
	}

	public static void notEquals(Float actual, Float unexpected, String message) {
		if (actual == null || unexpected == null || Float.compare(actual, unexpected) == 0) {
			fail(message);
		}
	}

	public static void notEquals(Double actual, Double unexpected, String message) {
		if (actual == null || unexpected == null || Double.compare(actual, unexpected) == 0) {
			fail(message);
		}
	}

	// //////////////////////////////////声明为相等

	public static void equals(Integer actual, Integer expected, String message) {
		if (actual == null || expected == null || actual != expected) {
			fail(message);
		}
	}

	public static void equals(Long actual, Long expected, String message) {
		if (actual == null || expected == null || actual != expected) {
			fail(message);
		}
	}

	public static void equals(Double actual, Double expected, String message) {
		if (actual == null || expected == null || Double.compare(actual, expected) != 0) {
			fail(message);
		}
	}

	public static void equals(Float actual, Float expected, String message) {
		if (actual == null || expected == null || Float.compare(actual, expected) != 0) {
			fail(message);
		}
	}

	private static void fail(String message) {
		if (StringUtils.isBlank(message)) {
			throw new BizException();
		}

		throw new BizException(message);
	}

	public static void notFalse(Boolean value, String message) {
		if (value == null || !value) {
			fail(message);
		}
	}

	public static void notTrue(Boolean value, String message) {
		if (value == null || value) {
			fail(message);
		}
	}

	/**
	 * 之间（含边界）
	 * 
	 * @param value
	 *            值
	 * @param least
	 *            至少
	 * @param most
	 *            至多
	 * @param leastMessage
	 *            小于至少值时信息
	 * @param mostMessage
	 *            大于至多值时信息
	 */
	public static void between(Integer value, Integer least, Integer most, String leastMessage, String mostMessage) {
		if(value == null || least == null || most == null)	fail(leastMessage);

		if (value < least) {
			fail(leastMessage);

		} else if (value > most) {
			fail(mostMessage);
		}
	}

	/**
	 * 之间（含边界）
	 * 
	 * @param value
	 *            值
	 * @param least
	 *            至少
	 * @param most
	 *            至多
	 * @param message
	 *            小于至少值或大于至多值时信息
	 */
	public static void between(Integer value, Integer least, Integer most, String message) {
		if(value == null || least == null || most == null)	fail(message);
		if (value < least || value > most) {
			fail(message);
		}
	}

	/**
	 * 小于或等于
	 * 
	 * @param value
	 *            值
	 * @param most
	 *            最大值
	 * @param message
	 *            信息
	 */
	public static void lessOrEquals(Integer value, Integer most, String message) {
		if(value == null || most == null)	fail(message);
		if (value > most) {
			fail(message);
		}
	}

	/**
	 * 小于
	 * 
	 * @param value
	 * @param most
	 * @param message
	 */
	public static void lessThan(Integer value, Integer most, String message) {
		if(value == null || most == null)	fail(message);
		if (value >= most) {
			fail(message);
		}
	}

	/**
	 * 大于或等于
	 * 
	 * @param value
	 * @param least
	 * @param message
	 */
	public static void moreOrEquals(Integer value, Integer least, String message) {
		if(value == null || least == null)	fail(message);
		if (value < least) {
			fail(message);
		}
	}

	/**
	 * 大于
	 * 
	 * @param value
	 * @param least
	 * @param message
	 */
	public static void moreThan(Integer value, Integer least, String message) {
		if(value == null || least == null)	fail(message);
		if (value <= least) {
			fail(message);
		}
	}
	
	public static void moreThanZero(Integer value, String message) {
		moreThan(value, 0, message);
	}
	
	public static void moreThanZero(Long value, String message) {
		if (value == null || value <= 0L) {
			fail(message);
		}
	}

}
