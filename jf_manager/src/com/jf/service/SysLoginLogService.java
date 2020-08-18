package com.jf.service;

import com.jf.dao.SysAppLoginLogMapper;
import com.jf.dao.SysLoginLogMapper;
import com.jf.dao.SysStaffInfoMapper;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLoginLogService extends BaseService<SysLoginLog, SysLoginLogExample> {

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;
	
	@Autowired
	private SysStaffInfoMapper sysStaffInfoMapper;

	@Autowired
	private SysAppLoginLogMapper sysAppLoginLogMapper;

	
	@Autowired
	public void setSysLoginLogMapper(SysLoginLogMapper sysLoginLogMapper) {
		super.setDao(sysLoginLogMapper);
		this.sysLoginLogMapper = sysLoginLogMapper;
	}
	
	/**
	 * 
	 * @Title insertSysLoginLog   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月30日 下午2:36:01
	 */
	public void insertSysLoginLog(SysLoginLog sysLoginLog, Integer loginErrorCount) {
		if(sysLoginLog.getStaffInfoId() != null) {
			sysLoginLogMapper.insertSelective(sysLoginLog);
			SysStaffInfo sysStaffInfo = new SysStaffInfo();
			sysStaffInfo.setStaffId(sysLoginLog.getStaffInfoId());
			sysStaffInfo.setLoginErrorCount(loginErrorCount);
			sysStaffInfoMapper.updateByPrimaryKeySelective(sysStaffInfo);
		}
	}

	public List<SysAppLoginLog> selectByExample(SysAppLoginLogExample example) {
		return sysAppLoginLogMapper.selectByExample(example);
	}

	public int countByExample(SysAppLoginLogExample example) {
		return sysAppLoginLogMapper.countByExample(example);
	}

	
}
