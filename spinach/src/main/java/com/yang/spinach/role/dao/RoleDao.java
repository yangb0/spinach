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

}
