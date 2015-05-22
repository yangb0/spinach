package com.yang.spinach.account.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yang.spinach.account.entity.Account;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.account.dao.Account
 */
@Repository
public interface AccountDao {
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

	/**
	 * 根据用户名和用户类型查找用户
	 * 
	 * @param username
	 * @param userType
	 * @return
	 */
	Account selectByUsername(@Param("username") String username,
			@Param("userType") Long userType);
}
