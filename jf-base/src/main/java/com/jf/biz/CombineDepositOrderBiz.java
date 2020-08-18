package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CombineDepositOrderExtMapper;
import com.jf.dao.CombineDepositOrderMapper;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineDepositOrderExample;
import com.jf.entity.CombineDepositOrderExt;
import com.jf.entity.CombineDepositOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CombineDepositOrderBiz extends BaseService<CombineDepositOrder, CombineDepositOrderExample> {

	@Autowired
	private CombineDepositOrderMapper dao;
	@Autowired
	private CombineDepositOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCombineDepositOrderMapper(CombineDepositOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CombineDepositOrderExt save(CombineDepositOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CombineDepositOrder update(CombineDepositOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CombineDepositOrder model = new CombineDepositOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CombineDepositOrderExt findById(int id){
		return extDao.findById(id);
	}

	public CombineDepositOrderExt find(CombineDepositOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CombineDepositOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CombineDepositOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CombineDepositOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CombineDepositOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CombineDepositOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CombineDepositOrderExt> list(CombineDepositOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CombineDepositOrderExt> paginate(CombineDepositOrderExtExample example) {
		List<CombineDepositOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
