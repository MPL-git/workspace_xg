package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SearchLogMapper;
import com.jf.entity.SearchLog;
import com.jf.entity.SearchLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchLogService extends BaseService<SearchLog, SearchLogExample> {
	@Autowired
	private SearchLogMapper searchLogMapper;
	
	@Autowired
	public void setSearchLogMapper(SearchLogMapper searchLogMapper) {
		this.setDao(searchLogMapper);
		this.searchLogMapper = searchLogMapper;
	}
	
}
