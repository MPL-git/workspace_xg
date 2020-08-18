package com.jf.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SysParamCfgCustomMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
	private SysParamCfgCustomMapper sysParamCfgCustomMapper;

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

	/**
	 * 根据code查找配置，并按name -> value 构造map
	 */
	public Map<String, String> getNameMapByCode(String code) {
		SysParamCfgExample example = new SysParamCfgExample();
		example.createCriteria().andParamCodeEqualTo(code);
		List<SysParamCfg> list = selectByExample(example);
		Map<String, String> result = Maps.newHashMap();
		if (!CollectionUtils.isEmpty(list)) {
			for (SysParamCfg cfg : list) {
				result.put(cfg.getParamName(), cfg.getParamValue());
			}
		}
		return result;
	}

	public List<Integer> getSpecialHideTypeList() {
		return getIntList("SPECIAL_PRODUCT_TYPE_HIDE");
	}

	public List<String> getSpecialHideMchtCodeList() {
		return getStrList("SPECIAL_MCHT_HIDE");
	}

	public List<String> getSpecialHideCategoryList() {
		return getStrList("SPECIAL_CATEGORY_HIDE");
	}

	public List<Integer> getSpecialHideAreaProductTypeIdList() {
		return getIntList("SPECIAL_PRODUCT_TYPE_AREA_HIDE");
	}

	private List<Integer> getIntList(String code) {
		SysParamCfg sysParamCfg = findByCode(code);
		if (sysParamCfg == null || StringUtil.isBlank(sysParamCfg.getParamValue())) {
			return Collections.emptyList();
		}

		try {
			List<Integer> ids = Lists.newArrayList();
			for (String idStr : sysParamCfg.getParamValue().split(Const.COMMA)) {
				ids.add(Integer.parseInt(idStr));
			}
			return ids;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	private List<String> getStrList(String code) {
		SysParamCfg sysParamCfg = findByCode(code);
		if (sysParamCfg == null || StringUtil.isBlank(sysParamCfg.getParamValue())) {
			return Collections.emptyList();
		}

		try {
			return Lists.newArrayList(sysParamCfg.getParamValue().split(Const.COMMA));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public boolean increasePoolIntegral(Integer currentPoolIntegral, int increment) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("code", "INTEGRAL_LOTTERY_CONFIG");
		params.put("name", "INTEGRAL_POOL");
		if (currentPoolIntegral != null) {
			params.put("currentPoolIntegral", currentPoolIntegral);
		}
		params.put("increment", increment);
        return sysParamCfgCustomMapper.increasePoolIntegral(params) > 0;
	}

	public void resetPoolIntegral() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("code", "INTEGRAL_LOTTERY_CONFIG");
		params.put("name", "INTEGRAL_POOL");
		sysParamCfgCustomMapper.resetPoolIntegral(params);
	}
}
