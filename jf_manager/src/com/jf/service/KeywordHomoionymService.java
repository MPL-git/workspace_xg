package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.KeywordHomoionymMapper;
import com.jf.entity.KeywordHomoionym;
import com.jf.entity.KeywordHomoionymExample;

@Service
@Transactional
public class KeywordHomoionymService extends BaseService<KeywordHomoionym,KeywordHomoionymExample> {
	@Autowired
	private KeywordHomoionymMapper keywordHomoionymMapper;

	@Autowired
	public void setKeywordHomoionymMapper(KeywordHomoionymMapper keywordHomoionymMapper) {
		super.setDao(keywordHomoionymMapper);
		this.keywordHomoionymMapper = keywordHomoionymMapper;
	}

}
