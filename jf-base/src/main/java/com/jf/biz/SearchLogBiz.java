package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SearchLogExtMapper;
import com.jf.dao.SearchLogMapper;
import com.jf.entity.SearchLog;
import com.jf.entity.SearchLogExample;
import com.jf.entity.SearchLogExt;
import com.jf.entity.SearchLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchLogBiz extends BaseService<SearchLog, SearchLogExample> {

	@Autowired
	private SearchLogMapper dao;
	@Autowired
	private SearchLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSearchLogMapper(SearchLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SearchLogExt save(SearchLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SearchLog update(SearchLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SearchLog model = new SearchLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SearchLogExt findById(int id){
		return extDao.findById(id);
	}

	public SearchLogExt find(SearchLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SearchLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SearchLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SearchLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SearchLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SearchLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SearchLogExt> list(SearchLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SearchLogExt> paginate(SearchLogExtExample example) {
		List<SearchLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
