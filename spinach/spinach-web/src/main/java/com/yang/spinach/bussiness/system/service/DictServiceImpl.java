package com.yang.spinach.bussiness.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.spinach.bussiness.system.dao.DictDao;
import com.yang.spinach.bussiness.system.entity.Dict;
import com.yang.spinach.frame.pagePlugin.Pagination;
import com.yang.spinach.frame.service.impl.BaseServiceImpl;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yang.spinach.bussiness.system.entity.service.impl.Dict
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {
	@Autowired
	private DictDao dictDao;

	@Override
	public List<Dict> selectByColumn(String column) {
		return dictDao.selectByColumn(column);
	}

	@Override
	public List<Dict> listPage(Pagination pagination, Dict dict) {
		return dictDao.listPage(pagination, dict);
	}

}
