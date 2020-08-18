package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ContractRenewLogExtMapper;
import com.jf.dao.ContractRenewLogMapper;
import com.jf.entity.ContractRenewLog;
import com.jf.entity.ContractRenewLogExample;
import com.jf.entity.ContractRenewLogExt;
import com.jf.entity.ContractRenewLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContractRenewLogBiz extends BaseService<ContractRenewLog, ContractRenewLogExample> {

	@Autowired
	private ContractRenewLogMapper dao;
	@Autowired
	private ContractRenewLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setContractRenewLogMapper(ContractRenewLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ContractRenewLogExt save(ContractRenewLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ContractRenewLog update(ContractRenewLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ContractRenewLog model = new ContractRenewLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ContractRenewLogExt findById(int id){
		return extDao.findById(id);
	}

	public ContractRenewLogExt find(ContractRenewLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ContractRenewLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ContractRenewLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ContractRenewLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ContractRenewLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ContractRenewLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ContractRenewLogExt> list(ContractRenewLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ContractRenewLogExt> paginate(ContractRenewLogExtExample example) {
		List<ContractRenewLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
