package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.RefundRegisterDateLockExtMapper;
import com.jf.dao.RefundRegisterDateLockMapper;
import com.jf.entity.RefundRegisterDateLock;
import com.jf.entity.RefundRegisterDateLockExample;
import com.jf.entity.RefundRegisterDateLockExt;
import com.jf.entity.RefundRegisterDateLockExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RefundRegisterDateLockBiz extends BaseService<RefundRegisterDateLock, RefundRegisterDateLockExample> {

	@Autowired
	private RefundRegisterDateLockMapper dao;
	@Autowired
	private RefundRegisterDateLockExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setRefundRegisterDateLockMapper(RefundRegisterDateLockMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public RefundRegisterDateLockExt save(RefundRegisterDateLockExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public RefundRegisterDateLock update(RefundRegisterDateLock model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		RefundRegisterDateLock model = new RefundRegisterDateLock();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public RefundRegisterDateLockExt findById(int id){
		return extDao.findById(id);
	}

	public RefundRegisterDateLockExt find(RefundRegisterDateLockExtExample example){
		return extDao.find(example.fill());
	}

	public int count(RefundRegisterDateLockExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, RefundRegisterDateLockExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, RefundRegisterDateLockExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, RefundRegisterDateLockExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(RefundRegisterDateLockExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<RefundRegisterDateLockExt> list(RefundRegisterDateLockExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<RefundRegisterDateLockExt> paginate(RefundRegisterDateLockExtExample example) {
		List<RefundRegisterDateLockExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
