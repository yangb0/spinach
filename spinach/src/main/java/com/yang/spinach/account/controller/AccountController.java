package com.yang.spinach.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.account.entity.Account;
import com.yang.spinach.account.service.AccountService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.account.web.Account
 */
@Controller
@RequestMapping(value = "/user")
public class AccountController {
	@Resource
	private AccountService accountService;

	@RequestMapping("get")
	@ResponseBody
	public Object get(Long id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Account a = accountService.selectAccountById(id);
			map.put("status", 1);
			map.put("data", a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping("/list")
	public String list(){
		return "/user/list";
	}
}
