package com.yang.spinach.role.service;

import com.yang.spinach.role.entity.Role;


/**
 * 
 * @author <Auto generate>
 * @version  2015-05-21 23:16:42
 * @see com.yang.spinach.service.Role
 */
public interface RoleService {
	/**
	 *保存
	 *
	 */
	public Integer  saveRole(Role entity ) throws Exception;
	/**
	 *修改
	 *
	 */
	public Integer updateRoleById(Role entity ) throws Exception;
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role selectRoleById(Object id) throws Exception;
	
	
	
}
