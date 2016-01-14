package com.yang.spinach.common.filter;

import com.yang.spinach.common.utils.Const;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @author yang
 *
 */
public class WebContextFilter implements Filter {

	@Override
	public void destroy() {

	}

	/**
	 * 在进入时将request和response注册到WebContext中，结束时清除
	 * 
	 * @param request
	 *            要注入的request
	 * @param response
	 *            要注入的response
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * HttpServletResponse httpResponse = (HttpServletResponse) response;
		 * httpResponse.setHeader("Pragma","No-cache");
		 * httpResponse.setHeader("Cache-Control","no-cache");
		 * httpResponse.setHeader("Expires","0");
		 */
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			WebContext.registry(req, resp);
			chain.doFilter(req, resp);
		} finally {
			WebContext.release();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils
						.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});

		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Date d = null;
				try {
					d = DateUtils.parseDate(text, Const.parsePatterns);
				} catch (Exception e) {
				}
				setValue(d);
			}
		});

		// Timestamp 类型转换
		binder.registerCustomEditor(Timestamp.class,
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						Date d = null;
						try {
							d = DateUtils.parseDate(text, Const.parsePatterns);
						} catch (Exception e) {
						}
						setValue(d == null ? null : new Timestamp(d.getTime()));
					}
				});
	}
}
