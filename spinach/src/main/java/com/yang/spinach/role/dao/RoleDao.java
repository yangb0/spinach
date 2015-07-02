package com.yang.spinach.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yang.spinach.frame.utils.page.Pagination;
import com.yang.spinach.role.entity.Role;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.dao.Role
 */
@Repository
public interface RoleDao {
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

	public Integer bathSaveRole(@Param("rids") Long[] rid, @Param("id") Long id);

	public List<Role> findByAccountId(Long id);

}
