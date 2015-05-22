package com.yang.spinach.account.service;

import com.yang.spinach.account.entity.Account;


/**
 * 
 * @author <Auto generate>
 * @version  2015-04-15 13:44:42
 * @see com.yang.spinach.account.service.Account
 */
public interface AccountService {
	/**
	 *保存
	 *
	 */
	public Integer  saveAccount(Account entity ) throws Exception;
	/**
	 *修改
	 *
	 */
	public Integer updateAccountById(Account entity ) throws Exception;
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Account selectAccountById(Long id) throws Exception;
	
	Account selectByUsername(String username,Long userType);
	
}
