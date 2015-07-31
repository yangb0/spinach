package com.yang.spinach.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.account.entity.Account;
import com.yang.spinach.account.service.AccountService;
import com.yang.spinach.dict.entity.Dict;
import com.yang.spinach.dict.service.DictService;
import com.yang.spinach.frame.filter.WebContext;
import com.yang.spinach.frame.utils.Const;
import com.yang.spinach.frame.utils.ValidatorUtils;
import com.yang.spinach.frame.utils.page.Pagination;
import com.yang.spinach.role.entity.Role;
import com.yang.spinach.role.service.RoleService;

/**
 * 
 * @author yang
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.account.web.Account
 */
@Controller
@RequestMapping(value = "/user")
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
			Account a = accountService.selectAccountById(id);
			map.put(Const.STATUS, 1);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/list")
	public String list(Account account, Pagination pagination) {
		List<Account> list = accountService.listPage(account, pagination);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute(Const.PAGINATION, pagination);
		return "/user/list";
	}

	@RequiresPermissions("sys:user:add")
	@RequestMapping("/add")
	public String add(Long id) {
		List<Dict> list = dictService.selectByColumn(Const.USER_TYPR);
		Role r = new Role();
		r.setDisabled(0);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute("id", id);
		return "/user/add";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Account account, Long roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			String message = ValidatorUtils.validate(account);
			Integer i = 0;
			if (StringUtils.isBlank(message)) {
				if (account.getId() != null && account.getId() != 0) {
					i = accountService.updateAccountById(account);
				} else {
					Account a = accountService.selectByUsername(account.getUsername());
					if(a!=null){
						map.put(Const.MSG, "用户名已存在");
						return map;
					}
					account.setPassword(new Md5Hash(account.getPassword())
							.toString());
					i = accountService.saveAccount(account);
				}
				if (i > 0) {
					map.put(Const.STATUS, 0);
					map.put(Const.MSG, "保存成功");
				}
			} else {
				map.put(Const.MSG, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/editRole")
	public String editRole(Long id) {
		try {
			Role role = new Role();
			role.setDisabled(0);
			Pagination p = new Pagination();
			p.setRows(50);// 角色一般不会太多,50个应该足够了.........
			List<Role> list = roleService.listPage(role, p);
			List<Role> l = roleService.findByAccountId(id);
			for (Role r : list) {
				if (l.contains(r)) {
					r.setChecked(Const.CHECKED);
				}
			}
			WebContext.setAttribute("list", list);
			WebContext.setAttribute("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/editRole";
	}

	@RequestMapping("/saveRole")
	public String saveRole(Long id, Long[] rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			roleService.delRoleBYId(id);
			roleService.bathSaveRole(rid, id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return "redirect:/user/list";
	}
}
