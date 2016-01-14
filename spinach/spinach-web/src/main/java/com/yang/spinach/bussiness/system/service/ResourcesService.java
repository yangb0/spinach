package com.yang.spinach.bussiness.system.service;

import java.util.List;

import com.yang.spinach.bussiness.system.entity.Resources;
import com.yang.spinach.frame.service.BaseService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.bussiness.system.entity.service.Resources
 */
public interface ResourcesService extends BaseService<Resources> {

	/**
	 * 根据用户获取资源
	 * 
	 * @param id
	 * @return @
	 */
	List<Resources> findByAccountId(Long id);

	List<Resources> list(Resources resource);

	public List<Resources> findByRoleId(Long id);
}
