package com.jf.common.ext.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hdl on 2017/4/8 0008.
 * @author hdl
 */
public class CookieUtil {

    /**
     * Get cookie value by cookie name.
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, name, null);
    }

    /**
     * Get cookie value by cookie name.
     */
    public static String getCookie(HttpServletRequest request, String name, String defaultValue) {
        Cookie cookie = getCookieObject(request, name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name, Integer defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     */
    public static Cookie getCookieObject(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * Set Cookie to response.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path see Cookie.setPath(String)
     * @param domain the domain name within which this cookie is visible; form is according to RFC 2109
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (domain != null)
            cookie.setDomain(domain);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * Set Cookie with path = "/".
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        setCookie(response, name, value, maxAgeInSeconds, "/", null);
    }

    /**
     * Remove Cookie with path = "/".
     */
    public static void removeCookie(HttpServletResponse response,String name) {
        setCookie(response, name, null, 0, "/", null);
    }
}
