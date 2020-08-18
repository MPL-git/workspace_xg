package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CashTransferExtMapper;
import com.jf.dao.CashTransferMapper;
import com.jf.entity.CashTransfer;
import com.jf.entity.CashTransferExample;
import com.jf.entity.CashTransferExt;
import com.jf.entity.CashTransferExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CashTransferBiz extends BaseService<CashTransfer, CashTransferExample> {

	@Autowired
	private CashTransferMapper dao;
	@Autowired
	private CashTransferExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCashTransferMapper(CashTransferMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CashTransferExt save(CashTransferExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CashTransfer update(CashTransfer model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CashTransfer model = new CashTransfer();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CashTransferExt findById(int id){
		return extDao.findById(id);
	}

	public CashTransferExt find(CashTransferExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CashTransferExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CashTransferExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CashTransferExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CashTransferExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CashTransferExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CashTransferExt> list(CashTransferExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CashTransferExt> paginate(CashTransferExtExample example) {
		List<CashTransferExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
