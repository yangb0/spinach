package com.yang.spinach.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yang.spinach.bussiness.system.entity.Role;
import com.yang.spinach.frame.dao.MyMapper;
import com.yang.spinach.frame.pagePlugin.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.bussiness.system.entity.dao.Role
 */
@Repository
public interface RoleDao extends MyMapper<Role> {

	List<Role> listPage(@Param("role") Role role,
			@Param("pagination") Pagination pagination);

	/**
	 * 根据角色id删除该角色所有权限
	 * 
	 * @param id
	 * @return
	 */
	public Integer delPermBYId(Long id);

	/**
	 * 给角色设置权限
	 * 
	 * @param rid
	 * @param id
	 * @return
	 */
	public Integer bathSavePerm(@Param("rids") Long[] rid, @Param("id") Long id);

	public Integer delRoleBYId(Long id);

	public Integer bathSaveRole(@Param("rid") Long rid, @Param("id") Long id);

	public List<Role> findByAccountId(Long id);

}
