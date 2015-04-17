package com.yang.spinach.frame.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param request 要注入的request
     * @param response 要注入的response
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                             ServletException {
        /*
         * HttpServletResponse httpResponse = (HttpServletResponse) response;
         * httpResponse.setHeader("Pragma","No-cache"); httpResponse.setHeader("Cache-Control","no-cache");
         * httpResponse.setHeader("Expires","0");
         */
        try {
            WebContext.registry((HttpServletRequest) request, (HttpServletResponse) response);
            chain.doFilter(request, response);
        } finally {
            WebContext.release();
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
