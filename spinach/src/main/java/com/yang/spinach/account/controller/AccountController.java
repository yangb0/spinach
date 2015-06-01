package com.yang.spinach.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eclipse.jetty.util.security.Credential.MD5;
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

	@RequestMapping("get/{id}")
	@ResponseBody
	public Object get(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Account a = accountService.selectAccountById(id);
			map.put("status", 1);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/list")
	public String list(Account account, Pagination pagination) {
		List<Account> list = accountService.listPage(account, pagination);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute("pagination", pagination);
		return "/user/list";
	}

	@RequiresPermissions("sys:user:add")
	@RequestMapping("/info/{userId}")
	public String add(@PathVariable Long userId) {
		List<Dict> list = dictService.selectByColumn(Const.USER_TYPR);
		WebContext.setAttribute("list", list);
		WebContext.setAttribute("userId", userId);
		return "/user/info";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Account account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			String message = ValidatorUtils.validate(account);
			Integer i = 0;
			if (StringUtils.isBlank(message)) {
				if (account.getId() != null && account.getId() != 0) {
					i = accountService.updateAccountById(account);
				} else {
					account.setPassword(MD5.digest(account.getPassword()));
					i = accountService.saveAccount(account);
				}
				if(i>0){
					map.put("status", 0);
					map.put("msg", "保存成功");
				}
			} else {
				map.put("msg", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}
}
