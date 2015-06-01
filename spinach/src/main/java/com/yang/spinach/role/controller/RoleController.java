package com.yang.spinach.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.frame.filter.WebContext;
import com.yang.spinach.frame.utils.Const;
import com.yang.spinach.frame.utils.page.Pagination;
import com.yang.spinach.role.entity.Role;
import com.yang.spinach.role.service.RoleService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.web.Role
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/list")
	public String list(Role role, Pagination pagination) {
		List<Role> list = roleService.listPage(role, pagination);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute("pagination", pagination);
		return "/role/list";
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Role a = roleService.selectRoleById(id);
			map.put("status", 1);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequiresPermissions("sys:user:add")
	@RequestMapping("/info/{id}")
	public String add(@PathVariable Long id) {
		WebContext.setAttribute("id", id);
		return "/role/info";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Integer i=0;
			if(role.getId()!=null&&role.getId()!=0){
				i = roleService.updateRoleById(role);
			}else{
				i = roleService.saveRole(role);
			}
			if (i > 0) {
				map.put("status", 0);
				map.put("data", "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}
}
