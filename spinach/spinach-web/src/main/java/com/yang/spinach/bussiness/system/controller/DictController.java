package com.yang.spinach.bussiness.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.bussiness.system.entity.Dict;
import com.yang.spinach.bussiness.system.service.DictService;
import com.yang.spinach.common.filter.WebContext;
import com.yang.spinach.common.utils.Const;
import com.yang.spinach.frame.pagePlugin.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yang.spinach.bussiness.system.entity.web.Dict
 */
@Controller
@RequestMapping(value = "dict")
public class DictController {
	@Resource
	private DictService dictService;

	@RequestMapping("/list")
	public String list() {
		return "/system/dict/list";
	}
	
	@ResponseBody
	@RequestMapping("/list.json")
	public Object list(Pagination pagination, Dict dict) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Dict> list = dictService.listPage(pagination, dict);
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
			Dict a = dictService.selectByPrimaryKey(id);
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
		List<Dict> list = dictService.selectByColumn(Const.DISABLED);
		WebContext.setAttribute("list", list);
		if(id!=null){
			Dict dict = dictService.selectByPrimaryKey(id);
			WebContext.setAttribute("dict", dict);
		}
		return "/system/dict/add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Dict dict) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Integer i ;
			if (dict.getId() != null && dict.getId() != 0) {
				i = dictService.updateByPrimaryKey(dict);
			} else {
				i = dictService.save(dict);
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
