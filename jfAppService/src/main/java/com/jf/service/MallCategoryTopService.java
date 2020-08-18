package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MallCategoryTopMapper;
import com.jf.entity.MallCategoryTop;
import com.jf.entity.MallCategoryTopExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午12:59:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MallCategoryTopService extends BaseService<MallCategoryTop, MallCategoryTopExample> {
	
	@Autowired
	private MallCategoryTopMapper mallCategoryTopMapper;

	@Autowired
	public void setMallCategoryTopMapper(MallCategoryTopMapper mallCategoryTopMapper) {
		this.setDao(mallCategoryTopMapper);
		this.mallCategoryTopMapper = mallCategoryTopMapper;
	}
	
	
}
