package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponChangeLogExtMapper;
import com.jf.dao.CouponChangeLogMapper;
import com.jf.entity.CouponChangeLog;
import com.jf.entity.CouponChangeLogExample;
import com.jf.entity.CouponChangeLogExt;
import com.jf.entity.CouponChangeLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponChangeLogBiz extends BaseService<CouponChangeLog, CouponChangeLogExample> {

	@Autowired
	private CouponChangeLogMapper dao;
	@Autowired
	private CouponChangeLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponChangeLogMapper(CouponChangeLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponChangeLogExt save(CouponChangeLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponChangeLog update(CouponChangeLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponChangeLog model = new CouponChangeLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponChangeLogExt findById(int id){
		return extDao.findById(id);
	}

	public CouponChangeLogExt find(CouponChangeLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponChangeLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponChangeLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponChangeLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponChangeLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponChangeLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponChangeLogExt> list(CouponChangeLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponChangeLogExt> paginate(CouponChangeLogExtExample example) {
		List<CouponChangeLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
