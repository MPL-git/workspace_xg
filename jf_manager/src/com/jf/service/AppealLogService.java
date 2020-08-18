package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
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
	private AppealLogMapper appealLogMapper;
	
	@Autowired
	public void setAppealLogMapper(AppealLogMapper appealLogMapper) {
		super.setDao(appealLogMapper);
		this.dao = appealLogMapper;
	}

	public List<AppealLogCustom> selectByAppealLogCustomExample(AppealLogCustomExample example) {
		return appealLogCustomMapper.selectByExample(example);
	}

	public Map<String, Object> saveAppealLog(HttpServletRequest request, Integer staffId, String staffName) {
		Integer appealOrderId = Integer.parseInt(request.getParameter("appealOrderId"));
		String serviceStatus = request.getParameter("serviceStatus");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		String userType = request.getParameter("userType");
		String logPicNameA = request.getParameter("logPicNameA");
		String logPicNameB = request.getParameter("logPicNameB");
		String logPicNameC = request.getParameter("logPicNameC");
		Map<String, Object> map = new HashMap<String, Object>();
		List<AppealLogPic> appealLogPicList = new ArrayList<AppealLogPic>();
		Date date = new Date();
		
		AppealOrder appealOrder = new AppealOrder();
		appealOrder.setId(appealOrderId);
		appealOrder.setServiceStatus(serviceStatus); //客服状态(平台介入状态)
		appealOrder.setUpdateBy(staffId);
		appealOrder.setUpdateDate(date);
		if(serviceStatus.equals("1")) {
			appealOrder.setStatus(status);
			AppealLog appealLog = new AppealLog();
			appealLog.setAppealOrderId(appealOrderId);
			appealLog.setUserId(staffId);
			appealLog.setUserType(userType);
			appealLog.setUserName(staffName);
			appealLog.setContent(content);
			appealLog.setCreateBy(staffId);
			appealLog.setCreateDate(date);
			appealLog.setDelFlag("0");
			appealLogMapper.insertSelective(appealLog);
			map.put("appealLog", appealLog);
			if(!StringUtil.isEmpty(logPicNameA)) {
				AppealLogPic appealLogPicA = new AppealLogPic();
				appealLogPicA.setAppealLogId(appealLog.getId());
				appealLogPicA.setPic(logPicNameA);
				appealLogPicA.setCreateBy(staffId);
				appealLogPicA.setCreateDate(date);
				appealLogPicA.setDelFlag("0");
				appealLogPicMapper.insertSelective(appealLogPicA);
				appealLogPicList.add(appealLogPicA);
			}
			if(!StringUtil.isEmpty(logPicNameB)) {
				AppealLogPic appealLogPicB = new AppealLogPic();
				appealLogPicB.setAppealLogId(appealLog.getId());
				appealLogPicB.setPic(logPicNameB);
				appealLogPicB.setCreateBy(staffId);
				appealLogPicB.setCreateDate(date);
				appealLogPicB.setDelFlag("0");
				appealLogPicMapper.insertSelective(appealLogPicB);
				appealLogPicList.add(appealLogPicB);
			}
			if(!StringUtil.isEmpty(logPicNameC)) {
				AppealLogPic appealLogPicC = new AppealLogPic();
				appealLogPicC.setAppealLogId(appealLog.getId());
				appealLogPicC.setPic(logPicNameC);
				appealLogPicC.setCreateBy(staffId);
				appealLogPicC.setCreateDate(date);
				appealLogPicC.setDelFlag("0");
				appealLogPicMapper.insertSelective(appealLogPicC);
				appealLogPicList.add(appealLogPicC);
			}
		}
		appealOrderMapper.updateByPrimaryKeySelective(appealOrder);
		map.put("appealOrder", appealOrder);
		map.put("appealLogPicList", appealLogPicList);
		return map;
	}
	
}
