package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月18日 上午10:33:49 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SysParamCfgService extends BaseService<SysParamCfg, SysParamCfgExample> {
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;

	@Autowired
	public void setSysParamCfgMapper(SysParamCfgMapper sysParamCfgMapper) {
		this.setDao(sysParamCfgMapper);
		this.sysParamCfgMapper = sysParamCfgMapper;
	}

	/**
	 *
	 */
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

	public SysParamCfg saveByCode(String code, String name, String value){
		SysParamCfg model = findByCode(code);
		if(model == null){
			model = new SysParamCfg();
			model.setParamCode(code);
		}
		model.setParamName(name);
		model.setParamValue(value);
		//model.setParamOrder(order);
		//model.setParamDesc(desc);
		if(model.getParamId() == null){
			insert(model);
		}else{
			updateByPrimaryKeySelective(model);
		}
		return model;
	}
	
}
