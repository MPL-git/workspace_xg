package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.TaobaokeConfigMapper;
import com.jf.entity.TaobaokeConfig;
import com.jf.entity.TaobaokeConfigExample;

@Service
@Transactional
public class TaobaokeConfigService extends BaseService<TaobaokeConfig, TaobaokeConfigExample> {
	@Autowired
	private TaobaokeConfigMapper taobaokeConfigMapper;

	@Autowired
	public void setTaobaokeConfigMapper(TaobaokeConfigMapper taobaokeConfigMapper) {
		this.setDao(taobaokeConfigMapper);
		this.taobaokeConfigMapper = taobaokeConfigMapper;
	}
}
