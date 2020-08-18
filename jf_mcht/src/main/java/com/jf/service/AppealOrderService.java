package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppealOrderCustomMapper;
import com.jf.dao.AppealOrderMapper;
import com.jf.entity.AppealLog;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
import com.jf.entity.AppealOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppealOrderService extends BaseService<AppealOrder,AppealOrderExample> {
	@Autowired
	private AppealOrderMapper dao;
	
	@Autowired
	private AppealOrderCustomMapper appealOrderCustomMapper;
	
	@Autowired
	private AppealLogService appealLogService;
	
	@Autowired
	public void setAppealOrderMapper(AppealOrderMapper appealOrderMapper) {
		super.setDao(appealOrderMapper);
		this.dao = appealOrderMapper;
	}

	public int countAppealOrderCustomByExample(AppealOrderCustomExample example) {
		return appealOrderCustomMapper.countByExample(example);
	}

	public List<AppealOrderCustom> selectAppealOrderCustomByExample(AppealOrderCustomExample example) {
		return appealOrderCustomMapper.selectByExample(example);
	}

	public void updateAppealOrder(AppealOrder appealOrder, AppealLog appealLog) {
		this.updateByPrimaryKey(appealOrder);
		appealLogService.insertSelective(appealLog);
	}
	
}
