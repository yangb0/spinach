package com.yang.spinach.bussiness.system.service;

import com.yang.spinach.bussiness.system.dao.RoleDao;
import com.yang.spinach.bussiness.system.entity.Role;
import com.yang.spinach.frame.pagePlugin.Pagination;
import com.yang.spinach.frame.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.bussiness.system.entity.service.impl.Role
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> listPage(Role role, Pagination pagination) {
		return roleDao.listPage(role, pagination);
	}

	@Override
	public Integer delPermBYId(Long id) {
		return roleDao.delPermBYId(id);
	}

	@Override
	public Integer bathSavePerm(Long[] rid, Long id) {
		return roleDao.bathSavePerm(rid, id);
	}

	@Override
	public List<Role> findByAccountId(Long id) {
		return roleDao.findByAccountId(id);
	}

	@Override
	public Integer delRoleBYId(Long id) {
		return roleDao.delRoleBYId(id);
	}

	@Override
	public Integer bathSaveRole(Long rid, Long id) {
		return roleDao.bathSaveRole(rid, id);
	}


	@Override
	public Boolean updatePremission(Long roleId, Long[] resourcesIds) {
		this.delPermBYId(roleId);
		if(resourcesIds!=null&&resourcesIds.length>0){
			this.bathSavePerm(resourcesIds,roleId);
		}
		return true;
	}
}
