package com.yang.spinach.role.service;


import com.yang.spinach.role.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.spinach.role.dao.RoleDao;



/**
 * 
 * @author <Auto generate>
 * @version  2015-05-21 23:16:42
 * @see com.yang.spinach.service.impl.Role
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
    private RoleDao roleDao;
   
    @Override
	public Integer  saveRole(Role entity ) throws Exception{
	       return roleDao.saveRole(entity);
	}

	
	@Override
    public Integer updateRoleById(Role entity ) throws Exception{
	return roleDao.updateRoleById(entity);
    }
    @Override
	public Role selectRoleById(Object id) throws Exception{
	 return roleDao.selectRoleById(id);
	}
	


}
