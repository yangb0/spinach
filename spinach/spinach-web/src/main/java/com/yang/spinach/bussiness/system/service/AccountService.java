package com.yang.spinach.bussiness.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yang.spinach.bussiness.system.entity.Account;
import com.yang.spinach.frame.pagePlugin.Pagination;
import com.yang.spinach.frame.service.BaseService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.bussiness.system.entity.Account
 */
public interface AccountService extends BaseService<Account>{

	Account selectByUsername(String username);

	List<Account> listPage(Account account, Pagination pagination);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	Set<String> findPermissions(String username);
	
	public Map<String,Object> saveAccount(Account account,Long roleId);
}
