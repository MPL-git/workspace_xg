package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppealLogCustomMapper;
import com.jf.dao.AppealLogMapper;
import com.jf.dao.AppealLogPicMapper;
import com.jf.dao.AppealOrderMapper;
import com.jf.entity.AppealLog;
import com.jf.entity.AppealLogCustom;
import com.jf.entity.AppealLogCustomExample;
import com.jf.entity.AppealLogExample;
import com.jf.entity.AppealLogPic;
import com.jf.entity.AppealOrder;
import com.jf.entity.SysAppMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppealLogService extends BaseService<AppealLog,AppealLogExample> {
	@Autowired
	private AppealLogMapper dao;
	
	@Autowired
	private AppealLogCustomMapper appealLogCustomMapper;
	
	@Autowired
	private AppealLogPicMapper appealLogPicMapper;
	
	@Autowired
	private AppealOrderMapper appealOrderMapper;
	
	@Autowired
	private SysAppMessageService sysAppMessageService;
	
	@Autowired
	public void setAppealLogMapper(AppealLogMapper appealLogMapper) {
		super.setDao(appealLogMapper);
		this.dao = appealLogMapper;
	}

	public List<AppealLogCustom> selectByAppealLogCustomExample(AppealLogCustomExample example) {
		return appealLogCustomMapper.selectByExample(example);
	}

	public void saveAppealLog(AppealLog appealLog, AppealOrder appealOrder,List<AppealLogPic> appealLogPics,SysAppMessage sysAppMessage) {
		this.insertSelective(appealLog);
		appealOrderMapper.updateByPrimaryKey(appealOrder);
		for(AppealLogPic appealLogPic:appealLogPics){
			appealLogPic.setAppealLogId(appealLog.getId());
			appealLogPicMapper.insertSelective(appealLogPic);
		}
//		sysAppMessageService.insertSelective(sysAppMessage);
	}
	
	
}
