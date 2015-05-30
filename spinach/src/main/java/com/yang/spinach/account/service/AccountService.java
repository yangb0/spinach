package com.yang.spinach.account.service;

import java.util.List;
import java.util.Set;

import com.yang.spinach.account.entity.Account;
import com.yang.spinach.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.account.service.Account
 */
public interface AccountService {
	/**
	 * 保存
	 *
	 */
	public Integer saveAccount(Account entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateAccountById(Account entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Account selectAccountById(Long id) throws Exception;

	Account selectByUsername(String username, Long userType);

	List<Account> listPage(Account account, Pagination pagination);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	Set<String> findPermissions(String username, Long type);
}
