package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ThirdPlatformProductInfoMapper;
import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThirdPlatformProductInfoService extends BaseService<ThirdPlatformProductInfo, ThirdPlatformProductInfoExample> {
	@Autowired
	private ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper;

	@Autowired
	public void setThirdPlatformProductInfoMapper(ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper) {
		super.setDao(thirdPlatformProductInfoMapper);
		this.thirdPlatformProductInfoMapper = thirdPlatformProductInfoMapper;
	}
}
