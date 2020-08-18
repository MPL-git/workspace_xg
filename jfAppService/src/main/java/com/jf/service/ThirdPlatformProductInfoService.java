package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ThirdPlatformProductInfoCustomMapper;
import com.jf.dao.ThirdPlatformProductInfoMapper;
import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoCustom;
import com.jf.entity.ThirdPlatformProductInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ThirdPlatformProductInfoService extends BaseService<ThirdPlatformProductInfo, ThirdPlatformProductInfoExample> {
	@Autowired
	private ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper;
	@Autowired
	private ThirdPlatformProductInfoCustomMapper thirdPlatformProductInfoCustomMapper;
	@Autowired
	public void setThirdPlatformProductInfoMapper(ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper) {
		this.setDao(thirdPlatformProductInfoMapper);
		this.thirdPlatformProductInfoMapper = thirdPlatformProductInfoMapper;
	}
	public List<ThirdPlatformProductInfoCustom> getThirdProductInfoList(Map<String, Object> paramsMap) {
		
		return thirdPlatformProductInfoCustomMapper.getThirdProductInfoList(paramsMap);
	}
	public List<ThirdPlatformProductInfoCustom> getChannelTaoBaoProductList(Map<String, Object> paramsMap) {
		
		return thirdPlatformProductInfoCustomMapper.getChannelTaoBaoProductList(paramsMap);
	}
	
	
}
