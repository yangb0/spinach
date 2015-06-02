package com.yang.spinach.resources.service;

import java.util.List;

import com.yang.spinach.resources.entity.Resources;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.service.Resources
 */
public interface ResourcesService {
	/**
	 * 保存
	 *
	 */
	public Integer saveResources(Resources entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateResourcesById(Resources entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Resources selectResourcesById(Object id) throws Exception;

	/**
	 * 根据用户获取资源
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Resources> findByAccountId(Long id) throws Exception;

	List<Resources> list(Resources resource);
}
