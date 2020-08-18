package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ObligeeExtMapper;
import com.jf.dao.ObligeeMapper;
import com.jf.entity.Obligee;
import com.jf.entity.ObligeeExample;
import com.jf.entity.ObligeeExt;
import com.jf.entity.ObligeeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ObligeeBiz extends BaseService<Obligee, ObligeeExample> {

	@Autowired
	private ObligeeMapper dao;
	@Autowired
	private ObligeeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setObligeeMapper(ObligeeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ObligeeExt save(ObligeeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Obligee update(Obligee model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Obligee model = new Obligee();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ObligeeExt findById(int id){
		return extDao.findById(id);
	}

	public ObligeeExt find(ObligeeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ObligeeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ObligeeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ObligeeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ObligeeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ObligeeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ObligeeExt> list(ObligeeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ObligeeExt> paginate(ObligeeExtExample example) {
		List<ObligeeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
