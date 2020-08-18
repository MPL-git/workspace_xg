package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DepositOrderStatusLogExtMapper;
import com.jf.dao.DepositOrderStatusLogMapper;
import com.jf.entity.DepositOrderStatusLog;
import com.jf.entity.DepositOrderStatusLogExample;
import com.jf.entity.DepositOrderStatusLogExt;
import com.jf.entity.DepositOrderStatusLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepositOrderStatusLogBiz extends BaseService<DepositOrderStatusLog, DepositOrderStatusLogExample> {

	@Autowired
	private DepositOrderStatusLogMapper dao;
	@Autowired
	private DepositOrderStatusLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDepositOrderStatusLogMapper(DepositOrderStatusLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DepositOrderStatusLogExt save(DepositOrderStatusLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DepositOrderStatusLog update(DepositOrderStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DepositOrderStatusLog model = new DepositOrderStatusLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DepositOrderStatusLogExt findById(int id){
		return extDao.findById(id);
	}

	public DepositOrderStatusLogExt find(DepositOrderStatusLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DepositOrderStatusLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DepositOrderStatusLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DepositOrderStatusLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DepositOrderStatusLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DepositOrderStatusLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DepositOrderStatusLogExt> list(DepositOrderStatusLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DepositOrderStatusLogExt> paginate(DepositOrderStatusLogExtExample example) {
		List<DepositOrderStatusLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
