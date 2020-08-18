package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CumulativeSignInSettingCustomMapper;
import com.jf.dao.CumulativeSignInSettingMapper;
import com.jf.entity.CumulativeSignInSetting;
import com.jf.entity.CumulativeSignInSettingCustom;
import com.jf.entity.CumulativeSignInSettingExample;

@Service
@Transactional
public class CumulativeSignInSettingService extends BaseService<CumulativeSignInSetting,CumulativeSignInSettingExample> {
	@Autowired
	private CumulativeSignInSettingMapper cumulativeSignInSettingMapper;
	@Autowired
	private CumulativeSignInSettingCustomMapper cumulativeSignInSettingCustomMapper;
	@Autowired
	public void setCumulativeSignInSettingMapper(CumulativeSignInSettingMapper cumulativeSignInSettingMapper) {
		super.setDao(cumulativeSignInSettingMapper);
		this.cumulativeSignInSettingMapper = cumulativeSignInSettingMapper;
	}
	
	public int countCumulativeSignInSettingCustomByExample(CumulativeSignInSettingExample example){
		return cumulativeSignInSettingCustomMapper.countByExample(example);
	}
    public List<CumulativeSignInSettingCustom> selectCumulativeSignInSettingCustomByExample(CumulativeSignInSettingExample example){
    	return cumulativeSignInSettingCustomMapper.selectByExample(example);
    }

	public void batchInsert(List<CumulativeSignInSetting> cumulativeSignInSettingList) {
		cumulativeSignInSettingCustomMapper.batchInsert(cumulativeSignInSettingList);
	}

	public String getSingleProductActivityStatus(Integer productId) {
		return cumulativeSignInSettingCustomMapper.getSingleProductActivityStatus(productId);
	}
}
