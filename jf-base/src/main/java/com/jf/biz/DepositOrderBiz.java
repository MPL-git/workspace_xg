package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DepositOrderExtMapper;
import com.jf.dao.DepositOrderMapper;
import com.jf.entity.DepositOrder;
import com.jf.entity.DepositOrderExample;
import com.jf.entity.DepositOrderExt;
import com.jf.entity.DepositOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepositOrderBiz extends BaseService<DepositOrder, DepositOrderExample> {

	@Autowired
	private DepositOrderMapper dao;
	@Autowired
	private DepositOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDepositOrderMapper(DepositOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DepositOrderExt save(DepositOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DepositOrder update(DepositOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DepositOrder model = new DepositOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DepositOrderExt findById(int id){
		return extDao.findById(id);
	}

	public DepositOrderExt find(DepositOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DepositOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DepositOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DepositOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DepositOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DepositOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DepositOrderExt> list(DepositOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DepositOrderExt> paginate(DepositOrderExtExample example) {
		List<DepositOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
