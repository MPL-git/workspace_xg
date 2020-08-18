package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.HotSearchWordMapper;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;
import com.jf.entity.SearchLog;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月18日 上午11:46:34 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class HotSearchWordService extends BaseService<HotSearchWord, HotSearchWordExample> {
	@Autowired
	private HotSearchWordMapper hotSearchWordMapper;
	@Autowired
	private SearchLogService searchLogService;

	@Autowired
	public void setHotSearchWordMapper(HotSearchWordMapper hotSearchWordMapper) {
		this.setDao(hotSearchWordMapper);
		this.hotSearchWordMapper = hotSearchWordMapper;
	}

	public void addHotSearchWord(JSONObject reqDataJson,Integer memberId) {
		String searchName = "";
		String client = "";
		String reqImei = "";
		Date date = new Date();
		if(reqDataJson.containsKey("client") && !StringUtil.isBlank(reqDataJson.getString("client"))){
			client = reqDataJson.getString("client");
		}
		if(reqDataJson.containsKey("reqImei") && !StringUtil.isBlank(reqDataJson.getString("reqImei"))){
			reqImei = reqDataJson.getString("reqImei");
		}
		if(reqDataJson.containsKey("searchName") && !StringUtil.isBlank(reqDataJson.getString("searchName"))){
			searchName = reqDataJson.getString("searchName");
		}
		
		try {
			if(!StringUtil.isBlank(searchName)){
				searchName = searchName.trim();
				HotSearchWord hotSearchWord = null;
				HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
				hotSearchWordExample.createCriteria().andWordEqualTo(searchName).andDelFlagEqualTo("0");
				List<HotSearchWord> hotSearchWords = selectByExample(hotSearchWordExample);
				if(CollectionUtil.isNotEmpty(hotSearchWords)){
					hotSearchWord = hotSearchWords.get(0);
					hotSearchWord.setSearchCount(hotSearchWord.getSearchCount()+1);
					hotSearchWord.setUpdateDate(date);
					updateByPrimaryKeySelective(hotSearchWord);
				}else{
					hotSearchWord = new HotSearchWord();
					hotSearchWord.setSearchCount(1);
					hotSearchWord.setWord(searchName);
					hotSearchWord.setSource("1");
					hotSearchWord.setCreateBy(memberId);
					hotSearchWord.setCreateDate(date);
					hotSearchWord.setDelFlag("0");
					insertSelective(hotSearchWord);
				}
				//创建一条搜索日志
				SearchLog log = new SearchLog();
				log.setHotSearchWordId(hotSearchWord.getId());
				log.setWord(searchName);
				log.setMemberId(memberId);
				log.setClient(client);
				log.setReqImei(reqImei);
				log.setCreateBy(memberId);
				log.setCreateDate(date);
				log.setDelFlag("0");
				searchLogService.insertSelective(log);
			}
		} catch (Exception e) {
			
		}
	}
	
	
}
