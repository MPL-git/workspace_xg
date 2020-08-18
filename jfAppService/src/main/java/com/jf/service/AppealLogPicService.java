package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppealLogPicMapper;
import com.jf.entity.AppealLogPic;
import com.jf.entity.AppealLogPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月6日 下午3:19:17 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AppealLogPicService extends BaseService<AppealLogPic, AppealLogPicExample> {
	
	@Autowired
	private AppealLogPicMapper appealLogPicMapper;
	
	@Autowired
	public void setAppealLogPicMapper(AppealLogPicMapper appealLogPicMapper) {
		this.setDao(appealLogPicMapper);
		this.appealLogPicMapper = appealLogPicMapper;
	}

	public AppealLogPic add(Integer appealLogId, Integer memberId, Date date, String str, String remarks) {
		AppealLogPic appealLogPic = new AppealLogPic();
		appealLogPic.setAppealLogId(appealLogId);
		appealLogPic.setCreateBy(memberId);
		appealLogPic.setCreateDate(date);
		appealLogPic.setDelFlag("0");
		appealLogPic.setPic(str);
		appealLogPic.setRemarks(remarks);
		return saveModel(appealLogPic);
	}

	public AppealLogPic saveModel(AppealLogPic appealLogPic) {
		appealLogPicMapper.insertSelective(appealLogPic);
		return appealLogPic;
	}
	
}
