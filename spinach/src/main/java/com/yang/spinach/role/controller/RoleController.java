package com.yang.spinach.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.frame.filter.WebContext;
import com.yang.spinach.frame.utils.Const;
import com.yang.spinach.frame.utils.page.Pagination;
import com.yang.spinach.resources.entity.Resources;
import com.yang.spinach.resources.service.ResourcesService;
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
	@Resource
	private RoleService roleService;
	@Resource
	ResourcesService resourcesService;

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
		map.put(Const.STATUS, -1);
		try {
			Role a = roleService.selectRoleById(id);
			map.put(Const.STATUS, 1);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequiresPermissions("sys:role:add")
	@RequestMapping("/add")
	public String add(Long id) {
		WebContext.setAttribute("id", id);
		return "/role/add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Integer i = 0;
			if (role.getId() != null && role.getId() != 0) {
				i = roleService.updateRoleById(role);
			} else {
				i = roleService.saveRole(role);
			}
			if (i > 0) {
				map.put(Const.STATUS, 0);
				map.put("data", "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/editPerm")
	public String editPerm(Long id) {
		try {
			Resources resource = new Resources();
			resource.setDisabled(0);
			List<Resources> list = resourcesService.list(resource);
			List<Resources> l = resourcesService.findByRoleId(id);
			for (Resources r : list) {
				if (l.contains(r)) {
					r.setChecked(Const.CHECKED);
				}
			}
			WebContext.setAttribute("list", list);
			WebContext.setAttribute("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/role/editPerm";
	}

	@RequestMapping("/savePerm")
	public String savePerm(Long id, Long[] rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			roleService.delPermBYId(id);
			roleService.bathSavePerm(rid, id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return "redirect:/role/list";
	}
}
