package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WithdrawOrderExtMapper;
import com.jf.dao.WithdrawOrderMapper;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import com.jf.entity.WithdrawOrderExt;
import com.jf.entity.WithdrawOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WithdrawOrderBiz extends BaseService<WithdrawOrder, WithdrawOrderExample> {

	@Autowired
	private WithdrawOrderMapper dao;
	@Autowired
	private WithdrawOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWithdrawOrderMapper(WithdrawOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WithdrawOrderExt save(WithdrawOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WithdrawOrder update(WithdrawOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WithdrawOrder model = new WithdrawOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WithdrawOrderExt findById(int id){
		return extDao.findById(id);
	}

	public WithdrawOrderExt find(WithdrawOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WithdrawOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WithdrawOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WithdrawOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WithdrawOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WithdrawOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WithdrawOrderExt> list(WithdrawOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WithdrawOrderExt> paginate(WithdrawOrderExtExample example) {
		List<WithdrawOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
