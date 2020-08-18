package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ViolatePunishStandardCustomMapper;
import com.jf.dao.ViolatePunishStandardMapper;
import com.jf.entity.ViolatePunishStandard;
import com.jf.entity.ViolatePunishStandardCustom;
import com.jf.entity.ViolatePunishStandardCustomExample;
import com.jf.entity.ViolatePunishStandardExample;

@Service
@Transactional
public class ViolatePunishStandardService extends BaseService<ViolatePunishStandard,ViolatePunishStandardExample> {
	@Autowired
	private ViolatePunishStandardMapper dao;
	
	@Autowired
	private ViolatePunishStandardCustomMapper violatePunishStandardCustomMapper;
	
	@Autowired
	public void setViolatePunishStandardMapper(ViolatePunishStandardMapper violatePunishStandardMapper) {
		super.setDao(violatePunishStandardMapper);
		this.dao = violatePunishStandardMapper;
	}
	
	public int countViolatePunishStandardCustomByExample(ViolatePunishStandardCustomExample example){
		return violatePunishStandardCustomMapper.countByExample(example);
	}
	
	public List<ViolatePunishStandardCustom> selectViolatePunishStandardCustomByExample(ViolatePunishStandardCustomExample example){
		return violatePunishStandardCustomMapper.selectByExample(example);
	}

	public void save(ViolatePunishStandard violatePunishStandard) {
		if(violatePunishStandard.getId()!=null){
			this.updateByPrimaryKeySelective(violatePunishStandard);
		}else{
			this.insertSelective(violatePunishStandard);
		}
	}
	
}
