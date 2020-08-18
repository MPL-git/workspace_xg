package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.NovaStrategyExtMapper;
import com.jf.dao.NovaStrategyMapper;
import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyExample;
import com.jf.entity.NovaStrategyExt;
import com.jf.entity.NovaStrategyExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NovaStrategyBiz extends BaseService<NovaStrategy, NovaStrategyExample> {

	@Autowired
	private NovaStrategyMapper dao;
	@Autowired
	private NovaStrategyExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setNovaStrategyMapper(NovaStrategyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public NovaStrategyExt save(NovaStrategyExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public NovaStrategy update(NovaStrategy model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		NovaStrategy model = new NovaStrategy();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public NovaStrategyExt findById(int id){
		return extDao.findById(id);
	}

	public NovaStrategyExt find(NovaStrategyExtExample example){
		return extDao.find(example.fill());
	}

	public int count(NovaStrategyExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, NovaStrategyExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, NovaStrategyExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, NovaStrategyExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(NovaStrategyExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<NovaStrategyExt> list(NovaStrategyExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<NovaStrategyExt> paginate(NovaStrategyExtExample example) {
		List<NovaStrategyExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
