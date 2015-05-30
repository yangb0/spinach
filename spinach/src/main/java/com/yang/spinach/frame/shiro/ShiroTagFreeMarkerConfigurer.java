package com.yang.spinach.frame.shiro;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.dreampie.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;

public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();  
		this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}
