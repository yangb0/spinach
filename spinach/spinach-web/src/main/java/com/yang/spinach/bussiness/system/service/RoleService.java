package com.yang.spinach.bussiness.system.service;

import java.util.List;

import com.yang.spinach.bussiness.system.entity.Role;
import com.yang.spinach.frame.pagePlugin.Pagination;
import com.yang.spinach.frame.service.BaseService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.bussiness.system.entity.service.Role
 */
public interface RoleService extends BaseService<Role>{

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
	 * @param roleId
	 * @param id
	 * @return
	 */
	public Boolean bathSaveRole(Long accountId,Long[] roleIds);

	/**
	 * 查找用户拥有角色
	 * @param id
	 * @return
	 */
	public List<Role> findByAccountId(Long id);

	/**
	 * 修改角色权限
	 * @param roleId
	 * @param resourcesIds
     * @return
     */
	public Boolean updatePremission(Long roleId,Long[] resourcesIds);

}
