package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppealLogMapper;
import com.jf.entity.AppealLog;
import com.jf.entity.AppealLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月6日 下午3:16:59 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AppealLogService extends BaseService<AppealLog, AppealLogExample> {
	
	@Autowired
	private AppealLogMapper appealLogMapper;
	
	@Autowired
	public void setAppealLogMapper(AppealLogMapper appealLogMapper) {
		this.setDao(appealLogMapper);
		this.appealLogMapper = appealLogMapper;
	}

	public AppealLog add(Integer appealOrderId, String remarks, Integer memberId, Date date, String memberName,
			String appealOrderType) {
		AppealLog appealLog = new AppealLog();
		appealLog.setAppealOrderId(appealOrderId);
		appealLog.setContent(remarks);
		appealLog.setCreateBy(memberId);
		appealLog.setCreateDate(date);
		appealLog.setDelFlag("0");
		appealLog.setRemarks(remarks);
		appealLog.setUserId(memberId);
		appealLog.setUserName(memberName);
		appealLog.setUserType(appealOrderType);
		return saveModel(appealLog);
	}

	public AppealLog saveModel(AppealLog appealLog) {
		appealLogMapper.insertSelective(appealLog);
		return appealLog;
	}
	
}
