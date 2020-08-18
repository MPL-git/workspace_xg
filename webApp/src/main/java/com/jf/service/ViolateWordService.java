package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ViolateWordMapper;
import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ViolateWordService extends BaseService<ViolateWord, ViolateWordExample> {
	@Autowired
	private ViolateWordMapper violateWordMapper;
	@Autowired
	public void setViolateWordMapper(ViolateWordMapper violateWordMapper) {
		this.setDao(violateWordMapper);
		this.violateWordMapper = violateWordMapper;
	}
	
	/**
	 * 违禁词过滤
	 * @param content
	 * @return
	 */
	public String getProhibitedWordsFilter(String content) {
		if(!StringUtil.isBlank(content)){
			List<String> violateWords = DataDicUtil.getViolateWordList();
			if(CollectionUtils.isNotEmpty(violateWords)){
				for (String word : violateWords) {
					if(StringUtil.isBlank(content)){
						break;
					}
					if(content.contains(word)){
						int lenth = word.length();
						String replaceWord = "";
						for (int i = 0; i < lenth; i++) {
							replaceWord += " ";
						}
						content = content.replace(word, replaceWord);
					}
				}
			}
		}
		return content;
	}
}
