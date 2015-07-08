package com.yang.spinach.dict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.dict.entity.Dict;
import com.yang.spinach.dict.service.DictService;
import com.yang.spinach.frame.filter.WebContext;
import com.yang.spinach.frame.utils.Const;
import com.yang.spinach.frame.utils.page.Pagination;
import com.yang.spinach.role.entity.Role;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yang.spinach.web.Dict
 */
@Controller
@RequestMapping(value = "/dict")
public class DictController {
	@Resource
	private DictService dictService;

	@RequestMapping("/list")
	public String list(Pagination pagination, Dict dict) {
		List<Dict> list = dictService.listPage(pagination, dict);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute(Const.PAGINATION, pagination);
		return "/dict/list";
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Dict a = dictService.selectDictById(id);
			map.put(Const.STATUS, 0);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequiresPermissions("sys:dict:add")
	@RequestMapping("/add")
	public String add(Long id) {
		WebContext.setAttribute("id", id);
		return "/dict/add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Dict dict) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Integer i = 0;
			if (dict.getId() != null && dict.getId() != 0) {
				i = dictService.updateDictById(dict);
			} else {
				i = dictService.saveDict(dict);
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
}
