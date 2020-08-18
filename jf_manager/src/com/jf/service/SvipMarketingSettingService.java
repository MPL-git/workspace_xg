package com.jf.service;

import com.jf.dao.SvipMarketingSettingMapper;
import com.jf.entity.SvipMarketingSetting;
import com.jf.entity.SvipMarketingSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SvipMarketingSettingService extends BaseService<SvipMarketingSetting, SvipMarketingSettingExample>{
	@Autowired
	private SvipMarketingSettingMapper svipMarketingSettingMapper;
	@Autowired
	public void setActivityAuthMapper(SvipMarketingSettingMapper svipMarketingSettingMapper) {
		super.setDao(svipMarketingSettingMapper);
		this.svipMarketingSettingMapper = svipMarketingSettingMapper;
	}
}
