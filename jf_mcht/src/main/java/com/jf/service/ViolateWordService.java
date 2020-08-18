package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ViolateWordMapper;
import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViolateWordService extends BaseService<ViolateWord, ViolateWordExample> {
	@Autowired
	private ViolateWordMapper violateWordMapper;

	@Autowired
	public void setViolateWordMapper(ViolateWordMapper violateWordMapper) {
		super.setDao(violateWordMapper);
		this.violateWordMapper = violateWordMapper;
	}
}
