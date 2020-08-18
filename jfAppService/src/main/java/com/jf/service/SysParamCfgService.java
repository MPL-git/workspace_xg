package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.vo.DateRange;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	public String findValByCode(String code) {
		SysParamCfg cfg = findByCode(code);
		return cfg == null ? null : cfg.getParamValue();
	}

	public Map<String, DateRange> findTaskSuspendTimeConfigMap() {
		SysParamCfgExample example = new SysParamCfgExample();
		example.createCriteria().andParamCodeEqualTo("TASK_SUSPEND_TIME");
		List<SysParamCfg> list = this.selectByExample(example);
		if (org.springframework.util.CollectionUtils.isEmpty(list)) return Collections.emptyMap();

		Map<String, DateRange> map = Maps.newHashMap();
		for (SysParamCfg cfg : list) {
			String value = cfg.getParamValue();
			if (!StringUtils.hasText(value)) {
				continue;
			}
			try {
				String[] dataStrs = value.split(",");
				Date begin = DateUtil.getDate(dataStrs[0]);
				Date end = DateUtil.getDate(dataStrs[1]);
				map.put(cfg.getParamName(), DateRange.of(begin, end));
			} catch (Exception e) {

			}
		}
		return map;
	}
}
