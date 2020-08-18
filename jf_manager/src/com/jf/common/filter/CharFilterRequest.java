package com.jf.common.filter;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.jf.common.utils.StringUtils;

public class CharFilterRequest extends HttpServletRequestWrapper {
	private Map params;

	private String[] url4EscapeViolateWord = {"/activityAreaNew/addOrUpdateActivityArea.shtml"};

	public CharFilterRequest(HttpServletRequest request, Map newParams) {
		super(request);
		this.params = newParams;
	}

	public Map getParameterMap() {
		return params;
	}

	public Enumeration getParameterNames() {
		Vector l = new Vector(params.keySet());
		return l.elements();
	}

	public String[] getParameterValues(String name) {
		String uri = this.getRequestURI();
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			String[] value = (String[]) v;
			for (int i = 0; i < value.length; i++) {
				value[i] = StringUtils.escapeHtmlAndIllegal(value[i]);

				if (value[i].contains("测试违禁词最具过滤")) {
					System.out.println(value[i]);
				}

				// 过滤违禁词
				if (url4EscapeViolateWord != null && url4EscapeViolateWord.length > 0) {
					for (int j = 0; j < url4EscapeViolateWord.length; j++) {
						if (uri.contains(url4EscapeViolateWord[j])) {
							value[i] = StringUtils.escapeViolateWord(value[i]);
						}
					}
				}
			}
			return (String[]) value;
		} else if (v instanceof String) {
			String value = (String) v;
			value = StringUtils.escapeHtmlAndIllegal(value);

			// 过滤违禁词
			if (url4EscapeViolateWord != null && url4EscapeViolateWord.length > 0) {
				for (int j = 0; j < url4EscapeViolateWord.length; j++) {
					if (uri.contains(url4EscapeViolateWord[j])) {
						value = StringUtils.escapeViolateWord(value);
					}
				}
			}

			return new String[] { (String) value };
		} else {
			return new String[] { v.toString() };
		}
	}

	public String getParameter(String name) {
		String uri = this.getRequestURI();
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				String value = strArr[0];
				value = StringUtils.escapeHtmlAndIllegal(value);

				// 过滤违禁词
				if (url4EscapeViolateWord != null && url4EscapeViolateWord.length > 0) {
					for (int j = 0; j < url4EscapeViolateWord.length; j++) {
						if (uri.contains(url4EscapeViolateWord[j])) {
							value = StringUtils.escapeViolateWord(value);
						}
					}
				}

				return value;
			} else {
				return null;
			}
		} else if (v instanceof String) {
			String value = (String) v;
			value = StringUtils.escapeHtmlAndIllegal(value);

			// 过滤违禁词
			if (url4EscapeViolateWord != null && url4EscapeViolateWord.length > 0) {
				for (int j = 0; j < url4EscapeViolateWord.length; j++) {
					if (uri.contains(url4EscapeViolateWord[j])) {
						value = StringUtils.escapeViolateWord(value);
					}
				}
			}

			return (String) value;
		} else {
			return v.toString();
		}
	}

}
