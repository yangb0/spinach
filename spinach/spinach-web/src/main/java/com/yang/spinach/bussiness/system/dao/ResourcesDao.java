package com.yang.spinach.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.spinach.bussiness.system.entity.Resources;
import com.yang.spinach.frame.dao.MyMapper;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.bussiness.system.entity.dao.Resources
 */
public interface ResourcesDao extends MyMapper<Resources> {

	List<Resources> findByAccountId(Long id);

	List<Resources> list(@Param("resource") Resources resource);

	List<Resources> findByRoleId(Long id);
}
