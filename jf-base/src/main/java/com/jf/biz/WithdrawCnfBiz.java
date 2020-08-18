package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WithdrawCnfExtMapper;
import com.jf.dao.WithdrawCnfMapper;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;
import com.jf.entity.WithdrawCnfExt;
import com.jf.entity.WithdrawCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WithdrawCnfBiz extends BaseService<WithdrawCnf, WithdrawCnfExample> {

	@Autowired
	private WithdrawCnfMapper dao;
	@Autowired
	private WithdrawCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWithdrawCnfMapper(WithdrawCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WithdrawCnfExt save(WithdrawCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WithdrawCnf update(WithdrawCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WithdrawCnf model = new WithdrawCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WithdrawCnfExt findById(int id){
		return extDao.findById(id);
	}

	public WithdrawCnfExt find(WithdrawCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WithdrawCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WithdrawCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WithdrawCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WithdrawCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WithdrawCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WithdrawCnfExt> list(WithdrawCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WithdrawCnfExt> paginate(WithdrawCnfExtExample example) {
		List<WithdrawCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
