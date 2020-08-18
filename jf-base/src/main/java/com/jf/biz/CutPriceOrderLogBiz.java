package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutPriceOrderLogExtMapper;
import com.jf.dao.CutPriceOrderLogMapper;
import com.jf.entity.CutPriceOrderLog;
import com.jf.entity.CutPriceOrderLogExample;
import com.jf.entity.CutPriceOrderLogExt;
import com.jf.entity.CutPriceOrderLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutPriceOrderLogBiz extends BaseService<CutPriceOrderLog, CutPriceOrderLogExample> {

	@Autowired
	private CutPriceOrderLogMapper dao;
	@Autowired
	private CutPriceOrderLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutPriceOrderLogMapper(CutPriceOrderLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutPriceOrderLogExt save(CutPriceOrderLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutPriceOrderLog update(CutPriceOrderLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutPriceOrderLog model = new CutPriceOrderLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutPriceOrderLogExt findById(int id){
		return extDao.findById(id);
	}

	public CutPriceOrderLogExt find(CutPriceOrderLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutPriceOrderLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutPriceOrderLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutPriceOrderLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutPriceOrderLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutPriceOrderLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutPriceOrderLogExt> list(CutPriceOrderLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutPriceOrderLogExt> paginate(CutPriceOrderLogExtExample example) {
		List<CutPriceOrderLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
