package com.yang.spinach.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.spinach.bussiness.system.entity.Dict;
import com.yang.spinach.frame.dao.MyMapper;
import com.yang.spinach.frame.pagePlugin.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yang.spinach.bussiness.system.entity.dao.Dict
 */
public interface DictDao extends MyMapper<Dict> {

	List<Dict> selectByColumn(String column);

	List<Dict> listPage(@Param("pagination") Pagination pagination,
			@Param("dict") Dict dict);

}
