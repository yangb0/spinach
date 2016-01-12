package com.yang.spinach.bussiness.system.service;

import java.util.List;

import com.yang.spinach.bussiness.system.entity.Dict;
import com.yang.spinach.frame.pagePlugin.Pagination;
import com.yang.spinach.frame.service.BaseService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yang.spinach.bussiness.system.entity.service.Dict
 */
public interface DictService extends BaseService<Dict>{

	/**
	 * 根据对应表字段获取
	 * 
	 * @param column
	 * @return
	 */
	List<Dict> selectByColumn(String column);

	List<Dict> listPage(Pagination pagination, Dict dict);

}
