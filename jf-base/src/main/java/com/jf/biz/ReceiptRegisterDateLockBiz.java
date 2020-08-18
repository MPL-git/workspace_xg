package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ReceiptRegisterDateLockExtMapper;
import com.jf.dao.ReceiptRegisterDateLockMapper;
import com.jf.entity.ReceiptRegisterDateLock;
import com.jf.entity.ReceiptRegisterDateLockExample;
import com.jf.entity.ReceiptRegisterDateLockExt;
import com.jf.entity.ReceiptRegisterDateLockExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReceiptRegisterDateLockBiz extends BaseService<ReceiptRegisterDateLock, ReceiptRegisterDateLockExample> {

	@Autowired
	private ReceiptRegisterDateLockMapper dao;
	@Autowired
	private ReceiptRegisterDateLockExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setReceiptRegisterDateLockMapper(ReceiptRegisterDateLockMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ReceiptRegisterDateLockExt save(ReceiptRegisterDateLockExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ReceiptRegisterDateLock update(ReceiptRegisterDateLock model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ReceiptRegisterDateLock model = new ReceiptRegisterDateLock();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ReceiptRegisterDateLockExt findById(int id){
		return extDao.findById(id);
	}

	public ReceiptRegisterDateLockExt find(ReceiptRegisterDateLockExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ReceiptRegisterDateLockExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ReceiptRegisterDateLockExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ReceiptRegisterDateLockExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ReceiptRegisterDateLockExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ReceiptRegisterDateLockExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ReceiptRegisterDateLockExt> list(ReceiptRegisterDateLockExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ReceiptRegisterDateLockExt> paginate(ReceiptRegisterDateLockExtExample example) {
		List<ReceiptRegisterDateLockExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
