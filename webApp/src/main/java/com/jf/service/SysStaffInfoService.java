package com.jf.service;
/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月15日 上午11:55:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/

import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysStaffInfoMapper;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;

@Service
@Transactional
public class SysStaffInfoService extends BaseService<SysStaffInfo, SysStaffInfoExample> {
	
	@Autowired
	private SysStaffInfoMapper sysStaffInfoMapper;

	public void setSysStaffInfoMapper(SysStaffInfoMapper sysStaffInfoMapper) {
		this.setDao(sysStaffInfoMapper);
		this.sysStaffInfoMapper = sysStaffInfoMapper;
	}

	public SysStaffInfo findModelByStaffId(Integer staffId) {
		SysStaffInfo sysStaffInfo = new SysStaffInfo();
		
		SysStaffInfoExample sysStaffInfoExample = new SysStaffInfoExample();
		sysStaffInfoExample.createCriteria().andStaffIdEqualTo(staffId);
		List<SysStaffInfo> sysStaffInfos = sysStaffInfoMapper.selectByExample(sysStaffInfoExample);
		if(CollectionUtils.isNotEmpty(sysStaffInfos)){
			sysStaffInfo = sysStaffInfos.get(0);
		}
		return sysStaffInfo;
	}
	
}
