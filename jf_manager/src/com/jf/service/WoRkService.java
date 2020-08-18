package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.WoRkCustomMapper;
import com.jf.dao.WoRkMapper;
import com.jf.entity.WoRk;
import com.jf.entity.WoRkCustom;
import com.jf.entity.WoRkCustomExample;
import com.jf.entity.WoRkExample;

@Service
public class WoRkService extends BaseService<WoRk, WoRkExample> {

	@Autowired
	private WoRkMapper woRkMapper;
	
	@Autowired
	private WoRkCustomMapper woRkCustomManager;
	
	@Autowired
	public void setworkManager(WoRkMapper woRkMapper) {
		super.setDao(woRkMapper);
		this.woRkMapper = woRkMapper;
	}
	
	public Integer countByCustomExample(WoRkCustomExample example) {
		return woRkCustomManager.countByCustomExample(example);
	}

	public List<WoRkCustom> selectByCustomExample(WoRkCustomExample example) {
		return woRkCustomManager.selectByCustomExample(example);
	}
	
	public WoRkCustom selectByPrimaryKeyCustom(Integer staffRoleId) {
		return woRkCustomManager.selectByPrimaryKeyCustom(staffRoleId);
	}
	
	public List<Map<String, Object>> getstaffidList() {
		return woRkCustomManager.getstaffidList();
	}
	

	public List<Map<String, Object>> getstaffidListc() {
		return woRkCustomManager.getstaffidListc();
	}
	
}
