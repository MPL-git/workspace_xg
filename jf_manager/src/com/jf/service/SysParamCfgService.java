package com.jf.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysParamCfgCustomMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;

@Service
@Transactional
public class SysParamCfgService extends BaseService<SysParamCfg, SysParamCfgExample> {
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	private SysParamCfgCustomMapper sysParamCfgCustomMapper;

	@Autowired
	public void setSysParamCfgMapper(SysParamCfgMapper sysParamCfgMapper) {
		super.setDao(sysParamCfgMapper);
		this.sysParamCfgMapper = sysParamCfgMapper;
	}
	
	public List<SysParamCfg> getSysParamCfgListByParamCode(String paramCode){
		return sysParamCfgCustomMapper.getSysParamCfgListByParamCode(paramCode);
	}
	
	
	public SysParamCfg findByCode(String code){
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		SysParamCfgExample.Criteria criteria = sysParamCfgExample.createCriteria();
		criteria.andParamCodeEqualTo(code);
		List<SysParamCfg> sysParamCfgs = selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			return sysParamCfgs.get(0);
		}
		return null;
	}


}
