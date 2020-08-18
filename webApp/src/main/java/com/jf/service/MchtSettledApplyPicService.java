package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSettledApplyPicMapper;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MchtSettledApplyPicExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月23日 上午10:35:22 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MchtSettledApplyPicService extends BaseService<MchtSettledApplyPic, MchtSettledApplyPicExample> {
	
	@Autowired
	private MchtSettledApplyPicMapper mchtSettledApplyPicMapper;

	@Autowired
	public void setMchtSettledApplyPicMapper(MchtSettledApplyPicMapper mchtSettledApplyPicMapper) {
		this.setDao(mchtSettledApplyPicMapper);
		this.mchtSettledApplyPicMapper = mchtSettledApplyPicMapper;
	}
	
	
}
