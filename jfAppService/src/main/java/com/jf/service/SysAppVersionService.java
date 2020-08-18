package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppVersionMapper;
import com.jf.dao.SysAppVersionCustomMapper;
import com.jf.entity.AppVersion;
import com.jf.entity.AppVersionExample;
import com.jf.entity.SysAppCustomVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月22日 下午2:12:25 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SysAppVersionService extends BaseService<AppVersion, AppVersionExample> {
	
	@Autowired
	private AppVersionMapper sysAppVersionMapper;
	
	@Autowired
	private SysAppVersionCustomMapper sysAppVersionCustomMapper;

	@Autowired
	public void setAppVersionMapper(AppVersionMapper sysAppVersionMapper) {
		this.setDao(sysAppVersionMapper);
		this.sysAppVersionMapper = sysAppVersionMapper;
	}

	public List<SysAppCustomVersion> getAppNewVersion(Map<String, String> paramsMap) {
		
		return sysAppVersionCustomMapper.getAppNewVersion(paramsMap);
	}
	
}
