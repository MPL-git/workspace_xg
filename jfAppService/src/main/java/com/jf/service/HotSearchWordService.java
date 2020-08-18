package com.jf.service;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.HotSearchWordMapper;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;
import com.jf.entity.SearchLog;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	public void addHotSearchWord(JSONObject reqDataJson) {
		String searchName = "";
		Integer memberId = null;
		String client = "";
		String reqImei = "";
		Date date = new Date();
		if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
			memberId = reqDataJson.getInt("memberId");
		}
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

	public List<Map<String,Object>> randomHotSearchWord(String hotSearchPage, int fetchSize) {
		HotSearchWordExample example = new HotSearchWordExample();
		HotSearchWordExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("1")
				.andDelFlagEqualTo(StateConst.FALSE);
        if (StringUtil.isEmpty(hotSearchPage)) { //默认 1首页/商城热搜
            criteria.andHotSearchPageEqualTo("1");
        } else {
            criteria.andHotSearchPageEqualTo(hotSearchPage);
        }
        example.setLimitStart(0);
		example.setLimitSize(fetchSize);
		example.setOrderByClause("RAND()");
		List<HotSearchWord> wordList = this.selectByExample(example);

		if (CollectionUtils.isEmpty(wordList)) return Collections.emptyList();

        return FluentIterable.from(wordList).transform(new Function<HotSearchWord, Map<String,Object>>() {
            @Override
            public Map<String,Object> apply(HotSearchWord input) {
                Map<String, Object> item = Maps.newHashMap();
                item.put("searchName", input.getWord());
                item.put("tag", StringUtils.hasText(input.getTag()) ? input.getTag() : "");
                return item;
            }
        }).toList();
	}
}
