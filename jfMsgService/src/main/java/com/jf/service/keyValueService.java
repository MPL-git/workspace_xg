package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.KeyValueMapper;
import com.jf.entity.KeyValue;
import com.jf.entity.KeyValueExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class keyValueService extends BaseService<KeyValue, KeyValueExample> {
	@Autowired
	private KeyValueMapper keyValueMapper;

	@Autowired
	public void setkeyValueMapper(KeyValueMapper keyValueMapper) {
		super.setDao(keyValueMapper);
		this.keyValueMapper = keyValueMapper;
	}

}
