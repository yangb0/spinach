package com.yang.spinach.bussiness.system.controller;

import com.yang.spinach.bussiness.system.entity.Dict;
import com.yang.spinach.bussiness.system.entity.Resources;
import com.yang.spinach.bussiness.system.entity.Role;
import com.yang.spinach.bussiness.system.service.DictService;
import com.yang.spinach.bussiness.system.service.ResourcesService;
import com.yang.spinach.bussiness.system.service.RoleService;
import com.yang.spinach.common.filter.WebContext;
import com.yang.spinach.common.utils.Const;
import com.yang.spinach.frame.pagePlugin.Pagination;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.bussiness.system.entity.web.Role
 */
@Controller
@RequestMapping(value = "role")
public class RoleController {
	@Resource
	private RoleService roleService;
	@Resource
	ResourcesService resourcesService;
	@Resource
	private DictService dictService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "/system/role/list";
	}

	@ResponseBody
	@RequestMapping("/list.json")
	public Object list(Role role, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role> list = roleService.listPage(role, pagination);
		map.put("list", list);
		map.put(Const.PAGINATION, pagination);
		return map;
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Role a = roleService.selectByPrimaryKey(id);
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
		List<Dict> list = dictService.selectByColumn(Const.DISABLED);
		WebContext.setAttribute("list", list);
		if(id!=null){
			Role r = roleService.selectByPrimaryKey(id);
			WebContext.setAttribute("role", r);
		}
		return "/system/role/add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Integer i ;
			if (role.getId() != null && role.getId() != 0) {
				i = roleService.updateByPrimaryKey(role);
			} else {
				i = roleService.save(role);
			}
			if ( i!=null&&i > 0) {
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
		WebContext.setAttribute("id", id);
		return "/system/role/editPerm";
	}

	@ResponseBody
	@RequestMapping("rolePermission.json")
	public Object rolePermission(Long id){
		Map<String,Object> map = new HashMap<String, Object>();
		Resources resource = new Resources();
		resource.setDisabled(0);
		List<Resources> list = resourcesService.list(resource);
		List<Resources> l = resourcesService.findByRoleId(id);
		for (Resources r : list) {
			if (l.contains(r)) {
				r.setChecked(Const.CHECKED);
			}
		}
		map.put("list", list);
		return map;
	}

	@ResponseBody
	@RequestMapping("/savePerm")
	public Object savePerm(Long id, Long[] rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		map.put(Const.MSG, Const.DEFAULT_ERROR);
		try {
			boolean f = roleService.updatePremission(id,rid);
			if(f){
				map.put(Const.STATUS,0);
				map.put(Const.MSG,"保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
