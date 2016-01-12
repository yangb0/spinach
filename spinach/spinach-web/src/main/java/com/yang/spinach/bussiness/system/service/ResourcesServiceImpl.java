package com.yang.spinach.bussiness.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.spinach.bussiness.system.dao.ResourcesDao;
import com.yang.spinach.bussiness.system.entity.Resources;
import com.yang.spinach.frame.service.impl.BaseServiceImpl;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.bussiness.system.entity.service.impl.Resources
 */
@Service("resourcesService")
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements
		ResourcesService {
	@Autowired
	private ResourcesDao resourcesDao;

	@Override
	public List<Resources> findByAccountId(Long id) {
		return resourcesDao.findByAccountId(id);
	}

	@Override
	public List<Resources> list(Resources resource) {
		return resourcesDao.list(resource);
	}

	public List<Resources> findByRoleId(Long id) {
		return resourcesDao.findByRoleId(id);
	}

}
