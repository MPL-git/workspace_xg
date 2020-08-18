package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.KeywordHomoionymMapper;
import com.jf.entity.KeywordHomoionym;
import com.jf.entity.KeywordHomoionymExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KeywordMomoionymService extends BaseService<KeywordHomoionym, KeywordHomoionymExample> {
	@Autowired
	private KeywordHomoionymMapper keywordMomoionymMapper;
	@Autowired
	public void setKeywordHomoionymMapper(KeywordHomoionymMapper keywordMomoionymMapper) {
		this.setDao(keywordMomoionymMapper);
		this.keywordMomoionymMapper = keywordMomoionymMapper;
	}

	public String getKeywordHomoionym(String searchName) {
		String homoionym = "";
		KeywordHomoionymExample keywordMomoionymExample = new KeywordHomoionymExample();
		keywordMomoionymExample.createCriteria().andDelFlagEqualTo("0").andKeywordEqualTo(searchName);
		List<KeywordHomoionym> momoionyms = keywordMomoionymMapper.selectByExample(keywordMomoionymExample);
		if(CollectionUtils.isNotEmpty(momoionyms)){
			homoionym = momoionyms.get(0).getHomoionym();
		}
		return homoionym;
	}
}
