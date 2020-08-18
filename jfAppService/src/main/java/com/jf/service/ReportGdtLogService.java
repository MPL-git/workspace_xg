package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ReportGdtLogMapper;
import com.jf.entity.ReportGdtLog;
import com.jf.entity.ReportGdtLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年11月13日 下午3:13:15 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ReportGdtLogService extends BaseService<ReportGdtLog, ReportGdtLogExample> {
	@Autowired
	private ReportGdtLogMapper reportGdtLogMapper;

	@Autowired
	public void setReportGdtLogMapper(ReportGdtLogMapper reportGdtLogMapper) {
		this.setDao(reportGdtLogMapper);
		this.reportGdtLogMapper = reportGdtLogMapper;
	}

	public ReportGdtLog saveModel(ReportGdtLog reportGdtLog) {
		Date d = new Date();
		reportGdtLog.setCreateDate(d);
		reportGdtLogMapper.insertSelective(reportGdtLog);
		return reportGdtLog;
	}
	
}
