package com.yang.spinach.account.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.spinach.account.dao.AccountDao;
import com.yang.spinach.account.entity.Account;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.account.service.impl.Account
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountDao accountDao;

	@Override
	public Integer saveAccount(Account entity) throws Exception {
		return accountDao.saveAccount(entity);
	}

	@Override
	public Integer updateAccountById(Account entity) throws Exception {
		return accountDao.updateAccountById(entity);
	}

	@Override
	public Account selectAccountById(Long id) throws Exception {
		return accountDao.selectAccountById(id);
	}

	@Override
	public Account selectByUsername(String username, Long userType) {
		return accountDao.selectByUsername(username, userType);
	}

}
