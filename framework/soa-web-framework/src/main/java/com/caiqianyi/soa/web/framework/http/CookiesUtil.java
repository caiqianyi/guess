package com.caiqianyi.soa.web.framework.http;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @comment Cookies读、写、删
 * @author xhl
 */
public class CookiesUtil {
	/**
	 * 设置Cookies
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @Author xhl
	 */
	public static void addCookies(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);

		// 设置COOKIE的路径，也可以不设置
		cookie.setPath("/");

		// 设置生存期(单位：秒)，如果设置为负值的话，则为浏览器进程Cookie(内存中保存)，关闭浏览器就失效。
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie里的值
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @Author xhl
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = packCookie(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 封装Cookie
	 * 
	 * @param request
	 * @return
	 * @Author xhl
	 */
	public static Map<String, Cookie> packCookie(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 根据Cookie名称删除
	 * 
	 * @Author xhl
	 */
	public static void delCookies(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				if (name.equals(cookieName)) {
					cookies[i].setValue(null);
					cookies[i].setMaxAge(0);
					cookies[i].setPath("/");
					response.addCookie(cookies[i]);
				}
			}
		}
	}
}
