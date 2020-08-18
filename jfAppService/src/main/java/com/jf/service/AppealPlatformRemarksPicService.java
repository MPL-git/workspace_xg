package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppealPlatformRemarksPicMapper;
import com.jf.entity.AppealPlatformRemarksPic;
import com.jf.entity.AppealPlatformRemarksPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月25日 上午10:34:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AppealPlatformRemarksPicService extends BaseService<AppealPlatformRemarksPic, AppealPlatformRemarksPicExample> {
	
	@Autowired
	private AppealPlatformRemarksPicMapper appealPlatformRemarksPicMapper;

	@Autowired
	public void setAppealPlatformRemarksPicMapper(AppealPlatformRemarksPicMapper appealPlatformRemarksPicMapper) {
		this.setDao(appealPlatformRemarksPicMapper);
		this.appealPlatformRemarksPicMapper = appealPlatformRemarksPicMapper;
	}
	
	
}
