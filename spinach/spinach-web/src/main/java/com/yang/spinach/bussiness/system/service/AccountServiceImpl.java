package com.yang.spinach.bussiness.system.service;

import com.yang.spinach.bussiness.system.dao.AccountDao;
import com.yang.spinach.bussiness.system.dao.RoleDao;
import com.yang.spinach.bussiness.system.entity.Account;
import com.yang.spinach.common.utils.Const;
import com.yang.spinach.frame.pagePlugin.Pagination;
import com.yang.spinach.frame.service.impl.BaseServiceImpl;
import com.yang.spinach.frame.utils.ValidatorUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.bussiness.system.entity.impl.Account
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account>
		implements AccountService {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Account> listPage(Account account, Pagination pagination) {
		return accountDao.listPage(account, pagination);
	}

	@Override
	public Set<String> findPermissions(String username) {
		return accountDao.findPermissions(username);
	}

	@Override
	public Account selectByUsername(String username) {
		return accountDao.selectByUsername(username);
	}

	@Override
	public Map<String, Object> saveAccount(Account account, Long roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Integer i = 0;
			if (StringUtils.isNotBlank(account.getPassword())) {
				account.setPassword(new Md5Hash(account.getPassword())
						.toString());
			}
			if (account.getId() != null && account.getId() != 0) {
				i = updateByPrimaryKey(account);
			} else {
				String message = ValidatorUtils.validate(account);
				if (StringUtils.isBlank(message)) {
					Account a = selectByUsername(account
							.getUsername());
					if (a != null) {
						map.put(Const.MSG, "用户名已存在");
						return map;
					}
					i = save(account);
					System.out.println(account.getId()+"...........");
				} else {
					map.put(Const.MSG, message);
				}
			}
			if(roleId!=null){
				roleDao.delRoleBYId(account.getId());
				roleDao.bathSaveRole(roleId, account.getId());
			}
			if (i > 0) {
				map.put(Const.STATUS, 0);
				map.put(Const.MSG, "保存成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

}
