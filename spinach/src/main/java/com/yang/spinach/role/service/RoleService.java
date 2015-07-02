package com.yang.spinach.role.service;

import java.util.List;

import com.yang.spinach.frame.utils.page.Pagination;
import com.yang.spinach.role.entity.Role;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.service.Role
 */
public interface RoleService {
	/**
	 * 保存
	 *
	 */
	public Integer saveRole(Role entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateRoleById(Role entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role selectRoleById(Object id) throws Exception;

	List<Role> listPage(Role role, Pagination pagination);

	/**
	 * 根据角色id删除该角色所有权限
	 * 
	 * @param id
	 * @return
	 */
	public Integer delPermBYId(Long id);

	/**
	 * 根据用户删除对应角色
	 * @param id
	 * @return
	 */
	public Integer delRoleBYId(Long id);

	/**
	 * 给角色设置权限
	 * 
	 * @param rid
	 * @param id
	 * @return
	 */
	public Integer bathSavePerm(Long[] rid, Long id);

	/**
	 * 给用户设置角色
	 * @param rid
	 * @param id
	 * @return
	 */
	public Integer bathSaveRole(Long[] rid, Long id);

	/**
	 * 查找用户拥有角色
	 * @param id
	 * @return
	 */
	public List<Role> findByAccountId(Long id);
}
