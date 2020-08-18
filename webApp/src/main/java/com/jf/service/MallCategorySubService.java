package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MallCategorySubMapper;
import com.jf.entity.MallCategorySub;
import com.jf.entity.MallCategorySubExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午1:15:15 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MallCategorySubService extends BaseService<MallCategorySub, MallCategorySubExample> {
	@Autowired
	private MallCategorySubMapper mallCategorySubMapper;

	@Autowired
	public void setMallCategorySubMapper(MallCategorySubMapper mallCategorySubMapper) {
		this.setDao(mallCategorySubMapper);
		this.mallCategorySubMapper = mallCategorySubMapper;
	}
	
	
}
