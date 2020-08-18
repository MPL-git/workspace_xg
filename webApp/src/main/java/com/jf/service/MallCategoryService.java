package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MallCategoryMapper;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月18日 上午11:57:07 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MallCategoryService extends BaseService<MallCategory, MallCategoryExample> {
	@Autowired
	private MallCategoryMapper mallCategoryMapper;

	@Autowired
	public void setMallCategoryMapper(MallCategoryMapper mallCategoryMapper) {
		this.setDao(mallCategoryMapper);
		this.mallCategoryMapper = mallCategoryMapper;
	}
	
	
}
