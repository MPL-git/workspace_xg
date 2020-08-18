package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WithdrawOrderStatusLogExtMapper;
import com.jf.dao.WithdrawOrderStatusLogMapper;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WithdrawOrderStatusLogExample;
import com.jf.entity.WithdrawOrderStatusLogExt;
import com.jf.entity.WithdrawOrderStatusLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WithdrawOrderStatusLogBiz extends BaseService<WithdrawOrderStatusLog, WithdrawOrderStatusLogExample> {

	@Autowired
	private WithdrawOrderStatusLogMapper dao;
	@Autowired
	private WithdrawOrderStatusLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWithdrawOrderStatusLogMapper(WithdrawOrderStatusLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WithdrawOrderStatusLogExt save(WithdrawOrderStatusLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WithdrawOrderStatusLog update(WithdrawOrderStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WithdrawOrderStatusLog model = new WithdrawOrderStatusLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WithdrawOrderStatusLogExt findById(int id){
		return extDao.findById(id);
	}

	public WithdrawOrderStatusLogExt find(WithdrawOrderStatusLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WithdrawOrderStatusLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WithdrawOrderStatusLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WithdrawOrderStatusLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WithdrawOrderStatusLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WithdrawOrderStatusLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WithdrawOrderStatusLogExt> list(WithdrawOrderStatusLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WithdrawOrderStatusLogExt> paginate(WithdrawOrderStatusLogExtExample example) {
		List<WithdrawOrderStatusLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
