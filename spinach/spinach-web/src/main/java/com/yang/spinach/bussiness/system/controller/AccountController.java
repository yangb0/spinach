package com.yang.spinach.bussiness.system.controller;

import com.yang.spinach.bussiness.system.entity.Account;
import com.yang.spinach.bussiness.system.entity.Dict;
import com.yang.spinach.bussiness.system.entity.Role;
import com.yang.spinach.bussiness.system.service.AccountService;
import com.yang.spinach.bussiness.system.service.DictService;
import com.yang.spinach.bussiness.system.service.RoleService;
import com.yang.spinach.common.filter.WebContext;
import com.yang.spinach.common.utils.Const;
import com.yang.spinach.frame.pagePlugin.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yang
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.bussiness.system.entity.web.Account
 */
@Controller
@RequestMapping(value = "user")
public class AccountController {
	@Resource
	private AccountService accountService;
	@Resource
	private DictService dictService;
	@Resource
	private RoleService roleService;

	@RequestMapping("get/{id}")
	@ResponseBody
	public Object get(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Account a = accountService.selectByPrimaryKey(id);
			List<Role> role = roleService.findByAccountId(id);
			if (!CollectionUtils.isEmpty(role)) {

			}
			map.put(Const.STATUS, 0);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	/**
	 * 用户列表主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/system/user/list";
	}

	/**
	 * 获取用户数据
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.json")
	public Object list(Account account, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Account> list = accountService.listPage(account, pagination);
		map.put("list", list);
		map.put(Const.PAGINATION, pagination);
		return map;
	}

	@RequiresPermissions("sys:user:add")
	@RequestMapping("/add")
	public String add(Long id) {
		List<Dict> list = dictService.selectByColumn(Const.DISABLED);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute("id", id);
		if (id != null) {
			Account a = accountService.selectByPrimaryKey(id);
			WebContext.setAttribute("a", a);
		}
		// 管理员用户
		WebContext.setAttribute("userType", 2);
		return "/system/user/add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Account account) {
		return accountService.saveAccount(account);
	}

	@RequestMapping("/updateUserRole")
	public String updateUserRole(Account account){
		Role r = new Role();
		r.setDisabled(0);
		List<Role> roleList = roleService.listPage(r, null);
		WebContext.setAttribute("roList", roleList);
		WebContext.setAttribute("account",account);
		if(account!=null&&account.getId()!=null){
			List<Role> userRoList = roleService.findByAccountId(account.getId());
			List<Long> userRoIds = new ArrayList<Long>();
			for(Role role:userRoList){
				userRoIds.add(role.getId());
			}
			WebContext.setAttribute("userRoIds",userRoIds);
		}
		return "/system/user/user_role";
	}

	@ResponseBody
	@RequestMapping("saveRole.json")
	public Object saveRole(Long accountId,Long[] roleIds){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		boolean f = false;
		try {
			f = roleService.bathSaveRole(accountId,roleIds);
			if(f){
				map.put(Const.STATUS,0);
				map.put(Const.MSG,"保存成功!");
			}
		}catch (Exception e){
			e.printStackTrace();
			map.put(Const.MSG,Const.DEFAULT_ERROR);
		}
		return map;
	}
}
