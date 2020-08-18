package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntegralGiveExtMapper;
import com.jf.dao.IntegralGiveMapper;
import com.jf.entity.IntegralGive;
import com.jf.entity.IntegralGiveExample;
import com.jf.entity.IntegralGiveExt;
import com.jf.entity.IntegralGiveExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegralGiveBiz extends BaseService<IntegralGive, IntegralGiveExample> {

	@Autowired
	private IntegralGiveMapper dao;
	@Autowired
	private IntegralGiveExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntegralGiveMapper(IntegralGiveMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntegralGiveExt save(IntegralGiveExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntegralGive update(IntegralGive model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntegralGive model = new IntegralGive();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntegralGiveExt findById(int id){
		return extDao.findById(id);
	}

	public IntegralGiveExt find(IntegralGiveExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntegralGiveExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntegralGiveExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntegralGiveExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntegralGiveExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntegralGiveExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntegralGiveExt> list(IntegralGiveExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntegralGiveExt> paginate(IntegralGiveExtExample example) {
		List<IntegralGiveExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
