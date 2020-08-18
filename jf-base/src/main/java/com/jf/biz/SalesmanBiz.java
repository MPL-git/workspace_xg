package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SalesmanExtMapper;
import com.jf.dao.SalesmanMapper;
import com.jf.entity.Salesman;
import com.jf.entity.SalesmanExample;
import com.jf.entity.SalesmanExt;
import com.jf.entity.SalesmanExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesmanBiz extends BaseService<Salesman, SalesmanExample> {

	@Autowired
	private SalesmanMapper dao;
	@Autowired
	private SalesmanExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSalesmanMapper(SalesmanMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SalesmanExt save(SalesmanExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Salesman update(Salesman model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Salesman model = new Salesman();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SalesmanExt findById(int id){
		return extDao.findById(id);
	}

	public SalesmanExt find(SalesmanExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SalesmanExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SalesmanExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SalesmanExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SalesmanExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SalesmanExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SalesmanExt> list(SalesmanExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SalesmanExt> paginate(SalesmanExtExample example) {
		List<SalesmanExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
