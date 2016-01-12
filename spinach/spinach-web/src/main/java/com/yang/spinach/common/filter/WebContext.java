package com.yang.spinach.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * 
 *
 */
public class WebContext {

	private static final ThreadLocal<Object[]> WEBCONTEXT_LOCAL = new ThreadLocal<Object[]>();

	/**
	 * 得到当前request
	 * 
	 * @return request
	 */
	public static HttpServletRequest currentRequest() {
		Object[] locals = WEBCONTEXT_LOCAL.get();
		return locals == null ? null : (HttpServletRequest) locals[0];
	}

	/**
	 * 得到当前response
	 * 
	 * @return response
	 */
	public static HttpServletResponse currentResponse() {
		Object[] locals = WEBCONTEXT_LOCAL.get();
		return locals == null ? null : (HttpServletResponse) locals[1];
	}

	public static void setAttribute(String name, Object o) {
		currentRequest().setAttribute(name, o);
	}

	/**
	 * 在进入WebContextFilter过滤器时，将request和response注册到ThreadLocal中
	 * 
	 * @param request
	 *            要注入的request
	 * @param response
	 *            要注入的response
	 * @see com.jointown.core.web.filter.xhbs.util.web.filter.WebContextFilter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public static void registry(HttpServletRequest request,
			HttpServletResponse response) {
		Object[] locals = new Object[] { request, response };
		WEBCONTEXT_LOCAL.set(locals);
	}

	/**
	 * 在WebContextFilter过滤器完成时，将request和response从ThreadLocal中清除
	 */
	public static void release() {
		WEBCONTEXT_LOCAL.set(null);
	}
}
