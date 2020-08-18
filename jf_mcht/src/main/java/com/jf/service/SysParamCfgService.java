package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysParamCfgCustomMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public List<SysParamCfg> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private SysParamCfgExample builderQuery(QueryObject queryObject) {
		SysParamCfgExample example = new SysParamCfgExample();
		SysParamCfgExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("paramCode") != null){
			criteria.andParamCodeEqualTo(queryObject.getQueryToStr("paramCode"));
		}
		if(queryObject.getQuery("paramCode%") != null){
			criteria.andParamCodeLike(queryObject.getQueryToStr("paramCode%") + "%");
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
