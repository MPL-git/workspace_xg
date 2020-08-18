package com.jf.common.utils;

import javax.servlet.http.HttpSession;

import com.jf.entity.StaffBean;

public final class LoginUtil {
	
	private static String SESSION_LOGIN_STAFF = "LOGIN_STAFF";
	
	/**
	 * 获取登录人员信息
	 * @param session
	 * @return
	 */
	public static StaffBean getLoginStaff(HttpSession session) {
		return (StaffBean)session.getAttribute(SESSION_LOGIN_STAFF);
	}
	
	/**
	 * 设置登录人员信息
	 * @param session
	 * @param staffBean
	 */
	public static void setLoginStaff(HttpSession session, StaffBean staffBean) {
		session.setAttribute(SESSION_LOGIN_STAFF, staffBean);
	}
}
