package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MallCategoryItemMapper;
import com.jf.entity.MallCategoryItem;
import com.jf.entity.MallCategoryItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月18日 上午11:48:03 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MallCategoryItemService extends BaseService<MallCategoryItem, MallCategoryItemExample> {
	@Autowired
	private MallCategoryItemMapper mallCategoryItemMapper;

	@Autowired
	public void setMallCategoryItemMapper(MallCategoryItemMapper mallCategoryItemMapper) {
		this.setDao(mallCategoryItemMapper);
		this.mallCategoryItemMapper = mallCategoryItemMapper;
	}
	
	
}
