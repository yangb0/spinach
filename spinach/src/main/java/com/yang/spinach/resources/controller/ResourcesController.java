package com.yang.spinach.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.frame.shiro.ShiroSessionUtils;
import com.yang.spinach.resources.entity.Resources;
import com.yang.spinach.resources.service.ResourcesService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.web.Resources
 */
@Controller
@RequestMapping(value = "/resources")
public class ResourcesController {
	@Autowired
	private ResourcesService resourcesService;

	@ResponseBody
	@RequestMapping("/currentResources.json")
	public List<Resources> currentResources() {
		Long accountId = ShiroSessionUtils.getLoginAccount().getId();
		try {
			return resourcesService.findByAccountId(accountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
