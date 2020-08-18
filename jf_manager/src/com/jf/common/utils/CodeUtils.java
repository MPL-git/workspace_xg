package com.jf.common.utils;

import java.io.UnsupportedEncodingException;

public class CodeUtils {

	public static String decode(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO8859-1"),"UTF-8");
	}
}
